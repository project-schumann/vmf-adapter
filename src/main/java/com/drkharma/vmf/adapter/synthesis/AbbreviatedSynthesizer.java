package com.drkharma.vmf.adapter.synthesis;

import com.drkharma.vmf.AbbreviatedVectorMusic;
import com.drkharma.vmf.PitchClass;
import com.drkharma.vmf.RelativeNote;
import com.drkharma.vmf.RelativeVMHeader;

import javax.sound.midi.*;
import java.nio.ByteBuffer;

/**
 * Sequences AbbreviatedVectorMusic as a MIDI sequence
 * and plays it back.
 */
public class AbbreviatedSynthesizer {

    /**
     * The message type code for a tempo marking.
     */
    private static final byte SET_TEMPO_MESSAGE_TYPE = 0x51;

    /**
     * The number of microseconds for each minute.
     */
    private static final int MICROSECONDS_PER_MINUTE = 600000;

    /**
     * The abbreviated vector music to sequence and play back.
     */
    private final AbbreviatedVectorMusic vectorMusic;

    /**
     * The header for the abbreviated vector music.
     */
    private final RelativeVMHeader header;

    /**
     * The sequence for playback.
     */
    private Sequence sequence;

    /**
     * Constructor.
     * Uses the provided AbbreviatedVectorMusic for synthesis.
     * @param vm The AbbreviatedVectorMusic for synthesis.
     */
    public AbbreviatedSynthesizer(AbbreviatedVectorMusic vm) {
        this.vectorMusic = vm;
        this.header = vm.getHeader();

        try {
            this.sequenceMusic();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts the AbbreviatedVectorMusic into a MIDI sequence for playback.
     */
    private void sequenceMusic() throws InvalidMidiDataException {
        // Find the number of ticks per quarter note.
        int ticksPerQuarter = this.header.getTickValue().getDenominator();

        // PPQ means use mode where we express number of ticks per quarter note.
        this.sequence = new Sequence(Sequence.PPQ, ticksPerQuarter);

        // Create the track.
        Track track = this.sequence.createTrack();

        // Enable general midi sound set.
        byte[] messageBytes = {(byte) 0xF0, 0x7E, 0x7F, 0x09, 0x01, (byte) 0xF7};
        SysexMessage sm = new SysexMessage();
        sm.setMessage(messageBytes, 6);
        MidiEvent me = new MidiEvent(sm,(long)0);
        track.add(me);

        // Set Tempo
        // First calculate length of a quarter note.
        // TODO: Adjust this to get all tempo changes.
        int quarterLength = (1 / this.header.getMetronomeMarkings().get(0).getQuarterBPM()) * (MICROSECONDS_PER_MINUTE);

        ByteBuffer byteBuffer = ByteBuffer.allocate(3);
        byteBuffer.putInt(quarterLength);

        MetaMessage tempoMessage = new MetaMessage();
        tempoMessage.setMessage(SET_TEMPO_MESSAGE_TYPE ,byteBuffer.array(), 3);

        // TODO: Adjust this to get all tempo changes.
        me = new MidiEvent(tempoMessage, 0);
        t.add(me);

        //****  set instrument to Piano  ****
        ShortMessage shortMessage = new ShortMessage();
        shortMessage.setMessage(0xC0, 0x00, 0x00);
        me = new MidiEvent(mm,(long)0);
        t.add(me);

        // Write Notes.
        boolean isFirstNote = true;
        int noteNumber = 0;

        for (RelativeNote n : this.vectorMusic.getNotes()) {
            if (isFirstNote) {
                int refOctave = this.header.getReferenceOctave();
                PitchClass refPC = this.header.getReferencePitchClass();

                // Calculate Midi Note Number
                noteNumber = ((refOctave - 1) * 12) + refPC.getPitchClassCode();
            } else {
                noteNumber = noteNumber + n.getPitchDelta();
            }

            ShortMessage mm = new ShortMessage();
            // 100 is an arbitrary velocity.
            mm.setMessage(ShortMessage.NOTE_ON, 0, noteNumber, 100);

            // Start at tick 1. metadata is on tick 0.
            me = new MidiEvent(mm, 1 + n.getOffset());
            t.add(me);

            // Note Off.
            mm = new ShortMessage();
            mm.setMessage(ShortMessage.NOTE_OFF, noteNumber, 0);
            me = new MidiEvent(mm, n.getDuration());
            t.add(me);
        }

        //****  set end of track (meta event) 19 ticks later  ****
        MetaMessage mt = new MetaMessage();
        byte[] bet = {}; // empty array
        mt.setMessage(0x2F,bet,0);
        me = new MidiEvent(mt, (long)140);
        t.add(me);
    }
}
