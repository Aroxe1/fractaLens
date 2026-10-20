package fr.univartois.butinfo.fractalens.complex;

import java.awt.Color;
import java.util.List;

/**
 * Applies a fixed color strategy to generate colors based on the iteration count.
 */
public class FixedColorStrategy implements IColorStrategy {

    private final List<Color> colors;
    private final int maxIteration;
    private final Color color;

    /**
     * Creates a strategy that selects a color from a repeating palette.
     *
     * @param colors the colors to cycle through, in order
     * @throws IllegalArgumentException if the palette is empty
     * @throws NullPointerException if the palette or any of its colors is null
     */
    public FixedColorStrategy(List<Color> colors) {
        this.colors = List.copyOf(colors);
        if (this.colors.isEmpty()) {
            throw new IllegalArgumentException("The color palette must not be empty.");
        }
        this.maxIteration = 0;
        this.color = null;
    }

    /**
     * Creates a new instance of {@link FixedColorStrategy}.
     *
     * @param maxIteration the maximum number of iterations
     * @param color the base color used to generate the final colors
     */
    public FixedColorStrategy(int maxIteration, Color color){
        this.colors = null;
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
        if (colors != null) {
            int index = Math.max(0, iteration) % colors.size();
            return colors.get(index);
        }
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