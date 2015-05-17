package com.drkharma.vmf;

import java.util.List;

/**
 * Represents a piece in the abbreviated format.
 */
public class AbbreviatedVectorMusic {

    /**
     * The header of the VMF file.
     */
    private RelativeVMHeader header;

    /**
     * The notes in this piece.
     */
    private List<RelativeNote> notes;

    /**
     * Gets the header of the VMF file.
     *
     * @return The header of the VMF file.
     */
    public RelativeVMHeader getHeader() {
        return header;
    }

    /**
     * Sets the header of the VMF file.
     *
     * @param header The header of the VMF file.
     */
    public void setHeader(RelativeVMHeader header) {
        this.header = header;
    }

    /**
     * Gets the notes in this piece.
     *
     * @return The notes in this piece.
     */
    public List<RelativeNote> getNotes() {
        return notes;
    }

    /**
     * Sets the notes in this piece.
     *
     * @param notes The notes in this piece.
     */
    public void setNotes(List<RelativeNote> notes) {
        this.notes = notes;
    }

    /**
     * Adds a note to this piece.
     *
     * @param note The note to add.
     */
    public void addNote(RelativeNote note) {
        this.notes.add(note);
    }
}
