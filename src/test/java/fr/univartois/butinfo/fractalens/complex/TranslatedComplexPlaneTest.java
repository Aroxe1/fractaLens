package fr.univartois.butinfo.fractalens.complex;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the translated complex plane.
 */
class TranslatedComplexPlaneTest {

    /**
     * Tests that the complex number is correctly translated by an offset.
     */
    @Test
    void testAsComplex() {
        ComplexPlane plane = new ComplexPlane(10, 10);
        IComplex offset = new Complex(2, 3);
        TranslatedComplexPlane translatedPlane =
                new TranslatedComplexPlane(plane, offset);

        IComplex result = translatedPlane.asComplex(2, 3);

        assertEquals(0.0, result.getRealPart(), 0.0001);
        assertEquals(6.0, result.getImaginaryPart(), 0.0001);
    }

    /**
     * Tests that the complex number remains unchanged when the offset is zero.
     */
    @Test
    void testAsComplexWithZeroOffset() {
        ComplexPlane plane = new ComplexPlane(10, 10);
        IComplex offset = new Complex(0, 0);
        TranslatedComplexPlane translatedPlane =
                new TranslatedComplexPlane(plane, offset);

        IComplex result = translatedPlane.asComplex(2, 3);

        assertEquals(-2.0, result.getRealPart(), 0.0001);
        assertEquals(3.0, result.getImaginaryPart(), 0.0001);
    }

    /**
     * Tests that the complex number is correctly translated by a negative offset.
     */
    @Test
    void testAsComplexWithNegativeOffset() {
        ComplexPlane plane = new ComplexPlane(10, 10);
        IComplex offset = new Complex(-2, -3);
        TranslatedComplexPlane translatedPlane =
                new TranslatedComplexPlane(plane, offset);

        IComplex result = translatedPlane.asComplex(2, 3);

        assertEquals(-4.0, result.getRealPart(), 0.0001);
        assertEquals(0.0, result.getImaginaryPart(), 0.0001);
    }
}