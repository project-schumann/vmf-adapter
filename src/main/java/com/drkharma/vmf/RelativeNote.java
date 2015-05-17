package com.drkharma.vmf;

/**
 * Represents a note that is relative to a reference.
 */
public class RelativeNote {

    /**
     * The duration of this note in ticks.
     */
    private int duration;

    /**
     * The offset of this note from the beginning previous note.
     * If this is the first note in the piece, the offset is from the beginning of the music.
     */
    private int offset;

    /**
     * The change in pitch from the previous note in semitones.
     */
    private int pitchDelta;

    /**
     * Creates a note relative to another note.
     *
     * @param note            The source note.
     * @param referencePC     The reference pitch class.
     * @param referenceOctave The reference octave.
     */
    public RelativeNote(Note note, PitchClass referencePC, int referenceOctave) {
        this.duration = note.getDuration();
        this.offset = note.getOffset();

        // TODO: Work out algorithm.
        //C4 -> B3
        // B - C = 11 - 0
        // 4 - 3 = 1

        // PClast - PCnow = dPC
        // Olast - Onow = dO.
        // dPC * (12 * dO) = delta
    }

    /**
     * Gets the duration of this note.
     *
     * @return The duration of this note.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the duration of this note.
     *
     * @param duration The duration of this note.
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Gets the offset of this note.
     *
     * @return The offset of this note.
     */
    public int getOffset() {
        return offset;
    }

    /**
     * Sets the offset of this note.
     *
     * @param offset The offset of this note.
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }

    /**
     * Gets the pitch delta of this note from the last note.
     *
     * @return The pitch delta of this note from the last note.
     */
    public int getPitchDelta() {
        return pitchDelta;
    }

    /**
     * Sets the pitch delta of this note from the last note.
     *
     * @param pitchDelta The pitch delta of this note from the last note.
     */
    public void setPitchDelta(int pitchDelta) {
        this.pitchDelta = pitchDelta;
    }
}
