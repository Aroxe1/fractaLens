
package fr.univartois.butinfo.fractalens.generator;

import fr.univartois.butinfo.fractalens.complex.IColorStrategy;
import fr.univartois.butinfo.fractalens.complex.IComplex;
import fr.univartois.butinfo.fractalens.complex.IComplexPlane;
import fr.univartois.butinfo.fractalens.complex.sequence.IComplexSequence;
import fr.univartois.butinfo.fractalens.image.BufferedImageAdapter;
import fr.univartois.butinfo.fractalens.image.IFractalImage;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.function.BiFunction;

/**
 * Generates a fractal image using a complex sequence.
 * @author Gautier Tinchon (implementation)
 */
public class FractalGenerator implements IFractalGenerator {

    /**
     * The strategy used to determine the color of a pixel.
     */
    private final IColorStrategy colorStrategy;

    /**
     * The height of the generated image, in pixels.
     */
    private final int height;

    /**
     * The width of the generated image, in pixels.
     */
    private final int width;

    /**
     * The maximum number of iterations to perform for a pixel.
     */
    private final int maxIterations;

    /**
     * The complex plane used to get the complex number of a pixel.
     */
    private final IComplexPlane plane;

    /**
     * The path of the file in which to save the image.
     */
    private final String outputFile;

    /**
     * The function creating the complex sequence from the complex number of a
     * pixel and the maximum number of iterations.
     */
    private final BiFunction<IComplex, Integer, IComplexSequence>
            sequenceFactory;

    /**
     * The generated image, or {@code null} if it has not been generated yet.
     */
    private IFractalImage image;

    /**
     * Creates a generator from the complete builder configuration.
     *
     * @param builder the builder containing the configuration
     */
    public FractalGenerator(FractalGeneratorBuilder builder) {
        this.width = builder.getWidth();
        this.height = builder.getHeight();
        this.maxIterations = builder.getMaxIterations();
        this.plane = builder.createPlane();
        this.colorStrategy = builder.getPalette();
        this.outputFile = builder.getOutputFile();
        this.sequenceFactory = builder.getSequenceFactory();
    }
    /**
     * Generates the fractal image by coloring each pixel.
     *
     * @return the generated image
     */
    @Override
    public IFractalImage generateImage() {
        BufferedImage bufferedImage = new BufferedImage(
                width, height, BufferedImage.TYPE_INT_RGB);

        image = new BufferedImageAdapter(bufferedImage);

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                Color color = computePixelColor(row, column);
                image.setColor(row, column, color);
            }
        }

        return image;
    }

    /**
     * Calculates the color of a pixel using the selected sequence.
     *
     * @param row row of the pixel
     * @param column column of the pixel
     * @return color assigned to the pixel
     */
    @Override
    public Color computePixelColor(int row, int column) {
        IComplex complex = plane.asComplex(row, column);

        IComplexSequence sequence =
                sequenceFactory.apply(complex, maxIterations);

        int iteration = 0;

        for (IComplex z : sequence) {
            iteration++;
        }

        return colorStrategy.getColor(iteration);
    }

    /**
     * Saves the generated fractal image.
     */
    @Override
    public void saveImage() {
        if (image == null) {
            generateImage();
        }

        try {
            image.saveAs(outputFile);
        } catch (IOException e) {
            throw new UncheckedIOException("Cannot save fractal image: " + outputFile, e);
        }
    }
}
