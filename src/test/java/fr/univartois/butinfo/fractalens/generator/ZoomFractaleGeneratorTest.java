package fr.univartois.butinfo.fractalens.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.ColorPaletteType;


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

    /**
     * Checks that the expected number of generators is created.
     */
    @Test
    void testNumberOfGenerators() {
        ZoomFractalGenerator zoom = new ZoomFractalGenerator(createBuilder(), 0.2, 0.25, 3);

        assertEquals(3, zoom.getGenerators().size());
    }

    /**
     * Checks that no generator is created when no image is requested.
     */
    @Test
    void testNoImage() {
        ZoomFractalGenerator zoom = new ZoomFractalGenerator(createBuilder(), 0.2, 0.25, 0);

        assertTrue(zoom.getGenerators().isEmpty());
    }

    /**
     * Checks that every generator is a distinct object.
     */
    @Test
    void testGeneratorsAreDistinct() {
        List<IFractalGenerator> generators = new ZoomFractalGenerator(createBuilder(), 0.2, 0.25, 3).getGenerators();

        assertNotSame(generators.get(0), generators.get(1));
        assertNotSame(generators.get(1), generators.get(2));
        assertNotSame(generators.get(0), generators.get(2));
    }

    /**
     * Checks that the zoom really changes between the generators.
     * With a zoom of 0.2, the top-left pixel of a 20x20 image is the point
     * -2 + 2i, whose modulus is greater than 2: the sequence diverges at once
     * and the pixel is white. With a smaller zoom, this pixel is much closer
     * to the center and the sequence does not diverge, so the pixel is dark.
     */
    @Test
    void testZoomChangesBetweenGenerators() {
        List<IFractalGenerator> generators = new ZoomFractalGenerator(createBuilder(), 0.2, 0.25, 3).getGenerators();

        Color first = generators.get(0).computePixelColor(0, 0);
        Color last = generators.get(2).computePixelColor(0, 0);

        assertEquals(Color.WHITE, first);
        assertNotEquals(first, last);
    }

    /**
     * Checks that the first generators keep their own zoom, even if the
     * builder is modified afterwards to create the following ones.
     */
    @Test
    void testFirstGeneratorKeepsItsZoom() {
        FractalGeneratorBuilder builder = createBuilder();
        IFractalGenerator alone = builder.withZoomFactor(0.2).build();
        Color expected = alone.computePixelColor(0, 0);

        List<IFractalGenerator> generators = new ZoomFractalGenerator(builder, 0.2, 0.25, 3).getGenerators();

        assertEquals(expected, generators.get(0).computePixelColor(0, 0));
    }

    /**
     * Checks that all the generators produce an image of the same size.
     */
    @Test
    void testGeneratedImagesHaveTheSameSize() {
        List<IFractalGenerator> generators = new ZoomFractalGenerator(createBuilder(), 0.2, 0.5, 2).getGenerators();

        var first = generators.get(0).generateImage();
        var second = generators.get(1).generateImage();

        assertEquals(first.getWidth(), second.getWidth());
        assertEquals(first.getHeight(), second.getHeight());
    }
}
