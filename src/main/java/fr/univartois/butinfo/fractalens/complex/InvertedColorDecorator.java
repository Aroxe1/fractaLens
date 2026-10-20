package fr.univartois.butinfo.fractalens.complex;

import java.awt.Color;
import java.util.Objects;

/**
 * Decorates a color strategy by inverting each RGB component.
 */
public class InvertedColorDecorator implements IColorStrategy {

    private final IColorStrategy strategy;

    /**
     * Creates a decorator for the given color strategy.
     *
     * @param strategy the strategy to decorate
     */
    public InvertedColorDecorator(IColorStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy, "strategy must not be null");
    }

    /**
     * Returns the decorated color with each RGB component subtracted from 255.
     *
     * @param iteration the number of iterations performed
     * @return the inverted color
     */
    @Override
    public Color getColor(int iteration) {
        Color color = strategy.getColor(iteration);
        return new Color(
                255 - color.getRed(),
                255 - color.getGreen(),
                255 - color.getBlue());
    }
}
