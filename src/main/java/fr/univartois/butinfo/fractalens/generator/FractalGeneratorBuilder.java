package fr.univartois.butinfo.fractalens.generator;

import fr.univartois.butinfo.fractalens.complex.*;
import fr.univartois.butinfo.fractalens.complex.sequence.*;
import java.util.Objects;
import java.util.function.BiFunction;

/** Configures the image, complex sequence, plane and output file. */
public class FractalGeneratorBuilder {
    private int width;
    private int height;
    private int maxIterations;
    private IComplex c;
    private double zoom;
    private IComplex center;
    private IColorStrategy palette;
    private ColorPaletteType paletteType;
    private String outputFile;
    private BiFunction<IComplex, Integer, IComplexSequence> sequenceFactory;

    public FractalGeneratorBuilder withImageWidth(int width) {
        this.width = width;
        return this;
    }

    public FractalGeneratorBuilder withImageHeight(int height) {
        this.height = height;
        return this;
    }

    public FractalGeneratorBuilder withNumberOfTerms(int maxIterations) {
        this.maxIterations = maxIterations;
        return this;
    }

    /** Configures a Julia sequence with the supplied constant. */
    public FractalGeneratorBuilder withJuliaConstant(IComplex c) {
        this.c = c;
        this.sequenceFactory = (point, terms) -> new JuliaSequence(point, c, terms);
        return this;
    }

    public FractalGeneratorBuilder usingSequence(ComplexSequenceType sequence) {
        return usingSequence(Objects.requireNonNull(sequence)::create);
    }

    /** Supplies a factory receiving the pixel's complex point and term count. */
    public FractalGeneratorBuilder usingSequence(
            BiFunction<IComplex, Integer, IComplexSequence> sequenceFactory) {
        this.sequenceFactory = Objects.requireNonNull(sequenceFactory);
        return this;
    }

    /** Sets the complex distance between adjacent pixels (smaller means closer). */
    public FractalGeneratorBuilder withZoomFactor(double zoom) {
        this.zoom = zoom;
        return this;
    }

    public FractalGeneratorBuilder centeredAt(IComplex center) {
        this.center = Objects.requireNonNull(center);
        return this;
    }

    public FractalGeneratorBuilder withColorPalette(ColorPaletteType paletteType) {
        this.paletteType = Objects.requireNonNull(paletteType);
        this.palette = null;
        return this;
    }

    public FractalGeneratorBuilder withColorPalette(IColorStrategy palette) {
        this.palette = Objects.requireNonNull(palette);
        this.paletteType = null;
        return this;
    }

    public FractalGeneratorBuilder savingTo(String outputFile) {
        this.outputFile = Objects.requireNonNull(outputFile);
        return this;
    }

    public FractalGenerator build() {
        if (width <= 0 || height <= 0 || maxIterations <= 0) {
            throw new IllegalStateException("Image dimensions and term count must be positive");
        }
        if (!Double.isFinite(zoom) || zoom <= 0 || center == null
                || (palette == null && paletteType == null) || sequenceFactory == null
                || outputFile == null || outputFile.isBlank()) {
            throw new IllegalStateException("Configure sequence, zoom, center, palette and output file");
        }
        return new FractalGenerator(this);
    }

    int getWidth() { return width; }
    int getHeight() { return height; }
    int getMaxIterations() { return maxIterations; }
    String getOutputFile() { return outputFile; }
    IColorStrategy getPalette() {
        return paletteType == null ? palette : paletteType.getColor(maxIterations);
    }
    IComplexPlane createPlane() {
        return new TranslatedComplexPlane(
                new ScaledComplexPlane(new ComplexPlane(width, height), zoom), center);
    }
    BiFunction<IComplex, Integer, IComplexSequence> getSequenceFactory() {
        return sequenceFactory;
    }

}


