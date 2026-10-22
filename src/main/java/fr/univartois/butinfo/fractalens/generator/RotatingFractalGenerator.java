package fr.univartois.butinfo.fractalens.generator;

import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.IComplex;
import fr.univartois.butinfo.fractalens.image.IFractalImage;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Generates an animated rotation of a Julia fractal.
 */
public class RotatingFractalGenerator implements IFractalGenerator {

    private final AnimatedFractalGenerator animation;

    /**
     * Creates a rotation animation for a Julia fractal by generating
     * several frames with different complex constants.
     *
     * @param builder the configured fractal builder
     * @param numberFrames the number of frames in the animation
     * @param outputPath the output GIF path
     * @param delayMS the delay between frames in milliseconds
     * @throws IllegalArgumentException if the number of frames is not positive
     */
    public RotatingFractalGenerator(
            FractalGeneratorBuilder builder,
            int numberFrames,
            String outputPath,
            int delayMS) {

        if (numberFrames <= 0) {
            throw new IllegalArgumentException(
                    "The number of frames must be positive");
        }

        List<IFractalGenerator> generators = new ArrayList<>();

        double initialC = 0.7885;

        for (int k = 0; k < numberFrames; k++) {
            double theta = 2 * Math.PI * k / numberFrames;

            double re = initialC * Math.cos(theta);
            double im = initialC * Math.sin(theta);

            IComplex c = new Complex(re, im);

            FractalGenerator generator = builder
                    .withJuliaConstant(c)
                    .savingTo("frame-" + k + ".png")
                    .build();

            generators.add(generator);
        }

        this.animation = new AnimatedFractalGenerator(
                generators,
                outputPath,
                delayMS);
    }

    /**
     * Generates the image for the animation.
     *
     * @return the generated fractal image
     */
    @Override
    public IFractalImage generateImage() {
        return animation.generateImage();
    }

    /**
     * Computes the color of a pixel in the animation.
     *
     * @param row the row of the pixel
     * @param column the column of the pixel
     * @return the color of the pixel
     */
    @Override
    public Color computePixelColor(int row, int column) {
        return animation.computePixelColor(row, column);
    }

    /**
     * Saves the generated animation to the output file.
     */
    @Override
    public void saveImage() {
        animation.saveImage();
    }
}