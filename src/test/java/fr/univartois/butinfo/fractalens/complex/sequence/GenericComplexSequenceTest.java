package fr.univartois.butinfo.fractalens.complex.sequence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

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
     * Collects all the terms returned by the iterator of a sequence.
     *
     * @param sequence The sequence to iterate over.
     *
     * @return The list of the terms of the sequence.
     */
    private static List<IComplex> terms(IComplexSequence sequence) {
        List<IComplex> list = new ArrayList<>();
        for (IComplex z : sequence) {
            list.add(z);
        }
        return list;
    }

    /**
     * Tests that {@link GenericComplexSequence#computeNext(IComplex)} applies
     * the function to the previous term and the first term of the sequence.
     */
    @Test
    void testComputeNext() {
        IComplexSequence sequence = new GenericComplexSequence(
                (z, c) -> z.add(c), createComplex(0, 1), 10);

        assertEquals(createComplex(0, 2), sequence.computeNext(createComplex(0, 1)));
        assertEquals(createComplex(3, 1), sequence.computeNext(createComplex(3, 0)));
    }

    /**
     * Tests {@link GenericComplexSequence#computeNext(IComplex)} with the
     * formula z² + c.
     */
    @Test
    void testComputeNextSquare() {
        IComplexSequence sequence = new GenericComplexSequence(
                (z, c) -> z.pow(2).add(c), createComplex(1, 1), 10);

        // (2 + 0i)² + (1 + i) = 5 + i
        assertEquals(createComplex(5, 1), sequence.computeNext(createComplex(2, 0)));
        // (0 + i)² + (1 + i) = 0 + i
        assertEquals(createComplex(0, 1), sequence.computeNext(createComplex(0, 1)));
    }

    /**
     * Tests that the first term returned by the iterator is the one given
     * to the constructor.
     */
    @Test
    void testIteratorStartsWithFirstTerm() {
        IComplexSequence sequence = new GenericComplexSequence(
                (z, c) -> z.pow(2).add(c), createComplex(0.5, 0.5), 10);

        assertEquals(createComplex(0.5, 0.5), sequence.iterator().next());
    }

    /**
     * Tests that the iterator stops after maxIterations terms when the
     * sequence stays bounded.
     */
    @Test
    void testIteratorStopsAtMaxIterations() {
        IComplexSequence sequence = new GenericComplexSequence(
                (z, c) -> z.pow(2).add(c), createComplex(0, 0), 5);

        List<IComplex> list = terms(sequence);
        assertEquals(5, list.size());
        for (IComplex z : list) {
            assertEquals(createComplex(0, 0), z);
        }
    }

    /**
     * Tests the terms of z(n+1) = z(n)² + c with c = z0 = i (bounded cycle).
     */
    @Test
    void testBoundedSequence() {
        IComplexSequence sequence = new GenericComplexSequence(
                (z, c) -> z.pow(2).add(c), createComplex(0, 1), 4);

        assertEquals(List.of(
                createComplex(0, 1),    // z0 = i
                createComplex(-1, 1),   // i² + i
                createComplex(0, -1),   // (-1 + i)² + i
                createComplex(-1, 1)),  // (-i)² + i
                terms(sequence));
    }

    /**
     * Tests that the iterator stops as soon as a term escapes the disk of
     * radius 2 (the escaping term is not returned).
     */
    @Test
    void testIteratorStopsWhenSequenceEscapes() {
        IComplexSequence sequence = new GenericComplexSequence(
                (z, c) -> z.pow(2).add(c), createComplex(1, 0), 100);

        // 1, 1² + 1 = 2, 2² + 1 = 5 (|5| > 2 : stop)
        assertEquals(List.of(createComplex(1, 0), createComplex(2, 0)), terms(sequence));
    }

    /**
     * Tests that no term is returned when the first term is already outside
     * the disk of radius 2.
     */
    @Test
    void testFirstTermOutsideDisk() {
        IComplexSequence sequence = new GenericComplexSequence(
                (z, c) -> z.pow(2).add(c), createComplex(3, 0), 100);

        assertFalse(sequence.iterator().hasNext());
    }

    /**
     * Tests that no term is returned when maxIterations is 0.
     */
    @Test
    void testZeroIterations() {
        IComplexSequence sequence = new GenericComplexSequence(
                (z, c) -> z.pow(2).add(c), createComplex(0, 0), 0);

        assertFalse(sequence.iterator().hasNext());
    }

    /**
     * Tests that a negative maxIterations is rejected when creating the iterator.
     */
    @Test
    void testNegativeIterations() {
        IComplexSequence sequence = new GenericComplexSequence(
                (z, c) -> z.pow(2).add(c), createComplex(0, 0), -1);

        assertThrows(IllegalArgumentException.class, sequence::iterator);
    }

    /**
     * Tests that calling next() on an exhausted iterator throws an exception.
     */
    @Test
    void testNextOnExhaustedIterator() {
        IComplexSequence sequence = new GenericComplexSequence(
                (z, c) -> z.pow(2).add(c), createComplex(0, 0), 1);

        Iterator<IComplex> it = sequence.iterator();
        assertTrue(it.hasNext());
        it.next();
        assertFalse(it.hasNext());
        assertThrows(NoSuchElementException.class, it::next);
    }

    /**
     * Tests that two iterators of the same sequence are independent.
     */
    @Test
    void testIteratorsAreIndependent() {
        IComplexSequence sequence = new GenericComplexSequence(
                (z, c) -> z.pow(2).add(c), createComplex(0, 1), 10);

        Iterator<IComplex> first = sequence.iterator();
        Iterator<IComplex> second = sequence.iterator();

        first.next();
        first.next();

        assertEquals(createComplex(0, 1), second.next());
        assertEquals(createComplex(0, -1), first.next());
    }
}
