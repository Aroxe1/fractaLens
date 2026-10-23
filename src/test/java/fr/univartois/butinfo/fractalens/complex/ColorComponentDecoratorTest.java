package fr.univartois.butinfo.fractalens.complex;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;

import org.junit.jupiter.api.Test;

/**
 * Tests that the color component decorator keeps only selected RGB components.
 * @author Lylian Michel (unit tests)
 */
class ColorComponentDecoratorTest {

    private static final Color SOURCE_COLOR = new Color(120, 200, 60);

    private IColorStrategy sourceStrategy() {
        return iteration -> SOURCE_COLOR;
    }

    @Test
    void testKeepsRedOnly() {
        IColorStrategy strategy =
                new ColorComponentDecorator(sourceStrategy(), true, false, false);

        assertEquals(new Color(120, 0, 0), strategy.getColor(0));
    }

    @Test
    void testKeepsGreenOnly() {
        IColorStrategy strategy =
                new ColorComponentDecorator(sourceStrategy(), false, true, false);

        assertEquals(new Color(0, 200, 0), strategy.getColor(0));
    }

    @Test
    void testKeepsBlueOnly() {
        IColorStrategy strategy =
                new ColorComponentDecorator(sourceStrategy(), false, false, true);

        assertEquals(new Color(0, 0, 60), strategy.getColor(0));
    }

    @Test
    void testKeepsCyanComponents() {
        IColorStrategy strategy =
                new ColorComponentDecorator(sourceStrategy(), false, true, true);

        assertEquals(new Color(0, 200, 60), strategy.getColor(0));
    }

    @Test
    void testKeepsMagentaComponents() {
        IColorStrategy strategy =
                new ColorComponentDecorator(sourceStrategy(), true, false, true);

        assertEquals(new Color(120, 0, 60), strategy.getColor(0));
    }

    @Test
    void testKeepsYellowComponents() {
        IColorStrategy strategy =
                new ColorComponentDecorator(sourceStrategy(), true, true, false);

        assertEquals(new Color(120, 200, 0), strategy.getColor(0));
    }
}
