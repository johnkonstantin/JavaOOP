import javax.swing.*;

public class AboutView extends JFrame {
    public AboutView() {
        JTextArea textArea = new JTextArea("A bear walks through the forest, sees - the car is on fire. He got into it and burned.");
        add(textArea);
        setSize(300, 150);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setFocusable(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
