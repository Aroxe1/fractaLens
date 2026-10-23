package fr.univartois.butinfo.fractalens.generator;


import fr.univartois.butinfo.fractalens.image.IFractalImage;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Generates an animation with progressively smaller complex-plane scales.
 * @author Alice Demey (implementation)
 */
public class ZoomFractalGenerator implements IFractalGenerator {
    /**
     * The generators created for the animation, from the first image (the
     * lowest zoom) to the last one (the highest zoom).
     */
    private final IFractalGenerator animation;
    List<IFractalGenerator> generators = new ArrayList<>();


    /**
     * Creates a new instance of {@code ZoomFractalGenerator}.
     *
     * @param builder The builder used to create the generators. Its zoom factor
     *        is modified for each generator, all its other properties are kept.
     * @param startZoom The zoom factor of the first generator.
     * @param factor The value by which the zoom is multiplied for each new
     *        generator (between 0 and 1, so that the image zooms in).
     * @param nbImages The number of generators to create.
     * @param outputPath the path of the animated GIF
     * @param delayMS the duration of each frame in milliseconds
     */
    public ZoomFractalGenerator(FractalGeneratorBuilder builder, double startZoom, double factor, int nbImages, String outputPath, int delayMS)  {
        if (startZoom <= 0 || factor <= 0 || factor >= 1 || nbImages <= 0) {
            throw new IllegalArgumentException("Invalid zoom parameters");
        }
        double zoom = startZoom;
        for( int i = 0; i < nbImages; i++ ) {
            generators.add(builder.withZoomFactor(zoom).build());
            zoom *= factor;
        }
        this.animation = new AnimatedFractalGenerator(generators, outputPath, delayMS);
    }

    /**
     * Generates a full fractal image.
     *
     * @return The generated image.
     */
    @Override
    public IFractalImage generateImage() {
        return animation.generateImage();
    }

    /**
     * Computes the color of a given pixel. This method iterates of the sequence
     * considered by this generator to evaluate the number of iterations
     * required to decide whether this sequence diverges.
     *
     * @param row The row of the pixel.
     * @param column The column of the pixel.
     *
     * @return The computed color for the pixel.
     */
    @Override
    public Color computePixelColor(int row, int column) {
        return animation.computePixelColor(row, column);
    }

    /**
     * Saves the generated image in the output file.
     */
    @Override
    public void saveImage() {
        animation.saveImage();
    }

}
