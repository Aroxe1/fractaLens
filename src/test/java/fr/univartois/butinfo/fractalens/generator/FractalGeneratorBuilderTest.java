
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
 */
class FractalGeneratorBuilderTest {

    @Test
    void testCompleteConfiguration(@org.junit.jupiter.api.io.TempDir java.nio.file.Path directory)
            throws java.io.IOException {
        java.nio.file.Path output = directory.resolve("fractal.png");
        java.util.List<Integer> terms = new java.util.ArrayList<>();
        java.util.List<IComplex> points = new java.util.ArrayList<>();
        FractalGenerator generator = new FractalGeneratorBuilder()
                .setWidth(4).setHeight(2).setMaxIterations(7)
                .setZoom(0.5).setCenter(new Complex(-1, 1))
                .setSequence((point, count) -> {
                    points.add(point);
                    terms.add(count);
                    return new fr.univartois.butinfo.fractalens.complex.sequence.MandelBrotSequence(point, count);
                })
                .setPalette(iteration -> java.awt.Color.RED)
                .setOutputFile(output.toString()).build();

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
                .setWidth(2).setHeight(2)
                .setPalette(fr.univartois.butinfo.fractalens.complex.ColorPaletteType.GRAYSCALE)
                .setSequence(fr.univartois.butinfo.fractalens.complex.sequence.ComplexSequenceType.MANDELBROT)
                .setZoom(0.5).setCenter(new Complex(0, 0))
                .setOutputFile("unused.png").setMaxIterations(7).build();

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

        assertSame(builder, builder.setWidth(800));
    }

    /**
     * Tests that setting the image height returns
     * the current builder instance.
     */
    @Test
    void testSetHeight() {
        FractalGeneratorBuilder builder =
                new FractalGeneratorBuilder();

        assertSame(builder, builder.setHeight(600));
    }

    /**
     * Tests that setting the maximum number of iterations
     * returns the current builder instance.
     */
    @Test
    void testSetMaxIterations() {
        FractalGeneratorBuilder builder =
                new FractalGeneratorBuilder();

        assertSame(builder, builder.setMaxIterations(100));
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

        assertSame(builder, builder.setC(c));
    }


    /**
     * Tests that the builder creates a fractal generator
     * using a valid complex constant.
     */
    @Test
    void testBuild() {
        IComplex c = new Complex(-0.7, 0.27015);

        FractalGenerator generator = new FractalGeneratorBuilder()
                .setWidth(800)
                .setHeight(600)
                .setMaxIterations(100)
                .setC(c)
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
                .setWidth(800)
                .setHeight(600)
                .setMaxIterations(100)
                .setC(null);

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
                .setWidth(800)
                .setHeight(600)
                .setMaxIterations(100)
                .setC(c);

        FractalGenerator first = builder.build();
        FractalGenerator second = builder.build();

        assertNotNull(first);
        assertNotNull(second);
        assertNotSame(first, second);
    }
}
