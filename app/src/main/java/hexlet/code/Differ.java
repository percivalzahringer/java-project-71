package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;


public class Differ {
    public static String generate(String firstFilepath, String secondFilepath, String formatName)
            throws Exception {

        Map<String, Object> parsedFirstFile = getData(firstFilepath);
        Map<String, Object> parsedSecondFile = getData(secondFilepath);
        Map<String, Object> differences = Comparison.genDiff(parsedFirstFile, parsedSecondFile);

        return Formatter.format(differences, formatName);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }


    private static Map<String, Object> getData(String filePath) throws Exception {
        Path fullPath = getFullPath(filePath);
        if (!Files.exists(fullPath)) {
            throw new Exception("File '" + fullPath + "' does not exist");
        }
        String content = Files.readString(fullPath);
        String dataFormat = getDataFormat(filePath);
        return Parser.parse(content, dataFormat);
    }

    private static Path getFullPath(String filePath) {
        return Paths.get(filePath).toAbsolutePath().normalize();
    }

    private static String getDataFormat(String filePath) {
        int index = filePath.lastIndexOf('.');
        return index > 0
                ? filePath.substring(index + 1)
                : "";
    }
}
