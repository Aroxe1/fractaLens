package fr.univartois.butinfo.fractalens;

import static org.junit.jupiter.api.Assertions.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import fr.univartois.butinfo.fractalens.complex.ColorPaletteType;
import fr.univartois.butinfo.fractalens.complex.sequence.ComplexSequenceType;
import picocli.CommandLine;
import picocli.CommandLine.ParseResult;

/**
 * Unit tests for the {@link FractalCommand} class.
 *
 * @see FractalCommand
 * @author Maxence Royon (unit tests)
 */
class FractalCommandTest {

    /**
     * The temporary directory in which the images are written.
     */
    @TempDir
    Path tempDir;

    /**
     * Creates a command line on a new {@link FractalCommand}, whose outputs are
     * captured instead of being printed in the console.
     *
     * @param out The writer receiving the standard output.
     * @param err The writer receiving the error output.
     *
     * @return The created command line.
     */
    private static CommandLine commandLine(StringWriter out, StringWriter err) {
        CommandLine commandLine = new CommandLine(new FractalCommand());
        commandLine.setOut(new PrintWriter(out));
        commandLine.setErr(new PrintWriter(err));
        return commandLine;
    }

    /**
     * Gives the value of an option after parsing the given arguments.
     *
     * @param option The long name of the option.
     * @param args The arguments to parse.
     *
     * @return The value of the option.
     */
    private static Object valueOf(String option, String... args) {
        CommandLine commandLine = new CommandLine(new FractalCommand());
        commandLine.parseArgs(args);
        return commandLine.getCommandSpec().findOption(option).getValue();
    }

    /**
     * Checks the default values used when no option is given.
     */
    @Test
    void testDefaultValues() {
        assertEquals(ComplexSequenceType.MANDELBROT, valueOf("--fractal-name"));
        assertEquals(100, valueOf("--nb-iterations"));
        assertEquals(600, valueOf("--height"));
        assertEquals(800, valueOf("--width"));
        assertEquals(0.005, valueOf("--scale"));
        assertEquals(0.0, valueOf("--focus-x"));
        assertEquals(0.0, valueOf("--focus-y"));
        assertEquals(ColorPaletteType.GRAY, valueOf("--palette-name"));
        assertEquals(ImageType.SINGLE, valueOf("--type"));
        assertEquals(50, valueOf("--image-count"));
        assertEquals("fractal.png", valueOf("--output"));
    }

    /**
     * Checks that the short names of the options are read correctly.
     */
    @Test
    void testShortOptions() {
        ParseResult result = new CommandLine(new FractalCommand()).parseArgs(
                "-f", "JULIA1", "-n", "42", "-h", "300", "-w", "400",
                "-s", "0.01", "-x", "-0.5", "-y", "0.25", "-p", "GRADIENT",
                "-t", "ZOOM", "-i", "12", "-o", "image.gif");

        assertEquals(ComplexSequenceType.JULIA1, result.matchedOptionValue("-f", null));
        assertEquals(42, (int) result.matchedOptionValue("-n", 0));
        assertEquals(300, (int) result.matchedOptionValue("-h", 0));
        assertEquals(400, (int) result.matchedOptionValue("-w", 0));
        assertEquals(0.01, result.matchedOptionValue("-s", 0.0), 1e-12);
        assertEquals(-0.5, result.matchedOptionValue("-x", 0.0), 1e-12);
        assertEquals(0.25, result.matchedOptionValue("-y", 0.0), 1e-12);
        assertEquals(ColorPaletteType.GRADIENT, result.matchedOptionValue("-p", null));
        assertEquals(ImageType.ZOOM, result.matchedOptionValue("-t", null));
        assertEquals(12, (int) result.matchedOptionValue("-i", 0));
        assertEquals("image.gif", result.matchedOptionValue("-o", null));
    }

    /**
     * Checks that the long names of the options are read correctly.
     */
    @Test
    void testLongOptions() {
        assertEquals(ComplexSequenceType.TRICORN,
                valueOf("--fractal-name", "--fractal-name", "TRICORN"));
        assertEquals(250, valueOf("--height", "--height", "250"));
        assertEquals(ImageType.ROTATION, valueOf("--type", "--type", "ROTATION"));
        assertEquals("out.png", valueOf("--output", "--output", "out.png"));
    }

    /**
     * Checks that {@code -h} is the height of the image, and not the help.
     */
    @Test
    void testShortHIsHeight() {
        CommandLine commandLine = commandLine(new StringWriter(), new StringWriter());

        commandLine.parseArgs("-h", "123");

        Object height = commandLine.getCommandSpec().findOption("--height").getValue();
        assertEquals(123, height);
        assertFalse(commandLine.isUsageHelpRequested());
    }

    /**
     * Checks that {@code --help} displays the description of all the options.
     */
    @Test
    void testHelp() {
        StringWriter out = new StringWriter();
        int exitCode = commandLine(out, new StringWriter()).execute("--help");

        assertEquals(0, exitCode);
        String help = out.toString();
        for (String option : new String[] {
                "--fractal-name", "--nb-iterations", "--height", "--width", "--scale",
                "--focus-x", "--focus-y", "--palette-name", "--type",
                "--image-count", "--output" }) {
            assertTrue(help.contains(option), "Missing option in help: " + option);
        }
    }

    /**
     * Checks that an unknown fractal name is rejected with an error message.
     */
    @Test
    void testUnknownFractalName() {
        StringWriter err = new StringWriter();
        int exitCode = commandLine(new StringWriter(), err).execute("-f", "UNKNOWN");

        assertNotEquals(0, exitCode);
        assertTrue(err.toString().contains("--fractal-name"));
    }

    /**
     * Checks that a text given instead of a number is rejected.
     */
    @Test
    void testInvalidNumber() {
        StringWriter err = new StringWriter();
        int exitCode = commandLine(new StringWriter(), err).execute("-w", "abc");

        assertNotEquals(0, exitCode);
        assertTrue(err.toString().contains("abc"));
    }

    /**
     * Checks that an unknown option is rejected.
     */
    @Test
    void testUnknownOption() {
        int exitCode = commandLine(new StringWriter(), new StringWriter())
                .execute("--unknown");

        assertNotEquals(0, exitCode);
    }

    /**
     * Checks that a single image is generated in the output file.
     */
    @Test
    void testGenerateSingleImage() {
        Path output = tempDir.resolve("single.png");

        int exitCode = commandLine(new StringWriter(), new StringWriter()).execute(
                "-w", "20", "-h", "20", "-n", "10", "-t", "SINGLE",
                "-o", output.toString());

        assertEquals(0, exitCode);
        assertTrue(Files.exists(output));
    }

    /**
     * Checks that a zoom animation is generated in the output file.
     */
    @Test
    void testGenerateZoom() {
        Path output = tempDir.resolve("zoom.gif");

        int exitCode = commandLine(new StringWriter(), new StringWriter()).execute(
                "-w", "20", "-h", "20", "-n", "10", "-t", "ZOOM", "-i", "3",
                "-o", output.toString());

        assertEquals(0, exitCode);
        assertTrue(Files.exists(output));
    }

    /**
     * Checks that a rotation animation is generated in the output file.
     */
    @Test
    void testGenerateRotation() {
        Path output = tempDir.resolve("rotation.gif");

        int exitCode = commandLine(new StringWriter(), new StringWriter()).execute(
                "-w", "20", "-h", "20", "-n", "10", "-t", "ROTATION", "-i", "3",
                "-o", output.toString());

        assertEquals(0, exitCode);
        assertTrue(Files.exists(output));
    }
}