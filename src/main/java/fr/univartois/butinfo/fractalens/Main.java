package fr.univartois.butinfo.fractalens;

import fr.univartois.butinfo.fractalens.complex.*;
import fr.univartois.butinfo.fractalens.complex.sequence.*;
import fr.univartois.butinfo.fractalens.generator.FractalGenerator;
import fr.univartois.butinfo.fractalens.generator.IFractalGenerator;

import java.awt.Color;
import java.io.IOException;
import java.nio.file.Path;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

/**
 * Generates fractal images for sprints 3 and 4.
 */
public class Main {

    /**
     * Generates all fractal images.
     *
     * @param args optional filenames for the two Julia images
     * @throws IOException if an image cannot be saved
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 0 && args.length != 2) {
            throw new IllegalArgumentException("Usage: Main [julia1.png julia2.png]");
        }

        IComplex c1 = new Complex(-0.4, 0.6);
        IComplex c2 = new Complex(0.285, 0.01);

        // Sprint 3: Julia fractals.
        generateFractal(800, 800, 250, 0.001,
                new Complex(0.1, 0.6),
                new GradientColorStrategy(Color.BLUE, Color.WHITE, 250),
                (z0, n) -> new JuliaSequence(z0, c1, n),
                args.length == 2 ? args[0] : "Julia-1.png");

        generateFractal(800, 800, 250, 0.001,
                new Complex(-0.47, -0.21),
                new ColorComponentDecorator(
                        new InvertedColorDecorator(new GrayScaleColorStrategy(250)),
                        false, true, true),
                (z0, n) -> new JuliaSequence(z0, c2, n),
                args.length == 2 ? args[1] : "Julia-2.png");

        // Sprint 4: Mandelbrot.
        generateFractal(600, 800, 250, 0.004,
                new Complex(-0.75, 0),
                new GrayScaleColorStrategy(250),
                (z0, n) -> new MandelBrotSequence(z0, n),
                "Mandelbrot.png");

        // Sprint 4: Burning Ship.
        generateFractal(1000, 1000, 64, 0.0001,
                new Complex(-1.755, -0.03),
                new GrayScaleColorStrategy(64),
                (z0, n) -> new BurningShipSequence(new Complex(0, 0), z0, n),
                "BurningShip.png");

        // Sprint 4: a sequence defined using a lambda expression.
        BinaryOperator<IComplex> formula =
                (z, z0) -> z.pow(3).add(z0);

        generateFractal(800, 800, 150, 0.004,
                new Complex(0, 0),
                new GrayScaleColorStrategy(150),
                (z0, n) -> new GenericComplexSequence(formula, z0, n),
                "Lambda.png");

        // Sprint 4: other complex sequences.
        generateFractal(800, 800, 150, 0.004,
                new Complex(0, 0),
                new GrayScaleColorStrategy(150),
                (z0, n) -> new MultiJuliaSequence(z0, c1, n, 3),
                "MultiJulia.png");

        generateFractal(800, 800, 150, 0.004,
                new Complex(0, 0),
                new GrayScaleColorStrategy(150),
                (z0, n) -> new MultiBrotSequence(z0, n, 3),
                "Multibrot.png");

        generateFractal(800, 800, 150, 0.004,
                new Complex(0, 0),
                new GrayScaleColorStrategy(150),
                (z0, n) -> new MultiBurningShipSequence(new Complex(0, 0), z0, 3, n),
                "MultiBurningShip.png");

        // Tricorn and MultiTricorn are always generated.
        generateFractal(800, 800, 150, 0.004,
                new Complex(0, 0),
                new GrayScaleColorStrategy(150),
                (z0, n) -> new TricornSequence(new Complex(0, 0), z0, n),
                "Tricorn.png");

        generateFractal(800, 800, 150, 0.004,
                new Complex(0, 0),
                new GrayScaleColorStrategy(150),
                (z0, n) -> new MultiTricornSequence(new Complex(0, 0), z0, 3, n),
                "MultiTricorn.png");
    }

    /**
     * Creates and saves a fractal image.
     *
     * @param width image width
     * @param height image height
     * @param maxIterations maximum number of iterations
     * @param zoom zoom factor
     * @param center center of the complex plane
     * @param palette color strategy
     * @param sequenceFactory factory creating a sequence for each pixel
     * @param filename output filename
     * @throws IOException if the image cannot be saved
     */
    private static void generateFractal(
            int width, int height, int maxIterations, double zoom,
            IComplex center, IColorStrategy palette,
            BiFunction<IComplex, Integer, IComplexSequence> sequenceFactory,
            String filename) throws IOException {

        // Zoom first, then move the center.
        IComplexPlane plane = new TranslatedComplexPlane(
                new ScaledComplexPlane(new ComplexPlane(width, height), zoom),
                center);

        IFractalGenerator generator = new FractalGenerator(
                width, height, maxIterations, plane, palette, sequenceFactory);

        generator.generateImage().saveAs(filename);
        System.out.println("Image saved: " + Path.of(filename).toAbsolutePath());
    }
}
