
package fr.univartois.butinfo.fractalens.complex.sequence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.IComplex;

/**
 * Tests the generalized Tricorn complex sequence.
 * @author Lylian Michel (unit tests)
 */
class MultiTricornSequenceTest {

    /**
     * Tests the calculation with power 2.
     */
    @Test
    void testComputeNextPower2() {
        IComplex z0 = new Complex(1, 2);
        IComplex c = new Complex(0.5, -0.5);

        MultiTricornSequence sequence =
                new MultiTricornSequence(z0, c, 2, 10);

        IComplex result = sequence.computeNext(z0);

        assertEquals(-2.5, result.getRealPart(), 0.000001);
        assertEquals(-4.5, result.getImaginaryPart(), 0.000001);
    }

    /**
     * Tests the calculation with power 3.
     */
    @Test
    void testComputeNextPower3() {
        IComplex z0 = new Complex(1, 2);
        IComplex c = new Complex(0.5, -0.5);

        MultiTricornSequence sequence =
                new MultiTricornSequence(z0, c, 3, 10);

        IComplex result = sequence.computeNext(z0);

        assertEquals(-10.5, result.getRealPart(), 0.000001);
        assertEquals(1.5, result.getImaginaryPart(), 0.000001);
    }

    /**
     * Tests the calculation with power 4.
     */
    @Test
    void testComputeNextPower4() {
        IComplex z0 = new Complex(1, 1);
        IComplex c = new Complex(0.5, 0.25);

        MultiTricornSequence sequence =
                new MultiTricornSequence(z0, c, 4, 10);

        IComplex result = sequence.computeNext(z0);

        assertEquals(-3.5, result.getRealPart(), 0.000001);
        assertEquals(0.25, result.getImaginaryPart(), 0.000001);
    }

    /**
     * Tests the maximum number of iterations.
     */
    @Test
    void testIterator() {
        IComplex z0 = new Complex(0, 0);
        IComplex c = new Complex(0, 0);

        MultiTricornSequence sequence =
                new MultiTricornSequence(z0, c, 3, 5);

        int iterations = 0;

        for (IComplex z : sequence) {
            iterations++;
        }

        assertEquals(5, iterations);
    }
}
