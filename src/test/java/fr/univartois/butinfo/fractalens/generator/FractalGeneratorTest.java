
package fr.univartois.butinfo.fractalens.generator;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.GrayScaleColorStrategy;
import fr.univartois.butinfo.fractalens.complex.IColorPalette;
import fr.univartois.butinfo.fractalens.complex.IColorStrategy;
import fr.univartois.butinfo.fractalens.complex.IComplexPlane;
import fr.univartois.butinfo.fractalens.complex.ColorComponentDecorator;
import fr.univartois.butinfo.fractalens.image.BufferedImageAdapter;
import fr.univartois.butinfo.fractalens.image.IFractalImage;

/**
 * Tests the Julia fractal generator.
 */
class FractalGeneratorTest {

    /**
     * Checks that the generated image has the correct dimensions.
     */
    @Test
    void testGenerateImageDimensions() {
        FractalGenerator generator =
                new FractalGenerator(3, 2, 10, new Complex(0, 0));

        IFractalImage image = generator.generateImage();

        assertNotNull(image);
        assertInstanceOf(BufferedImageAdapter.class, image);
        assertEquals(3, image.getWidth());
        assertEquals(2, image.getHeight());
    }

    /**
     * Checks the colors of stable and diverging points.
     */
    @Test
    void testComputePixelColorOnDivergence() {
        FractalGenerator generator =
                new FractalGenerator(4, 4, 10, new Complex(0, 0));

        GrayScaleColorStrategy strategy = new GrayScaleColorStrategy(10);

        // Checks the color of a stable point.
        assertEquals(
                strategy.getColor(10),
                generator.computePixelColor(2, 2)
        );

        // Checks the color of a diverging point.
        assertEquals(
                strategy.getColor(0),
                generator.computePixelColor(0, 0)
        );
    }

    /**
     * Checks that pixels are saved at the correct coordinates.
     */
    @Test
    void testGenerateImageUsesCorrectCoordinates(
            @TempDir Path tempDirectory) throws IOException {

        FractalGenerator generator =
                new FractalGenerator(4, 3, 10, new Complex(0, 0));

        IFractalImage image = generator.generateImage();

        Path output = tempDirectory.resolve("julia-test.png");
        image.saveAs(output.toString());

        BufferedImage saved = ImageIO.read(output.toFile());

        assertNotNull(saved);
        assertEquals(image.getWidth(), saved.getWidth());
        assertEquals(image.getHeight(), saved.getHeight());

        for (int row = 0; row < image.getHeight(); row++) {
            for (int column = 0; column < image.getWidth(); column++) {
                Color expected =
                        generator.computePixelColor(row, column);

                int x = column;
                int y = row;
                assertEquals(
                        expected.getRGB(),
                        saved.getRGB(column, row)
                );
            }
        }
    }

    /**
     * Checks that pixel coordinates are mapped through the supplied complex plane.
     */
    @Test
    void testComputePixelColorUsesSuppliedPlaneAndStrategy() {
        IComplexPlane plane = (row, column) -> new Complex(0, 0);
        Color expected = Color.GREEN;
        IColorStrategy strategy =
                iteration -> iteration == 10 ? expected : Color.MAGENTA;
        FractalGenerator generator = new FractalGenerator(
                2, 2, 10, new Complex(0, 0), plane, strategy);

        assertEquals(expected, generator.computePixelColor(0, 0));
    }

    /**
     * Checks that RGB palette decorators can be used by the image generator.
     */
    @Test
    void testComputePixelColorUsesSuppliedPalette() {
        IComplexPlane plane = (row, column) -> new Complex(0, 0);
        IColorPalette palette = new ColorComponentDecorator(
                iteration -> new Color(120, 200, 60).getRGB(),
                true, false, false);
        FractalGenerator generator = new FractalGenerator(
                2, 2, 10, new Complex(0, 0), plane, palette);

        assertEquals(new Color(120, 0, 0), generator.computePixelColor(0, 0));
    }
}
