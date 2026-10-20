package fr.univartois.butinfo.fractalens.complex;

import org.junit.jupiter.api.Test;
import java.awt.Color;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the grayscale color strategy.
 */
class GrayScaleColorStrategyTest {

    /**
     * Tests that the color is black when the iteration count reaches the maximum.
     */
    @Test
    void testGetColorBlack() {
        GrayScaleColorStrategy strategy = new GrayScaleColorStrategy();

        Color result = strategy.getColor(10, 10);

        assertEquals(Color.BLACK, result);
    }

    /**
     * Tests that the color is white when the iteration count is zero.
     */
    @Test
    void testGetColorWhite() {
        GrayScaleColorStrategy strategy = new GrayScaleColorStrategy();

        Color result = strategy.getColor(0, 10);

        assertEquals(Color.WHITE, result);
    }

    /**
     * Tests that the color is gray when the iteration count is half the maximum.
     */
    @Test
    void testGetColorGray() {
        GrayScaleColorStrategy strategy = new GrayScaleColorStrategy();

        Color result = strategy.getColor(5, 10);

        assertEquals(new Color(0.5f, 0.5f, 0.5f), result);
    }

    /**
     * Tests the result when the maximum number of iterations is zero.
     */
    @Test
    void testGetColorMaxIterationZero() {
        GrayScaleColorStrategy strategy = new GrayScaleColorStrategy();

        Color result = strategy.getColor(5, 0);

        assertEquals(Color.BLACK, result);
    }

    /**
     * Tests the result when the maximum number of iterations is negative.
     */
    @Test
    void testGetColorMaxIterationNegative() {
        GrayScaleColorStrategy strategy = new GrayScaleColorStrategy();

        Color result = strategy.getColor(5, -1);

        assertEquals(Color.BLACK, result);
    }

    /**
     * Tests the result when the iteration count exceeds the maximum.
     */
    @Test
    void testGetColorIterationAboveMaximum() {
        GrayScaleColorStrategy strategy = new GrayScaleColorStrategy();

        Color result = strategy.getColor(15, 10);

        assertEquals(Color.BLACK, result);
    }
}