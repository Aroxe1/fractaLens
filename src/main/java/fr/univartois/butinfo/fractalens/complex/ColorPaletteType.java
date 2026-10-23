package fr.univartois.butinfo.fractalens.complex;

import java.awt.*;
import java.util.List;

/**
 * Creates the predefined color palettes accepted by the command line.
 * @author Alice Demey (implementation)
 */
public enum ColorPaletteType {

    /**
     * The palette coloring pixels in grayscale.
     */
    GRAY{
        @Override
        public IColorStrategy getColor(int iteration) {
            return new GrayScaleColorStrategy(iteration);
        }
    },
    /**
     * The grayscale palette, keeping only the green and blue components
     * (which gives shades of cyan).
     */
    COMPONENT{
        @Override
        public IColorStrategy getColor(int iteration) {
            return new ColorComponentDecorator(new GrayScaleColorStrategy(iteration), false, true, true);
        }
    },
    /**
     * The palette producing a gradient from black to blue.
     */
    GRADIENT{
        @Override
        public IColorStrategy getColor(int iteration) {
            return new GradientColorStrategy(Color.BLACK,Color.BLUE, iteration);
        }
    },
    /**
     * The palette using a fixed list of colors (red, green and blue).
     */
    FIXED{
        @Override
        public IColorStrategy getColor(int iteration) {
            return new FixedColorStrategy(List.of(Color.RED, Color.GREEN, Color.BLUE));
        }
    },
    /**
     * The grayscale palette, with its colors inverted.
     */
    INVERTED{
        @Override
        public IColorStrategy getColor(int iteration) {
            return new InvertedColorDecorator(new GrayScaleColorStrategy(iteration));
        }
    };

    /**
     * Creates the color strategy associated with this palette.
     *
     * @param iteration The maximum number of iterations.
     *
     * @return The created color strategy.
     */
    public abstract IColorStrategy getColor(int iteration);

    /**
     * Creates the palette for the supplied iteration budget.
     * @param maxIterations the maximum number of iterations
     * @return the configured color strategy
     */
    public IColorStrategy create(int maxIterations) {
        return getColor(maxIterations);
    }
}
