package fr.univartois.butinfo.fractalens.complex.sequence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.IComplex;

/**
 * Tests for {@link ComplexSequenceType}.
 *
 * <p>Each constant is compared with the corresponding sequence class used directly.
 * The maximum number of iterations is different from the powers used by the
 * "multi" sequences, so that swapping them in a constructor call makes the test
 * fail.</p>
 * @author Maxence Royon (unit tests)
 */
class ComplexSequenceTypeTest {

    /**
     * The maximum number of iterations used in the tests.
     */
    private static final int MAX_ITERATIONS = 20;

    /**
     * The point of the complex plane used in the tests.
     */
    private static final IComplex Z0 = createComplex(0.3, 0.4);

    /**
     * The origin of the complex plane.
     */
    private static final IComplex ZERO = createComplex(0, 0);

    /**
     * Creates a new complex number.
     *
     * @param realPart The real part of the complex number.
     * @param imaginaryPart The imaginary part of the complex number.
     *
     * @return The created complex number.
     */
    private static IComplex createComplex(double realPart, double imaginaryPart) {
        return new Complex(realPart, imaginaryPart);
    }

    /**
     * Collects all the terms returned by the iterator of a sequence.
     *
     * @param sequence The sequence to iterate over.
     *
     * @return The list of the terms of the sequence.
     */
    private static List<IComplex> terms(IComplexSequence sequence) {
        List<IComplex> list = new ArrayList<>();
        for (IComplex z : sequence) {
            list.add(z);
        }
        return list;
    }

    /**
     * Creates the sequence of the given type with the parameters of the tests.
     *
     * @param type The type of sequence to create.
     *
     * @return The created sequence.
     */
    private static IComplexSequence create(ComplexSequenceType type) {
        return type.create(Z0, MAX_ITERATIONS);
    }

    /**
     * Tests the constants of the enumeration.
     */
    @Test
    void testValues() {
        assertEquals(10, ComplexSequenceType.values().length);
        assertEquals(ComplexSequenceType.JULIA1, ComplexSequenceType.valueOf("JULIA1"));
        assertEquals(ComplexSequenceType.MANDELBROT, ComplexSequenceType.valueOf("MANDELBROT"));
        assertEquals(ComplexSequenceType.MULTIBURNINGSHIP,
                ComplexSequenceType.valueOf("MULTIBURNINGSHIP"));
    }

    /**
     * Tests that each call to create returns a new sequence.
     */
    @Test
    void testCreateReturnsNewSequence() {
        for (ComplexSequenceType type : ComplexSequenceType.values()) {
            IComplexSequence first = create(type);
            IComplexSequence second = create(type);
            assertEquals(terms(first), terms(second));
            assertNotSame(first, second);
        }
    }

    /**
     * Tests the Julia constants: each one creates a {@link JuliaSequence} with its own
     * constant c (for the Julia sequence, 0² + c = c).
     */
    @Test
    void testJulia() {
        IComplex[] constants = {
            createComplex(-0.4, 0.6),
            createComplex(0.285, 0.01)
        };
        ComplexSequenceType[] types = {
            ComplexSequenceType.JULIA1,
            ComplexSequenceType.JULIA2
        };

        for (int i = 0; i < types.length; i++) {
            IComplexSequence sequence = create(types[i]);
            assertInstanceOf(JuliaSequence.class, sequence);
            assertEquals(constants[i], sequence.computeNext(ZERO));
            assertEquals(terms(new JuliaSequence(Z0, constants[i], MAX_ITERATIONS)),
                    terms(sequence));
        }
    }

    /**
     * Tests the Tricorn constant.
     */
    @Test
    void testTricorn() {
        IComplexSequence sequence = create(ComplexSequenceType.TRICORN);

        assertInstanceOf(TricornSequence.class, sequence);
        assertEquals(Z0, sequence.computeNext(ZERO));
        assertEquals(terms(new TricornSequence(Z0, Z0, MAX_ITERATIONS)), terms(sequence));
    }

