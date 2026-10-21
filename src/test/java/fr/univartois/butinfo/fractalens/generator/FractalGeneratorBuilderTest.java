
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
