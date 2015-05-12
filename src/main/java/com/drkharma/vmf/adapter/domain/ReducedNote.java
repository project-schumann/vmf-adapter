package com.drkharma.vmf.adapter.domain;

/**
 * A single note in a piece of music or song.
 *
 * @author Patrick Ayoup
 * @version 0.0.1
 * @since 0.0.1
 */
public class ReducedNote {

    private int offset;

    private int duration;

    private int pitchDelta;

    /**
     * Creates a new note.
     *
     * @param offset     The offset from the beginning of the previous note in ticks.
     * @param duration   The duration of the note in ticks.
     * @param pitchDelta The difference in pitch from the previous note in semitones.
     */
    public ReducedNote(int offset, int duration, int pitchDelta) {
        this.offset = offset;
        this.duration = duration;
        this.pitchDelta = pitchDelta;
    }

    /**
     * Gets the offset from the beginning of the previous note in ticks.
     * <p/>
     * <p>
     * The first note in a piece will usually have an offset of 0.
     * In the case of an anacrusis, the offset of the first note will
     * be the number of ticks equivalent to the rests which precede it.
     * </p>
     *
     * @return The offset from the beginning of the previous note in ticks.
     */
    public int getOffset() {
        return this.offset;
    }

    /**
     * Gets the duration of the note in ticks.
     *
     * @return The duration of the note in ticks.
     */
    public int getDuration() {
        return this.duration;
    }

    /**
     * Gets the difference in pitch from the previous note in semitones.
     * <p/>
     * <p>
     * A positive number indicates that the pitch has risen, while a
     * negative number indicates that the pitch has fallen.
     * <p/>
     * The first note in a piece will have a pitch delta of 0 and the
     * pitch that this represents is the reference pitch which is
     * contained in the header of this piece or song.
     * </p>
     *
     * @return The difference in pitch from the previous note in semitones.
     */
    public int getPitchDelta() {
        return this.pitchDelta;
    }

    /**
     * Verifies the equality between two notes.
     *
     * @param obj An object to compare with.
     * @return True if both notes are equivalent, False otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass().equals(ReducedNote.class)) {
            ReducedNote that = (ReducedNote) obj;

            return this.offset == that.offset
                    && this.duration == that.duration
                    && this.pitchDelta == that.pitchDelta;
        } else {
            return false;
        }
    }
}