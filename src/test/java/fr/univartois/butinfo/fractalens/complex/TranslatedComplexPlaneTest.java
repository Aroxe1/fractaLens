package fr.univartois.butinfo.fractalens.complex;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests the translation of complex-plane coordinates.
 */
class TranslatedComplexPlaneTest {

    private IComplexPlane planeAt(double real, double imaginary) {
        return (row, column) -> new Complex(real, imaginary);
    }

    @Test
    void testTranslationAddsRealAndImaginaryParts() {
        IComplexPlane translated = new TranslatedComplexPlane(
                planeAt(2, 3), new Complex(-0.5, 0.25));

        IComplex result = translated.asComplex(0, 0);

        assertEquals(1.5, result.getRealPart(), 0.0001);
        assertEquals(3.25, result.getImaginaryPart(), 0.0001);
    }

    @Test
    void testNullTranslationLeavesPointUnchanged() {
        IComplexPlane translated = new TranslatedComplexPlane(
                planeAt(2, 3), new Complex(0, 0));

        IComplex result = translated.asComplex(0, 0);

        assertEquals(2, result.getRealPart(), 0.0001);
        assertEquals(3, result.getImaginaryPart(), 0.0001);
    }

    @Test
    void testZoomIsAppliedBeforeTranslation() {
        IComplexPlane zoomThenTranslate = new TranslatedComplexPlane(
                new ScaledComplexPlane(planeAt(2, 3), 2),
                new Complex(-0.5, 0.25));

        IComplex result = zoomThenTranslate.asComplex(0, 0);

        assertEquals(3.5, result.getRealPart(), 0.0001);
        assertEquals(6.25, result.getImaginaryPart(), 0.0001);
    }
}
