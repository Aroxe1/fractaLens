
package fr.univartois.butinfo.fractalens.complex;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;

import org.junit.jupiter.api.Test;

/**
 * Tests the grayscale color strategy.
 * @author Gautier Tinchon (unit tests)
 */
class GrayScaleColorStrategyTest {

    /**
     * Checks the color with zero iterations.
     */
    @Test
    void testGetColorWhite() {
        GrayScaleColorStrategy strategy =
                new GrayScaleColorStrategy(100);

        assertEquals(Color.WHITE, strategy.getColor(0));
    }

    /**
     * Checks the color with the maximum iterations.
     */
    @Test
    void testGetColorBlack() {
        GrayScaleColorStrategy strategy =
                new GrayScaleColorStrategy(100);

        assertEquals(Color.BLACK, strategy.getColor(100));
    }

    /**
     * Checks the color with half the iterations.
     */
    @Test
    void testGetColorGray() {
        GrayScaleColorStrategy strategy =
                new GrayScaleColorStrategy(100);

        Color expected = new Color(128, 128, 128);

        assertEquals(expected, strategy.getColor(50));
    }

    /**
     * Checks that iterations cannot exceed the maximum.
     */
    @Test
    void testGetColorAboveMaximum() {
        GrayScaleColorStrategy strategy =
                new GrayScaleColorStrategy(100);

        assertEquals(Color.BLACK, strategy.getColor(150));
    }

    /**
     * Checks the color when the maximum is zero.
     */
    @Test
    void testGetColorMaxIterationZero() {
        GrayScaleColorStrategy strategy =
                new GrayScaleColorStrategy(0);

        assertEquals(Color.BLACK, strategy.getColor(50));
    }

    /**
     * Checks the color when the maximum is negative.
     */
    @Test
    void testGetColorMaxIterationNegative() {
        GrayScaleColorStrategy strategy =
                new GrayScaleColorStrategy(-10);

        assertEquals(Color.BLACK, strategy.getColor(50));
    }
}
