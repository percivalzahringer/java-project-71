package hexlet.code.formatters;

import hexlet.code.Comparison.Status;
import hexlet.code.Comparison;
import java.util.List;
import java.util.Map;
import static java.lang.String.format;


public class PlainFormatter {

    public static String render(Map<String, Object> differences) throws Exception {

        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, Object> entry : differences.entrySet()) {

            String key = entry.getKey();
            Status value = (Status) entry.getValue();
            String status = value.getStatusName();

            switch (status) {
                case Comparison.CHANGED -> {
                    String oldValue = stringify(value.getOldValue());
                    String newValue = stringify(value.getNewValue());
                    String name = stringify(key);

                    result.append(format("Property %s was updated. From %s to %s",
                                    name, oldValue, newValue))
                            .append("\n");

                }
                case Comparison.ADDED -> {
                    String newValue = stringify(value.getNewValue());
                    String name = stringify(key);

                    result.append(format("Property %s was added with value: %s",
                                    name, newValue))
                            .append("\n");

                }
                case Comparison.DELETED -> {
                    String name = stringify(key);

                    result.append(format("Property %s was removed", name))
                            .append("\n");

                }
                case Comparison.UNCHANGED -> {
                    continue;

                }
                default -> {
                    throw new Exception("Unknown status: '" + status + "'");
                }
            }
        }

        return result.toString().trim();
    }

    private static String stringify(Object value) {

        if (value == null) {
            return "null";
        }

        if (value instanceof String) {
            return "'" + value + "'";
        }

        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }

        return value.toString();
    }
}
