package fr.univartois.butinfo.fractalens;

import fr.univartois.butinfo.fractalens.complex.ColorPaletteType;
import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.sequence.ComplexSequenceType;
import fr.univartois.butinfo.fractalens.generator.FractalGeneratorBuilder;

/** Generates an image for every available complex sequence. */
public class Main {

    /** Configures and saves each image directly through the builder. */
    public static void main(String[] args) {
        // JULIA1
        new FractalGeneratorBuilder()
                .usingSequence(ComplexSequenceType.JULIA1)
                .withNumberOfTerms(250)
                .withImageWidth(800)
                .withImageHeight(800)
                .withZoomFactor(0.001)
                .centeredAt(new Complex(0.1, 0.6))
                .withColorPalette(ColorPaletteType.GRADIENT)
                .savingTo("julia_1.png")
                .build()
                .saveImage();

        // JULIA2
        new FractalGeneratorBuilder()
                .usingSequence(ComplexSequenceType.JULIA2)
                .withNumberOfTerms(250)
                .withImageWidth(800)
                .withImageHeight(800)
                .withZoomFactor(0.001)
                .centeredAt(new Complex(-0.47, -0.21))
                .withColorPalette(ColorPaletteType.COMPONENT)
                .savingTo("julia_2.png")
                .build()
                .saveImage();

        // MANDELBROT
        new FractalGeneratorBuilder()
                .usingSequence(ComplexSequenceType.MANDELBROT)
                .withNumberOfTerms(150)
                .withImageWidth(800)
                .withImageHeight(600)
                .withZoomFactor(0.005)
                .centeredAt(new Complex(-0.5, 0))
                .withColorPalette(ColorPaletteType.GRADIENT)
                .savingTo("mandelbrot.png")
                .build()
                .saveImage();

        // BURNINGSHIP
        new FractalGeneratorBuilder()
                .usingSequence(ComplexSequenceType.BURNINGSHIP)
                .withNumberOfTerms(100)
                .withImageWidth(800)
                .withImageHeight(800)
                .withZoomFactor(0.0001)
                .centeredAt(new Complex(-1.755, -0.03))
                .withColorPalette(ColorPaletteType.INVERTED)
                .savingTo("burning_ship.png")
                .build()
                .saveImage();

        // TRICORN
        new FractalGeneratorBuilder()
                .usingSequence(ComplexSequenceType.TRICORN)
                .withNumberOfTerms(150)
                .withImageWidth(800)
                .withImageHeight(800)
                .withZoomFactor(0.005)
                .centeredAt(new Complex(0, 0))
                .withColorPalette(ColorPaletteType.COMPONENT)
                .savingTo("tricorn.png")
                .build()
                .saveImage();

        // MULTIBROT
        new FractalGeneratorBuilder()
                .usingSequence(ComplexSequenceType.MULTIBROT)
                .withNumberOfTerms(150)
                .withImageWidth(800)
                .withImageHeight(800)
                .withZoomFactor(0.004)
                .centeredAt(new Complex(0, 0))
                .withColorPalette(ColorPaletteType.GRADIENT)
                .savingTo("multibrot.png")
                .build()
                .saveImage();

        // MULTIJULIA
        new FractalGeneratorBuilder()
                .usingSequence(ComplexSequenceType.MULTIJULIA)
                .withNumberOfTerms(150)
                .withImageWidth(800)
                .withImageHeight(800)
                .withZoomFactor(0.004)
                .centeredAt(new Complex(0, 0))
                .withColorPalette(ColorPaletteType.INVERTED)
                .savingTo("multijulia.png")
                .build()
                .saveImage();

        // MULTIJULIA2
        new FractalGeneratorBuilder()
                .usingSequence(ComplexSequenceType.MULTIJULIA2)
                .withNumberOfTerms(150)
                .withImageWidth(800)
                .withImageHeight(800)
                .withZoomFactor(0.004)
                .centeredAt(new Complex(0, 0))
                .withColorPalette(ColorPaletteType.COMPONENT)
                .savingTo("multijulia_2.png")
                .build()
                .saveImage();

        // MULTITRICORN
        new FractalGeneratorBuilder()
                .usingSequence(ComplexSequenceType.MULTITRICORN)
                .withNumberOfTerms(150)
                .withImageWidth(800)
                .withImageHeight(800)
                .withZoomFactor(0.004)
                .centeredAt(new Complex(0, 0))
                .withColorPalette(ColorPaletteType.GRAY)
                .savingTo("multitricorn.png")
                .build()
                .saveImage();

        // MULTIBURNINGSHIP
        new FractalGeneratorBuilder()
                .usingSequence(ComplexSequenceType.MULTIBURNINGSHIP)
                .withNumberOfTerms(150)
                .withImageWidth(800)
                .withImageHeight(800)
                .withZoomFactor(0.004)
                .centeredAt(new Complex(0, 0))
                .withColorPalette(ColorPaletteType.GRADIENT)
                .savingTo("multi_burning_ship.png")
                .build()
                .saveImage();

    }
}


