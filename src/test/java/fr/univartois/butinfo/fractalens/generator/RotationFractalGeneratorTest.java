package fr.univartois.butinfo.fractalens.generator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the RotatingFractalGenerator class.
 * @author Gautier Tinchon (unit tests)
 */
class RotatingFractalGeneratorTest {

    /** Checks that rotating Tricorn does not silently replace it with Julia. */
    @Test
    void testRotationKeepsTheSelectedFractal() {
        FractalGeneratorBuilder builder = new FractalGeneratorBuilder()
                .usingSequence(fr.univartois.butinfo.fractalens.complex.sequence.ComplexSequenceType.TRICORN)
                .withImageWidth(12).withImageHeight(10).withNumberOfTerms(30)
                .withZoomFactor(0.25)
                .centeredAt(new fr.univartois.butinfo.fractalens.complex.Complex(0, 0))
                .withColorPalette(fr.univartois.butinfo.fractalens.complex.ColorPaletteType.GRADIENT)
                .savingTo("unused.png");
        IFractalGenerator expected = builder.build();
        IFractalGenerator rotated = new RotatingFractalGenerator(builder, 4, "unused.gif", 80);
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 12; column++) {
                assertEquals(expected.computePixelColor(row, column), rotated.computePixelColor(row, column));
            }
        }
    }

    /**
     * Tests that the constructor rejects a non-positive number of frames.
     */
    @Test
    void testInvalidNumberOfFrames() {
        FractalGeneratorBuilder builder = new FractalGeneratorBuilder();

        assertThrows(
                IllegalArgumentException.class,
                () -> new RotatingFractalGenerator(
                        builder, 0, "animation.gif", 100)
        );
    }

    /**
     * Tests that the constructor rejects a negative number of frames.
     */
    @Test
    void testNegativeNumberOfFrames() {
        FractalGeneratorBuilder builder = new FractalGeneratorBuilder();

        assertThrows(
                IllegalArgumentException.class,
                () -> new RotatingFractalGenerator(
                        builder, -1, "animation.gif", 100)
        );
    }
}
