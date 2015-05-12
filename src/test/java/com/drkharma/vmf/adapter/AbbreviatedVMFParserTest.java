package com.drkharma.vmf.adapter;

import com.drkharma.vmf.adapter.domain.ReducedMusic;
import com.drkharma.vmf.adapter.domain.ReducedNote;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Test class for {@link AbbreviatedVMFParser}
 *
 * @author Patrick Ayoup
 * @version 0.0.1
 * @since 0.0.1
 */
public class AbbreviatedVMFParserTest {
    /**
     * Tests parsing of a basic VMF file
     */
    @Test
    public void testParse001() {
        // Setup
        final String SIMPLE_VMF = "simple.vmf";

        ReducedMusic expected = new ReducedMusic(
                Arrays.asList(
                        new ReducedNote(0, 1, 0),
                        new ReducedNote(1, 1, 4),
                        new ReducedNote(1, 1, 3),
                        new ReducedNote(1, 1, -3)
                )
        );

        // Execute the operation.
        ReducedMusic actual = AbbreviatedVMFParser.parse(new File(SIMPLE_VMF));

        assertEquals(expected, actual);
    }

    // TODO: Determine other test cases.
}