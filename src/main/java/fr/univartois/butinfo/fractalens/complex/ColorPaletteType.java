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
    },

    BLUEGRADIENT {
        @Override
        public IColorStrategy getColor(int iteration) {
            return new GradientColorStrategy(List.of(
                    new Color(0, 7, 100),      // bleu nuit
                    new Color(32, 107, 203),   // bleu électrique
                    new Color(237, 255, 255),  // blanc lumineux
                    new Color(255, 170, 0),    // orange doré
                    new Color(100, 20, 60),    // pourpre
                    new Color(0, 7, 100),      // bleu nuit
                    new Color(32, 107, 203),   // bleu électrique
                    new Color(237, 255, 255),  // blanc lumineux
                    new Color(255, 170, 0),    // orange doré
                    new Color(0, 0, 0)),       // noir : l'intérieur de la fractale
                    iteration);
        }
    },

    NEBULA {
        @Override
        public IColorStrategy getColor(int iteration) {
            return new GradientColorStrategy(List.of(
                    new Color(8, 0, 18),       // noir violacé
                    new Color(35, 5, 65),      // violet profond
                    new Color(85, 20, 135),    // violet
                    new Color(155, 45, 185),   // orchidée
                    new Color(225, 150, 255),  // lavande lumineuse
                    new Color(110, 25, 110),   // prune
                    new Color(35, 5, 65),      // violet profond
                    new Color(85, 20, 135),    // violet
                    new Color(155, 45, 185),   // orchidée
                    new Color(225, 150, 255),  // lavande lumineuse
                    new Color(0, 0, 0)),       // noir : l'intérieur de la fractale
                    iteration);
        }
    },

    SYNTHWAVE {
        @Override
        public IColorStrategy getColor(int iteration) {
            return new GradientColorStrategy(List.of(
                    new Color(10, 5, 35),      // bleu nuit
                    new Color(60, 10, 110),    // indigo
                    new Color(200, 20, 160),   // magenta
                    new Color(255, 70, 140),   // rose fluo
                    new Color(255, 170, 110),  // pêche
                    new Color(0, 230, 255),    // cyan électrique
                    new Color(25, 20, 90),     // bleu profond
                    new Color(200, 20, 160),   // magenta
                    new Color(255, 70, 140),   // rose fluo
                    new Color(0, 230, 255),    // cyan électrique
                    new Color(0, 0, 0)),       // noir : l'intérieur de la fractale
                    iteration);
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
