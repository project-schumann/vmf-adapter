package com.drkharma.vmf.adapter;

import com.drkharma.vmf.*;
import com.drkharma.vmf.parser.VMFParser;
import com.drkharma.vmf.parser.exception.TimeSignatureMissingException;

import java.io.File;
import java.io.IOException;

/**
 * Adapts a VMF model to a reduced format.
 */
public class VMFAdapter {

    /**
     * The full Vector Music model.
     */
    private VectorMusic vectorMusic;

    /**
     * The adapted Vector Music model.
     */
    private AbbreviatedVectorMusic abbreviatedVectorMusic;

    /**
     * Initializes a VMFAdapter with an existing
     * VectorMusic instance.
     *
     * @param vectorMusic The piece to abbreviate.
     */
    public VMFAdapter(VectorMusic vectorMusic) {
        this.vectorMusic = vectorMusic;
        this.abbreviatedVectorMusic = new AbbreviatedVectorMusic();
    }

    /**
     * Initializes a VMFAdapter with the contents
     * of the provided VMF file.
     *
     * @param vmfFile The piece to abbreviate.
     * @throws IOException                   If the file is missing or malformed.
     * @throws TimeSignatureMissingException If the file does not contain a time signature.
     */
    public VMFAdapter(File vmfFile) throws IOException, TimeSignatureMissingException {
        VMFParser parser = new VMFParser(vmfFile);

        this.vectorMusic = parser.parse();
        this.abbreviatedVectorMusic = new AbbreviatedVectorMusic();
    }

    /**
     * Converts the model provided in the constructor to an
     * abbreviated vmf model.
     */
    public AbbreviatedVectorMusic abbreviate() {
        this.replicateHeader();
        this.adaptNotes();

        return this.abbreviatedVectorMusic;
    }

    /**
     * Copies the header from the full vector music
     * to the abbreviated vector music.
     */
    private void replicateHeader() {
        this.abbreviatedVectorMusic.setHeader(new RelativeVMHeader(this.vectorMusic.getHeader()));
    }

    /**
     * Adapts the notes of the piece to a reduced set of essential parameters.
     * <p/>
     * Additionally, notes are relative to a reference rather than absolutely defined.
     */
    private void adaptNotes() {
        boolean referenceRecorded = false;
        PitchClass referencePC = null;
        int referenceOctave = 4;

        for (Note note : this.vectorMusic.getNotes()) {
            if (!referenceRecorded) {
                this.abbreviatedVectorMusic.getHeader().setReferencePitchClass(note.getPitchClass());
                this.abbreviatedVectorMusic.getHeader().setReferenceOctave(note.getOctave());

                referenceRecorded = true;
            }

            this.abbreviatedVectorMusic.addNote(new RelativeNote(note, referencePC, referenceOctave));

            referencePC = note.getPitchClass();
            referenceOctave = note.getOctave();
        }
    }
}
