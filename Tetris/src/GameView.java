import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class GameView extends JPanel {

    static HashMap<Class<?>, Color> colorMap = new HashMap<>();
    static {
        colorMap.put(CubeBlock.class, new Color(204, 102, 102));
        colorMap.put(LBlock.class, new Color(102, 204, 102));
        colorMap.put(LineBlock.class, new Color(102, 102, 204));
        colorMap.put(MirroredLBlock.class, new Color(204, 204, 102));
        colorMap.put(SBlock.class, new Color(204, 102, 204));
        colorMap.put(TBlock.class, new Color(102, 204, 204));
        colorMap.put(ZBlock.class, new Color(218, 170, 0));
    }

    int fieldHeight;
    int fieldWidth;

    public GameView(int width, int height) {
        fieldHeight = height;
        fieldWidth = width;
    }

    int squareHeight() {
        return (int) getSize().getHeight() / fieldHeight;
    }

    int squareWidth() {
        return (int) getSize().getWidth() / fieldWidth;
    }

    void drawSquare(Graphics g, int j, int i, Class<?> blockClass, int boardTop) {
        int x = j * squareWidth();
        int y = i * squareHeight();

        Color color = colorPicker(blockClass);

        g.setColor(color);
        g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);

        g.setColor(color.brighter());
        g.drawLine(x, y + squareHeight() - 1, x, y);
        g.drawLine(x, y, x + squareWidth() - 1, y);

        g.setColor(color.darker());
        g.drawLine(x + 1, y + squareHeight() - 1,
                x + squareWidth() - 1, y + squareHeight() - 1);
        g.drawLine(x + squareWidth() - 1, y + squareHeight() - 1,
                x + squareWidth() - 1, y + 1);
    }

    private Color colorPicker(Class<?> blockClass) {
        return blockClass == null?  new Color(0,0,0) : colorMap.get(blockClass);
    }
}
