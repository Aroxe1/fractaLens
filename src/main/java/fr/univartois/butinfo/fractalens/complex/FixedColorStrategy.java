
package fr.univartois.butinfo.fractalens.complex;

import java.awt.Color;

/**
 * Applies a fixed color strategy.
 */
public class FixedColorStrategy implements IColorStrategy {

    private final Color color;

    /**
     * Creates a new instance of {@link FixedColorStrategy}.
     *
     * @param color the color to use
     */
    public FixedColorStrategy(Color color) {
        this.color = color;
    }

    /**
     * Returns the fixed color.
     *
     * @param iteration the current number of iterations
     * @return the chosen color
     */
    @Override
    public Color getColor(int iteration) {
        return color;
    }
}
