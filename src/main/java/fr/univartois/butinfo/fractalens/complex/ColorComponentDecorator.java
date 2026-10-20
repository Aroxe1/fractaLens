package fr.univartois.butinfo.fractalens.complex;

import java.awt.*;

public class ColorComponentDecorator implements IColorStrategy {
    /**
     * Whether the red component is kept.
     */
    private boolean red;
    /**
     * Whether the green component is kept.
     */
    private boolean green;
    /**
     * Whether the blue component is kept.
     */
    private boolean blue;

    /**
     * The decorated color strategy.
     */
    private IColorStrategy strategy;


    /**
     * Creates a new instance of {@code ColorComponentDecorator}.
     *
     * @param strategy The color strategy to decorate.
     * @param red Whether to keep the red component.
     * @param green Whether to keep the green component.
     * @param blue Whether to keep the blue component.
     */
    public ColorComponentDecorator(IColorStrategy strategy, boolean red, boolean green, boolean blue) {
        this.strategy = strategy;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    /**
     * Determines the color to apply to a pixel, by taking the color given by
     * the decorated strategy and setting to 0 the components that must not be
     * kept.
     *
     * @param iteration The number of iterations performed.
     *
     * @return The color obtained after keeping only the selected components.
     */
    @Override
    public Color getColor(int iteration) {
        Color color = strategy.getColor(iteration);
        int R=color.getRed();
        int G=color.getGreen();
        int B=color.getBlue();
        if(!red){
            R=0;
        }
        if(!green){
            G=0;
        }
        if(!blue){
            B=0;
        }
        return new Color(R,G,B);
    }

}
