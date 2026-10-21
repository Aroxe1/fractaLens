package fr.univartois.butinfo.fractalens.complex.sequence;

import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.IComplex;

import java.util.Iterator;

public class BurningShipSequence implements IComplexSequence{
    /**
     * The first term of the sequence.
     */
    private IComplex z0;
    /**
     * The complex constant added at each step.
     */
    private IComplex c;
    /**
     * The maximum number of iterations.
     */
    private int maxIterations;


    /**
     * Creates a new instance of {@code BurningShipSequence}.
     *
     * @param z0 The first term of the sequence.
     * @param c The complex constant used to compute the next terms.
     * @param maxIterations The maximum number of iterations.
     */
    public BurningShipSequence(IComplex z0, IComplex c, int maxIterations) {
        this.z0 = z0;
        this.c = c;
        this.maxIterations = maxIterations;
    }

    /**
     * Computes the next term of the sequence from the previous one, using the
     * formula {@code (|Re(previous)| + i|Im(previous)|)^2 + c}.
     *
     * @param previous The previous term of the sequence.
     *
     * @return The next term of the sequence.
     */
    @Override
    public IComplex computeNext(IComplex previous) {
        double real = Math.abs(previous.getRealPart());
        double imaginary = Math.abs(previous.getImaginaryPart());
        IComplex z = new Complex(real,imaginary);
        return z.multiply(z).add(c);
    }

    /**
     * Creates an iterator to traverse the terms of the sequence.
     *
     * @return an iterator over the complex numbers in the sequence
     */
    @Override
    public Iterator<IComplex> iterator() {
        return new ComplexSequenceIterator(this,c, maxIterations);
    }

}
