package fr.univartois.butinfo.fractalens.complex.sequence;

import fr.univartois.butinfo.fractalens.complex.IComplex;

import java.util.Iterator;

public class MandelBrotSequence implements IComplexSequence {
    /**
     * first
     */
    private IComplex z0;

    /**
     * The maximum number of iterations.
     */
    private int maxIterations;

    /**
     * Creates a Mandelbrot sequence.
     *
     * @param z0 the point of the complex plane being tested (first term of the sequence)
     * @param maxIterations the maximum number of iterations
     */
    public MandelBrotSequence(IComplex z0, int maxIterations) {
        this.z0 = z0;
        this.maxIterations = maxIterations;
    }

    /**
     * Computes the next term of the sequence from the previous one, using the
     * formula {@code z^2 + z0}.
     *
     * @param z The previous term of the sequence.
     *
     * @return The next term of the sequence.
     */
    public IComplex computeNext(IComplex z){
        return z.pow(2).add(getZ0());
    }

    /**
     * Returns the initial complex number of the sequence.
     *
     * @return the initial complex number
     */
    public IComplex getZ0() {
        return this.z0;
    }

    /**
     * Creates an iterator over the terms of this sequence. The iterator stops
     * when the maximum number of iterations is reached or when the sequence
     * diverges.
     *
     * @return An iterator over the terms of the sequence.
     */
    @Override
    public Iterator<IComplex> iterator() {
        return new ComplexSequenceIterator(this , z0 , maxIterations);
    }
}
