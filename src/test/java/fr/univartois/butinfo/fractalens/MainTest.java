package fr.univartois.butinfo.fractalens;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

/** End-to-end checks of the sprint-seven entry point.
 * @author Lylian Michel
 */
class MainTest {
    @TempDir Path directory;

    @Test void noArgumentsShowsAllOptionsWithoutGeneratingAnImage() {
        StringWriter out = new StringWriter();
        assertEquals(0, Main.run(new String[0], new PrintWriter(out), new PrintWriter(new StringWriter())));
        for (String option : new String[]{"--fractal-name", "--height", "--width", "--scale",
                "--focus-x", "--focus-y", "--nb-iterations", "--palette-name", "--type", "--output"}) {
            assertTrue(out.toString().contains(option), option);
        }
    }

    @Test void invalidDimensionsShowFullHelpAndDoNotWriteAnImage() {
        StringWriter err = new StringWriter();
        Path output = directory.resolve("invalid.png");
        int status = Main.run(new String[]{"-w", "0", "-o", output.toString()},
                new PrintWriter(new StringWriter()), new PrintWriter(err));
        assertEquals(2, status);
        assertTrue(err.toString().contains("--focus-y"));
        assertFalse(Files.exists(output));
    }

    @Test void unknownOptionShowsFullHelp() {
        StringWriter err = new StringWriter();
        assertEquals(2, Main.run(new String[]{"--unknown"}, new PrintWriter(new StringWriter()), new PrintWriter(err)));
        assertTrue(err.toString().contains("--palette-name"));
    }

    @Test void commandLineProducesTheRequestedStaticDimensions() throws Exception {
        Path output = directory.resolve("mandelbrot.png");
        assertEquals(0, Main.run(new String[]{"-f", "MANDELBROT", "-w", "30", "-h", "24",
                "-s", "0.05", "-x", "-0.5", "-y", "0", "-n", "25", "-p", "GRADIENT",
                "-t", "SINGLE", "-o", output.toString()}, new PrintWriter(new StringWriter()),
                new PrintWriter(new StringWriter())));
        var image = ImageIO.read(output.toFile());
        assertEquals(30, image.getWidth());
        assertEquals(24, image.getHeight());
    }

    @Test void outputFailureReturnsAnErrorRatherThanSuccess() {
        StringWriter err = new StringWriter();
        assertEquals(1, Main.run(new String[]{"-w", "8", "-h", "8", "-o",
                directory.resolve("missing").resolve("image.png").toString()},
                new PrintWriter(new StringWriter()), new PrintWriter(err)));
        assertTrue(err.toString().contains("Error:"));
    }
}
