package fr.univartois.butinfo.fractalens.generator;


import fr.univartois.butinfo.fractalens.image.IFractalImage;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ZoomFractalGenerator  {
    /**
     * The generators created for the animation, from the first image (the
     * lowest zoom) to the last one (the highest zoom).
     */
    private final List<IFractalGenerator> generators;

    /**
     * Creates a new instance of {@code ZoomFractalGenerator}.
     *
     * @param builder The builder used to create the generators. Its zoom factor
     *        is modified for each generator, all its other properties are kept.
     * @param startZoom The zoom factor of the first generator.
     * @param factor The value by which the zoom is multiplied for each new
     *        generator (between 0 and 1, so that the image zooms in).
     * @param nbImages The number of generators to create.
     */
    public ZoomFractalGenerator(FractalGeneratorBuilder builder, double startZoom, double factor, int nbImages)  {
        this.generators = new ArrayList<>();
        double zoom = startZoom;
        for( int i = 0; i < nbImages; i++ ) {
            generators.add(builder.withZoomFactor(zoom).build());
            zoom *= factor;
        }
    }

    /**
     * Gives the generators created for the animation.
     *
     * @return The list of the generators, ordered from the lowest to the highest
     *         zoom.
     */
    public List<IFractalGenerator> getGenerators() {
        return generators;
    }


}
