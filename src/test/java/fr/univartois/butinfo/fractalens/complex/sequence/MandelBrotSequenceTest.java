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
 * Tests for {@link MandelBrotSequence}.
 */
class MandelBrotSequenceTest {

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
     * Tests that {@link MandelBrotSequence#computeNext(IComplex)} computes
     * previous² + z0.
     */
    @Test
    void testComputeNext() {
        IComplexSequence sequence = new MandelBrotSequence(createComplex(1, 1), 10);

        // (2 + 0i)² + (1 + i) = 5 + i
        assertEquals(createComplex(5, 1), sequence.computeNext(createComplex(2, 0)));
        // (0 + i)² + (1 + i) = 0 + i
        assertEquals(createComplex(0, 1), sequence.computeNext(createComplex(0, 1)));
        // (0 + 0i)² + (1 + i) = 1 + i
        assertEquals(createComplex(1, 1), sequence.computeNext(createComplex(0, 0)));
    }

    /**
     * Tests that the first term returned by the iterator is z0.
     */
    @Test
    void testFirstTermIsZ0() {
        IComplexSequence sequence = new MandelBrotSequence(createComplex(0.25, -0.5), 10);

        assertEquals(createComplex(0.25, -0.5), sequence.iterator().next());
    }

    /**
     * Tests that the sequence of 0 stays at 0 and stops after maxIterations terms.
     */
    @Test
    void testZeroStaysBounded() {
        IComplexSequence sequence = new MandelBrotSequence(createComplex(0, 0), 5);

        List<IComplex> list = terms(sequence);
        assertEquals(5, list.size());
        for (IComplex z : list) {
            assertEquals(createComplex(0, 0), z);
        }
    }

    /**
     * Tests the sequence of -1, which oscillates between -1 and 0.
     */
    @Test
    void testMinusOneOscillates() {
        IComplexSequence sequence = new MandelBrotSequence(createComplex(-1, 0), 4);

        assertEquals(List.of(
                createComplex(-1, 0),   // z0
                createComplex(0, 0),    // (-1)² - 1
                createComplex(-1, 0),   // 0² - 1
                createComplex(0, 0)),   // (-1)² - 1
                terms(sequence));
    }

    /**
     * Tests the sequence of i, which ends in a cycle between -1 + i and -i.
     */
    @Test
    void testImaginaryUnit() {
        IComplexSequence sequence = new MandelBrotSequence(createComplex(0, 1), 5);

        assertEquals(List.of(
                createComplex(0, 1),    // z0 = i
                createComplex(-1, 1),   // i² + i
                createComplex(0, -1),   // (-1 + i)² + i
                createComplex(-1, 1),   // (-i)² + i
                createComplex(0, -1)),  // (-1 + i)² + i
                terms(sequence));
    }

    /**
     * Tests the sequence of -2, which stays exactly on the circle of radius 2
     * (|z| = 2 is still considered bounded).
     */
    @Test
    void testMinusTwoOnBoundary() {
        IComplexSequence sequence = new MandelBrotSequence(createComplex(-2, 0), 3);

        assertEquals(List.of(
                createComplex(-2, 0),   // z0
                createComplex(2, 0),    // (-2)² - 2
                createComplex(2, 0)),   // 2² - 2
                terms(sequence));
    }

    /**
     * Tests that the iterator stops as soon as a term escapes the disk of
     * radius 2 (the escaping term is not returned).
     */
    @Test
    void testOneEscapes() {
        IComplexSequence sequence = new MandelBrotSequence(createComplex(1, 0), 100);

        // 1, 1² + 1 = 2, 2² + 1 = 5 (|5| > 2 : stop)
        assertEquals(List.of(createComplex(1, 0), createComplex(2, 0)), terms(sequence));
    }

    /**
     * Tests a complex point that escapes after the first term.
     */
    @Test
    void testComplexPointEscapes() {
        IComplexSequence sequence = new MandelBrotSequence(createComplex(1, 1), 100);

        // 1 + i, (1 + i)² + (1 + i) = 1 + 3i (|1 + 3i| > 2 : stop)
        assertEquals(List.of(createComplex(1, 1)), terms(sequence));
    }

    /**
     * Tests that no term is returned when z0 is outside the disk of radius 2.
     */
    @Test
    void testZ0OutsideDisk() {
        IComplexSequence sequence = new MandelBrotSequence(createComplex(3, 0), 100);

        assertFalse(sequence.iterator().hasNext());
    }

    /**
     * Tests that no term is returned when maxIterations is 0.
     */
    @Test
    void testZeroIterations() {
        IComplexSequence sequence = new MandelBrotSequence(createComplex(0, 0), 0);

        assertFalse(sequence.iterator().hasNext());
    }

    /**
     * Tests that a negative maxIterations is rejected when creating the iterator.
     */
    @Test
    void testNegativeIterations() {
        IComplexSequence sequence = new MandelBrotSequence(createComplex(0, 0), -1);

        assertThrows(IllegalArgumentException.class, sequence::iterator);
    }

    /**
     * Tests that calling next() on an exhausted iterator throws an exception.
     */
    @Test
    void testNextOnExhaustedIterator() {
        IComplexSequence sequence = new MandelBrotSequence(createComplex(0, 0), 1);

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
        IComplexSequence sequence = new MandelBrotSequence(createComplex(0, 1), 10);

        Iterator<IComplex> first = sequence.iterator();
        Iterator<IComplex> second = sequence.iterator();

        first.next();
        first.next();

        assertEquals(createComplex(0, 1), second.next());
        assertEquals(createComplex(0, -1), first.next());
    }
}
