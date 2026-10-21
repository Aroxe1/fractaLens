
package fr.univartois.butinfo.fractalens.fractals;

import fr.univartois.butinfo.fractalens.complex.IComplex;
import fr.univartois.butinfo.fractalens.complex.IComplexSequence;

import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.*;

class FractalGeneratorBuilderTest {

    @Test
    void testBuilderCreatesGenerator() {
        FractalGenerator generator =
                new FractalGeneratorBuilder().build();

        assertNotNull(generator);
    }

    @Test
    void testImageSize() {
        FractalGeneratorBuilder builder =
                new FractalGeneratorBuilder();

        assertSame(builder, builder.withSize(800, 600));
    }

    @Test
    void testZoom() {
        FractalGeneratorBuilder builder =
                new FractalGeneratorBuilder();

        assertSame(builder, builder.withZoom(2.0));
    }

    @Test
    void testCenter() {
        FractalGeneratorBuilder builder =
                new FractalGeneratorBuilder();

        assertSame(builder, builder.withCenter(-0.5, 0.0));
    }

    @Test
    void testMaxIterations() {
        FractalGeneratorBuilder builder =
                new FractalGeneratorBuilder();

        assertSame(builder, builder.withMaxIterations(100));
    }

    @Test
    void testOutputFile() {
        FractalGeneratorBuilder builder =
                new FractalGeneratorBuilder();

        assertSame(builder,
                builder.withOutputFile("fractale.png"));
    }

    @Test
    void testCompleteConfiguration() {
        FractalGenerator generator =
                new FractalGeneratorBuilder()
                        .withSize(800, 600)
                        .withZoom(1.5)
                        .withCenter(-0.5, 0.0)
                        .withMaxIterations(150)
                        .withOutputFile("julia.png")
                        .build();

        assertNotNull(generator);
    }

    @Test
    void testDifferentGenerators() {
        FractalGeneratorBuilder builder =
                new FractalGeneratorBuilder();

        FractalGenerator first = builder.build();
        FractalGenerator second = builder.build();

        assertNotNull(first);
        assertNotNull(second);
        assertNotSame(first, second);
    }
}
