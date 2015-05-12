package com.drkharma.vmf.adapter.domain;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test class for {@link ReducedMusic}
 */
public class ReducedMusicTest {

    private static final ReducedMusic FIXTURE = new ReducedMusic(
            Arrays.asList(
                    new ReducedNote(0, 1, 0),
                    new ReducedNote(1, 1, 4),
                    new ReducedNote(1, 1, 3),
                    new ReducedNote(1, 1, -3)
            )
    );

    /**
     * Tests equivalency check for two equal pieces.
     */
    @Test
    public void testEquals001() {
        ReducedMusic other = new ReducedMusic(
                Arrays.asList(
                        new ReducedNote(0, 1, 0),
                        new ReducedNote(1, 1, 4),
                        new ReducedNote(1, 1, 3),
                        new ReducedNote(1, 1, -3)
                )
        );

        assertTrue(FIXTURE.equals(other));
    }

    /**
     * Tests equivalency check for an object of a different type.
     */
    @Test
    public void testEquals002() {
        String other = "This is a string.";

        assertFalse(FIXTURE.equals(other));
    }

    /**
     * Tests equivalency check for a piece with a different number of notes.
     */
    @Test
    public void testEquals003() {
        ReducedMusic other = new ReducedMusic(
                Arrays.asList(
                        new ReducedNote(0, 1, 0),
                        new ReducedNote(1, 1, 4),
                        new ReducedNote(1, 1, 3)
                )
        );

        assertFalse(FIXTURE.equals(other));
    }

    /**
     * Tests equivalency check for a piece with the same number of notes
     * but is different.
     */
    @Test
    public void testEquals004() {
        ReducedMusic other = new ReducedMusic(
                Arrays.asList(
                        new ReducedNote(0, 1, 0),
                        new ReducedNote(1, 1, 4),
                        new ReducedNote(1, 1, 6),
                        new ReducedNote(1, 1, -2)
                )
        );

        assertFalse(FIXTURE.equals(other));
    }

    /**
     * Tests equivalency check to a null reference.
     */
    @Test
    public void testEquals005() {
        assertFalse(FIXTURE.equals(null));
    }
}
