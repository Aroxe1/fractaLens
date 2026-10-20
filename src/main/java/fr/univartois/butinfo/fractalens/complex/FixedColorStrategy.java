package fr.univartois.butinfo.fractalens.complex;

import java.awt.*;

/**
 * Applies a fixed color strategy to generate colors based on the iteration count.
 */
public class FixedColorStrategy implements IColorStrategy {

    /**
     * The maximum number of iterations used to calculate the color intensity.
     */
    private int maxIteration;
    private Color color;

    /**
     * Creates a new instance of {@link FixedColorStrategy}.
     *
     * @param maxIteration the maximum number of iterations
     * @param color the base color used to generate the final colors
     */
    public FixedColorStrategy(int maxIteration, Color color){
        this.maxIteration = maxIteration;
        this.color = color;
    }

    /**
     * Calculates the color based on the iteration count and the base color.
     *
     * @param iteration the current number of iterations
     * @return the calculated color, or black if the maximum number of iterations is invalid
     */
    @Override
    public Color getColor(int iteration) {
        if(maxIteration<=0){
            return Color.BLACK;
        }
        int bounded = Math.clamp(iteration, 0, maxIteration);
        double ratio = (double) bounded / maxIteration;

        float red = (float) (color.getRed()/255.0);
        float green = (float) (color.getGreen()/255.0);
        float blue = (float) (color.getBlue()/255.0);

        float finred =  (float) (red*ratio);
        float finblue =  (float) (blue*ratio);
        float fingreen =  (float) (green*ratio);

        return new Color(finred,fingreen,finblue);
    }
}