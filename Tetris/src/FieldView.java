import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class FieldView extends GameView {

    private Field field;

    public FieldView(Field field) {
        super(field.getWidth(), field.getHeight());
        this.field = field;
        field.addObserver(new FieldObserver());
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Dimension size = getSize();
        int boardTop = (int) size.getHeight() - fieldHeight * squareHeight();

        for (int i = 0; i < fieldHeight; i++) {
            for (int j = 0; j < fieldWidth; j++) {
                drawSquare(g, j, i, field.getPointAt(j, i).getType(), boardTop);
            }
        }
    }

    private class FieldObserver implements Observer {
        @Override
        public void update(Observable o, Object arg) {
            repaint();
        }
    }
}
