package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;

@Command(name = "gendiff",
        description = "Compares two configuration files and shows a difference.")

public class App implements Callable<Integer> {

    static final String HOME = System.getProperty("user.home");

    @Option(names = {"-f", "--file"}, description = "output file", paramLabel = "file")
    String file;
    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    boolean helpRequested = false;
    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
    boolean versionInfoRequested = false;
    @Parameters(index = "0", description = "path to first file", paramLabel = "file1") private String file1;
    @Parameters(index = "1", description = "path to second file", paramLabel = "file2") private String file2;

    public static void main(String[] args) {
        System.exit(new CommandLine(new App()).execute(args));
    }

    @Override
    public Integer call() throws Exception {
        Path path1 = Paths.get(file1).toAbsolutePath().normalize();
        Path path2 = Paths.get(file2).toAbsolutePath().normalize();
        var differResult = Differ.generate(path1, path2);
        System.out.println(differResult);
        return 0;
    }
}