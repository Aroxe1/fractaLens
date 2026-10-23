package fr.univartois.butinfo.fractalens;

import java.io.PrintWriter;
import picocli.CommandLine;

/**
 * Starts the Picocli interface and reports command-line errors in English.
 *
 * @author Lylian Michel
 */
public final class Main {
    private Main() {
        // Utility class.
    }

    /**
     * Executes the application with the supplied command-line options.
     *
     * @param args the options parsed by Picocli
     */
    public static void main(String[] args) {
        int status = run(args, new PrintWriter(System.out, true), new PrintWriter(System.err, true));
        if (status != 0) {
            System.exit(status);
        }
    }

    /**
     * Runs the command with configurable output streams.
     *
     * @param args the command-line arguments
     * @param out the standard output writer
     * @param err the error output writer
     * @return zero on success, two on invalid usage, or one on generation failure
     */
    static int run(String[] args, PrintWriter out, PrintWriter err) {
        CommandLine commandLine = new CommandLine(new FractalCommand())
                .setOut(out).setErr(err).setCaseInsensitiveEnumValuesAllowed(true);
        commandLine.setExecutionExceptionHandler((exception, command, result) -> {
            err.println("Error: " + exception.getMessage());
            command.usage(err);
            return exception instanceof IllegalArgumentException ? 2 : 1;
        });
        if (args.length == 0) {
            commandLine.usage(out);
            return 0;
        }
        return commandLine.execute(args);
    }
}
