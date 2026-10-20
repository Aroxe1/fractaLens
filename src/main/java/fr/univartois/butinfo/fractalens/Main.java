package fr.univartois.butinfo.fractalens;

import java.awt.image.BufferedImage;
import java.util.function.BiFunction;

import fr.univartois.butinfo.fractalens.complex.*;
import fr.univartois.butinfo.fractalens.complex.sequence.IComplexSequence;
import fr.univartois.butinfo.fractalens.complex.sequence.JuliaSequence;
import fr.univartois.butinfo.fractalens.generator.FractalGenerator;
import fr.univartois.butinfo.fractalens.generator.IFractalGenerator;
import fr.univartois.butinfo.fractalens.image.BufferedImageAdapter;
import fr.univartois.butinfo.fractalens.image.IFractalImage;

/**
 * Runs the Julia fractal demonstration for sprint 2.
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
        String outputPath = "julia.png";

        // Create the complex plane with a zoom factor.
        IComplexPlane plane = new ScaledComplexPlane(new ComplexPlane(width, height), zoom);

        // Create a new Julia sequence for each pixel.
        IComplex c = new Complex(-0.4, 0.6);
        BiFunction<IComplex, Integer, IComplexSequence> sequenceFactory =
                (z0, nbTerms) -> new JuliaSequence(z0, c, nbTerms);

        // Create the image and the fractal generator.
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        IFractalImage image = new BufferedImageAdapter(bufferedImage);
        IFractalGenerator generator = new FractalGenerator(
                width, height, maxIterations, c
        );


        // Generate and save the image.
        generator.generateImage();
        generator.saveImage();
        System.out.println("Julia fractal saved to: " + outputPath);
    }
}
