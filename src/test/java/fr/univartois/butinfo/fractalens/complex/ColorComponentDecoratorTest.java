package fr.univartois.butinfo.fractalens.complex;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;

import org.junit.jupiter.api.Test;

/**
 * Tests that the color component decorator keeps only selected RGB components.
 */
class ColorComponentDecoratorTest {

    private static final Color SOURCE_COLOR = new Color(120, 200, 60);

    private IColorPalette sourcePalette() {
        return iteration -> SOURCE_COLOR.getRGB();
    }

    @Test
    void testKeepsRedOnly() {
        IColorPalette strategy =
                new ColorComponentDecorator(sourcePalette(), true, false, false);

        assertEquals(new Color(120, 0, 0).getRGB(), strategy.getColor(0));
    }

    @Test
    void testKeepsGreenOnly() {
        IColorPalette strategy =
                new ColorComponentDecorator(sourcePalette(), false, true, false);

        assertEquals(new Color(0, 200, 0).getRGB(), strategy.getColor(0));
    }

    @Test
    void testKeepsBlueOnly() {
        IColorPalette strategy =
                new ColorComponentDecorator(sourcePalette(), false, false, true);

        assertEquals(new Color(0, 0, 60).getRGB(), strategy.getColor(0));
    }

    @Test
    void testKeepsCyanComponents() {
        IColorPalette strategy =
                new ColorComponentDecorator(sourcePalette(), false, true, true);

        assertEquals(new Color(0, 200, 60).getRGB(), strategy.getColor(0));
    }

    @Test
    void testKeepsMagentaComponents() {
        IColorPalette strategy =
                new ColorComponentDecorator(sourcePalette(), true, false, true);

        assertEquals(new Color(120, 0, 60).getRGB(), strategy.getColor(0));
    }

    @Test
    void testKeepsYellowComponents() {
        IColorPalette strategy =
                new ColorComponentDecorator(sourcePalette(), true, true, false);

        assertEquals(new Color(120, 200, 0).getRGB(), strategy.getColor(0));
    }
}
