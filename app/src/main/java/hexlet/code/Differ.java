package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;


public class Differ{
    public static String generate(Path firstF, Path secondF) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String firstFile = Files.readString(firstF);
        String secondFile = Files.readString(secondF);
        HashMap<String, Object> firstFileAsMap
                = (HashMap<String, Object>) objectMapper.readValue(firstFile,
                new TypeReference<Map<String,Object>>(){});
        HashMap<String, Object> secondFileAsMap
                = (HashMap<String, Object>) objectMapper.readValue(secondFile,
                new TypeReference<Map<String,Object>>(){});
        var matchingResult = new HashMap<String, Object>();
        var recFirstFile = firstFileAsMap.entrySet();
        var recSecondFile = secondFileAsMap.entrySet();

        for (var checkFirstFile : recFirstFile) {
            if (!secondFileAsMap.containsKey(checkFirstFile.getKey())) {
                String keyAddFromFirstFile = "- " + checkFirstFile.getKey();
                matchingResult.put(keyAddFromFirstFile, checkFirstFile.getValue());
            }
            if (secondFileAsMap.containsKey(checkFirstFile.getKey())
                    && secondFileAsMap.get(checkFirstFile.getKey()).equals(checkFirstFile.getValue())) {
                String processedKey = "  " + checkFirstFile.getKey();
                matchingResult.put(processedKey, checkFirstFile.getValue());
            }
        }
        for (var checkSecondFile : recSecondFile) {
            if (!firstFileAsMap.containsKey(checkSecondFile.getKey())) {
                String keyAddFromSecondFile = "+ " + checkSecondFile.getKey();
                matchingResult.put(keyAddFromSecondFile, checkSecondFile.getValue());
            }
            if (firstFileAsMap.containsKey(checkSecondFile.getKey())
                    && !firstFileAsMap.get(checkSecondFile.getKey()).equals(checkSecondFile.getValue())) {
                String oldKey = "- " + checkSecondFile.getKey();
                String newKey = "+ " + checkSecondFile.getKey();
                matchingResult.put(oldKey, firstFileAsMap.get(checkSecondFile.getKey()));//
                matchingResult.put(newKey, checkSecondFile.getValue());
            }
        }
        SortedMap<String, Object> sortedRes = new TreeMap<>(matchingResult);
        StringBuilder resultMapAsString = new StringBuilder("{");
        for (String key : sortedRes.keySet()) {
            resultMapAsString.append("\n" + " ".repeat(4) + key + ": " + matchingResult.get(key) + ", ");
        }
        resultMapAsString.delete(resultMapAsString.length() - 2, resultMapAsString.length()).append("\n" + "}");
        String result = resultMapAsString.toString();
        return result;
    }
}
