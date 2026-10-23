package fr.univartois.butinfo.fractalens.complex.sequence;

import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.IComplex;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests the behavior of BurningShipSequence.
 * @author Alice Demey (unit tests)
 */
public class BurningShipSequenceTest {
    /**
     * Creates a new complex number.
     *
     * @param realPart The real part of the complex number.
     * @param imaginaryPart The imaginary part of the complex number.
     *
     * @return The created complex number.
     */
    private static IComplex createComplex(double realPart, double imaginaryPart) {
        return new Complex(realPart,imaginaryPart);
    }

    private static IComplexSequence createBurningShipSequence(IComplex first, IComplex constant, int nbTerms) {
        return new BurningShipSequence(first, constant, nbTerms);
    }

    @Test
    void testBurningShipComputeNext() {
        IComplex z0 = createComplex(-1, 2);
        IComplex c = createComplex(0, 0);
        IComplexSequence ship = createBurningShipSequence(z0, c, 50);

        IComplex z1 = createComplex(-3, 4);
        assertEquals(z1, ship.computeNext(z0));

        IComplex z2 = createComplex(-7, 24);
        assertEquals(z2, ship.computeNext(z1));

        IComplex z3 = createComplex(-527, 336);
        assertEquals(z3, ship.computeNext(z2));
    }

    /**
     * Test method for
     * {@link fr.univartois.butinfo.fractalens.complex.sequence.IComplexSequence#iterator()}
     * when the Burning Ship sequence does not diverge.
     */
    @Test
    void testBurningShipIteratorOnConvergence() {
        IComplex z0 = createComplex(-0.5, 0);
        IComplex c = createComplex(0, 0);
        int nbTerms = 50;

        IComplexSequence ship = createBurningShipSequence(z0, c, nbTerms);
        int nbIterations = 0;

        for (var _ : ship) {
            nbIterations++;
        }

        assertEquals(nbTerms, nbIterations);
    }

    /**
     * Test method for
     * {@link fr.univartois.butinfo.fractalens.complex.sequence.IComplexSequence#iterator()}
     * when the Burning Ship sequence diverges.
     */
    @Test
    void testBurningShipIteratorOnDivergence() {
        IComplex z0 = createComplex(2, 0);
        IComplex c = createComplex(0.5, 0.5);
        int nbTerms = 50;

        IComplexSequence ship = createBurningShipSequence(z0, c, nbTerms);
        int nbIterations = 0;

        for (var _ : ship) {
            nbIterations++;
        }

        assertTrue(nbIterations < nbTerms);
    }


}
