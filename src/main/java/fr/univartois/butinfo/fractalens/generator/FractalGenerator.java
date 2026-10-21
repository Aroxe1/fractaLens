
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
import java.util.function.BiFunction;

/**
 * Generates a fractal image using a complex sequence.
 */
public class FractalGenerator implements IFractalGenerator {

    private final IColorStrategy colorStrategy;
    private final int height;
    private final int width;
    private final int maxIterations;
    private final IComplexPlane plane;
    private final String outputFile;

    private final BiFunction<IComplex, Integer, IComplexSequence>
            sequenceFactory;

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
     * Creates a generator using a custom complex sequence.
     *
     * @param width width of the image
     * @param height height of the image
     * @param maxIterations maximum number of iterations
     * @param plane complex plane
     * @param colorStrategy color strategy
     * @param sequenceFactory factory used to create sequences
     */
    public FractalGenerator(
            int width,
            int height,
            int maxIterations,
            IComplexPlane plane,
            IColorStrategy colorStrategy,
            BiFunction<IComplex, Integer, IComplexSequence> sequenceFactory) {

        this(width, height, maxIterations, plane,
                colorStrategy, "Fractal.png", sequenceFactory);
    }

    /**
     * Creates a generator using a custom sequence and output file.
     *
     * @param width width of the image
     * @param height height of the image
     * @param maxIterations maximum number of iterations
     * @param plane complex plane
     * @param colorStrategy color strategy
     * @param outputFile name of the output file
     * @param sequenceFactory factory used to create sequences
     */
    public FractalGenerator(
            int width,
            int height,
            int maxIterations,
            IComplexPlane plane,
            IColorStrategy colorStrategy,
            String outputFile,
            BiFunction<IComplex, Integer, IComplexSequence> sequenceFactory) {

        this.width = width;
        this.height = height;
        this.maxIterations = maxIterations;
        this.plane = plane;
        this.colorStrategy = colorStrategy;
        this.outputFile = outputFile;
        this.sequenceFactory = sequenceFactory;
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
            e.printStackTrace();
        }
    }
}
