package fr.univartois.butinfo.fractalens.generator;

import fr.univartois.butinfo.fractalens.complex.*;
import fr.univartois.butinfo.fractalens.complex.sequence.IComplexSequence;
import fr.univartois.butinfo.fractalens.complex.sequence.JuliaSequence;
import fr.univartois.butinfo.fractalens.image.BufferedImageAdapter;
import fr.univartois.butinfo.fractalens.image.IFractalImage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.function.BiFunction;

public class FractalGenerator implements IFractalGenerator{

    private IColorStrategy colorStrategy = new GrayScaleColorStrategy();
    private int height = 600;
    private int width = 800;
    private int maxIterations = 100;
    private IComplex c = new Complex(-0.4, 0.6);
    private IFractalImage image;

    public FractalGenerator(IFractalImage image, IComplexPlane plane, BiFunction<IComplex, Integer, IComplexSequence> sequenceFactory, GrayScaleColorStrategy grayScaleColorStrategy, int maxIterations, String outputPath) {

    }

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
