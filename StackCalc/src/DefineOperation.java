public class DefineOperation extends Operation {
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
        if (args.length < 2) {
            System.out.println("Too few DEFINE args!");
            return;
        }
        context.defineValue(args[0], Double.valueOf(args[1]));
    }
}
