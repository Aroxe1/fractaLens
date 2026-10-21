
package fr.univartois.butinfo.fractalens.complex.sequence;

import java.util.Iterator;

import fr.univartois.butinfo.fractalens.complex.IComplex;

/**
 * Represents a Tricorn complex sequence.
 * Each term is calculated by squaring the conjugate
 * of the previous term and adding a constant.
 */
public class TricornSequence implements IComplexSequence {

    /**
     * The initial complex number.
     */
    private final IComplex z0;

    /**
     * The constant added to each term.
     */
    private final IComplex c;

    /**
     * The maximum number of iterations.
     */
    private final int maxIterations;

    /**
     * Creates a new Tricorn sequence.
     *
     * @param z0 the initial complex number
     * @param c the constant added to each term
     * @param maxIterations the maximum number of iterations
     */
    public TricornSequence(IComplex z0, IComplex c, int maxIterations) {
        this.z0 = z0;
        this.c = c;
        this.maxIterations = maxIterations;
    }

    /**
     * Calculates the next term of the Tricorn sequence.
     *
     * @param previous the previous complex number
     * @return the next complex number
     */
    @Override
    public IComplex computeNext(IComplex previous) {
        IComplex conjugate = previous.conjugate();
        return conjugate.multiply(conjugate).add(c);
    }

    /**
     * Returns an iterator to browse the sequence.
     *
     * @return an iterator of complex numbers
     */
    @Override
    public Iterator<IComplex> iterator() {
        return new ComplexSequenceIterator(this, z0, maxIterations);
    }
}
