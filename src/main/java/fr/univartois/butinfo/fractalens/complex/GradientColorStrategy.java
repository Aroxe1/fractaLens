package fr.univartois.butinfo.fractalens.complex;

import java.awt.Color;

/**
 * Interpolates between two colors according to the iteration count.
 */
public class GradientColorStrategy extends AbstractColorStrategy implements IColorStrategy {

    private final Color startColor;
    private final Color endColor;

    /**
     * Creates a gradient between two colors.
     *
     * @param startColor the color at zero iterations
     * @param endColor the color at the maximum number of iterations
     * @param maxIterations the iteration count at which the end color is reached
     * @throws IllegalArgumentException if {@code maxIterations} is not positive
     */
    public GradientColorStrategy(Color startColor, Color endColor, int maxIterations) {
        super(maxIterations);
        this.startColor = startColor;
        this.endColor = endColor;
        if (maxIterations <= 0) {
            throw new IllegalArgumentException("maxIterations must be positive.");
        }
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
        double ratio = (double) boundedIteration / maxIteration;
        return new Color(
                interpolate(startColor.getRed(), endColor.getRed(), ratio),
                interpolate(startColor.getGreen(), endColor.getGreen(), ratio),
                interpolate(startColor.getBlue(), endColor.getBlue(), ratio));
    }

    private static int interpolate(int start, int end, double ratio) {
        return (int) Math.round(start + ratio * (end - start));
    }
}
