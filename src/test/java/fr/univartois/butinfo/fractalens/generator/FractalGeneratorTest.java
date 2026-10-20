
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
import fr.univartois.butinfo.fractalens.image.BufferedImageAdapter;
import fr.univartois.butinfo.fractalens.image.IFractalImage;

/**
 * Tests du générateur de fractales de Julia.
 */
class FractalGeneratorTest {

    /**
     * Vérifie que l'image générée possède les bonnes dimensions.
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
     * Vérifie les couleurs des points stables et divergents.
     */
    @Test
    void testComputePixelColorOnDivergence() {
        FractalGenerator generator =
                new FractalGenerator(4, 4, 10, new Complex(0, 0));

        GrayScaleColorStrategy strategy = new GrayScaleColorStrategy(10);

        // Le point (2, 2) correspond au nombre complexe (0, 0).
        assertEquals(
                strategy.getColor(0),
                generator.computePixelColor(2, 2)
        );

        // Le point (0, 0) correspond au nombre complexe (-2, -2).
        assertEquals(
                strategy.getColor(10),
                generator.computePixelColor(0, 0)
        );
    }

    /**
     * Vérifie que les pixels sont enregistrés aux bonnes coordonnées.
     *
     * @param tempDirectory dossier temporaire utilisé pour le test
     * @throws IOException si l'image ne peut pas être lue ou enregistrée
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

                assertEquals(
                        expected.getRGB(),
                        saved.getRGB(column, row)
                );
            }
        }
    }
}
