package com.drkharma.vmf;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class for {@link RelativeNote}
 */
public class RelativeNoteTest {

    /**
     * Test case where pitch class doesn't change
     * and octave increases
     */
    @Test
    public void testConstructor001() {
        // C4 -> C5 : +12 semitones
        Note n = new Note(1, 3, 0, 5, 3);
        RelativeNote rn = new RelativeNote(n, PitchClass.C, 4);

        assertEquals(n.getDuration(), rn.getDuration());
        assertEquals(n.getOffset(), rn.getOffset());
        assertEquals(12, rn.getPitchDelta());
    }

    /**
     * Test case where pitch class doesn't change
     * and octave decreases.
     */
    @Test
    public void testConstructor002() {
        // C4 -> C3: -12 semitones
        Note n = new Note(1, 3, 0, 3, 3);
        RelativeNote rn = new RelativeNote(n, PitchClass.C, 4);

        assertEquals(n.getDuration(), rn.getDuration());
        assertEquals(n.getOffset(), rn.getOffset());
        assertEquals(-12, rn.getPitchDelta());
    }

    /**
     * Test case where pitch class doesn't change
     * and octave doesn't change.
     */
    @Test
    public void testConstructor003() {
        // C4 -> C4: 0 semitones
        Note n = new Note(1, 3, 0, 4, 3);
        RelativeNote rn = new RelativeNote(n, PitchClass.C, 4);

        assertEquals(n.getDuration(), rn.getDuration());
        assertEquals(n.getOffset(), rn.getOffset());
        assertEquals(0, rn.getPitchDelta());
    }

    /**
     * Test case where pitch class increases
     * and octave increases
     */
    @Test
    public void testConstructor004() {
        // C4 -> D5: 14 semitones
        Note n = new Note(1, 3, 2, 5, 3);
        RelativeNote rn = new RelativeNote(n, PitchClass.C, 4);

        assertEquals(n.getDuration(), rn.getDuration());
        assertEquals(n.getOffset(), rn.getOffset());
        assertEquals(14, rn.getPitchDelta());
    }

    /**
     * Test case where pitch class increases
     * and octave decreases
     */
    @Test
    public void testConstructor005() {
        // C4 -> D3: -10 semitones
        Note n = new Note(1, 3, 2, 3, 3);
        RelativeNote rn = new RelativeNote(n, PitchClass.C, 4);

        assertEquals(n.getDuration(), rn.getDuration());
        assertEquals(n.getOffset(), rn.getOffset());
        assertEquals(-10, rn.getPitchDelta());
    }

    /**
     * Test case where pitch class increases
     * and octave doesn't change.
     */
    @Test
    public void testConstructor006() {
        // C4 -> D4: 2 semitones
        Note n = new Note(1, 3, 2, 4, 3);
        RelativeNote rn = new RelativeNote(n, PitchClass.C, 4);

        assertEquals(n.getDuration(), rn.getDuration());
        assertEquals(n.getOffset(), rn.getOffset());
        assertEquals(2, rn.getPitchDelta());
    }

    /**
     * Test case where pitch class decreases
     * and octave increases
     */
    @Test
    public void testConstructor007() {
        // D4 -> C5: 10 semitones
        Note n = new Note(1, 3, 0, 5, 3);
        RelativeNote rn = new RelativeNote(n, PitchClass.D, 4);

        assertEquals(n.getDuration(), rn.getDuration());
        assertEquals(n.getOffset(), rn.getOffset());
        assertEquals(10, rn.getPitchDelta());
    }

    /**
     * Test case where pitch class decreases
     * and octave decreases.
     */
    @Test
    public void testConstructor008() {
        // D4 -> C3: -14 semitones
        Note n = new Note(1, 3, 0, 3, 3);
        RelativeNote rn = new RelativeNote(n, PitchClass.D, 4);

        assertEquals(n.getDuration(), rn.getDuration());
        assertEquals(n.getOffset(), rn.getOffset());
        assertEquals(-14, rn.getPitchDelta());
    }

    /**
     * Test case where pitch class decreases
     * and octave doesn't change.
     */
    @Test
    public void testConstructor009() {
        // D4 -> C4: -2 semitones
        Note n = new Note(1, 3, 0, 4, 3);
        RelativeNote rn = new RelativeNote(n, PitchClass.D, 4);

        assertEquals(n.getDuration(), rn.getDuration());
        assertEquals(n.getOffset(), rn.getOffset());
        assertEquals(-2, rn.getPitchDelta());
    }
}