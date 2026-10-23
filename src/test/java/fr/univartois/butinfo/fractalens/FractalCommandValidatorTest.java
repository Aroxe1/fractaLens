
package fr.univartois.butinfo.fractalens;

import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the validation of fractal command parameters.
 */
class FractalCommandValidatorTest {

    /**
     * The validator used by the tests.
     */
    private final FractalCommandValidator validator =
            new FractalCommandValidator();

    /**
     * Creates a fractal command with the given command-line arguments.
     *
     * @param args the command-line arguments
     * @return the configured fractal command
     */
    private FractalCommand createCommand(String... args) {
        FractalCommand command = new FractalCommand();
        new CommandLine(command).parseArgs(args);
        return command;
    }

    /**
     * Tests that the default command parameters are valid.
     */
    @Test
    void testValidCommand() {
        FractalCommand command = createCommand();

        assertDoesNotThrow(() -> validator.validate(command));
    }

    /**
     * Tests that a null command causes a NullPointerException.
     */
    @Test
    void testNullCommand() {
        assertThrows(NullPointerException.class,
                () -> validator.validate(null));
    }

    /**
     * Tests that a NaN real focus coordinate is rejected.
     */
    @Test
    void testInvalidFocusX() {
        FractalCommand command = createCommand("-x", "NaN");

        assertThrows(IllegalArgumentException.class,
                () -> validator.validate(command));
    }

    /**
     * Tests that an infinite imaginary focus coordinate is rejected.
     */
    @Test
    void testInvalidFocusY() {
        FractalCommand command = createCommand("-y", "Infinity");

        assertThrows(IllegalArgumentException.class,
                () -> validator.validate(command));
    }

    /**
     * Tests that a zero image width is rejected.
     */
    @Test
    void testInvalidWidth() {
        FractalCommand command = createCommand("-w", "0");

        assertThrows(IllegalArgumentException.class,
                () -> validator.validate(command));
    }

    /**
     * Tests that a negative image height is rejected.
     */
    @Test
    void testInvalidHeight() {
        FractalCommand command = createCommand("-h", "-1");

        assertThrows(IllegalArgumentException.class,
                () -> validator.validate(command));
    }

    /**
     * Tests that a non-positive maximum iteration count is rejected.
     */
    @Test
    void testInvalidIterations() {
        FractalCommand command = createCommand("-n", "0");

        assertThrows(IllegalArgumentException.class,
                () -> validator.validate(command));
    }

    /**
     * Tests that a negative scale is rejected.
     */
    @Test
    void testInvalidScale() {
        FractalCommand command = createCommand("-s", "-0.5");

        assertThrows(IllegalArgumentException.class,
                () -> validator.validate(command));
    }

    /**
     * Tests that an infinite scale is rejected.
     */
    @Test
    void testInfiniteScale() {
        FractalCommand command = createCommand("-s", "Infinity");

        assertThrows(IllegalArgumentException.class,
                () -> validator.validate(command));
    }

    /**
     * Tests that a zoom animation cannot have zero images.
     */
    @Test
    void testInvalidImageCountForZoom() {
        FractalCommand command =
                createCommand("-t", "ZOOM", "-i", "0");

        assertThrows(IllegalArgumentException.class,
                () -> validator.validate(command));
    }

    /**
     * Tests that a rotation animation cannot have a negative image count.
     */
    @Test
    void testInvalidImageCountForRotation() {
        FractalCommand command =
                createCommand("-t", "ROTATION", "-i", "-1");

        assertThrows(IllegalArgumentException.class,
                () -> validator.validate(command));
    }

    /**
     * Tests that a single image does not require a positive image count.
     */
    @Test
    void testImageCountCanBeZeroForSingleImage() {
        FractalCommand command =
                createCommand("-t", "SINGLE", "-i", "0");

        assertDoesNotThrow(() -> validator.validate(command));
    }

    /**
     * Tests that a blank output file name is rejected.
     */
    @Test
    void testBlankOutput() {
        FractalCommand command = createCommand("-o", "   ");

        assertThrows(IllegalArgumentException.class,
                () -> validator.validate(command));
    }
}
