import java.util.List;
import java.util.Observable;

public class Field extends Observable {
    private int width;
    private int height;

    private Point[][] field;

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        field = new Point[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                field[i][j] = new Point(i, j);
            }
        }
    }

    public Point getPointAt(int x, int y) {
        return field[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isFineX(int x) {
        return (x < width && x >= 0);
    }

    public boolean isFineY(int y) {
        return (y < height && y >= 0);
    }

    public boolean updatePoints(List<Point> pointsList) {
        for (Point point : pointsList) {
            if (!isFineX(point.getX()) || !isFineY(point.getY())) {
                return false;
            }
        }
        for (Point point : pointsList) {
            field[point.getX()][point.getY()] = point;
        }
        setChanged();
        notifyObservers();
        return false;
    }

    public int clearFilledRows() {
        int count = 0;
        for (int y = 0; y < height; y++) {
            int filledPoints = 0;
            for (int x = 0; x < width; x++) {
                if (field[x][y].getType() != null) {
                    filledPoints++;
                }
            }
            if (filledPoints == width) {
                count++;
                for (int t = y - 1; t >= 0; t--) {
                    for (int x = 0; x < width; x++) {
                        if (t == 0) {
                            field[x][t].setType(null);
                        } else {
                            field[x][t+1].setType(field[x][t].getType());
                        }
                    }
                }
            }
        }
        if (count > 0) {
            setChanged();
            notifyObservers();
        }
        return count;
    }
}
