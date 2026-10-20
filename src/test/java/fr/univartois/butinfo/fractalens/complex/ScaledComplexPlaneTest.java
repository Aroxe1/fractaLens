
package fr.univartois.butinfo.fractalens.complex;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScaledComplexPlaneTest {

    @Test
    void testAsComplex() {
        ComplexPlane plane = new ComplexPlane(10, 10);
        ScaledComplexPlane scaledPlane = new ScaledComplexPlane(plane, 2.0);

        IComplex result = scaledPlane.asComplex(2, 3);

        assertEquals(-4.0, result.getRealPart(), 0.0001);
        assertEquals(6.0, result.getImaginaryPart(), 0.0001);
    }

    @Test
    void testAsComplexWithZoomOne() {
        ComplexPlane plane = new ComplexPlane(10, 10);
        ScaledComplexPlane scaledPlane = new ScaledComplexPlane(plane, 1.0);

        IComplex result = scaledPlane.asComplex(2, 3);

        assertEquals(-2.0, result.getRealPart(), 0.0001);
        assertEquals(3.0, result.getImaginaryPart(), 0.0001);
    }

    @Test
    void testAsComplexWithZoomZero() {
        ComplexPlane plane = new ComplexPlane(10, 10);
        ScaledComplexPlane scaledPlane = new ScaledComplexPlane(plane, 0.0);

        IComplex result = scaledPlane.asComplex(2, 3);

        assertEquals(0.0, result.getRealPart(), 0.0001);
        assertEquals(0.0, result.getImaginaryPart(), 0.0001);
    }
}
