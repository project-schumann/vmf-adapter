package com.drkharma.vmf.adapter.synthesis;

import com.drkharma.vmf.RelativeNote;
import com.drkharma.vmf.RelativeVMHeader;
import jm.music.data.Note;
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
        double quarterLength = (1.0 / header.getMetronomeMarkings().get(0).getQuarterBPM()) * 60 * 1000;
        this.tickLength = quarterLength * header.getTickValue().doubleValue();
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
                500, TimeUnit.MILLISECONDS);
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

                // Prepare the note.
                Note midiNote = new Note();
                midiNote.setPitch(currentPitch);

                // Play the note.
                Play.midi(midiNote);

                // TODO: Set the appropriate duration and handle rests.

                // Update the last note.
                lastNote = currentPitch;
            }
        }
    }
}
