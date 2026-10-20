package fr.univartois.butinfo.fractalens;

import fr.univartois.butinfo.fractalens.complex.*;
import fr.univartois.butinfo.fractalens.generator.FractalGenerator;
import fr.univartois.butinfo.fractalens.generator.IFractalGenerator;

/**
 * Runs the Julia fractal demonstration for sprint 3.
 */
public class Main {

    /**
     * Creates and saves a Julia fractal image.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        int width = 600;
        int height = 800;
        int maxIterations = 100;
        double zoom = 0.004;

        // Apply zoom before translation so the translation remains the image center.
        IComplexPlane plane = new TranslatedComplexPlane(
                new ScaledComplexPlane(new ComplexPlane(width, height), zoom),
                new Complex(0, 0));
        IComplex c = new Complex(-0.4, 0.6);
        IFractalGenerator generator = new FractalGenerator(
                width, height, maxIterations, c, plane,
                new GrayScaleColorStrategy(maxIterations)
        );

        generator.generateImage();
        generator.saveImage();
        System.out.println("Julia fractal saved to: Fractal.png");
    }
}
