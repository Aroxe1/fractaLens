package fr.univartois.butinfo.fractalens.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.awt.Color;
import java.util.function.BiFunction;

import org.junit.jupiter.api.Test;

import fr.univartois.butinfo.fractalens.color.GrayScaleColorStrategy;
import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.IComplex;
import fr.univartois.butinfo.fractalens.complex.IComplexPlane;
import fr.univartois.butinfo.fractalens.complex.sequence.IComplexSequence;
import fr.univartois.butinfo.fractalens.complex.sequence.JuliaSequence;
import fr.univartois.butinfo.fractalens.image.IFractalImage;

/**
 * Tests the fractal generator using a small image.
 */
class FractalGeneratorTest {

    /**
     * Simple image used to check which pixels are colored.
     */
    private static class TestImage implements IFractalImage {
        private final Color[][] pixels;
        private int coloredPixels;

        TestImage(int width, int height) {
            pixels = new Color[height][width];
        }

        @Override
        public int getHeight() {
            return pixels.length;
        }

        @Override
        public int getWidth() {
            return pixels[0].length;
        }

        @Override
        public void setColor(int row, int column, Color color) {
            pixels[row][column] = color;
            coloredPixels++;
        }

        @Override
        public void saveAs(String path) {
            // Saving is not needed in these tests.
        }

        Color getColor(int row, int column) {
            return pixels[row][column];
        }
    }

    /**
     * Creates a generator with a simple Julia sequence.
     *
     * @param image image to color
     * @param plane complex plane used by the generator
     * @param maxIterations maximum number of iterations
     * @return a generator ready for testing
     */
    private static IFractalGenerator createGenerator(
            IFractalImage image, IComplexPlane plane, int maxIterations) {
        IComplex constant = new Complex(0, 0);
        BiFunction<IComplex, Integer, IComplexSequence> sequenceFactory =
                (z0, nbTerms) -> new JuliaSequence(z0, constant, nbTerms);

        return new FractalGenerator(
                image, plane, sequenceFactory, new GrayScaleColorStrategy(),
                maxIterations, "test.png");
    }

    /**
     * Checks that every pixel gets a color.
     */
    @Test
    void testGenerateImageColorsEveryPixel() {
        TestImage image = new TestImage(3, 2);
        IComplexPlane plane = (row, column) -> new Complex(0, 0);
        IFractalGenerator generator = createGenerator(image, plane, 10);

        IFractalImage result = generator.generateImage();

        assertSame(image, result);
        assertEquals(6, image.coloredPixels);

        for (int row = 0; row < image.getHeight(); row++) {
            for (int column = 0; column < image.getWidth(); column++) {
                assertEquals(Color.WHITE, image.getColor(row, column));
            }
        }
    }

    /**
     * Checks the color of a convergent and a divergent sequence.
     */
    @Test
    void testComputePixelColorOnDivergence() {
        TestImage image = new TestImage(2, 1);
        IComplexPlane plane = (row, column) -> new Complex(column == 0 ? 0 : 3, 0);
        IFractalGenerator generator = createGenerator(image, plane, 10);

        assertEquals(Color.WHITE, generator.computePixelColor(0, 0));
        assertEquals(Color.BLACK, generator.computePixelColor(0, 1));
    }

    /**
     * Checks that rows and columns are not swapped.
     */
    @Test
    void testGenerateImageUsesCorrectCoordinates() {
        TestImage image = new TestImage(2, 2);
        IComplexPlane plane = (row, column) -> new Complex(row == 1 && column == 0 ? 3 : 0, 0);
        IFractalGenerator generator = createGenerator(image, plane, 10);

        generator.generateImage();

        assertEquals(Color.BLACK, image.getColor(1, 0));
        assertEquals(Color.WHITE, image.getColor(0, 0));
        assertEquals(Color.WHITE, image.getColor(0, 1));
        assertEquals(Color.WHITE, image.getColor(1, 1));
    }
}
