package fr.univartois.butinfo.fractalens;

import fr.univartois.butinfo.fractalens.complex.ColorPaletteType;
import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.GradientColorStrategy;
import fr.univartois.butinfo.fractalens.complex.sequence.ComplexSequenceType;
import fr.univartois.butinfo.fractalens.generator.FractalGeneratorBuilder;
import java.awt.Color;

/** Generates the three images selected for the builder demonstration. */
public class Main {

    /** All fractal settings can be changed here for the demonstration. */
    public static void main(String[] args) {
        new FractalGeneratorBuilder()
                .setSequence(ComplexSequenceType.JULIA1)
                .setMaxIterations(250)
                .setWidth(800)
                .setHeight(800)
                .setZoom(0.001)
                .setCenter(new Complex(0.1, 0.6))
                .setPalette(ColorPaletteType.GRADIENT)
                .setOutputFile("Julia.png")
                .build()
                .saveImage();

        new FractalGeneratorBuilder()
                .setSequence(ComplexSequenceType.MANDELBROT)
                .setMaxIterations(250)
                .setWidth(800)
                .setHeight(600)
                .setZoom(0.004)
                .setCenter(new Complex(-0.75, 0))
                .setPalette(ColorPaletteType.GRAYSCALE)
                .setOutputFile("Mandelbrot.png")
                .build()
                .saveImage();

        new FractalGeneratorBuilder()
                .setSequence(ComplexSequenceType.BURNINGSHIP)
                .setMaxIterations(64)
                .setWidth(1000)
                .setHeight(1000)
                .setZoom(0.0001)
                .setCenter(new Complex(-1.755, -0.03))
                .setPalette(new GradientColorStrategy(Color.BLACK, Color.ORANGE, 64))
                .setOutputFile("BurningShip.png")
                .build()
                .saveImage();
    }
}
