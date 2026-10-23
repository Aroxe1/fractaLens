
package fr.univartois.butinfo.fractalens;

import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import static org.junit.jupiter.api.Assertions.*;

class FractalCommandValidatorTest {

    private final FractalCommandValidator validator =
            new FractalCommandValidator();

    private FractalCommand createCommand(String... args) {
        FractalCommand command = new FractalCommand();
        new CommandLine(command).parseArgs(args);
        return command;
    }

    @Test
    void testValidCommand() {
        FractalCommand command = createCommand();

        assertDoesNotThrow(() -> validator.validate(command));
    }

    @Test
    void testNullCommand() {
        assertThrows(NullPointerException.class,
                () -> validator.validate(null));
    }

    @Test
    void testInvalidFocusX() {
        FractalCommand command = createCommand("-x", "NaN");

        assertThrows(IllegalArgumentException.class,
                () -> validator.validate(command));
    }

    @Test
    void testInvalidFocusY() {
        FractalCommand command = createCommand("-y", "Infinity");

        assertThrows(IllegalArgumentException.class,
                () -> validator.validate(command));
    }

    @Test
    void testInvalidWidth() {
        FractalCommand command = createCommand("-w", "0");

        assertThrows(IllegalArgumentException.class,
                () -> validator.validate(command));
    }

    @Test
    void testInvalidHeight() {
        FractalCommand command = createCommand("-h", "-1");

        assertThrows(IllegalArgumentException.class,
                () -> validator.validate(command));
    }

    @Test
    void testInvalidIterations() {
        FractalCommand command = createCommand("-n", "0");

        assertThrows(IllegalArgumentException.class,
                () -> validator.validate(command));
    }

    @Test
    void testInvalidScale() {
        FractalCommand command = createCommand("-s", "-0.5");

        assertThrows(IllegalArgumentException.class,
                () -> validator.validate(command));
    }

    @Test
    void testInfiniteScale() {
        FractalCommand command = createCommand("-s", "Infinity");

        assertThrows(IllegalArgumentException.class,
                () -> validator.validate(command));
    }

    @Test
    void testInvalidImageCountForZoom() {
        FractalCommand command =
                createCommand("-t", "ZOOM", "-i", "0");

        assertThrows(IllegalArgumentException.class,
                () -> validator.validate(command));
    }

    @Test
    void testInvalidImageCountForRotation() {
        FractalCommand command =
                createCommand("-t", "ROTATION", "-i", "-1");

        assertThrows(IllegalArgumentException.class,
                () -> validator.validate(command));
    }

    @Test
    void testImageCountCanBeZeroForSingleImage() {
        FractalCommand command =
                createCommand("-t", "SINGLE", "-i", "0");

        assertDoesNotThrow(() -> validator.validate(command));
    }

    @Test
    void testBlankOutput() {
        FractalCommand command = createCommand("-o", "   ");

        assertThrows(IllegalArgumentException.class,
                () -> validator.validate(command));
    }
}
