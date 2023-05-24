import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        Calculator calculator;
        if (args.length < 1) {
            System.out.println("No input file, use stdout.");
            calculator = new Calculator();
        }
        else {
            FileInputStream fileInputStream;
            try {
                fileInputStream = new FileInputStream(args[0]);
                calculator = new Calculator(fileInputStream);
            } catch (FileNotFoundException e) {
                System.out.println("Wrong file path, use stdout");
                calculator = new Calculator();
            }
        }
        while (calculator.processOperation()) {};
    }
}