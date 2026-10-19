package fr.univartois.butinfo.fractalens.complex.sequence;

import fr.univartois.butinfo.fractalens.complex.IComplex;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;


/**
 * Iterates over a complex sequence, computing one term per call to next().
 *
 * <p>The iterator is independent of any particular recurrence formula: all
 * terms are computed through IComplexSequence.computeNext(IComplex).</p>
 *
 * <p>Integration: adjust the package declaration and the two API method names
 * (computeNext, modulus) to match the existing repository interfaces.</p>
 */
public final class ComplexSequenceIterator implements Iterator<IComplex> {

    private static final double ESCAPE_RADIUS = 2.0;

    private final IComplexSequence sequence;
    private final int maxIterations;
    private IComplex current;
    private int iterations;

    /**
     * Builds an independent iterator starting at the given initial complex number.
     *
     * @param sequence source defining the recurrence
     * @param initial initial term z0 (not returned by next())
     * @param maxIterations maximum number of computed terms
     * @throws NullPointerException if sequence or initial is null
     * @throws IllegalArgumentException if maxIterations is negative
     */
    public ComplexSequenceIterator(
            IComplexSequence sequence,
            IComplex initial,
            int maxIterations) {
        this.sequence = Objects.requireNonNull(sequence, "sequence");
        this.current = Objects.requireNonNull(initial, "initial");
        if (maxIterations < 0) {
            throw new IllegalArgumentException("maxIterations must be >= 0");
        }
        this.maxIterations = maxIterations;
        this.iterations = 0;
    }

    /** Returns whether one more term can be computed. No calculation occurs here. */
    @Override
    public boolean hasNext() {
        return iterations < maxIterations && current.modulus() <= ESCAPE_RADIUS;
    }

    /** Computes and returns exactly one new term of the complex sequence. */
    @Override
    public IComplex next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Complex sequence is exhausted");
        }
        current = Objects.requireNonNull(sequence.computeNext(current), "next term");
        iterations++;
        return current;
    }
}

