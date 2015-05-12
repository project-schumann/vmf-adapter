package com.drkharma.vmf.adapter.domain;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test class for {@link ReducedNote}
 */
public class ReducedNoteTest {

    private static final ReducedNote FIXTURE = new ReducedNote(2, 3, 4);

    /**
     * Tests equality check for two equal notes.
     */
    @Test
    public void testEquals001() {
        ReducedNote other = new ReducedNote(2, 3, 4);

        assertTrue(FIXTURE.equals(other));
    }

    /**
     * Tests equality check for two notes which
     * differ in offset,
     */
    @Test
    public void testEquals002() {
        ReducedNote other = new ReducedNote(1, 3, 4);

        assertFalse(FIXTURE.equals(other));
    }

    /**
     * Tests equality check for two notes which
     * differ in duration.
     */
    @Test
    public void testEquals003() {
        ReducedNote other = new ReducedNote(2, 4, 4);

        assertFalse(FIXTURE.equals(other));
    }

    /**
     * Tests equality check for two notes which
     * differ in pitchDelta.
     */
    @Test
    public void testEquals004() {
        ReducedNote other = new ReducedNote(2, 3, 5);

        assertFalse(FIXTURE.equals(other));
    }

    /**
     * Tests equality check for an object
     * of a different type.
     */
    @Test
    public void testEquals005() {
        String other = "This is a string.";

        assertFalse(FIXTURE.equals(other));
    }

    /**
     * Tests equality check for a null object.
     */
    @Test
    public void testEquals006() {
        assertFalse(FIXTURE.equals(null));
    }
}
