import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Calculator {
    private Context _context = null;
    private OperationFabric _fabric;
    private BufferedReader _reader = null;

    Calculator(InputStream input) {
        _context = new Context();
        if (input == null) {
            throw new RuntimeException("Input stream is null!");
        }
        _fabric = new OperationFabric();
        _reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
    }
    Calculator() {
        _context = new Context();
        _fabric = new OperationFabric();
        _reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
    }

    public boolean processOperation() {
        String line;
        try {
            line = _reader.readLine();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (line == null) {
            return false;
        }
        if (line.charAt(0) == '#') {
            return true;
        }
        String[] splittedLine = line.split(" ");
        String operationName = splittedLine[0];
        Operation operation = _fabric.getOperation(operationName);
        if (operation != null) {
            operation.process(Arrays.copyOfRange(splittedLine, 1, splittedLine.length), _context);
        }
        return true;
    }

}