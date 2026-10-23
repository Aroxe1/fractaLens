package fr.univartois.butinfo.fractalens;

import fr.univartois.butinfo.fractalens.complex.ColorPaletteType;
import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.sequence.ComplexSequenceType;
import fr.univartois.butinfo.fractalens.generator.FractalGeneratorBuilder;
import fr.univartois.butinfo.fractalens.generator.IFractalGenerator;
import fr.univartois.butinfo.fractalens.generator.RotatingFractalGenerator;
import fr.univartois.butinfo.fractalens.generator.ZoomFractalGenerator;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Command(name = "fractalens", description = "Generates fractal images.")
public class FractalCommand implements Callable<Integer> {

    /**
     * The name of the fractal to generate.
     */
    @Option(names = {"-f", "--fractal-name"}, description = "Name of the fractal to generate.")
    private ComplexSequenceType fractalName = ComplexSequenceType.MANDELBROT;

    /**
     * The maximum number of iterations used to compute each pixel.
     */
    @Option(names = {"-n", "--nb-iterations"}, description = "Maximum number of iterations.")
    private int maxIterations = 100;

    /**
     * The height of the image, in pixels.
     */
    @Option(names = {"-h", "--height"}, description = "Height of the image, in pixels.")
    private int height = 600;

    /**
     * The width of the image, in pixels.
     */
    @Option(names = {"-w", "--width"}, description = "Width of the image, in pixels.")
    private int width = 800;

    /**
     * The scale (zoom) to apply on the image.
     */
    @Option(names = {"-s", "--scale"}, description = "Scale to apply on the image.")
    private double scale = 0.005;

    /**
     * The real part of the point at the center of the image.
     */
    @Option(names = {"-x", "--focus-x"}, description = "Real part of the center of the image.")
    private double focusX = 0;

    /**
     * The imaginary part of the point at the center of the image.
     */
    @Option(names = {"-y", "--focus-y"}, description = "Imaginary part of the center of the image.")
    private double focusY = 0;

    /**
     * The name of the color palette used to color the fractal.
     */
    @Option(names = {"-p", "--palette-name"}, description = "Name of the color palette.")
    private ColorPaletteType paletteName = ColorPaletteType.GRAY;

    /**
     * The type of image to generate.
     */
    @Option(names = {"-t", "--type"}, description = "Type of image: SINGLE, ZOOM or ROTATION.")
    private ImageType type = ImageType.SINGLE;

    /**
     * The number of images to generate for animations.
     */
    @Option(names = {"-i", "--image-count"}, description = "Number of images for animations.")
    private int imageCount = 50;

    /**
     * The name of the file in which the image is saved.
     */
    @Option(names = {"-o", "--output"}, description = "Name of the output file.")
    private String output = "fractal.png";

    /**
     * Whether the user asked to display the help message.
     */
    @Option(names = "--help", usageHelp = true, description = "Shows this help message.")
    private boolean help;

    /**
     * Returns the name of the fractal to generate.
     *
     * @return The fractal sequence type.
     */
    public ComplexSequenceType getFractalName() {
        return fractalName;
    }

    /**
     * Returns the maximum number of iterations.
     *
     * @return The maximum number of iterations.
     */
    public int getMaxIterations() {
        return maxIterations;
    }

    /**
     * Returns the height of the image in pixels.
     *
     * @return The image height.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the width of the image in pixels.
     *
     * @return The image width.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the scale applied to the image.
     *
     * @return The image scale.
     */
    public double getScale() {
        return scale;
    }

    /**
     * Returns the real part of the image center.
     *
     * @return The real coordinate of the image center.
     */
    public double getFocusX() {
        return focusX;
    }

    /**
     * Returns the imaginary part of the image center.
     *
     * @return The imaginary coordinate of the image center.
     */
    public double getFocusY() {
        return focusY;
    }

    /**
     * Returns the color palette used to generate the fractal.
     *
     * @return The selected color palette.
     */
    public ColorPaletteType getPaletteName() {
        return paletteName;
    }

    /**
     * Returns the type of image to generate.
     *
     * @return The image generation type.
     */
    public ImageType getType() {
        return type;
    }

    /**
     * Returns the number of images to generate for animations.
     *
     * @return The number of images.
     */
    public int getImageCount() {
        return imageCount;
    }

    /**
     * Returns the output file name.
     *
     * @return The output file name.
     */
    public String getOutput() {
        return output;
    }

    /**
     * Generates the fractal image configured by the command-line options,
     * and saves it in the output file.
     *
     * @return The exit code of the command ({@code 0} if the image has been generated).
     *
     * @throws Exception If an error occurs while generating the image.
     */
    @Override
    public Integer call() throws Exception {

        FractalGeneratorBuilder builder = new FractalGeneratorBuilder()
                .usingSequence(fractalName)
                .withNumberOfTerms(maxIterations)
                .withImageWidth(width)
                .withImageHeight(height)
                .withZoomFactor(scale)
                .centeredAt(new Complex(focusX, focusY))
                .withColorPalette(paletteName)
                .savingTo(output);

        IFractalGenerator generator =
                switch (type) {
                    case SINGLE -> builder.build();
                    case ZOOM -> new ZoomFractalGenerator(builder, scale, 0.92, imageCount, output, 80);
                    case ROTATION -> new RotatingFractalGenerator(builder, imageCount, output, 80);
                };

        generator.saveImage();

        return 0;
    }
}