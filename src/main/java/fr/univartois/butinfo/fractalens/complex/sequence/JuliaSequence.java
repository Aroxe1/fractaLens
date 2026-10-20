package fr.univartois.butinfo.fractalens.complex.sequence;

import fr.univartois.butinfo.fractalens.complex.IComplex;

import java.util.Iterator;

/**
 * Represents a sequence of complex numbers used for the Julia set.
 */
public class JuliaSequence implements  IComplexSequence
{
    private IComplex z0;
    private IComplex c;
    private int maxIterations;

    /**
     * Creates a Julia sequence with an initial value, a constant and a maximum number of iterations.
     *
     * @param z0 initial value of the sequence
     * @param c complex constant used to calculate the next terms
     * @param maxIterations maximum number of iterations
     */
    public JuliaSequence(IComplex z0, IComplex c, int maxIterations) {
        this.z0 = z0;
        this.c = c;
        this.maxIterations = maxIterations;
    }

    /**
     * Calculates the next term of the sequence from the previous term.
     *
     * @param previous previous term of the sequence
     * @return next term calculated using the formula z² + c
     */
    @Override
    public IComplex computeNext(IComplex previous) {
        return previous.multiply(previous).add(c);
    }

    /**
     * Creates an iterator to traverse the terms of the sequence.
     *
     * @return an iterator over the complex numbers in the sequence
     */
    @Override
    public Iterator<IComplex> iterator() {
       return new Iterator<IComplex>() {
            private IComplex actual = z0;
            private int iteration = 0;

            @Override
            public boolean hasNext() {
                return iteration < maxIterations && actual.abs() <= 2;
            }
            @Override
            public IComplex next() {

                IComplex result = actual;
                actual = computeNext(actual);
                iteration++;

                return result;
            }
        };
    }

}