public class PrintOperation extends Operation {
    @Override
    public void process(String[] args, Context context) {
        if (context == null) {
            System.out.println("Null context!");
            return;
        }
        Double a = context.peekStack();
        if (a == null) {
            System.out.println("Too few elements on stack!");
            return;
        }
        System.out.println(a);
    }
}
