package fr.univartois.butinfo.fractalens;

import fr.univartois.butinfo.fractalens.complex.*;
import fr.univartois.butinfo.fractalens.generator.FractalGenerator;
import fr.univartois.butinfo.fractalens.generator.IFractalGenerator;

import java.awt.Color;
import java.io.IOException;

/**
 * Runs the Julia fractal demonstration for sprint 3.
 */
public class Main {

    private static final int MAX_ITERATIONS = 250;

    /**
     * Creates and saves two Julia fractal images with different centers and colors.
     *
     * @param args optional output filenames, in image order (zero or two arguments)
     * @throws IOException if an image cannot be saved
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 0 && args.length != 2) {
            throw new IllegalArgumentException("Usage: Main [first.png second.png]");
        }
        generateJulia(new Complex(-0.4, 0.6), new Complex(0.1, 0.6),
                new GradientColorStrategy(Color.BLUE, Color.white, MAX_ITERATIONS),
                args.length == 2 ? args[0] : "Julia-1.png");
        generateJulia(new Complex(0.285, 0.01), new Complex(-0.47, -0.21),
                new ColorComponentDecorator(
                        new InvertedColorDecorator(new GrayScaleColorStrategy(MAX_ITERATIONS)),
                        false, true, true),
                args.length == 2 ? args[1] : "Julia-2.png");
    }

    private static void generateJulia(IComplex c, IComplex center,
            IColorStrategy palette, String path) throws IOException {
        int width = 800;
        int height = 800;
        double zoom = 0.001;

        // Apply zoom before translation so the translation remains the image center.
        IComplexPlane plane = new TranslatedComplexPlane(
                new ScaledComplexPlane(new ComplexPlane(width, height), zoom),
                center);
        IFractalGenerator generator = new FractalGenerator(
                width, height, MAX_ITERATIONS, c, plane, palette
        );

        generator.generateImage().saveAs(path);
        System.out.println("Julia fractal saved to: " + path);
    }

}
