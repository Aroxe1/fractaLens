package fr.univartois.butinfo.fractalens.image;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * Tests the BufferedImage adapter.
 */
class BufferedImageAdapterTest {

    /**
     * Checks that the image dimensions are correct.
     */
    @Test
    void testDimensions() {
        BufferedImage buffer = new BufferedImage(5, 3, BufferedImage.TYPE_INT_RGB);
        IFractalImage image = new BufferedImageAdapter(buffer);

        assertEquals(5, image.getWidth());
        assertEquals(3, image.getHeight());
    }

    /**
     * Checks that a pixel is colored at the correct position.
     */
    @Test
    void testSetColor() {
        BufferedImage buffer = new BufferedImage(4, 3, BufferedImage.TYPE_INT_RGB);
        IFractalImage image = new BufferedImageAdapter(buffer);

        // The adapter uses (row, column), but BufferedImage uses (x, y).
        image.setColor(1, 2, Color.RED);

        assertEquals(Color.RED.getRGB(), buffer.getRGB(2, 1));
        assertEquals(Color.BLACK.getRGB(), buffer.getRGB(1, 2));
    }

    /**
     * Checks that the image is saved as a PNG file.
     *
     * @param tempDirectory temporary folder provided by JUnit
     * @throws Exception if the image cannot be saved or read
     */
    @Test
    void testSaveAs(@TempDir Path tempDirectory) throws Exception {
        BufferedImage buffer = new BufferedImage(2, 3, BufferedImage.TYPE_INT_RGB);
        IFractalImage image = new BufferedImageAdapter(buffer);
        image.setColor(2, 1, Color.BLUE);

        File output = tempDirectory.resolve("fractal-test.png").toFile();
        image.saveAs(output.getAbsolutePath());

        assertTrue(output.isFile());
        BufferedImage saved = ImageIO.read(output);
        assertNotNull(saved);
        assertEquals(2, saved.getWidth());
        assertEquals(3, saved.getHeight());
        assertEquals(Color.BLUE.getRGB(), saved.getRGB(1, 2));
    }
}
