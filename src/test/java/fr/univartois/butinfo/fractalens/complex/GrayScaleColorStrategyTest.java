
package fr.univartois.butinfo.fractalens.complex;

import org.junit.jupiter.api.Test;
import java.awt.Color;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GrayScaleColorStrategyTest {

    @Test
    void testGetColorBlack() {
        GrayScaleColorStrategy strategy = new GrayScaleColorStrategy();

        Color result = strategy.getColor(10, 10);

        assertEquals(Color.BLACK, result);
    }

    @Test
    void testGetColorWhite() {
        GrayScaleColorStrategy strategy = new GrayScaleColorStrategy();

        Color result = strategy.getColor(0, 10);

        assertEquals(Color.WHITE, result);
    }

    @Test
    void testGetColorGray() {
        GrayScaleColorStrategy strategy = new GrayScaleColorStrategy();

        Color result = strategy.getColor(5, 10);

        assertEquals(new Color(0.5f, 0.5f, 0.5f), result);
    }

    @Test
    void testGetColorMaxIterationZero() {
        GrayScaleColorStrategy strategy = new GrayScaleColorStrategy();

        Color result = strategy.getColor(5, 0);

        assertEquals(Color.BLACK, result);
    }

    @Test
    void testGetColorMaxIterationNegative() {
        GrayScaleColorStrategy strategy = new GrayScaleColorStrategy();

        Color result = strategy.getColor(5, -1);

        assertEquals(Color.BLACK, result);
    }

    @Test
    void testGetColorIterationAboveMaximum() {
        GrayScaleColorStrategy strategy = new GrayScaleColorStrategy();

        Color result = strategy.getColor(15, 10);

        assertEquals(Color.BLACK, result);
    }
}
