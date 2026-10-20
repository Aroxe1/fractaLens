package fr.univartois.butinfo.fractalens.generator;

import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.GrayScaleColorStrategy;
import fr.univartois.butinfo.fractalens.complex.IColorStrategy;
import fr.univartois.butinfo.fractalens.complex.IComplex;
import fr.univartois.butinfo.fractalens.complex.sequence.IComplexSequence;
import fr.univartois.butinfo.fractalens.complex.sequence.JuliaSequence;
import fr.univartois.butinfo.fractalens.image.BufferedImageAdapter;
import fr.univartois.butinfo.fractalens.image.IFractalImage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.function.BiFunction;

/**
 * Generates a fractal image using the Julia set.
 */
public class FractalGenerator implements IFractalGenerator {

    private BiFunction<IComplex, Integer, IComplexSequence> sequence;
    private IColorStrategy colorStrategy;
    private int height;
    private int width;
    private int maxIterations;
    private IComplex c;
    private IFractalImage image;


    /**
     * Creates a fractal generator with the specified dimensions and iteration limit.
     *
     * @param width width of the image
     * @param height height of the image
     * @param maxIterations maximum number of iterations for each pixel
     * @param c constant used in the Julia sequence
     */
    public FractalGenerator(int width, int height, int maxIterations,IComplex c) {
        this.width = width;
        this.height = height;
        this.maxIterations = maxIterations;
        this.c = c;
        this.colorStrategy = new GrayScaleColorStrategy(maxIterations);
        this.sequence = (z0, iterations) -> new JuliaSequence(z0, c, iterations);
    }

    /**
     * Generates the fractal image by calculating the color of each pixel.
     *
     * @return the generated fractal image
     */
    @Override
    public IFractalImage generateImage() {
        BufferedImage bufferedImage = new BufferedImage(
                width, height, BufferedImage.TYPE_INT_RGB
        );

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
     * Calculates the color of a pixel based on its position and the Julia sequence.
     *
     * @param row row of the pixel
     * @param column column of the pixel
     * @return color assigned to the pixel
     */
    @Override
    public Color computePixelColor(int row, int column) {
        double x = (double) column / width * 4 - 2;
        double y = (double) row / height * 4 - 2;

        IComplex complex = new Complex(x, y);

        IComplexSequence sequence = this.sequence.apply(complex, maxIterations);

        int iteration = 0;

        for (IComplex z : sequence) {
            iteration++;
        }

        return colorStrategy.getColor(maxIterations-iteration);
    }

    /**
     * Saves the generated fractal image to a file named Fractal.png.
     * Generates the image first if it has not already been created.
     */
    @Override
    public void saveImage() {
        if (image == null) {
            generateImage();
        }

        try {
            image.saveAs("Fractal.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}