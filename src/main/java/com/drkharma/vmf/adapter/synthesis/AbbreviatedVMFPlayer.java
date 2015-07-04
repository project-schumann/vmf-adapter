package com.drkharma.vmf.adapter.synthesis;

import com.drkharma.vmf.RelativeNote;
import com.drkharma.vmf.RelativeVMHeader;
import jm.music.data.Note;
import jm.music.data.Part;
import jm.music.data.Phrase;
import jm.music.data.Score;
import jm.util.Play;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Plays abbreviated VMF notes which are streamed to the player.
 */
public class AbbreviatedVMFPlayer {

    /**
     * The queue containing notes to play.
     */
    private Queue<RelativeNote> noteQueue;

    /**
     * The midi code of the last note which was played.
     */
    private int lastNote;

    /**
     * The duration of the last note which was played.
     */
    private double lastDuration;

    /**
     * Indicator showing player status,
     */
    private boolean isPlayerOn;

    /**
     * The length of a tick in milliseconds.
     */
    private double tickLength;

    /**
     * The thread executor service which schedules execution repetition.
     */
    private ScheduledExecutorService threadExecutor;

    /**
     * The future result of the thread execution.
     * This is only necessary for stopping the player.
     */
    private ScheduledFuture future;

    /**
     * The tempo of the piece.
     */
    private int tempo;


    public AbbreviatedVMFPlayer() {
        this.noteQueue = new LinkedList<>();
    }

    /**
     * Initializes the player with the header of a new piece.
     * If the player is running, it is stopped and the queue is cleared.
     * @param header The header of the piece to play.
     */
    public void init(RelativeVMHeader header) {
        this.isPlayerOn = false;
        this.noteQueue.clear();

        // Load the reference as the last note to prepare the context.
        this.lastNote = (header.getReferenceOctave() * 12) + header.getReferencePitchClass().getPitchClassCode();

        // Determine the length of a quarter note.
        // TODO: Can this work with tempo changes?
        this.tickLength = header.getTickValue().doubleValue();

        this.tempo = header.getMetronomeMarkings().get(0).getQuarterBPM();
    }

    /**
     * Adds a new note to the queue.
     * @param note The note to add to the queue.
     */
    public void enqueueNote(RelativeNote note) {
        this.noteQueue.add(note);
    }

    /**
     * Starts the player.
     */
    public void start() {
        this.isPlayerOn = true;

        // Create the thread.
        this.threadExecutor = Executors.newSingleThreadScheduledExecutor();
        this.future = threadExecutor.scheduleWithFixedDelay(new PlayerThread(), 0,
                250, TimeUnit.MILLISECONDS);
    }

    /**
     * Stops the player.
     */
    public void stop() {
        this.isPlayerOn = false;

        this.future.cancel(false);
        this.threadExecutor.shutdown();
    }

    /**
     * Thread where notes are played through the synthesizer.
     */
    private class PlayerThread implements Runnable {

        /**
         * Plays all notes currently in the queue.
         */
        @Override
        public void run() {
            while (!noteQueue.isEmpty()) {
                RelativeNote currentNote = noteQueue.remove();
                int currentPitch = lastNote + currentNote.getPitchDelta();

                Score score = new Score();
                Part part = new Part();

                // Check if we need a rest.
                if (currentNote.getOffset() > lastDuration) {
                    // Add a rest in between
                    Note midiRest = new Note();
                    midiRest.setPitch(Note.REST);
                    midiRest.setLength(currentNote.getOffset() - lastDuration);

                    Phrase restPhrase = new Phrase();
                    restPhrase.add(midiRest);
                    part.addPhrase(restPhrase);
                }

                // Prepare the note.
                Note midiNote = new Note();
                midiNote.setPitch(currentPitch);
                midiNote.setLength(tickLength * currentNote.getDuration());

                Phrase notePhrase = new Phrase();
                notePhrase.add(midiNote);
                part.addPhrase(notePhrase);

                score.add(part);
                score.setTempo(tempo);

                Play.midi(score);

                // Update the last note.
                lastNote = currentPitch;
                lastDuration = currentNote.getDuration();
            }
        }
    }
}
