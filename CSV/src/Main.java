import java.io.*;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("No input file!");
            System.exit(0);
        }

        Reader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream(args[0]));
        }
        catch (IOException e) {
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
            System.exit(0);
        }

        WordsCounter counter = new WordsCounter(reader);
        HashMap<String, Integer> countedWords = counter.getCountedWords();
        reader.close();

        Writer writer = null;
        try {
            writer = new OutputStreamWriter(new FileOutputStream("output.csv"));
        }
        catch (IOException e) {
            System.err.println("Error while open output file: " + e.getLocalizedMessage());
            System.exit(0);
        }

        CSVWriter.printCSV(writer, countedWords);
        writer.close();
    }
}