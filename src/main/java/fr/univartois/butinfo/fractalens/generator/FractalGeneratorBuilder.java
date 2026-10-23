package fr.univartois.butinfo.fractalens.generator;

import fr.univartois.butinfo.fractalens.complex.*;
import fr.univartois.butinfo.fractalens.complex.sequence.*;
import java.util.Objects;
import java.util.function.BiFunction;

/** Configures the image, complex sequence, plane and output file.
 * @author Lylian Michel (implementation)
 */
public class FractalGeneratorBuilder {
    /** Creates an empty builder that must be configured before building. */
    public FractalGeneratorBuilder() {
        // Configuration is supplied through the fluent methods.
    }
    /**
     * The width of the image, in pixels.
     */
    private int width;

    /**
     * The height of the image, in pixels.
     */
    private int height;

    /**
     * The maximum number of iterations (terms) to compute for a pixel.
     */
    private int maxIterations;

    /**
     * The constant of the Julia sequence, if one has been configured.
     */
    private IComplex c;

    /**
     * The zoom factor, that is the distance in the complex plane between two
     * adjacent pixels.
     */
    private double zoom;

    /**
     * The point of the complex plane at the center of the image.
     */
    private IComplex center;

    /**
     * The color strategy to use, or {@code null} if a palette type is used.
     */
    private IColorStrategy palette;

    /**
     * The type of color palette to use, or {@code null} if a color strategy is
     * used.
     */
    private ColorPaletteType paletteType;

    /**
     * The path of the file in which to save the image.
     */
    private String outputFile;

    /**
     * The function creating the complex sequence from the complex number of a
     * pixel and the maximum number of iterations.
     */
    private BiFunction<IComplex, Integer, IComplexSequence> sequenceFactory;

    /**
     * Sets the width of the image.
     *
     * @param width The width of the image, in pixels.
     *
     * @return This builder.
     */
    public FractalGeneratorBuilder withImageWidth(int width) {
        this.width = width;
        return this;
    }

    /**
     * Sets the height of the image.
     *
     * @param height The height of the image, in pixels.
     *
     * @return This builder.
     */
    public FractalGeneratorBuilder withImageHeight(int height) {
        this.height = height;
        return this;
    }

    /**
     * Sets the maximum number of terms to compute for each pixel.
     *
     * @param maxIterations The maximum number of iterations.
     *
     * @return This builder.
     */
    public FractalGeneratorBuilder withNumberOfTerms(int maxIterations) {
        this.maxIterations = maxIterations;
        return this;
    }

    /**
     * Configures a Julia sequence with the supplied constant.
     *
     * @param c The constant of the Julia sequence.
     *
     * @return This builder.
     */
    public FractalGeneratorBuilder withJuliaConstant(IComplex c) {
        this.c = c;
        this.sequenceFactory = (point, terms) -> new JuliaSequence(point, c, terms);
        return this;
    }

    /**
     * Configures the sequence to use from a type of sequence.
     *
     * @param sequence The type of sequence to use.
     *
     * @return This builder.
     *
     * @throws NullPointerException If the sequence is {@code null}.
     */
    public FractalGeneratorBuilder usingSequence(ComplexSequenceType sequence) {
        return usingSequence(Objects.requireNonNull(sequence)::create);
    }

    /**
     * Configures the sequence to use from a factory receiving the complex
     * number of a pixel and the number of terms.
     *
     * @param sequenceFactory The factory creating the sequence.
     *
     * @return This builder.
     *
     * @throws NullPointerException If the factory is {@code null}.
     */
    public FractalGeneratorBuilder usingSequence(
            BiFunction<IComplex, Integer, IComplexSequence> sequenceFactory) {
        this.sequenceFactory = Objects.requireNonNull(sequenceFactory);
        return this;
    }

    /**
     * Sets the complex distance between adjacent pixels (the smaller it is, the
     * closer the image is).
     *
     * @param zoom The zoom factor.
     *
     * @return This builder.
     */
    public FractalGeneratorBuilder withZoomFactor(double zoom) {
        this.zoom = zoom;
        return this;
    }

    /**
     * Sets the point of the complex plane at the center of the image.
     *
     * @param center The center of the image.
     *
     * @return This builder.
     *
     * @throws NullPointerException If the center is {@code null}.
     */
    public FractalGeneratorBuilder centeredAt(IComplex center) {
        this.center = Objects.requireNonNull(center);
        return this;
    }

    /**
     * Sets the color palette from a type of palette. This replaces any color
     * strategy previously configured.
     *
     * @param paletteType The type of palette to use.
     *
     * @return This builder.
     *
     * @throws NullPointerException If the type is {@code null}.
     */
    public FractalGeneratorBuilder withColorPalette(ColorPaletteType paletteType) {
        this.paletteType = Objects.requireNonNull(paletteType);
        this.palette = null;
        return this;
    }

    /**
     * Sets the color palette from a color strategy. This replaces any type of
     * palette previously configured.
     *
     * @param palette The color strategy to use.
     *
     * @return This builder.
     *
     * @throws NullPointerException If the strategy is {@code null}.
     */
    public FractalGeneratorBuilder withColorPalette(IColorStrategy palette) {
        this.palette = Objects.requireNonNull(palette);
        this.paletteType = null;
        return this;
    }

    /**
     * Sets the file in which to save the image.
     *
     * @param outputFile The path of the output file.
     *
     * @return This builder.
     *
     * @throws NullPointerException If the path is {@code null}.
     */
    public FractalGeneratorBuilder savingTo(String outputFile) {
        this.outputFile = Objects.requireNonNull(outputFile);
        return this;
    }

    /**
     * Creates a generator from the current configuration of this builder.
     *
     * @return The created generator.
     *
     * @throws IllegalStateException If the image dimensions or the number of
     *         terms are not positive, or if the sequence, zoom, center,
     *         palette or output file has not been configured.
     */
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

    /**
     * Gives the width of the image.
     *
     * @return The width of the image, in pixels.
     */
    int getWidth() { return width; }

    /**
     * Gives the height of the image.
     *
     * @return The height of the image, in pixels.
     */
    int getHeight() { return height; }

    /**
     * Gives the maximum number of iterations.
     *
     * @return The maximum number of iterations.
     */
    int getMaxIterations() { return maxIterations; }
    /**
     * Gives the path of the output file.
     *
     * @return The path of the output file.
     */
    String getOutputFile() { return outputFile; }

    /**
     * Gives the color strategy to use. If a type of palette has been
     * configured, the strategy is created from it with the maximum number of
     * iterations.
     *
     * @return The color strategy.
     */
    IColorStrategy getPalette() {
        return paletteType == null ? palette : paletteType.getColor(maxIterations);
    }

    /**
     * Creates the complex plane corresponding to the configuration: a basic
     * plane of the size of the image, scaled by the zoom factor and then
     * translated to the center.
     *
     * @return The created complex plane.
     */
    IComplexPlane createPlane() {
        return new TranslatedComplexPlane(
                new ScaledComplexPlane(new ComplexPlane(width, height), zoom), center);
    }

    /**
     * Gives the function creating the complex sequence of a pixel.
     *
     * @return The sequence factory.
     */
    BiFunction<IComplex, Integer, IComplexSequence> getSequenceFactory() {
        return sequenceFactory;
    }

}


