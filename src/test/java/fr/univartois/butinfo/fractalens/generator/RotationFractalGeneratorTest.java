package fr.univartois.butinfo.fractalens.generator;

import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.IComplex;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RotationFractalGeneratorTest {

    @Test
    void testRotationAngleZero() {
        IComplex initial = new Complex(2, 3);
        RotationFractalGenerator generator =
                new RotationFractalGenerator(initial);

        IComplex result = generator.rotate(0);

        assertEquals(2.0, result.getRealPart(), 1e-10);
        assertEquals(3.0, result.getImaginaryPart(), 1e-10);
    }

    @Test
    void testRotationAngle90Degrees() {
        IComplex initial = new Complex(2, 3);
        RotationFractalGenerator generator =
                new RotationFractalGenerator(initial);

        IComplex result = generator.rotate(Math.PI / 2);

        assertEquals(-3.0, result.getRealPart(), 1e-10);
        assertEquals(2.0, result.getImaginaryPart(), 1e-10);
    }

    @Test
    void testRotationAngle180Degrees() {
        IComplex initial = new Complex(2, 3);
        RotationFractalGenerator generator =
                new RotationFractalGenerator(initial);

        IComplex result = generator.rotate(Math.PI);

        assertEquals(-2.0, result.getRealPart(), 1e-10);
        assertEquals(-3.0, result.getImaginaryPart(), 1e-10);
    }

    @Test
    void testRotationKeepsModulus() {
        IComplex initial = new Complex(2, 3);
        RotationFractalGenerator generator =
                new RotationFractalGenerator(initial);

        IComplex result = generator.rotate(Math.PI / 4);

        assertEquals(initial.abs(), result.abs(), 1e-10);
    }

    @Test
    void testNullConstant() {
        assertThrows(
                NullPointerException.class,
                () -> new RotationFractalGenerator(null)
        );
    }
}