package fr.univartois.butinfo.fractalens.complex;

import java.awt.Color;

public class InvertedColorDecorator implements IColorStrategy{
    /**
     * The color palette to decorate.
     */
    private IColorStrategy palette;

    /**
     * Creates a new instance of {@link InvertedColorDecorator}.
     *
     * @param palette the color palette to decorate
     */
    public InvertedColorDecorator(IColorStrategy palette,int maxIteration){
        this.palette = palette;
    }

    /**
     * Inverts the color obtained from the decorated palette.
     *
     * @param iteration the number of iterations before divergence
     * @return the inverted color, encoded as an integer
     */
    @Override
    public Color getColor(int iteration) {
        Color c = palette.getColor(iteration);
        return new Color(255 - c.getRed(),255 - c.getGreen(),255 - c.getBlue());
    }
}
