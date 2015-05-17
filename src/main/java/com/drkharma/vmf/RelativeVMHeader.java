package com.drkharma.vmf;

/**
 * A header allowing to record a relative reference pitch
 * for the piece.
 */
public class RelativeVMHeader extends VectorMusicHeader {

    /**
     * The reference pitch class.
     */
    private PitchClass referencePitchClass;

    /**
     * The reference octave.
     */
    private int referenceOctave;

    /**
     * Constructor copying fields from the provided Vector Music Header.
     *
     * @param vmHeader The vector music header to copy from.
     */
    public RelativeVMHeader(VectorMusicHeader vmHeader) {
        super(vmHeader.getTickValue(), vmHeader.getNumberOfParts(), vmHeader.getNumberOfVoices(),
                vmHeader.getName(), vmHeader.getTimeSignatures(),
                vmHeader.getKeySignatures(), vmHeader.getMetronomeMarkings());
    }

    /**
     * Gets the reference pitch class.
     *
     * @return The reference pitch class.
     */
    public PitchClass getReferencePitchClass() {
        return referencePitchClass;
    }

    /**
     * Sets the reference pitch class.
     *
     * @param referencePitchClass The reference pitch class.
     */
    public void setReferencePitchClass(PitchClass referencePitchClass) {
        this.referencePitchClass = referencePitchClass;
    }

    /**
     * Gets the reference referenceOctave.
     *
     * @return The reference referenceOctave.
     */
    public int getReferenceOctave() {
        return referenceOctave;
    }

    /**
     * Sets the reference referenceOctave.
     *
     * @param referenceOctave The reference referenceOctave.
     */
    public void setReferenceOctave(int referenceOctave) {
        this.referenceOctave = referenceOctave;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        RelativeVMHeader that = (RelativeVMHeader) o;

        if (referenceOctave != that.referenceOctave) return false;
        return referencePitchClass == that.referencePitchClass;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (referencePitchClass != null ? referencePitchClass.hashCode() : 0);
        result = 31 * result + referenceOctave;
        return result;
    }
}
