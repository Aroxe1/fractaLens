package fr.univartois.butinfo.fractalens.complex;
import java.awt.Color;

/**
 * Maps the iteration count to grayscale values.
 * @author Alice Demey (implementation)
 */
public class GrayScaleColorStrategy extends AbstractColorStrategy implements IColorStrategy {

    /**
     * Creates a new instance of {@link GrayScaleColorStrategy}.
     *
     * @param maxIteration the maximum number of iterations
     */
    public GrayScaleColorStrategy(int maxIteration) {
        super(maxIteration);
    }
    /**
     * Determines the grayscale color to apply to a pixel, based on the number of
     * iterations performed before the sequence diverges.
     *
     * If the number of iterations exceeds the maximum, it is capped to the
     * maximum. If the maximum is zero or negative, black is returned.
     *
     * @param iteration The number of iterations performed.
     *
     * @return The grayscale color corresponding to the pixel.
     */
    @Override
    public Color getColor(int iteration) {
        if(maxIteration <= 0){
            return Color.BLACK;
        }

        int bounded = Math.min(iteration, maxIteration);
        double ratio=(double) bounded/maxIteration;
        float gray = (float) (1-ratio);

        return new Color(gray, gray, gray);
    }

}