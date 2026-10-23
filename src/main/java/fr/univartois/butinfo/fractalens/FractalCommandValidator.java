
package fr.univartois.butinfo.fractalens;

/**
 * Validates the parameters used to generate a fractal image.
 *
 * <p>This validator checks the focus coordinates, image dimensions,
 * maximum number of iterations, scale, image count for animations,
 * and output file name.</p>
 * @author Gautier Tinchon (implementation)
 */
public class FractalCommandValidator {

    /**
     * Creates a new validator for fractal command parameters.
     */
    public FractalCommandValidator() {
        // No initialization is required.
    }

    /**
     * Validates the parameters of a fractal command.
     *
     * @param command the command containing the parameters to validate
     * @throws NullPointerException if the command is null
     * @throws IllegalArgumentException if a parameter is invalid
     */
    public void validate(FractalCommand command) {
        if (command == null) {
            throw new NullPointerException("command must not be null");
        }

        validateImageParameters(command);

        if (command.getImageCount() <= 0
                && command.getType() != ImageType.SINGLE) {
            throw new IllegalArgumentException(
                    "Image count must be positive for animation");
        }

        if (command.getOutput() == null
                || command.getOutput().isBlank()) {
            throw new IllegalArgumentException(
                    "Output must not be null");
        }

        validateAnimationParameters(command);
        validateOutputFormat(command);
    }

    /** Checks coordinates, dimensions, iteration count and image scale. */
    private void validateImageParameters(FractalCommand command) {

        if (!Double.isFinite(command.getFocusX())
                || !Double.isFinite(command.getFocusY())) {
            throw new IllegalArgumentException(
                    "FocusX or FocusY must be finite");
        }

        if (command.getWidth() <= 0 || command.getHeight() <= 0) {
            throw new IllegalArgumentException(
                    "Width and height must be positive");
        }

        if (command.getMaxIterations() <= 0) {
            throw new IllegalArgumentException(
                    "Max iterations must be positive");
        }

        if (command.getScale() <= 0
                || !Double.isFinite(command.getScale())) {
            throw new IllegalArgumentException(
                    "Scale must be finite and positive");
        }

    }

    /** Checks the zoom ratio and GIF frame duration. */
    private void validateAnimationParameters(FractalCommand command) {
        if (command.getType() == ImageType.ZOOM
                && (!Double.isFinite(command.getZoomRatio()) || command.getZoomRatio() <= 0
                || command.getZoomRatio() >= 1)) {
            throw new IllegalArgumentException("Zoom ratio must be finite and strictly between 0 and 1");
        }
        if (command.getType() != ImageType.SINGLE && command.getFrameDelay() < 10) {
            throw new IllegalArgumentException("GIF frame delay must be at least 10 milliseconds");
        }
    }

    /** Checks the file extension against the requested image type. */
    private void validateOutputFormat(FractalCommand command) {
        if (command.getType() != ImageType.SINGLE
                && !command.getOutput().toLowerCase(java.util.Locale.ROOT).endsWith(".gif")) {
            throw new IllegalArgumentException("Animated output must use the .gif extension");
        }
        if (command.getType() == ImageType.SINGLE) {
            String name = java.nio.file.Path.of(command.getOutput()).getFileName().toString();
            int dot = name.lastIndexOf('.');
            if (dot < 0 || !javax.imageio.ImageIO.getImageWritersBySuffix(name.substring(dot + 1)).hasNext()) {
                throw new IllegalArgumentException("Static output must use a supported image extension, such as .png or .jpg");
            }
        }
    }
}
