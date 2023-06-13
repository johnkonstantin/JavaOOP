import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BlockFactory {
    private static final String mFilename = "/command_list.properties";
    private static final String SEPARATOR = "=";

    Map<String, String> factory;

    public BlockFactory() {
        factory = new HashMap<>();
        InputStream stream = BlockFactory.class.getResourceAsStream(mFilename);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                if (line.length() > 0) {
                    String[] parts = line.split(SEPARATOR);
                    if (parts.length == 2) {
                        factory.put(parts[0].toLowerCase(), parts[1]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Block createRandomBlock() {
        Random generator = new Random();
        Object[] values = factory.values().toArray();
        String randomValue = (String) values[generator.nextInt(values.length)];

        Block block = null;
        try {
            Class blockClass = Class.forName(randomValue);
            try {
                block = (Block) blockClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException| NullPointerException e){
            block = null;
        }
        return block;
    }
}
