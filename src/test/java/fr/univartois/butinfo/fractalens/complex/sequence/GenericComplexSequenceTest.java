package fr.univartois.butinfo.fractalens.complex.sequence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.IComplex;

/**
 * Tests for {@link GenericComplexSequence}.
 */
class GenericComplexSequenceTest {

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
     * Tests that {@link GenericComplexSequence#computeNext(IComplex)} applies
     * the function to the previous term and the constant.
     */
    @Test
    void testComputeNext() {
        IComplexSequence sequence = new GenericComplexSequence(
                (z, k) -> z.add(k), createComplex(0, 1), createComplex(0, 0));

        assertEquals(createComplex(0, 2), sequence.computeNext(createComplex(0, 1)));
        assertEquals(createComplex(3, 1), sequence.computeNext(createComplex(3, 0)));
    }

    /**
     * Tests that {@link GenericComplexSequence#computeNext(IComplex)} uses the
     * constant given to the constructor, not the first term.
     */
    @Test
    void testComputeNextUsesConstant() {
        IComplexSequence sequence = new GenericComplexSequence(
                (z, k) -> z.multiply(k), createComplex(0, 1), createComplex(5, 5));

        // (2 + 0i) * i = 0 + 2i
        assertEquals(createComplex(0, 2), sequence.computeNext(createComplex(2, 0)));
    }

    /**
     * Tests that the first term returned by the iterator is the one given
     * to the constructor.
     */
    @Test
    void testIteratorStartsWithFirstTerm() {
        IComplexSequence sequence = new GenericComplexSequence(
                (z, k) -> z.add(k), createComplex(1, 0), createComplex(3, 4));

        assertEquals(createComplex(3, 4), sequence.iterator().next());
    }

    /**
     * Tests the terms of the sequence z(n+1) = z(n)² + c with c = 1 and z0 = 0.
     */
    @Test
    void testLambdaSequence() {
        IComplexSequence sequence = new GenericComplexSequence(
                (z, k) -> z.pow(2).add(k), createComplex(1, 0), createComplex(0, 0));

        Iterator<IComplex> it = sequence.iterator();
        assertEquals(createComplex(0, 0), it.next());    // premier terme
        assertEquals(createComplex(1, 0), it.next());    // 0² + 1
        assertEquals(createComplex(2, 0), it.next());    // 1² + 1
        assertEquals(createComplex(5, 0), it.next());    // 2² + 1
        assertEquals(createComplex(26, 0), it.next());   // 5² + 1
    }

    /**
     * Tests a sequence with a complex constant: z(n+1) = z(n)² + i with z0 = 0.
     */
    @Test
    void testSequenceWithComplexConstant() {
        IComplexSequence sequence = new GenericComplexSequence(
                (z, k) -> z.pow(2).add(k), createComplex(0, 1), createComplex(0, 0));

        Iterator<IComplex> it = sequence.iterator();
        assertEquals(createComplex(0, 0), it.next());    // z0
        assertEquals(createComplex(0, 1), it.next());    // 0² + i
        assertEquals(createComplex(-1, 1), it.next());   // i² + i
        assertEquals(createComplex(0, -1), it.next());   // (-1 + i)² + i = -2i + i
        assertEquals(createComplex(-1, 1), it.next());   // (-i)² + i
    }

    /**
     * Tests that the iterator never ends (the sequence is infinite).
     */
    @Test
    void testIteratorIsInfinite() {
        IComplexSequence sequence = new GenericComplexSequence(
                (z, k) -> z.add(k), createComplex(1, 0), createComplex(0, 0));

        Iterator<IComplex> it = sequence.iterator();
        for (int i = 0; i < 1000; i++) {
            assertTrue(it.hasNext());
            assertEquals(createComplex(i, 0), it.next());
        }
        assertTrue(it.hasNext());
    }

    /**
     * Tests that two iterators of the same sequence are independent.
     */
    @Test
    void testIteratorsAreIndependent() {
        IComplexSequence sequence = new GenericComplexSequence(
                (z, k) -> z.add(k), createComplex(1, 0), createComplex(0, 0));

        Iterator<IComplex> first = sequence.iterator();
        Iterator<IComplex> second = sequence.iterator();
        assertNotSame(first, second);

        first.next();
        first.next();
        first.next();

        assertEquals(createComplex(0, 0), second.next());
        assertEquals(createComplex(3, 0), first.next());
    }
}
