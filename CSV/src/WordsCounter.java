import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Locale;

public class WordsCounter {
    private final HashMap<String, Integer> _words;
    private final Reader _reader;

    public WordsCounter(Reader reader) {
        _words = new HashMap<String, Integer>();
        _reader = reader;
    }

    public HashMap<String, Integer> getCountedWords() throws IOException {
        if (_reader == null) {
            return null;
        }
        char[] buffer = new char[1024];
        int n;
        StringBuilder word = new StringBuilder();
        while ((n = _reader.read(buffer, 0, 1024)) > 0) {
            for (int i = 0; i < n; ++i) {
                if (Character.isLetterOrDigit(buffer[i])) {
                    word.append(buffer[i]);
                }
                else if (!word.isEmpty()){
                    String wordInLowerCase = word.toString().toLowerCase(Locale.ROOT);
                    if (_words.containsKey(wordInLowerCase)) {
                        _words.replace(wordInLowerCase, _words.get(wordInLowerCase) + 1);
                    }
                    else {
                        _words.put(wordInLowerCase, 1);
                    }
                    word = new StringBuilder();
                }
            }
        }
        return _words;
    }
}
