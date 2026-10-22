package fr.univartois.butinfo.fractalens;

import fr.univartois.butinfo.fractalens.complex.ColorPaletteType;
import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.sequence.ComplexSequenceType;
import fr.univartois.butinfo.fractalens.generator.AnimatedFractalGenerator;
import fr.univartois.butinfo.fractalens.generator.FractalGeneratorBuilder;
import fr.univartois.butinfo.fractalens.generator.ZoomFractalGenerator;

/** Entry point demonstrating animated fractal generation. */
public final class Main {

    private Main() {
        // Utility class.
    }

    /** Generates a zoom animation of the Multibrot set. */
    public static void main(String[] args) {
        String outputPath = args.length > 0 ? args[0] : "multibrot_zoom.gif";

        FractalGeneratorBuilder builder = new FractalGeneratorBuilder()
                .usingSequence(ComplexSequenceType.MULTIJULIA)
                .withNumberOfTerms(150)
                .withImageWidth(800)
                .withImageHeight(600)
                .centeredAt(new Complex(-0.2, 0))
                .withColorPalette(ColorPaletteType.GRADIENT)
                .savingTo("unused.png");

        //ZoomFractalGenerator zoom = new ZoomFractalGenerator(builder, 0.005, 0.92, 30);
        //AnimatedFractalGenerator animation = new AnimatedFractalGenerator(
        //zoom.getGenerators(), outputPath, 80);
        //animation.saveImage();

        ZoomFractalGenerator zoom = new ZoomFractalGenerator(
                builder, 0.005, 0.92, 30, outputPath, 80);
        zoom.saveImage();
    }
}
