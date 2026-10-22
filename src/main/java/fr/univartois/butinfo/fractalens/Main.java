package fr.univartois.butinfo.fractalens;

import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.GradientColorStrategy;
import fr.univartois.butinfo.fractalens.complex.sequence.ComplexSequenceType;
import fr.univartois.butinfo.fractalens.generator.FractalGeneratorBuilder;
import fr.univartois.butinfo.fractalens.generator.RotatingFractalGenerator;
import fr.univartois.butinfo.fractalens.generator.ZoomFractalGenerator;

import java.awt.Color;
import java.util.List;

/** Entry point demonstrating a rotating Julia fractal. */
public final class Main {

    private Main() {
        // Utility class.
    }

    /** Generates both a Julia rotation GIF and a fractal zoom GIF. */
    public static void main(String[] args) {
        String rotationOutput = args.length > 0 ? args[0] : "julia_rotation.gif";
        String zoomOutput = args.length > 1 ? args[1] : "multijulia_zoom1.gif";

        FractalGeneratorBuilder rotationBuilder = new FractalGeneratorBuilder()
                .withNumberOfTerms(150)
                .withImageWidth(800)
                .withImageHeight(600)
                .withZoomFactor(0.005)
                .centeredAt(new Complex(0, 0))
                .withColorPalette(new GradientColorStrategy(
                        List.of(
                                new Color(8, 0, 24),
                                new Color(35, 5, 85),
                                new Color(75, 15, 145),
                                new Color(125, 25, 180),
                                new Color(185, 35, 165),
                                new Color(225, 55, 125),
                                new Color(250, 85, 75),
                                new Color(255, 135, 45),
                                new Color(255, 190, 80),
                                new Color(18, 5, 35)),
                        150))
                .savingTo("unused.png");

        new RotatingFractalGenerator(
                rotationBuilder, 60, rotationOutput, 80).saveImage();

        FractalGeneratorBuilder zoomBuilder = new FractalGeneratorBuilder()
                .usingSequence(ComplexSequenceType.MULTIJULIA)
                .withNumberOfTerms(150)
                .withImageWidth(800)
                .withImageHeight(600)
                .centeredAt(new Complex(-0.522225, 0.05))
                .withColorPalette(new GradientColorStrategy(
                        List.of(
                                new Color(2, 8, 28),
                                new Color(5, 25, 75),
                                new Color(8, 55, 130),
                                new Color(5, 100, 175),
                                new Color(0, 145, 205),
                                new Color(0, 185, 210),
                                new Color(20, 215, 180),
                                new Color(90, 230, 135),
                                new Color(180, 240, 115),
                                new Color(3, 20, 35)),
                        150))
                .savingTo("unused.png");

        new ZoomFractalGenerator(
                zoomBuilder, 0.005, 0.92, 100, zoomOutput, 80).saveImage();
    }
}
