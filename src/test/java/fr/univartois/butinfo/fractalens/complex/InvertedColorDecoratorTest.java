package fr.univartois.butinfo.fractalens.complex;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;

import org.junit.jupiter.api.Test;

/**
 * Tests RGB inversion by the color strategy decorator.
 */
class InvertedColorDecoratorTest {

    private IColorStrategy strategyReturning(Color color) {
        return iteration -> color;
    }

    /**
     * Checks that black is inverted to white.
     */
    @Test
    void testInvertsBlackToWhite() {
        IColorStrategy strategy = new InvertedColorDecorator(
                strategyReturning(new Color(0, 0, 0)));

        assertEquals(new Color(255, 255, 255), strategy.getColor(0));
    }

    /**
     * Checks that white is inverted to black.
     */
    @Test
    void testInvertsWhiteToBlack() {
        IColorStrategy strategy = new InvertedColorDecorator(
                strategyReturning(new Color(255, 255, 255)));

        assertEquals(new Color(0, 0, 0), strategy.getColor(0));
    }

    /**
     * Checks that each RGB component is inverted independently.
     */
    @Test
    void testInvertsEachComponentIndependently() {
        IColorStrategy strategy = new InvertedColorDecorator(
                strategyReturning(new Color(10, 100, 200)));

        assertEquals(new Color(245, 155, 55), strategy.getColor(0));
    }

    /**
     * Checks that red is inverted to cyan.
     */
    @Test
    void testInvertsRedToCyan() {
        IColorStrategy strategy = new InvertedColorDecorator(
                strategyReturning(Color.RED));

        assertEquals(Color.CYAN, strategy.getColor(0));
    }
}
