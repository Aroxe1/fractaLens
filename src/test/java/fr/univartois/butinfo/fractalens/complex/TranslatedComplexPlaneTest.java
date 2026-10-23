package fr.univartois.butinfo.fractalens.complex;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the translated complex plane.
 * @author Gautier Tinchon (unit tests)
 */
class TranslatedComplexPlaneTest {

    @Test
    void testZoomBeforeTranslationPreservesCenter() {
        for (double zoom : new double[] {0.001, 0.004}) {
            IComplexPlane plane = new TranslatedComplexPlane(
                    new ScaledComplexPlane(new ComplexPlane(800, 800), zoom),
                    new Complex(0.1, 0.6));

            IComplex center = plane.asComplex(400, 400);
            assertEquals(0.1, center.getRealPart(), 0.000001);
            assertEquals(0.6, center.getImaginaryPart(), 0.000001);

            IComplex point = plane.asComplex(300, 500);
            assertEquals(0.1 + 100 * zoom, point.getRealPart(), 0.000001);
            assertEquals(0.6 + 100 * zoom, point.getImaginaryPart(), 0.000001);
        }
    }

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
