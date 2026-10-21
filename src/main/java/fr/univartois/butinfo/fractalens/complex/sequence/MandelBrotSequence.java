package fr.univartois.butinfo.fractalens.complex.sequence;

import fr.univartois.butinfo.fractalens.complex.IComplex;

public class MandelBrotSequence extends GenericComplexSequence {

    /**
     * Creates a Mandelbrot sequence.
     *
     * @param z0 the point of the complex plane being tested (first term of the sequence)
     * @param maxIterations the maximum number of iterations
     */
    public MandelBrotSequence(IComplex z0, int maxIterations) {
        super((z, first) -> z.pow(2).add(first), z0, maxIterations);
    }

    /**
     * Returns the initial complex number of the sequence.
     *
     * @return the initial complex number
     */
    public IComplex getZ0() {
        return getCurrent();
    }

}
