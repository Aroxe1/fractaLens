package fr.univartois.butinfo.fractalens.complex.sequence;

import fr.univartois.butinfo.fractalens.complex.IComplex;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ComplexSequenceIterator implements Iterator<IComplex> {

    private final IComplexSequence sequence;
    private IComplex current;
    private final int maxIterations;
    private int iterations;

    public ComplexSequenceIterator(
            IComplexSequence sequence,
            IComplex initial,
            int maxIterations) {

        this.sequence = Objects.requireNonNull(sequence);
        this.current = Objects.requireNonNull(initial);

        if (maxIterations < 0) {
            throw new IllegalArgumentException(
                    "Nombre d'iterations negatif"
            );
        }

        this.maxIterations = maxIterations;
        this.iterations = 0;
    }

    @Override
    public boolean hasNext() {
        return iterations < maxIterations
                && current.abs() <= 2;
    }

    @Override
    public IComplex next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        current = sequence.computeNext(current);
        iterations++;

        return current;
    }
}
