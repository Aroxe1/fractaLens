
package fr.univartois.butinfo.fractalens;

/**
 * Validates the parameters used to generate a fractal image.
 *
 * <p>This validator checks the focus coordinates, image dimensions,
 * maximum number of iterations, scale, image count for animations,
 * and output file name.</p>
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
    }
}
