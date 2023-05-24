import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Stack;

public class Context {
    private final Stack<Double> _stack;
    private final HashMap<String, Double> _definedValues;

    Context() {
        _stack = new Stack<Double>();
        _definedValues = new HashMap<String, Double>();
    }

    public void pushStack(Double number) {
        _stack.push(number);
    }

    public Double popStack() {
        Double number;
        try {
            number = _stack.pop();
        }
        catch (EmptyStackException e) {
            return null;
        }
        return number;
    }

    public Double peekStack() {
        Double number;
        try {
            number = _stack.peek();
        }
        catch (EmptyStackException e) {
            return null;
        }
        return number;
    }

    public void defineValue(String name, Double value) {
        if (name == null) {
            System.out.println("Null name!");
            return;
        }
        if (value == null) {
            System.out.println("Null value!");
            return;
        }
        _definedValues.put(name, value);
    }

    public Double getDefinedValue(String name) {
        return _definedValues.get(name);
    }

}
