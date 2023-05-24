public class PopOperation extends Operation {
    @Override
    public void process(String[] args, Context context) {
        if (context == null) {
            System.out.println("Null context!");
            return;
        }
        context.popStack();
    }
}
