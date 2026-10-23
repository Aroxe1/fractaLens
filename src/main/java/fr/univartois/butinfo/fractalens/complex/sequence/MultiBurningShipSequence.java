package fr.univartois.butinfo.fractalens.complex.sequence;

import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.IComplex;

import java.util.Iterator;

public class MultiBurningShipSequence implements IComplexSequence {
    /**
     * The first term of the sequence.
     */
    private IComplex z0;
    /**
     * The complex constant added at each step.
     */
    private IComplex c;
    /**
     * The power to which each term is raised.
     */
    private int power;
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
    public MultiBurningShipSequence(IComplex z0, IComplex c, int power, int maxIterations) {
        this.z0 = z0;
        this.c = c;
        this.power = power;
        this.maxIterations = maxIterations;
    }

    /**
     * Computes the next term of the sequence from the previous one, using the
     * formula {@code (|Re(previous)| + i|Im(previous)|)^p + c}, where {@code p}
     * is the power of this sequence.
     *
     * The power is computed by repeated multiplications of the complex number
     * obtained by taking the absolute value of the real and imaginary parts of the
     * previous term.
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

        IComplex result = z;
        for (int i = 1; i < power; i++) {
            result = result.multiply(z);
        }
        return result.add(c);
    }

    /**
     * Creates an iterator to traverse the terms of the sequence.
     *
     * @return an iterator over the complex numbers in the sequence
     */
    @Override
    public Iterator<IComplex> iterator() {
        return new ComplexSequenceIterator(this,z0, maxIterations);
    }


}
