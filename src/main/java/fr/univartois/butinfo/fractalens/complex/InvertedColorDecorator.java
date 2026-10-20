package fr.univartois.butinfo.fractalens.complex;

import java.awt.Color;

public class InvertedColorDecorator implements IColorStrategy {
    /**
     * The color palette to decorate.
     */
    private final IColorStrategy strategy;

    /**
     * Creates a new instance of {@link InvertedColorDecorator}.
     *
     * @param strategy the color strategy to decorate
     */
    public InvertedColorDecorator(IColorStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Inverts the color obtained from the decorated strategy.
     *
     * @param iteration the number of iterations before divergence
     * @return the inverted color
     */
    @Override
    public Color getColor(int iteration) {
        Color color = strategy.getColor(iteration);
        return new Color(255 - color.getRed(),
                255 - color.getGreen(),
                255 - color.getBlue());
    }
}