    /**
     * Tests the Burning Ship constant.
     */
    @Test
    void testBurningShip() {
        IComplexSequence sequence = create(ComplexSequenceType.BURNINGSHIP);

        assertInstanceOf(BurningShipSequence.class, sequence);
        assertEquals(Z0, sequence.computeNext(ZERO));
        assertEquals(terms(new BurningShipSequence(Z0, Z0, MAX_ITERATIONS)), terms(sequence));
    }

    /**
     * Tests the Mandelbrot constant.
     */
    @Test
    void testMandelbrot() {
        IComplexSequence sequence = create(ComplexSequenceType.MANDELBROT);

        assertInstanceOf(MandelBrotSequence.class, sequence);
        assertEquals(Z0, sequence.computeNext(ZERO));
        assertEquals(terms(new MandelBrotSequence(Z0, MAX_ITERATIONS)), terms(sequence));
    }

    /**
     * Tests the Multibrot constant (power 3).
     */
    @Test
    void testMultibrot() {
        IComplexSequence sequence = create(ComplexSequenceType.MULTIBROT);

        assertInstanceOf(MultiBrotSequence.class, sequence);
        assertEquals(Z0, sequence.computeNext(ZERO));
        // (1 + i)³ + z0 = -2 + 2i + z0
        assertEquals(createComplex(-2, 2).add(Z0), sequence.computeNext(createComplex(1, 1)));
        assertEquals(terms(new MultiBrotSequence(Z0, MAX_ITERATIONS, 3)), terms(sequence));
    }

    /**
     * Tests the first Multijulia constant (power 2).
     */
    @Test
    void testMultiJulia() {
        IComplex c = createComplex(-0.8, 0.156);
        IComplexSequence sequence = create(ComplexSequenceType.MULTIJULIA);

        assertInstanceOf(MultiJuliaSequence.class, sequence);
        assertEquals(c, sequence.computeNext(ZERO));
        // (1 + i)² + c = 2i + c
        assertEquals(createComplex(0, 2).add(c), sequence.computeNext(createComplex(1, 1)));
        assertEquals(terms(new MultiJuliaSequence(Z0, c, MAX_ITERATIONS, 2)),
                terms(sequence));
    }

    /**
     * Tests the second Multijulia constant (power 3).
     */
    @Test
    void testMultiJulia2() {
        IComplex c = createComplex(0.6, 0.45);
        IComplexSequence sequence = create(ComplexSequenceType.MULTIJULIA2);

        assertInstanceOf(MultiJuliaSequence.class, sequence);
        assertEquals(c, sequence.computeNext(ZERO));
        // (1 + i)³ + c = -2 + 2i + c
        assertEquals(createComplex(-2, 2).add(c), sequence.computeNext(createComplex(1, 1)));
        assertEquals(terms(new MultiJuliaSequence(Z0, c, MAX_ITERATIONS, 3)),
                terms(sequence));
    }

    /**
     * Tests the Multitricorn constant (power 3).
     */
    @Test
    void testMultiTricorn() {
        IComplexSequence sequence = create(ComplexSequenceType.MULTITRICORN);

        assertInstanceOf(MultiTricornSequence.class, sequence);
        assertEquals(Z0, sequence.computeNext(ZERO));
        assertEquals(terms(new MultiTricornSequence(Z0, Z0, 3, MAX_ITERATIONS)),
                terms(sequence));
    }

    /**
     * Tests the Multi Burning Ship constant (power 3).
     */
    @Test
    void testMultiBurningShip() {
        IComplexSequence sequence = create(ComplexSequenceType.MULTIBURNINGSHIP);

        assertInstanceOf(MultiBurningShipSequence.class, sequence);
        assertEquals(Z0, sequence.computeNext(ZERO));
        assertEquals(terms(new MultiBurningShipSequence(Z0, Z0, 3, MAX_ITERATIONS)),
                terms(sequence));
    }
}
