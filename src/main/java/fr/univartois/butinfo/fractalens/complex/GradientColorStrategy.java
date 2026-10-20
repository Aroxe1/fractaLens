package fr.univartois.butinfo.fractalens.complex;

import java.awt.*;

public class GradientColorStrategy extends AbstractColorStrategy implements IColorStrategy{
    /**
     * The starting color of the gradient.
     */
    private Color color1;
    /**
     * The ending color of the gradient.
     */
    private Color color2;


    /**
     * Creates a new instance of {@code GradientColorStrategy}.
     *
     * @param color1 The starting color of the gradient.
     * @param color2 The ending color of the gradient.
     * @param maxIteration The maximum number of iterations.
     */
    public GradientColorStrategy(int maxIteration, Color color1, Color color2) {
        super(maxIteration);
        this.color1 = color1;
        this.color2 = color2;
    }

    /**
     * Determines the color to apply to a pixel, based on the number of
     * iterations performed.
     *
     * If the number of iterations exceeds the maximum, it is capped to the
     * maximum. If the maximum is zero or negative, the starting color is
     * returned.
     *
     * @param iteration The number of iterations performed.
     *
     * @return The color of the gradient corresponding to the pixel.
     */
    @Override
    public Color getColor(int iteration) {
        if (maxIteration <= 0) {
            return color1;
        }
        int bounded = Math.min(iteration, maxIteration);
        double ratio = (double) bounded / maxIteration;

        int red = (int) (color1.getRed() + ratio*(color2.getRed()-color1.getRed()));
        int green = (int) (color1.getGreen() + ratio*(color2.getGreen()- color1.getGreen()));
        int blue= (int) (color1.getBlue() + ratio*(color2.getBlue()- color1.getBlue()));
        return new Color(red, green, blue);
    }

}
