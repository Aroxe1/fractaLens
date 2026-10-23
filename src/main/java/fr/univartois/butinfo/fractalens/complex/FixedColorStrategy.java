
package fr.univartois.butinfo.fractalens.complex;

import java.awt.Color;
import java.util.List;

/**
 * Selects a color from a fixed palette according to the iteration count.
 */
public class FixedColorStrategy implements IColorStrategy {
    /**
     * The colors of the palette, or {@code null} if this strategy uses a single
     * color.
     */
    private final List<Color> colors;

    /**
     * The single color used for every iteration, or {@code null} if this
     * strategy uses a palette.
     */
    private final Color color;

    /**
     * Creates a strategy that cycles through the provided palette.
     *
     * @param colors the palette colors, in order
     * @throws IllegalArgumentException if the palette is empty
     */
    public FixedColorStrategy(List<Color> colors) {
        if (colors.isEmpty()) {
            throw new IllegalArgumentException("The color palette must not be empty.");
        }
        this.colors = List.copyOf(colors);
        this.color = null;
    }

    /**
     * Creates a new instance of {@link FixedColorStrategy}.
     *
     * @param color the single color to use for every iteration
     */
    public FixedColorStrategy(Color color) {
        this.colors = null;
        this.color = color;
    }

    /**
     * Returns the color selected for the given iteration.
     *
     * @param iteration the current number of iterations
     * @return the selected color; palettes repeat from the beginning
     */
    @Override
    public Color getColor(int iteration) {
        if (colors != null) {
            int index = Math.max(0, iteration) % colors.size();
            return colors.get(index);
        }
        return color;
    }
}
