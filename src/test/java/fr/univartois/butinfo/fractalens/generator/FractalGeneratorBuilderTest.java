
package fr.univartois.butinfo.fractalens.generator;

import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.IComplex;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link FractalGeneratorBuilder} class.
 *
 * <p>These tests verify the configuration methods,
 * method chaining and fractal generator creation.</p>
 *
 * @see FractalGeneratorBuilder
 * @see FractalGenerator
 * @author Lylian Michel (unit tests)
 */
class FractalGeneratorBuilderTest {

    @Test
    void testCompleteConfiguration(@org.junit.jupiter.api.io.TempDir java.nio.file.Path directory)
            throws java.io.IOException {
        java.nio.file.Path output = directory.resolve("fractal.png");
        java.util.List<Integer> terms = new java.util.ArrayList<>();
        java.util.List<IComplex> points = new java.util.ArrayList<>();
        FractalGenerator generator = new FractalGeneratorBuilder()
                .withImageWidth(4).withImageHeight(2).withNumberOfTerms(7)
                .withZoomFactor(0.5).centeredAt(new Complex(-1, 1))
                .usingSequence((point, count) -> {
                    points.add(point);
                    terms.add(count);
                    return new fr.univartois.butinfo.fractalens.complex.sequence.MandelBrotSequence(point, count);
                })
                .withColorPalette(iteration -> java.awt.Color.RED)
                .savingTo(output.toString()).build();

        generator.saveImage();

        java.awt.image.BufferedImage image = javax.imageio.ImageIO.read(output.toFile());
        assertEquals(4, image.getWidth());
        assertEquals(2, image.getHeight());
        assertEquals(java.awt.Color.RED.getRGB(), image.getRGB(0, 0));
        assertEquals(8, points.size());
        assertEquals(-1, points.get(6).getRealPart(), 0.00001);
        assertEquals(1, points.get(6).getImaginaryPart(), 0.00001);
        assertTrue(terms.stream().allMatch(count -> count == 7));
    }

    @Test
    void testPaletteUsesFinalIterationCount() {
        FractalGenerator generator = new FractalGeneratorBuilder()
                .withImageWidth(2).withImageHeight(2)
                .withColorPalette(fr.univartois.butinfo.fractalens.complex.ColorPaletteType.GRAY)
                .usingSequence(fr.univartois.butinfo.fractalens.complex.sequence.ComplexSequenceType.MANDELBROT)
                .withZoomFactor(0.5).centeredAt(new Complex(0, 0))
                .savingTo("unused.png").withNumberOfTerms(7).build();

        assertEquals(java.awt.Color.BLACK, generator.computePixelColor(1, 1));
    }

    /**
     * Tests that setting the image width returns
     * the current builder instance.
     */
    @Test
    void testSetWidth() {
        FractalGeneratorBuilder builder =
                new FractalGeneratorBuilder();

        assertSame(builder, builder.withImageWidth(800));
    }

    /**
     * Tests that setting the image height returns
     * the current builder instance.
     */
    @Test
    void testSetHeight() {
        FractalGeneratorBuilder builder =
                new FractalGeneratorBuilder();

        assertSame(builder, builder.withImageHeight(600));
    }

    /**
     * Tests that setting the maximum number of iterations
     * returns the current builder instance.
     */
    @Test
    void testSetMaxIterations() {
        FractalGeneratorBuilder builder =
                new FractalGeneratorBuilder();

        assertSame(builder, builder.withNumberOfTerms(100));
    }

    /**
     * Tests that setting the complex constant returns
     * the current builder instance.
     */
    @Test
    void testSetC() {
        FractalGeneratorBuilder builder =
                new FractalGeneratorBuilder();

        IComplex c = null;

        assertSame(builder, builder.withJuliaConstant(c));
    }


    /**
     * Tests that the builder creates a fractal generator
     * using a valid complex constant.
     */
    @Test
    void testBuild() {
        IComplex c = new Complex(-0.7, 0.27015);

        FractalGenerator generator = new FractalGeneratorBuilder()
                .withImageWidth(800)
                .withImageHeight(600)
                .withNumberOfTerms(100)
                .withJuliaConstant(c)
                .withZoomFactor(0.005)
                .centeredAt(new Complex(0, 0))
                .withColorPalette(fr.univartois.butinfo.fractalens.complex.ColorPaletteType.GRAY)
                .savingTo("julia.png")
                .build();

        assertNotNull(generator);
    }

    /**
     * Tests that all configuration methods can be chained.
     */
    @Test
    void testMethodChaining() {
        FractalGeneratorBuilder builder =
                new FractalGeneratorBuilder();

        FractalGeneratorBuilder result = builder
                .withImageWidth(800)
                .withImageHeight(600)
                .withNumberOfTerms(100)
                .withJuliaConstant(null);

        assertSame(builder, result);
    }

    /**
     * Tests that two calls to build create
     * two different fractal generator instances.
     */
    @Test
    void testDifferentGenerators() {
        IComplex c = new Complex(-0.7, 0.27015);

        FractalGeneratorBuilder builder = new FractalGeneratorBuilder()
                .withImageWidth(800)
                .withImageHeight(600)
                .withNumberOfTerms(100)
                .withJuliaConstant(c)
                .withZoomFactor(0.005)
                .centeredAt(new Complex(0, 0))
                .withColorPalette(fr.univartois.butinfo.fractalens.complex.ColorPaletteType.GRAY)
                .savingTo("julia.png");

        FractalGenerator first = builder.build();
        FractalGenerator second = builder.build();

        assertNotNull(first);
        assertNotNull(second);
        assertNotSame(first, second);
    }

    @Test
    void testEverySequenceAndPalette() {
        for (var sequence : fr.univartois.butinfo.fractalens.complex.sequence.ComplexSequenceType.values()) {
            for (var palette : fr.univartois.butinfo.fractalens.complex.ColorPaletteType.values()) {
                FractalGenerator generator = new FractalGeneratorBuilder()
                        .usingSequence(sequence)
                        .withNumberOfTerms(10)
                        .withImageWidth(4)
                        .withImageHeight(4)
                        .withZoomFactor(0.5)
                        .centeredAt(new Complex(0, 0))
                        .withColorPalette(palette)
                        .savingTo("unused.png")
                        .build();

                assertNotNull(generator.generateImage(), sequence + " / " + palette);
            }
        }
    }

    @Test
    void testIncompleteConfigurationIsRejected() {
        assertThrows(IllegalStateException.class, () -> new FractalGeneratorBuilder()
                .withImageWidth(4)
                .withImageHeight(4)
                .withNumberOfTerms(10)
                .build());
    }
}


