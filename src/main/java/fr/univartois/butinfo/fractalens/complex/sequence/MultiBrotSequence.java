package fr.univartois.butinfo.fractalens.complex.sequence;

import fr.univartois.butinfo.fractalens.complex.IComplex;

/**
 * Generalizes the Mandelbrot recurrence to an integer power.
 * @author Gautier Tinchon (implementation)
 */
public class MultiBrotSequence extends MandelBrotSequence{
    /**
     * The power to which each term is raised.
     */
    private int power;

    /**
     * Creates a Mandelbrot sequence.
     *
     * @param z0            the point of the complex plane being tested (first term of the sequence)
     * @param maxIterations the maximum number of iterations
     * @param power the integer exponent of the recurrence
     */
    public MultiBrotSequence(IComplex z0, int maxIterations,int power) {
        super(z0, maxIterations);
        this.power = power;
    }

    /**
     * Computes the next complex number in the sequence by raising the current
     * number to the configured power and adding the initial complex number.
     *
     * @param z the current complex number
     * @return the next complex number in the sequence
     */
    @Override
    public IComplex computeNext(IComplex z){
        return z.pow(power).add(getZ0());
    }
}
