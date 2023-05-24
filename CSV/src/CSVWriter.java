import java.io.IOException;
import java.io.Writer;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;

public class CSVWriter {
    public static void printCSV(Writer writer, HashMap<String, Integer> words) throws IOException {
        if (writer == null) {
            System.err.println("Writer is null!");
            return;
        }

        Map<String, Integer> sortedMap = words.entrySet().stream()
                .sorted(Comparator.comparingInt(e -> -e.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> {
                            throw new AssertionError();
                        },
                        LinkedHashMap::new
                ));


        int wordsCount = 0;
        for (Map.Entry<String, Integer> o : sortedMap.entrySet()) {
            wordsCount += o.getValue();
        }

        String newline = System.getProperty("line.separator");
        writer.write("Слово,Частота,Частота (в %)");
        writer.write(newline);
        for (Map.Entry<String, Integer> o : sortedMap.entrySet()) {
            writer.write(o.getKey());
            writer.write(",");
            writer.write(String.valueOf(o.getValue()));
            writer.write(",");
            writer.write(String.format("%.3f", 100.0 * o.getValue() / wordsCount).replace(',', '.'));
            writer.write(newline);
        }
    }
}
