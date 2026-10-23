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
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;

import java.util.concurrent.Callable;

/**
 * Defines Picocli options and configures the fractal generator through its builder.
 *
 * @author Maxence Royon (command-line parsing)
 * @author Alice Demey (command execution)
 */
@Command(name = "fractalens", description = "Generates static fractals and animated GIFs.", showDefaultValues = true)
public class FractalCommand implements Callable<Integer> {

    /** Creates a command using the documented default generation settings. */
    public FractalCommand() {
        // Picocli fills the annotated fields after construction.
    }

    /**
     * The name of the fractal to generate.
     */
    @Option(names = {"-f", "--fractal-name"}, description = "Fractal sequence: ${COMPLETION-CANDIDATES}.")
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
    @Option(names = {"-p", "--palette-name"}, description = "Color palette: ${COMPLETION-CANDIDATES}.")
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

    /** Picocli metadata used to distinguish explicit output names from defaults. */
    @Spec
    private CommandSpec specification;

    /** The scale multiplier between successive zoom frames. */
    @Option(names = "--zoom-ratio", description = "Zoom scale multiplier, strictly between 0 and 1.")
    private double zoomRatio = 0.92;

    /** The duration of each GIF frame in milliseconds. */
    @Option(names = "--frame-delay", description = "GIF frame duration in milliseconds (at least 10).")
    private int frameDelay = 80;

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
        if (type != ImageType.SINGLE && specification != null
                && specification.commandLine().getParseResult() != null
                && !specification.commandLine().getParseResult().hasMatchedOption("--output")) {
            return "fractal.gif";
        }
        return output;
    }

    /**
     * Returns the multiplier between successive zoom scales.
     * @return the zoom scale multiplier
     */
    public double getZoomRatio() { return zoomRatio; }

    /**
     * Returns the duration of a GIF frame.
     * @return the frame duration in milliseconds
     */
    public int getFrameDelay() { return frameDelay; }

    /**
     * Generates the fractal image configured by the command-line options,
     * and saves it in the output file.
     *
     * @return The exit code of the command ({@code 0} if the image has been generated).
     *
     * @throws IllegalArgumentException if generation parameters are invalid
     * @throws java.io.UncheckedIOException if the static output cannot be saved
     */
    @Override
    public Integer call() {

        new FractalCommandValidator().validate(this);

        FractalGeneratorBuilder builder = new FractalGeneratorBuilder()
                .usingSequence(fractalName)
                .withNumberOfTerms(maxIterations)
                .withImageWidth(width)
                .withImageHeight(height)
                .withZoomFactor(scale)
                .centeredAt(new Complex(focusX, focusY))
                .withColorPalette(paletteName)
                .savingTo(getOutput());

        IFractalGenerator generator =
                switch (type) {
                    case SINGLE -> builder.build();
                    case ZOOM -> new ZoomFractalGenerator(builder, scale, zoomRatio, imageCount, getOutput(), frameDelay);
                    case ROTATION -> new RotatingFractalGenerator(builder, imageCount, getOutput(), frameDelay);
                };

        generator.saveImage();

        return 0;
    }
}
