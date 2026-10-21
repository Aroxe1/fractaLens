package fr.univartois.butinfo.fractalens.complex;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import java.awt.Color;

import org.junit.jupiter.api.Test;


class ColorPaletteTypeTest {

    /**
     * The maximum number of iterations used in the tests.
     */
    private static final int MAX = 100;

    /**
     * Checks that every constant creates a non-null strategy.
     */
    @Test
    void testAllPalettesCreateAStrategy() {
        for (ColorPaletteType type : ColorPaletteType.values()) {
            assertNotNull(type.getColor(MAX), type.name());
        }
    }

    /**
     * Checks that every call creates a new strategy.
     */
    @Test
    void testEachCallCreatesANewStrategy() {
        for (ColorPaletteType type : ColorPaletteType.values()) {
            assertNotSame(type.getColor(MAX), type.getColor(MAX), type.name());
        }
    }

    /**
     * Checks the type of the strategy created by each constant.
     */
    @Test
    void testStrategyTypes() {
        assertInstanceOf(GrayScaleColorStrategy.class, ColorPaletteType.GRAY.getColor(MAX));
        assertInstanceOf(ColorComponentDecorator.class, ColorPaletteType.COMPONENT.getColor(MAX));
        assertInstanceOf(GradientColorStrategy.class, ColorPaletteType.GRADIENT.getColor(MAX));
        assertInstanceOf(FixedColorStrategy.class, ColorPaletteType.FIXED.getColor(MAX));
        assertInstanceOf(InvertedColorDecorator.class, ColorPaletteType.INVERTED.getColor(MAX));
    }

    /**
     * Checks the colors of the grayscale palette.
     */
    @Test
    void testGray() {
        IColorStrategy strategy = ColorPaletteType.GRAY.getColor(MAX);

        assertEquals(Color.WHITE, strategy.getColor(0));
        assertEquals(Color.BLACK, strategy.getColor(MAX));
    }

    /**
     * Checks that the component palette only keeps the green and blue
     * components (cyan).
     */
    @Test
    void testComponent() {
        IColorStrategy strategy = ColorPaletteType.COMPONENT.getColor(MAX);

        assertEquals(new Color(0, 255, 255), strategy.getColor(0));
        assertEquals(Color.BLACK, strategy.getColor(MAX));
    }

    /**
     * Checks the colors of the gradient from black to blue.
     */
    @Test
    void testGradient() {
        IColorStrategy strategy = ColorPaletteType.GRADIENT.getColor(MAX);

        assertEquals(Color.BLACK, strategy.getColor(0));
        assertEquals(Color.BLUE, strategy.getColor(MAX));
    }

    /**
     * Checks that the inverted palette gives the opposite of the grayscale
     * palette.
     */
    @Test
    void testInverted() {
        IColorStrategy strategy = ColorPaletteType.INVERTED.getColor(MAX);

        assertEquals(Color.BLACK, strategy.getColor(0));
        assertEquals(Color.WHITE, strategy.getColor(MAX));
    }
}
