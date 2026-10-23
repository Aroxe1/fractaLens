
package fr.univartois.butinfo.fractalens.complex.sequence;

import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.IComplex;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the behavior of MultiJuliaSequence.
 * @author Gautier Tinchon (unit tests)
 */
class MultiJuliaSequenceTest {

    @Test
    void testPowerThree() {
        IComplex z0 = new Complex(2, 0);
        IComplex c = new Complex(1, 0);

        MultiJuliaSequence sequence =
                new MultiJuliaSequence(z0, c, 10, 3);

        IComplex result = sequence.computeNext(z0);

        assertEquals(9, result.getRealPart(), 0.0001);
        assertEquals(0, result.getImaginaryPart(), 0.0001);
    }

    @Test
    void testPowerFour() {
        IComplex z0 = new Complex(2, 0);
        IComplex c = new Complex(1, 0);

        MultiJuliaSequence sequence =
                new MultiJuliaSequence(z0, c, 10, 4);

        IComplex result = sequence.computeNext(z0);

        assertEquals(17, result.getRealPart(), 0.0001);
        assertEquals(0, result.getImaginaryPart(), 0.0001);
    }

    @Test
    void testPowerFive() {
        IComplex z0 = new Complex(2, 0);
        IComplex c = new Complex(1, 0);

        MultiJuliaSequence sequence =
                new MultiJuliaSequence(z0, c, 10, 5);

        IComplex result = sequence.computeNext(z0);

        assertEquals(33, result.getRealPart(), 0.0001);
        assertEquals(0, result.getImaginaryPart(), 0.0001);
    }
}
