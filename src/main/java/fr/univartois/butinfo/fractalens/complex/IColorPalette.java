package fr.univartois.butinfo.fractalens.complex;

import java.awt.Color;

public interface IColorPalette {

    /**
     * Gets the color associated with the given number of iterations.
     *
     * @param iteration the number of iterations before divergence
     * @return the color of the pixel, encoded as an integer
     */
    int getColor(int iteration);
}
