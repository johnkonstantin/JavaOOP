import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class OperationFabric {
    private String[] _operationName;
    private String[] _className;
    private final String _confFilePath = "conf";

    OperationFabric() {
        InputStream stream = null;
        try {
            stream = this.getClass().getClassLoader().getResourceAsStream(_confFilePath);
        }
        catch (NullPointerException e) {
            System.out.println("Null conf file path!");
            System.exit(0);
        }
        if (stream == null) {
            System.out.println("Where the conf file, Johnny?");
            System.exit(0);
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
        _operationName = new String[0];
        _className = new String[0];
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                String[] t = line.split(" ");
                if (t.length != 2) {
                    System.out.println("Who corrupted the conf file, Johnny?");
                    System.exit(0);
                }
                List<String> list = new ArrayList<String>(Arrays.asList(_operationName));
                list.add(t[0]);
                _operationName = list.toArray(new String[0]);
                list = new ArrayList<String>(Arrays.asList(_className));
                list.add(t[1]);
                _className = list.toArray(new String[0]);
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public Operation getOperation(String name) {
        for (int i = 0; i < _operationName.length; ++i) {
            if (Objects.equals(_operationName[i], name)) {
                Class cl;
                try {
                    cl = Class.forName(_className[i]);
                }
                catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                Operation op;
                try {
                    op = (Operation) cl.newInstance();
                }
                catch (InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                return op;

            }
        }
        System.out.println("Wrong operation name!");
        return null;
    }
}
