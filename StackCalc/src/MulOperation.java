public class MulOperation extends Operation {
    @Override
    public void process(String[] args, Context context) {
        if (context == null) {
            System.out.println("Null context!");
            return;
        }
        Double a = context.popStack();
        Double b = context.popStack();
        if (a == null || b == null) {
            System.out.println("Too few elements on stack!");
            return;
        }
        context.pushStack(a * b);
    }
}
