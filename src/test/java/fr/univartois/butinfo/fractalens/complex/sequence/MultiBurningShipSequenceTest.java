package fr.univartois.butinfo.fractalens.complex.sequence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.IComplex;

class MultiBurningShipSequenceTest {

    /**
     * Creates a new complex number.
     *
     * @param realPart The real part of the complex number.
     * @param imaginaryPart The imaginary part of the complex number.
     *
     * @return The created complex number.
     */
    private static IComplex createComplex(double realPart, double imaginaryPart) {
        return new Complex(realPart, imaginaryPart);
    }
    /**
     * Creates a new generalized Burning Ship sequence.
     *
     * @param first The first term of the sequence.
     * @param constant The constant added at each step.
     * @param power The power to which the term is raised at each step.
     * @param nbTerms The maximum number of terms to compute.
     *
     * @return The created sequence.
     */
    private static IComplexSequence createSequence(
            IComplex first, IComplex constant, int power, int nbTerms) {
        return new MultiBurningShipSequence(first, constant, power, nbTerms);
    }

    /**
     * Test method for
     * {@link MultiBurningShipSequence#computeNext(IComplex)} with different
     * powers and a null constant.
     */
    @Test
    void testComputeNextPowers() {
        IComplex z0 = createComplex(-1, 2);
        IComplex c = createComplex(0, 0);

        assertEquals(createComplex(1, 2), createSequence(z0, c, 1, 50).computeNext(z0));
        assertEquals(createComplex(-3, 4), createSequence(z0, c, 2, 50).computeNext(z0));
        assertEquals(createComplex(-11, -2), createSequence(z0, c, 3, 50).computeNext(z0));
        assertEquals(createComplex(-7, -24), createSequence(z0, c, 4, 50).computeNext(z0));
    }

    /**
     * Test method for
     * {@link MultiBurningShipSequence#computeNext(IComplex)} with a non-null
     * constant, to check that it is added to the result.
     */
    @Test
    void testComputeNextWithConstant() {
        IComplex z0 = createComplex(-1, -1);
        IComplexSequence seq = createSequence(z0, createComplex(0.5, -0.5), 2, 50);

        assertEquals(createComplex(0.5, 1.5), seq.computeNext(z0));
    }

    /**
     * Checks that the generalized sequence with a power of 2 gives the same
     * result as {@link BurningShipSequence}.
     */
    @Test
    void testPower2MatchesBurningShip() {
        IComplex z = createComplex(-1.5, 0.75);
        IComplex c = createComplex(0.25, -0.1);

        IComplex expected = new BurningShipSequence(z, c, 50).computeNext(z);
        IComplex actual = createSequence(z, c, 2, 50).computeNext(z);

        assertEquals(expected, actual);
    }


    /**
     * Test method for {@link MultiBurningShipSequence#iterator()} when the
     * sequence does not diverge: only the maximum number of iterations stops
     * the iterator.
     */
    @Test
    void testIteratorOnConvergence() {
        IComplexSequence seq = createSequence(createComplex(-0.5, 0), createComplex(0, 0), 3, 50);
        int nbIterations = 0;

        for (var _ : seq) {
            nbIterations++;
        }

        assertEquals(50, nbIterations);
    }

    /**
     * Test method for {@link MultiBurningShipSequence#iterator()} when the
     * sequence diverges: the iterator stops before the maximum number of
     * iterations.
     */
    @Test
    void testIteratorOnDivergence() {
        IComplexSequence seq = createSequence(createComplex(2, 0), createComplex(0.5, 0.5), 3, 50);
        int nbIterations = 0;

        for (var _ : seq) {
            nbIterations++;
        }

        assertTrue(nbIterations < 50);
    }

    /**
     * Tests the values returned by the iterator.
     */
    @Test
    void testIteratorNext() {
        IComplex z0 = createComplex(-0.5, 0.5);
        var iterator = createSequence(z0, createComplex(0, 0), 2, 3).iterator();

        assertEquals(z0, iterator.next());
        assertEquals(createComplex(0, 0.5), iterator.next());
        assertEquals(createComplex(-0.25, 0), iterator.next());
    }
}
