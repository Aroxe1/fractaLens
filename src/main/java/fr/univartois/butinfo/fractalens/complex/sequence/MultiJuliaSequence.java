package fr.univartois.butinfo.fractalens.complex.sequence;

import fr.univartois.butinfo.fractalens.complex.IComplex;

/**
 * Represents a generalized Julia sequence using a configurable power.
 */
public class MultiJuliaSequence extends JuliaSequence {
    /**
     * The power to which each term is raised.
     */
    private int power;

    /**
     * Creates a Julia sequence with an initial value, a constant,
     * a maximum number of iterations and a power.
     *
     * @param z0 initial value of the sequence
     * @param c complex constant used to calculate the next terms
     * @param maxIterations maximum number of iterations
     * @param power power used to calculate the next term
     */
    public MultiJuliaSequence(IComplex z0, IComplex c, int maxIterations, int power) {
        super(z0, c, maxIterations);
        this.power = power;
    }

    /**
     * Calculates the next term of the sequence by raising the current
     * complex number to the specified power and adding the constant.
     *
     * @param z current term of the sequence
     * @return next term calculated using the formula z^power + c
     */
    public IComplex computeNext(IComplex z) {
        return z.pow(power).add(getC());
    }
}
