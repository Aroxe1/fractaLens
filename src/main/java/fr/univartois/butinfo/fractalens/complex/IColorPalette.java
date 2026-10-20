package fr.univartois.butinfo.fractalens.complex;

/**
 * Provides a color for an iteration count, encoded as an RGB integer.
 */
public interface IColorPalette {

    /**
     * Gets the color associated with the given number of iterations.
     *
     * @param iteration the number of iterations before divergence
     * @return the color of the pixel, encoded as an integer
     */
    int getColor(int iteration);

    /**
     * Adapts a color strategy to a palette.
     *
     * @param strategy the strategy to adapt
     * @return a palette that returns the strategy colors encoded as RGB integers
     */
    static IColorPalette fromStrategy(IColorStrategy strategy) {
        return iteration -> strategy.getColor(iteration).getRGB();
    }
}
