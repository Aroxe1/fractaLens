
package fr.univartois.butinfo.fractalens.generator;

import fr.univartois.butinfo.fractalens.complex.IComplex;

/**
 * Builder used to configure and create a fractal generator.
 *
 * <p>This class allows the configuration of the image dimensions,
 * the maximum number of iterations and the complex constant.</p>
 *
 * <p>Each setter returns the builder itself to allow method chaining.</p>
 *
 * @see FractalGenerator
 */
public class FractalGeneratorBuilder {

    /**
     * The width of the generated image.
     */
    private int width;

    /**
     * The height of the generated image.
     */
    private int height;

    /**
     * The maximum number of iterations.
     */
    private int maxIterations;

    /**
     * The complex constant used to generate the fractal.
     */
    private IComplex c;

    /**
     * Sets the width of the generated image.
     *
     * @param width The image width.
     * @return The current builder instance.
     */
    public FractalGeneratorBuilder setWidth(int width) {
        this.width = width;
        return this;
    }

    /**
     * Sets the height of the generated image.
     *
     * @param height The image height.
     * @return The current builder instance.
     */
    public FractalGeneratorBuilder setHeight(int height) {
        this.height = height;
        return this;
    }

    /**
     * Sets the maximum number of iterations.
     *
     * @param maxIterations The maximum number of iterations.
     * @return The current builder instance.
     */
    public FractalGeneratorBuilder setMaxIterations(int maxIterations) {
        this.maxIterations = maxIterations;
        return this;
    }

    /**
     * Sets the complex constant used for fractal generation.
     *
     * @param c The complex constant.
     * @return The current builder instance.
     */
    public FractalGeneratorBuilder setC(IComplex c) {
        this.c = c;
        return this;
    }

    /**
     * Creates a fractal generator using the configured parameters.
     *
     * @return A new instance of {@link FractalGenerator}.
     */
    public FractalGenerator build() {
        return new FractalGenerator(width, height, maxIterations, c);
    }
}
