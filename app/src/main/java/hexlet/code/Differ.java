package hexlet.code;

import picocli.CommandLine.Option;
import picocli.CommandLine.Command;

@Command(name = "gendiff",
        description = "Compares two configuration files and shows a difference.")

public class Differ implements Runnable{

    @Option(names = { "-f", "--file" }, paramLabel = "ARCHIVE", description = "the archive file")
    boolean archive;

    @Option (names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    boolean help;
    @Option (names = {"-V", "--version"}, description = "Print version information and exit.") boolean V;

    @Override
    public void run() {
        System.out.println("");
    }
}