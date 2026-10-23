package fr.univartois.butinfo.fractalens.complex.sequence;

import java.util.function.BiFunction;

import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.IComplex;

/**
 * Enumerates the complex sequences available to generate a fractal.
 *
 * <p>Each constant stores a {@link BiFunction} that creates its sequence from the
 * point being tested ({@code z0}) and the maximum number of iterations. The constant
 * {@code c} of the Julia sequences and the power of the "multi" sequences are fixed
 * in each constant.</p>
 * @author Maxence Royon (implementation)
 */
public enum ComplexSequenceType {

    /**
     * Julia sequence with {@code c = -0.4 + 0.6i}.
     */
    JULIA1((z0, max) -> new JuliaSequence(z0, new Complex(-0.4, 0.6), max)),

    /**
     * Julia sequence with {@code c = 0.285 + 0.01i}.
     */
    JULIA2((z0, max) -> new JuliaSequence(z0, new Complex(0.285, 0.01), max)),

    /**
     * Tricorn sequence: {@code z(n+1) = conj(z(n))² + z0}.
     */
    TRICORN((z0, max) -> new TricornSequence(z0, z0, max)),

    /**
     * Burning Ship sequence: {@code z(n+1) = (|Re(z(n))| + i|Im(z(n))|)² + z0}.
     */
    BURNINGSHIP((z0, max) -> new BurningShipSequence(z0, z0, max)),

    /**
     * Mandelbrot sequence: {@code z(n+1) = z(n)² + z0}.
     */
    MANDELBROT((z0, max) -> new MandelBrotSequence(z0, max)),

    /**
     * Multibrot sequence of power 3: {@code z(n+1) = z(n)³ + z0}.
     */
    MULTIBROT((z0, max) -> new MultiBrotSequence(z0, max, 3)),

    /**
     * Multijulia sequence of power 2 with {@code c = -0.8 + 0.156i}:
     * {@code z(n+1) = z(n)² + c}.
     */
    MULTIJULIA((z0, max) -> new MultiJuliaSequence(z0, new Complex(-0.8, 0.156), max, 2)),

    /**
     * Multijulia sequence of power 3 with {@code c = 0.6 + 0.45i}:
     * {@code z(n+1) = z(n)³ + c} (three-armed spiral made of thin filaments).
     */
    MULTIJULIA2((z0, max) -> new MultiJuliaSequence(z0, new Complex(0.6, 0.45), max, 3)),

    /**
     * Multitricorn sequence of power 3: {@code z(n+1) = conj(z(n))³ + z0}.
     */
    MULTITRICORN((z0, max) -> new MultiTricornSequence(z0, z0,3, max)),

    /**
     * Multi Burning Ship sequence of power 3:
     * {@code z(n+1) = (|Re(z(n))| + i|Im(z(n))|)³ + z0}.
     */
    MULTIBURNINGSHIP((z0, max) -> new MultiBurningShipSequence(z0, z0, 3, max));

    /**
     * The function creating the sequence from the point being tested and the
     * maximum number of iterations.
     */
    private final BiFunction<IComplex, Integer, IComplexSequence> factory;

    /**
     * Creates a type of sequence.
     *
     * @param factory the function creating the sequence from the point being tested
     *        and the maximum number of iterations
     */
    ComplexSequenceType(BiFunction<IComplex, Integer, IComplexSequence> factory) {
        this.factory = factory;
    }

    /**
     * Creates the sequence of this type for a given point of the complex plane.
     *
     * @param z0 the point of the complex plane being tested (first term of the sequence)
     * @param maxIterations the maximum number of terms of the sequence
     *
     * @return the created sequence
     */
    public IComplexSequence create(IComplex z0, int maxIterations) {
        return factory.apply(z0, maxIterations);
    }
}
