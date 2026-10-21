package fr.univartois.butinfo.fractalens.generator;

import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.GrayScaleColorStrategy;
import fr.univartois.butinfo.fractalens.complex.IColorStrategy;
import fr.univartois.butinfo.fractalens.complex.IComplex;
import fr.univartois.butinfo.fractalens.complex.IComplexPlane;
import fr.univartois.butinfo.fractalens.complex.sequence.IComplexSequence;
import fr.univartois.butinfo.fractalens.complex.sequence.JuliaSequence;
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
     * Creates a Julia fractal generator with default settings.
     *
     * @param width width of the image
     * @param height height of the image
     * @param maxIterations maximum number of iterations
     * @param c constant used in the Julia sequence
     */
    public FractalGenerator(int width, int height,
                            int maxIterations, IComplex c) {
        this(width, height, maxIterations, c, "Fractal.png");
    }

    /**
     * Creates a Julia fractal generator with a custom output file.
     *
     * @param width width of the image
     * @param height height of the image
     * @param maxIterations maximum number of iterations
     * @param c constant used in the Julia sequence
     * @param outputFile name of the output file
     */
    public FractalGenerator(int width, int height,
                            int maxIterations, IComplex c,
                            String outputFile) {
        this(width, height, maxIterations, c,
                (row, column) -> new Complex(
                        (double) column / width * 4 - 2,
                        (double) row / height * 4 - 2),
                new GrayScaleColorStrategy(maxIterations),
                outputFile);
    }

    /**
     * Creates a Julia fractal generator with a custom plane and palette.
     *
     * @param width width of the image
     * @param height height of the image
     * @param maxIterations maximum number of iterations
     * @param c constant used in the Julia sequence
     * @param plane complex plane
     * @param colorStrategy color strategy
     */
    public FractalGenerator(int width, int height,
                            int maxIterations, IComplex c,
                            IComplexPlane plane,
                            IColorStrategy colorStrategy) {
        this(width, height, maxIterations, c, plane,
                colorStrategy, "Fractal.png");
    }

    /**
     * Creates a Julia generator with a custom plane, palette and file.
     *
     * @param width width of the image
     * @param height height of the image
     * @param maxIterations maximum number of iterations
     * @param c constant used in the Julia sequence
     * @param plane complex plane
     * @param colorStrategy color strategy
     * @param outputFile name of the output file
     */
    public FractalGenerator(int width, int height,
                            int maxIterations, IComplex c,
                            IComplexPlane plane,
                            IColorStrategy colorStrategy,
                            String outputFile) {
        this(width, height, maxIterations, plane,
                colorStrategy, outputFile, juliaFactory(c));
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
     * Creates a Julia sequence factory.
     *
     * @param c constant used in the Julia sequence
     * @return the sequence factory
     */
    private static BiFunction<IComplex, Integer, IComplexSequence>
    juliaFactory(IComplex c) {
        return (z0, nbTerms) -> new JuliaSequence(z0, c, nbTerms);
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