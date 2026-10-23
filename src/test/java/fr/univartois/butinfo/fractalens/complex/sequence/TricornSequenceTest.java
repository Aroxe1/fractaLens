
package fr.univartois.butinfo.fractalens.complex.sequence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.IComplex;

/**
 * Tests the Tricorn complex sequence.
 * @author Lylian Michel (unit tests)
 */
class TricornSequenceTest {

    /**
     * Tests the calculation of the next term.
     */
    @Test
    void testComputeNext() {
        IComplex z0 = new Complex(1, 2);
        IComplex c = new Complex(0.5, -0.5);

        TricornSequence sequence =
                new TricornSequence(z0, c, 10);

        IComplex result = sequence.computeNext(z0);

        assertEquals(-2.5, result.getRealPart(), 0.000001);
        assertEquals(-4.5, result.getImaginaryPart(), 0.000001);
    }

    /**
     * Tests the calculation with a negative real part.
     */
    @Test
    void testComputeNextNegative() {
        IComplex z0 = new Complex(-1, 1);
        IComplex c = new Complex(0.25, 0.5);

        TricornSequence sequence =
                new TricornSequence(z0, c, 10);

        IComplex result = sequence.computeNext(z0);

        assertEquals(0.25, result.getRealPart(), 0.000001);
        assertEquals(2.5, result.getImaginaryPart(), 0.000001);
    }

    /**
     * Tests the calculation when the initial term is zero.
     */
    @Test
    void testComputeNextZero() {
        IComplex z0 = new Complex(0, 0);
        IComplex c = new Complex(0.25, -0.75);

        TricornSequence sequence =
                new TricornSequence(z0, c, 10);

        IComplex result = sequence.computeNext(z0);

        assertEquals(0.25, result.getRealPart(), 0.000001);
        assertEquals(-0.75, result.getImaginaryPart(), 0.000001);
    }

    /**
     * Tests the maximum number of iterations.
     */
    @Test
    void testIterator() {
        IComplex z0 = new Complex(0, 0);
        IComplex c = new Complex(0, 0);

        TricornSequence sequence =
                new TricornSequence(z0, c, 3);

        int iterations = 0;

        for (IComplex z : sequence) {
            iterations++;
        }

        assertEquals(3, iterations);
    }
}
