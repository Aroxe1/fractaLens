package fr.univartois.butinfo.fractalens.complex;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the scaled complex plane.
 */
class ScaledComplexPlaneTest {

    /**
     * Tests that the complex number is correctly scaled by a zoom factor of 2.
     */
    @Test
    void testAsComplex() {
        ComplexPlane plane = new ComplexPlane(10, 10);
        ScaledComplexPlane scaledPlane = new ScaledComplexPlane(plane, 2.0);

        IComplex result = scaledPlane.asComplex(2, 3);

        assertEquals(-4.0, result.getRealPart(), 0.0001);
        assertEquals(6.0, result.getImaginaryPart(), 0.0001);
    }

    /**
     * Tests that the complex number remains unchanged when the zoom factor is 1.
     */
    @Test
    void testAsComplexWithZoomOne() {
        ComplexPlane plane = new ComplexPlane(10, 10);
        ScaledComplexPlane scaledPlane = new ScaledComplexPlane(plane, 1.0);

        IComplex result = scaledPlane.asComplex(2, 3);

        assertEquals(-2.0, result.getRealPart(), 0.0001);
        assertEquals(3.0, result.getImaginaryPart(), 0.0001);
    }

    /**
     * Tests that the complex number becomes zero when the zoom factor is 0.
     */
    @Test
    void testAsComplexWithZoomZero() {
        ComplexPlane plane = new ComplexPlane(10, 10);
        ScaledComplexPlane scaledPlane = new ScaledComplexPlane(plane, 0.0);

        IComplex result = scaledPlane.asComplex(2, 3);

        assertEquals(0.0, result.getRealPart(), 0.0001);
        assertEquals(0.0, result.getImaginaryPart(), 0.0001);
    }
}