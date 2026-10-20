package fr.univartois.butinfo.fractalens.complex;

import java.awt.Color;

/**
 * Defines a strategy for choosing a color from an iteration count.
 */
public interface IColorStrategy {

    /**
     * Determines the color for the given number of iterations.
     *
     * @param iteration number of iterations before divergence
     * @return the color of the pixel
     */
    Color getColor(int iteration);
}
