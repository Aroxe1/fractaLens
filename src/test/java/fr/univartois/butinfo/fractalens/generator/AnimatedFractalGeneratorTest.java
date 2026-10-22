package fr.univartois.butinfo.fractalens.generator;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageInputStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.w3c.dom.Node;

import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.image.IFractalImage;

/**
 * Unit tests for the {@link AnimatedFractalGenerator} class.
 *
 * @see AnimatedFractalGenerator
 */
class AnimatedFractalGeneratorTest {

    /**
     * The temporary directory in which the GIF files are written.
     */
    @TempDir
    Path tempDir;

    /**
     * Creates a small Julia fractal generator.
     *
     * @param width The width of the image.
     * @param height The height of the image.
     * @param real The real part of the Julia constant.
     *
     * @return The created generator.
     */
    private static IFractalGenerator julia(int width, int height, double real) {
        return new FractalGenerator(width, height, 20, new Complex(real, 0.27015));
    }

    /**
     * Creates a GIF reader on the given input stream.
     *
     * @param input The stream of the GIF file to read.
     *
     * @return A reader ready to read the file.
     */
    private static ImageReader gifReader(ImageInputStream input) {
        ImageReader reader = ImageIO.getImageReadersByFormatName("gif").next();
        reader.setInput(input);
        return reader;
    }

    /**
     * Checks that generateImage gives the image of the first generator.
     */
    @Test
    void testGenerateImageGivesFirstFrame() {
        List<IFractalGenerator> generators =
                List.of(julia(8, 6, -0.7), julia(20, 15, -0.6));
        AnimatedFractalGenerator animated =
                new AnimatedFractalGenerator(generators, "unused.gif", 100);

        IFractalImage image = animated.generateImage();

        assertNotNull(image);
        assertEquals(8, image.getWidth());
        assertEquals(6, image.getHeight());
    }

    /**
     * Checks that computePixelColor gives the color computed by the first generator.
     */
    @Test
    void testComputePixelColorUsesFirstGenerator() {
        IFractalGenerator first = julia(10, 10, -0.7);
        AnimatedFractalGenerator animated = new AnimatedFractalGenerator(
                List.of(first, julia(10, 10, 0.3)), "unused.gif", 100);

        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                assertEquals(first.computePixelColor(row, column),
                        animated.computePixelColor(row, column));
            }
        }
    }

    /**
     * Checks that the generator keeps its own copy of the list given to the
     * constructor.
     */
    @Test
    void testConstructorCopiesList() {
        List<IFractalGenerator> generators = new ArrayList<>();
        generators.add(julia(8, 6, -0.7));
        AnimatedFractalGenerator animated =
                new AnimatedFractalGenerator(generators, "unused.gif", 100);

        generators.clear();

        assertNotNull(animated.generateImage());
    }

    /**
     * Checks that saveImage writes a GIF file in the output path.
     *
     * @throws IOException If the file cannot be read.
     */
    @Test
    void testSaveImageCreatesGifFile() throws IOException {
        Path output = tempDir.resolve("animation.gif");
        AnimatedFractalGenerator animated = new AnimatedFractalGenerator(
                List.of(julia(10, 10, -0.7), julia(10, 10, -0.6)),
                output.toString(), 100);

        animated.saveImage();

        assertTrue(Files.exists(output));
        byte[] header = Files.readAllBytes(output);
        assertEquals("GIF89a", new String(header, 0, 6));
    }

    /**
     * Checks that the GIF contains one frame per generator, with the right size.
     *
     * @throws IOException If the file cannot be read.
     */
    @Test
    void testSaveImageWritesOneFramePerGenerator() throws IOException {
        Path output = tempDir.resolve("frames.gif");
        AnimatedFractalGenerator animated = new AnimatedFractalGenerator(
                List.of(julia(12, 9, -0.7), julia(12, 9, -0.65), julia(12, 9, -0.6)),
                output.toString(), 100);

        animated.saveImage();

        try (ImageInputStream input = ImageIO.createImageInputStream(output.toFile())) {
            ImageReader reader = gifReader(input);
            assertEquals(3, reader.getNumImages(true));
            assertEquals(12, reader.getWidth(0));
            assertEquals(9, reader.getHeight(0));
            reader.dispose();
        }
    }

    /**
     * Checks that the delay between two frames is stored in the GIF.
     * The GIF format stores the delay in hundredths of a second.
     *
     * @throws IOException If the file cannot be read.
     */
    @Test
    void testSaveImageUsesDelay() throws IOException {
        Path output = tempDir.resolve("delay.gif");
        AnimatedFractalGenerator animated = new AnimatedFractalGenerator(
                List.of(julia(10, 10, -0.7), julia(10, 10, -0.6)),
                output.toString(), 200);

        animated.saveImage();

        try (ImageInputStream input = ImageIO.createImageInputStream(output.toFile())) {
            ImageReader reader = gifReader(input);
            IIOMetadata metadata = reader.getImageMetadata(0);
            Node root = metadata.getAsTree("javax_imageio_gif_image_1.0");
            String delay = null;
            for (Node node = root.getFirstChild(); node != null; node = node.getNextSibling()) {
                if ("GraphicControlExtension".equals(node.getNodeName())) {
                    delay = node.getAttributes().getNamedItem("delayTime").getNodeValue();
                }
            }
            assertEquals("20", delay);
            reader.dispose();
        }
    }
}
