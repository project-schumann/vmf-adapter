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

        calculatePitchDelta(note, referencePC, referenceOctave);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RelativeNote that = (RelativeNote) o;

        if (duration != that.duration) return false;
        if (offset != that.offset) return false;
        return pitchDelta == that.pitchDelta;

    }

    @Override
    public int hashCode() {
        int result = duration;
        result = 31 * result + offset;
        result = 31 * result + pitchDelta;
        return result;
    }

    /**
     * Calculates the pitch delta.
     *
     * @param note            The source note.
     * @param referencePC     The reference pitch class.
     * @param referenceOctave The reference octave.
     */
    private void calculatePitchDelta(Note note, PitchClass referencePC, int referenceOctave) {
        final int SEMITONES_IN_OCTAVE = 12;

        int octaveDifference = note.getOctave() - referenceOctave;
        int pcDifference = note.getPitchClass().getPitchClassCode() - referencePC.getPitchClassCode();

        this.pitchDelta = pcDifference + (SEMITONES_IN_OCTAVE * octaveDifference);
    }
}
