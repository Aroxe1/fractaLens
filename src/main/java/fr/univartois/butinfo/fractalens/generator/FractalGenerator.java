package fr.univartois.butinfo.fractalens.generator;

import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.GrayScaleColorStrategy;
import fr.univartois.butinfo.fractalens.complex.IColorStrategy;
import fr.univartois.butinfo.fractalens.complex.IComplex;
import fr.univartois.butinfo.fractalens.complex.sequence.JuliaSequence;
import fr.univartois.butinfo.fractalens.image.BufferedImageAdapter;
import fr.univartois.butinfo.fractalens.image.IFractalImage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Generates a fractal image using the Julia set.
 */
public class FractalGenerator implements IFractalGenerator{

    private IColorStrategy colorStrategy = new GrayScaleColorStrategy();
    private int height = 600;
    private int width = 800;
    private int maxIterations = 100;
    private IComplex c = new Complex(-0.4, 0.6);
    private IFractalImage image;

    /**
     * Generates the fractal image by calculating the color of each pixel.
     *
     * @return the generated fractal image
     */
    @Override
    public IFractalImage generateImage() {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        image = new BufferedImageAdapter(bufferedImage);
        height = image.getHeight();
        width = image.getWidth();
        for(int row = 0; row < height; row++){
            for(int column = 0; column < width; column++){
                Color color = computePixelColor(row, column);
                image.setColor(row, column, color);
            }
        }
        return image;
    }

    /**
     * Calculates the color of a pixel based on its position and the number of iterations.
     *
     * @param row the row of the pixel
     * @param column the column of the pixel
     * @return the color of the pixel
     */
    @Override
    public Color computePixelColor(int row, int column) {
        double x = (double) column / width * 4 - 2;
        double y = (double) row / height * 4 - 2;

        IComplex complex = new Complex(x, y);

        JuliaSequence sequence =
                new JuliaSequence(complex, c, maxIterations);

        int iteration = 0;

        for (IComplex z : sequence) {
            iteration++;
        }

        return colorStrategy.getColor(iteration, maxIterations);
    }

    /**
     * Saves the generated fractal image to a file named Fractal.png.
     * Displays an error message if the image cannot be saved.
     */
    @Override
    public void saveImage() {
        try {
            image.saveAs("Fractal.png");
        }catch (IOException e){
            System.out.println("Error saving image");
            e.printStackTrace();
        }
    }
}