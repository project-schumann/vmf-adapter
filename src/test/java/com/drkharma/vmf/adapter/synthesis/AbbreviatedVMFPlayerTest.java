package com.drkharma.vmf.adapter.synthesis;

import com.drkharma.vmf.*;
import org.apache.commons.lang3.math.Fraction;
import org.junit.Test;

import java.util.Arrays;

/**
 * Test class for {@link AbbreviatedVMFPlayer}.
 */
public class AbbreviatedVMFPlayerTest {

    /**
     * Tests the playback of an abbreviated VMF stream.
     *
     * Remove annotation for manual testing.
     */
    @Test
    //@Ignore
    public void testPlayback001() {

        VectorMusicHeader vmHeader = new VectorMusicHeader(
                Fraction.ONE, 1, 1,
                Arrays.asList(new TimeSignature(0, "2/4")),
                Arrays.asList(new KeySignatureInstance(0, KeySignature.C_MAJOR_A_MINOR)),
                Arrays.asList(new MetronomeMarking(0, 120))
        );

        AbbreviatedVectorMusic vm = new AbbreviatedVectorMusic(
                new RelativeVMHeader(vmHeader),
                Arrays.asList(
                        new RelativeNote(0, 1, 0),
                        new RelativeNote(1, 1, 4),
                        new RelativeNote(1, 2, 3),
                        new RelativeNote(1, 1, -3)
                )
        );

        vm.getHeader().setReferencePitchClass(PitchClass.C);
        vm.getHeader().setReferenceOctave(4);

        AbbreviatedVMFPlayer player = new AbbreviatedVMFPlayer();

        player.init(vm.getHeader());

        for (RelativeNote n : vm.getNotes()) {
            player.enqueueNote(n);
        }

        player.start();

        try {
            Thread.sleep(10000);
            for (RelativeNote n : vm.getNotes()) {
                player.enqueueNote(n);
            }
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        player.stop();
    }
}
