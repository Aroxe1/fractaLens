package fr.univartois.butinfo.fractalens.complex;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;

import org.junit.jupiter.api.Test;

/**
 * Tests interpolation between the endpoint colors of a gradient.
 * @author Lylian Michel (unit tests)
 */
class GradientColorStrategyTest {

    private final GradientColorStrategy strategy =
            new GradientColorStrategy(new Color(0, 0, 0),
                    new Color(200, 100, 50), 100);

    /**
     * Checks the color at the beginning of the gradient.
     */
    @Test
    void testColorAtStartOfGradient() {
        assertEquals(new Color(0, 0, 0), strategy.getColor(0));
    }

    /**
     * Checks the interpolated color halfway through the gradient.
     */
    @Test
    void testColorAtHalfOfGradient() {
        assertEquals(new Color(100, 50, 25), strategy.getColor(50));
    }

    /**
     * Checks the color at the end of the gradient.
     */
    @Test
    void testColorAtEndOfGradient() {
        assertEquals(new Color(200, 100, 50), strategy.getColor(100));
    }

    /**
     * Checks that intermediate components are rounded to the nearest integer.
     */
    @Test
    void testIntermediateColorsAreRoundedToNearestInteger() {
        assertEquals(new Color(50, 25, 13), strategy.getColor(25));
        assertEquals(new Color(150, 75, 38), strategy.getColor(75));
    }
}
