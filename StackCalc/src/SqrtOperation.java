public class SqrtOperation extends Operation {
    @Override
    public void process(String[] args, Context context) {
        if (context == null) {
            System.out.println("Null context!");
            return;
        }
        Double a = context.popStack();
        if (a == null) {
            System.out.println("Too few elements on stack!");
            return;
        }
        context.pushStack(Math.sqrt(a));
    }
}
