package fr.univartois.butinfo.fractalens.generator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the RotatingFractalGenerator class.
 */
class RotatingFractalGeneratorTest {

    /**
     * Tests that the constructor rejects a non-positive number of frames.
     */
    @Test
    void testInvalidNumberOfFrames() {
        FractalGeneratorBuilder builder = new FractalGeneratorBuilder();

        assertThrows(
                IllegalArgumentException.class,
                () -> new RotatingFractalGenerator(
                        builder, 0, "animation.gif", 100)
        );
    }

    /**
     * Tests that the constructor rejects a negative number of frames.
     */
    @Test
    void testNegativeNumberOfFrames() {
        FractalGeneratorBuilder builder = new FractalGeneratorBuilder();

        assertThrows(
                IllegalArgumentException.class,
                () -> new RotatingFractalGenerator(
                        builder, -1, "animation.gif", 100)
        );
    }
}