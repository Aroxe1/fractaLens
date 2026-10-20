package fr.univartois.butinfo.fractalens.complex;

import java.awt.Color;

/**
 * Decorates a color palette by inverting each RGB component.
 */
public class InvertedColorDecorator implements IColorPalette {
    /**
     * The color palette to decorate.
     */
    private final IColorPalette palette;

    /**
     * Creates a decorator for the given color palette.
     *
     * @param palette the color palette to decorate
     */
    public InvertedColorDecorator(IColorPalette palette) {
        this.palette = palette;
    }

    /**
     * Creates a decorator for the given color palette.
     *
     * @param palette the color palette to decorate
     * @param maxIteration retained for compatibility with existing callers
     */
    public InvertedColorDecorator(IColorPalette palette, int maxIteration) {
        this(palette);
    }

    /**
     * Inverts the color obtained from the decorated palette.
     *
     * @param iteration the number of iterations before divergence
     * @return the inverted color, encoded as an integer
     */
    @Override
    public int getColor(int iteration) {
        Color c = new Color(palette.getColor(iteration));
        return new Color(255 - c.getRed(),255 - c.getGreen(),255 - c.getBlue()).getRGB();
    }
}
