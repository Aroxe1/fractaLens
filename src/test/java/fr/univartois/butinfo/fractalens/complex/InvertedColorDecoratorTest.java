package fr.univartois.butinfo.fractalens.complex;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;

import org.junit.jupiter.api.Test;

/**
 * Tests inversion of colors provided by a color palette.
 */
class InvertedColorDecoratorTest {

    private IColorPalette invertedPalette(Color color) {
        IColorPalette source = iteration -> color.getRGB();
        return new InvertedColorDecorator(source);
    }

    /**
     * Checks that black is inverted to white.
     */
    @Test
    void testInvertsBlackToWhite() {
        assertEquals(Color.WHITE.getRGB(), invertedPalette(Color.BLACK).getColor(0));
    }

    /**
     * Checks that white is inverted to black.
     */
    @Test
    void testInvertsWhiteToBlack() {
        assertEquals(Color.BLACK.getRGB(), invertedPalette(Color.WHITE).getColor(0));
    }

    /**
     * Checks that each RGB component is inverted independently.
     */
    @Test
    void testInvertsEachComponentIndependently() {
        Color source = new Color(10, 100, 200);
        Color expected = new Color(245, 155, 55);

        assertEquals(expected.getRGB(), invertedPalette(source).getColor(0));
    }

    /**
     * Checks that red is inverted to cyan.
     */
    @Test
    void testInvertsRedToCyan() {
        assertEquals(Color.CYAN.getRGB(), invertedPalette(Color.RED).getColor(0));
    }

    /**
     * Checks that the iteration count is passed to the decorated palette.
     */
    @Test
    void testPassesIterationToPalette() {
        IColorPalette source = iteration -> iteration == 7
                ? new Color(20, 40, 60).getRGB()
                : Color.BLACK.getRGB();
        IColorPalette inverted = new InvertedColorDecorator(source);

        assertEquals(new Color(235, 215, 195).getRGB(), inverted.getColor(7));
    }
}
