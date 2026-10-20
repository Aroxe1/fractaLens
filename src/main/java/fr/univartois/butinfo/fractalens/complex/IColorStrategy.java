package fr.univartois.butinfo.fractalens.complex;

import java.awt.Color;
public interface IColorStrategy {

    /**
     * @param iterations    nombre d'itérations avant divergence
     * @param maxIterations nombre maximum d'itérations
     * @return la couleur du pixel
     */
    Color getColor(int iteration, int maxIteration);
}
