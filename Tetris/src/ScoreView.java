import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class ScoreView extends JPanel implements Observer {

    Score score;
    JTextArea scoreArea;

    ScoreView(Score score) {
        this.score = score;
        score.addObserver(this);
        scoreArea = new JTextArea(score.get().toString());
        add(scoreArea);
    }

    @Override
    public void update(Observable o, Object arg) {
        scoreArea.setText(score.get().toString());
    }
}
