package hexlet.code.formatters;

import hexlet.code.Comparison.Status;
import hexlet.code.Comparison;
import java.util.Map;
import static java.lang.String.format;


public class StylishFormatter {
    private static final String ADDSYMBOL = "  +";
    private static final String REMOVESYMBOL = "  -";
    private static final String NONSYMBOL = "   ";


    public static String render(Map<String, Object> differences) throws Exception {

        var result = new StringBuilder();

        for (Map.Entry<String, Object> entry : differences.entrySet()) {

            String key = entry.getKey();
            Status value = (Status) entry.getValue();
            String status = value.getStatusName();

            switch (status) {
                case Comparison.CHANGED -> {

                    String oldValue = stringify(value.getOldValue());
                    String newValue = stringify(value.getNewValue());
                    String name = stringify(key);

                    result.append(format("%s %s: %s", REMOVESYMBOL, name, oldValue)).append("\n");
                    result.append(format("%s %s: %s", ADDSYMBOL, name, newValue)).append("\n");

                }
                case Comparison.ADDED -> {

                    String newValue = stringify(value.getNewValue());
                    String name = stringify(key);

                    result.append(format("%s %s: %s", ADDSYMBOL, name, newValue)).append("\n");

                }
                case Comparison.DELETED -> {

                    String oldValue = stringify(value.getOldValue());
                    String name = stringify(key);

                    result.append(format("%s %s: %s", REMOVESYMBOL, name, oldValue)).append("\n");

                }
                case Comparison.UNCHANGED -> {

                    String oldValue = stringify(value.getOldValue());
                    String name = stringify(key);

                    result.append(format("%s %s: %s", NONSYMBOL, name, oldValue)).append("\n");

                }
                default -> {
                    throw new Exception("Unknown status: '" + status + "'");
                }
            }
        }

        return format("{\n%s}", result);
    }

    private static String stringify(Object value) {

        if (value == null) {
            return "null";
        }
        return value.toString();
    }
}
