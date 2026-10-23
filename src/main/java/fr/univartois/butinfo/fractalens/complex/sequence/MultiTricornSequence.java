
package fr.univartois.butinfo.fractalens.complex.sequence;

import java.util.Iterator;

import fr.univartois.butinfo.fractalens.complex.IComplex;

/**
 * Represents a generalized Tricorn complex sequence.
 * The conjugate of each term is raised to a given power,
 * then a constant is added.
 * @author Lylian Michel (implementation)
 */
public class MultiTricornSequence implements IComplexSequence {

    /**
     * The initial complex number.
     */
    private final IComplex z0;

    /**
     * The constant added to each term.
     */
    private final IComplex c;

    /**
     * The power used to calculate the next term.
     */
    private final int power;

    /**
     * The maximum number of iterations.
     */
    private final int maxIterations;

    /**
     * Creates a new generalized Tricorn sequence.
     *
     * @param z0 the initial complex number
     * @param c the constant added to each term
     * @param power the power used, which must be at least 2
     * @param maxIterations the maximum number of iterations
     * @throws IllegalArgumentException if power is less than 2
     */
    public MultiTricornSequence(IComplex z0, IComplex c, int power, int maxIterations) {
        if (power < 2) {
            throw new IllegalArgumentException("power must be at least 2");
        }
        this.z0 = z0;
        this.c = c;
        this.power = power;
        this.maxIterations = maxIterations;
    }

    /**
     * Calculates the next term of the generalized Tricorn sequence.
     *
     * @param previous the previous complex number
     * @return the next complex number
     */
    @Override
    public IComplex computeNext(IComplex previous) {
        IComplex conjugate = previous.conjugate();
        IComplex result = conjugate;

        for (int i = 1; i < power; i++) {
            result = result.multiply(conjugate);
        }

        return result.add(c);
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
