package fr.univartois.butinfo.fractalens.complex;

import java.awt.Color;

/**
 * Decorates a color palette by keeping only selected RGB components.
 */
public class ColorComponentDecorator implements IColorPalette {
    /**
     * Whether the red component is kept.
     */
    private final boolean red;
    /**
     * Whether the green component is kept.
     */
    private final boolean green;
    /**
     * Whether the blue component is kept.
     */
    private final boolean blue;

    /**
     * The decorated color strategy.
     */
    private final IColorPalette palette;


    /**
     * Creates a new instance of {@code ColorComponentDecorator}.
     *
     * @param palette The color palette to decorate.
     * @param red Whether to keep the red component.
     * @param green Whether to keep the green component.
     * @param blue Whether to keep the blue component.
     */
    public ColorComponentDecorator(IColorPalette palette, boolean red, boolean green, boolean blue) {
        this.palette = palette;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    /**
     * Keeps selected RGB components and sets the others to zero.
     *
     * @param iteration The number of iterations performed.
     *
     * @return The color as an RGB integer.
     */
    @Override
    public int getColor(int iteration) {
        Color color = new Color(palette.getColor(iteration), true);
        int r=color.getRed();
        int g=color.getGreen();
        int b=color.getBlue();
        if(!red){
            r=0;
        }
        if(!green){
            g=0;
        }
        if(!blue){
            b=0;
        }
        return new Color(r, g, b).getRGB();
    }

}
