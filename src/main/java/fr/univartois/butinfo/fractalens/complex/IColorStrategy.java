package fr.univartois.butinfo.fractalens.complex;

import java.awt.Color;
public interface IColorStrategy {

    /**
     * @param iteration number of iterations before divergence
     * @return the color of the pixel
     */
    Color getColor(int iteration);
}
