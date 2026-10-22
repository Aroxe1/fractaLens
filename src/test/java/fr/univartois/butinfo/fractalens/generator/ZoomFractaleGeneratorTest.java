package fr.univartois.butinfo.fractalens.generator;

import java.awt.Color;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import fr.univartois.butinfo.fractalens.image.IFractalImage;
import org.junit.jupiter.api.Test;

import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.ColorPaletteType;
import fr.univartois.butinfo.fractalens.generator.FractalGeneratorBuilder;
import fr.univartois.butinfo.fractalens.generator.IFractalGenerator;
import fr.univartois.butinfo.fractalens.generator.ZoomFractalGenerator;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.*;

class ZoomFractalGeneratorTest {

    /**
     * Creates a builder configured with a small image and a Julia sequence.
     *
     * @return The configured builder.
     */
    private static FractalGeneratorBuilder createBuilder() {
        return new FractalGeneratorBuilder()
                .withImageWidth(20)
                .withImageHeight(20)
                .withNumberOfTerms(50)
                .withJuliaConstant(new Complex(-0.4, 0.6))
                .centeredAt(new Complex(0, 0))
                .withColorPalette(ColorPaletteType.GRAY)
                .savingTo("test-zoom.png");
    }

    private static ZoomFractalGenerator createZoom(int nbImages, String outputPath) {
        return new ZoomFractalGenerator(createBuilder(), 0.2, 0.25, nbImages, outputPath, 100);
    }


    @Test
    void testGenerateImage() {
        IFractalImage image = createZoom(3, "test-zoom.gif").generateImage();

        assertNotNull(image);
        assertEquals(20, image.getWidth());
        assertEquals(20, image.getHeight());
    }

    @Test
    void testSaveImageCreatesGif(@TempDir Path directory) {
        Path output = directory.resolve("zoom.gif");

        createZoom(3, output.toString()).saveImage();

        assertTrue(Files.exists(output));
    }


    /**
     * Checks that no generator is created when no image is requested.
     */
    @Test
    void testNoImage() {
        assertThrows(IllegalArgumentException.class,
                () -> new ZoomFractalGenerator(createBuilder(), 0.2, 0.25, 0,"test-zoom.gif", 100));
    }

    @Test
    void testComputePixelColorUsesFirstFrame() {
        ZoomFractalGenerator zoom = createZoom(3, "test-zoom.gif");
        IFractalGenerator sameZoomAlone = createBuilder().withZoomFactor(0.2).build();
        IFractalGenerator moreZoomed = createBuilder().withZoomFactor(0.0125).build();

        // The first frame has a zoom of 0.2: the top-left pixel is white.
        assertEquals(Color.WHITE, zoom.computePixelColor(0, 0));
        assertEquals(sameZoomAlone.computePixelColor(0, 0), zoom.computePixelColor(0, 0));
        assertNotEquals(moreZoomed.computePixelColor(0, 0), zoom.computePixelColor(0, 0));
    }



    @Test
    void testInvalidParameters() {
        FractalGeneratorBuilder builder = createBuilder();

        assertThrows(IllegalArgumentException.class,
                () -> new ZoomFractalGenerator(builder, 0.2, 1.5, 3, "test-zoom.gif", 100));
        assertThrows(IllegalArgumentException.class,
                () -> new ZoomFractalGenerator(builder, 0.2, 0.25, 0, "test-zoom.gif", 100));
    }


}
