package fr.univartois.butinfo.fractalens.complex.sequence;

import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.IComplex;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MultiBrotSequenceTest {

    @Test
    void testComputeNext() {
        IComplex z0 = new Complex(1, 1);
        MultiBrotSequence sequence = new MultiBrotSequence(z0, 100, 2);

        IComplex z = new Complex(2, 0);
        IComplex result = sequence.computeNext(z);

        // 2² + (1 + i) = 5 + i
        assertEquals(5.0, result.getRealPart(), 0.0001);
        assertEquals(1.0, result.getImaginaryPart(), 0.0001);
    }

    @Test
    void testComputeNextWithPowerThree() {
        IComplex z0 = new Complex(1, 0);
        MultiBrotSequence sequence = new MultiBrotSequence(z0, 100, 3);

        IComplex z = new Complex(2, 0);
        IComplex result = sequence.computeNext(z);

        // 2³ + 1 = 9
        assertEquals(9.0, result.getRealPart(), 0.0001);
        assertEquals(0.0, result.getImaginaryPart(), 0.0001);
    }

    @Test
    void testComputeNextWithZero() {
        IComplex z0 = new Complex(3, 2);
        MultiBrotSequence sequence = new MultiBrotSequence(z0, 100, 2);

        IComplex z = new Complex(0, 0);
        IComplex result = sequence.computeNext(z);

        // 0² + (3 + 2i) = 3 + 2i
        assertEquals(3.0, result.getRealPart(), 0.0001);
        assertEquals(2.0, result.getImaginaryPart(), 0.0001);
    }
}