package com.drkharma.vmf.adapter.domain;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * A complete piece of music or song.
 *
 * @author Patrick Ayoup
 * @version 0.0.1
 * @since 0.0.1
 */
public class ReducedMusic {

    private List<ReducedNote> notes;

    /**
     * Creates a new empty piece of music.
     */
    public ReducedMusic() {
        this.notes = new LinkedList<>();
    }

    /**
     * Creates a new piece of music populated with the provided notes.
     *
     * @param notes The notes which are part of this piece of music.
     */
    public ReducedMusic(List<ReducedNote> notes) {
        this.notes = notes;
    }

    /**
     * Gets the notes that are part of this piece of music.
     *
     * @return The notes that are part of this piece of music.
     */
    public List<ReducedNote> getNotes() {
        return notes;
    }

    /**
     * Verifies the equality between two pieces of music.
     * <p/>
     * <p>
     * If two pieces have the same number of notes and those notes are equivalent
     * and are in the same order, then the two pieces are equivalent.
     * </p>
     *
     * @param obj An object to compare with.
     * @return True if both pieces are equivalent, False otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass().equals(ReducedMusic.class)) {
            ReducedMusic that = (ReducedMusic) obj;

            if (this.getNotes().size() == that.getNotes().size()) {
                Iterator<ReducedNote> itThis = this.getNotes().iterator();
                Iterator<ReducedNote> itThat = that.getNotes().iterator();

                while (itThis.hasNext() && itThat.hasNext()) {
                    if (!itThis.next().equals(itThat.next())) {
                        return false;
                    }
                }

                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
