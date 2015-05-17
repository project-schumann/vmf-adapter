package com.drkharma.vmf.adapter;

import com.drkharma.vmf.*;
import com.drkharma.vmf.parser.exception.TimeSignatureMissingException;
import org.apache.commons.lang3.math.Fraction;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test class for {@link VMFAdapter}.
 */
public class VMFAdapterTest {

    /**
     * Tests the abbreviation of a valid VMF file.
     */
    @Test
    public void testAbbreviate001() {
        final String SIMPLE_VMF = "fixtures/simple.vmf";

        VectorMusicHeader vmHeader = new VectorMusicHeader(
                Fraction.ONE, 1, 1,
                Arrays.asList(new TimeSignature(0, "2/4")),
                Arrays.asList(new KeySignatureInstance(0, KeySignature.C_MAJOR_A_MINOR)),
                Arrays.asList(new MetronomeMarking(0, 120))
        );

        AbbreviatedVectorMusic actual = null;

        AbbreviatedVectorMusic expected = new AbbreviatedVectorMusic(
                new RelativeVMHeader(vmHeader),
                Arrays.asList(
                        new RelativeNote(0, 1, 0),
                        new RelativeNote(1, 1, 4),
                        new RelativeNote(1, 1, 3),
                        new RelativeNote(1, 1, -3)
                )
        );

        expected.getHeader().setReferencePitchClass(PitchClass.C);
        expected.getHeader().setReferenceOctave(4);

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        URI vmfURI = null;

        try {
            vmfURI = classloader.getResource(SIMPLE_VMF).toURI();
        } catch (URISyntaxException e) {
            fail("Fixture file not found.");
        }

        try {
            VMFAdapter adapter = new VMFAdapter(new File(vmfURI));
            actual = adapter.abbreviate();
        } catch (TimeSignatureMissingException | IOException e) {
            fail("Unexpected exception was thrown.");
        }

        assertEquals(expected, actual);
    }

    /**
     * Tests the parsing of a valid VMF file with sustained notes.
     */
    @Test
    public void testParse002() {
        final String SIMPLE_VMF = "fixtures/sustained.vmf";

        VectorMusicHeader vmHeader = new VectorMusicHeader(
                Fraction.ONE, 1, 1,
                Arrays.asList(new TimeSignature(0, "2/4")),
                Arrays.asList(new KeySignatureInstance(0, KeySignature.C_MAJOR_A_MINOR)),
                Arrays.asList(new MetronomeMarking(0, 120))
        );

        AbbreviatedVectorMusic actual = null;

        AbbreviatedVectorMusic expected = new AbbreviatedVectorMusic(
                new RelativeVMHeader(vmHeader),
                Arrays.asList(
                        new RelativeNote(0, 2, 0),
                        new RelativeNote(2, 2, 4),
                        new RelativeNote(2, 2, 3),
                        new RelativeNote(2, 2, -3)
                )
        );

        expected.getHeader().setReferencePitchClass(PitchClass.C);
        expected.getHeader().setReferenceOctave(4);

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        URI vmfURI = null;

        try {
            vmfURI = classloader.getResource(SIMPLE_VMF).toURI();
        } catch (URISyntaxException e) {
            fail("Fixture file not found.");
        }

        try {
            VMFAdapter adapter = new VMFAdapter(new File(vmfURI));
            actual = adapter.abbreviate();
        } catch (TimeSignatureMissingException | IOException e) {
            fail("Unexpected exception was thrown.");
        }

        assertEquals(expected, actual);
    }

    /**
     * Tests the parsing of a valid VMF file with rests.
     */
    @Test
    public void testParse003() {
        final String SIMPLE_VMF = "fixtures/rest.vmf";

        VectorMusicHeader vmHeader = new VectorMusicHeader(
                Fraction.ONE, 1, 1,
                Arrays.asList(new TimeSignature(0, "2/4")),
                Arrays.asList(new KeySignatureInstance(0, KeySignature.C_MAJOR_A_MINOR)),
                Arrays.asList(new MetronomeMarking(0, 120))
        );

        AbbreviatedVectorMusic actual = null;

        AbbreviatedVectorMusic expected = new AbbreviatedVectorMusic(
                new RelativeVMHeader(vmHeader),
                Arrays.asList(
                        new RelativeNote(0, 1, 0),
                        new RelativeNote(2, 1, 7),
                        new RelativeNote(1, 1, -3)
                )
        );

        expected.getHeader().setReferencePitchClass(PitchClass.C);
        expected.getHeader().setReferenceOctave(4);

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        URI vmfURI = null;

        try {
            vmfURI = classloader.getResource(SIMPLE_VMF).toURI();
        } catch (URISyntaxException e) {
            fail("Fixture file not found.");
        }

        try {
            VMFAdapter adapter = new VMFAdapter(new File(vmfURI));
            actual = adapter.abbreviate();
        } catch (TimeSignatureMissingException | IOException e) {
            fail("Unexpected exception was thrown.");
        }

        assertEquals(expected, actual);
    }
}