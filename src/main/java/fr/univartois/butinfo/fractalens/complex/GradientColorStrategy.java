package fr.univartois.butinfo.fractalens.complex;

import java.awt.Color;
import java.util.List;
import java.util.Objects;

/**
 * Interpolates between two colors according to the iteration count.
 */
public class GradientColorStrategy extends AbstractColorStrategy implements IColorStrategy {
    /**
     * The colors of the palette, or {@code null} if this strategy uses a single
     * color.
     */

    private final List<Color> colors;

    /**
     * Creates a gradient between two colors.
     *
     * @param startColor the color at zero iterations
     * @param endColor the color at the maximum number of iterations
     * @param maxIterations the iteration count at which the end color is reached
     * @throws IllegalArgumentException if {@code maxIterations} is not positive
     */
    public GradientColorStrategy(Color startColor, Color endColor, int maxIterations) {
        this(List.of(startColor, endColor), maxIterations);
    }

    /**
     * Creates a gradient passing through all supplied colors in order.
     *
     * @param colors the colors defining the gradient, from its start to its end
     * @param maxIterations the iteration count at which the last color is reached
     * @throws IllegalArgumentException if fewer than two colors are supplied or
     *         {@code maxIterations} is not positive
     */
    public GradientColorStrategy(List<Color> colors, int maxIterations) {
        super(maxIterations);
        Objects.requireNonNull(colors, "colors");
        if (colors.size() < 2) {
            throw new IllegalArgumentException("At least two colors are required.");
        }
        if (maxIterations <= 0) {
            throw new IllegalArgumentException("maxIterations must be positive.");
        }
        this.colors = List.copyOf(colors);
    }

    /**
     * Returns the interpolated color for the given iteration count.
     *
     * @param iteration the number of iterations performed
     * @return the interpolated color
     */
    @Override
    public Color getColor(int iteration) {
        int boundedIteration = Math.clamp(iteration, 0, maxIteration);
        double position = (double) boundedIteration / maxIteration * (colors.size() - 1);
        int segment = Math.min((int) position, colors.size() - 2);
        double ratio = position - segment;
        Color startColor = colors.get(segment);
        Color endColor = colors.get(segment + 1);
        return new Color(
                interpolate(startColor.getRed(), endColor.getRed(), ratio),
                interpolate(startColor.getGreen(), endColor.getGreen(), ratio),
                interpolate(startColor.getBlue(), endColor.getBlue(), ratio));
    }

    private static int interpolate(int start, int end, double ratio) {
        return (int) Math.round(start + ratio * (end - start));
    }
}
