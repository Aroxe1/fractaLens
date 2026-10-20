package fr.univartois.butinfo.fractalens.complex;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;

import org.junit.jupiter.api.Test;

/**
 * Tests inversion of colors provided by a color strategy.
 */
class InvertedColorDecoratorTest {

    private IColorStrategy invertedStrategy(Color color) {
        IColorStrategy source = iteration -> color;
        return new InvertedColorDecorator(source);
    }

    /**
     * Checks that black is inverted to white.
     */
    @Test
    void testInvertsBlackToWhite() {
        assertEquals(Color.WHITE, invertedStrategy(Color.BLACK).getColor(0));
    }

    /**
     * Checks that white is inverted to black.
     */
    @Test
    void testInvertsWhiteToBlack() {
        assertEquals(Color.BLACK, invertedStrategy(Color.WHITE).getColor(0));
    }

    /**
     * Checks that each RGB component is inverted independently.
     */
    @Test
    void testInvertsEachComponentIndependently() {
        Color source = new Color(10, 100, 200);
        Color expected = new Color(245, 155, 55);

        assertEquals(expected, invertedStrategy(source).getColor(0));
    }

    /**
     * Checks that red is inverted to cyan.
     */
    @Test
    void testInvertsRedToCyan() {
        assertEquals(Color.CYAN, invertedStrategy(Color.RED).getColor(0));
    }

    /**
     * Checks that the iteration count is passed to the decorated palette.
     */
    @Test
    void testPassesIterationToPalette() {
        IColorStrategy source = iteration -> iteration == 7
                ? new Color(20, 40, 60)
                : Color.BLACK;
        IColorStrategy inverted = new InvertedColorDecorator(source);

        assertEquals(new Color(235, 215, 195), inverted.getColor(7));
    }
}
