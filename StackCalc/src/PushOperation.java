import java.util.Objects;

public class PushOperation extends Operation {
    @Override
    public void process(String[] args, Context context) {
        if (args == null) {
            System.out.println("Null args!");
            return;
        }
        if (context == null) {
            System.out.println("Null context!");
            return;
        }
        if (args.length < 1) {
            System.out.println("Too few PUSH args!");
            return;
        }
        Double a = context.getDefinedValue(args[0]);
        try {
            context.pushStack(Objects.requireNonNullElseGet(a, () -> Double.valueOf(args[0])));
        }
        catch (NumberFormatException e) {
            System.out.println("Wrong argument!");
        }

    }
}
