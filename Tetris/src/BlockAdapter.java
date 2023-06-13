import java.util.ArrayList;

public class BlockAdapter {
    Block block;
    Field field;
    ArrayList<Point> points;
    int centerX;
    int centerY;

    public BlockAdapter(Block block, Field field, int x, int y) {
        this.field = field;
        this.block = block;
        centerX = x;
        centerY = y;

        placeBlockAt(x, y);
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
        placeBlockAt(centerX, centerY);
    }

    public void rotateLeft() {

        if (block.getClass() == CubeBlock.class) {
            return;
        }

        int[][] newPattern = new int[getBlock().getSize()][2];
        int[][] oldPattern = getBlock().getPattern().clone();
        for (int i = 0; i < getBlock().getSize(); i++) {
            newPattern[i][0] = -getBlock().getPattern()[i][1];
            newPattern[i][1] = getBlock().getPattern()[i][0];
        }
        block.setPattern(newPattern);
        if (checkCollisionsAt(centerX, centerY)) {
            placeBlockAt(centerX, centerY);
        } else {
            block.setPattern(oldPattern);
        }
    }

    public void rotateRight() {

        if (block.getClass() == CubeBlock.class) {
            return;
        }

        int[][] newPattern = new int[getBlock().getSize()][2];
        int[][] oldPattern = getBlock().getPattern().clone();
        for (int i = 0; i < getBlock().getSize(); i++) {
            newPattern[i][0] = getBlock().getPattern()[i][1];
            newPattern[i][1] = -getBlock().getPattern()[i][0];
        }
        block.setPattern(newPattern);
        if (checkCollisionsAt(centerX, centerY)) {
            placeBlockAt(centerX, centerY);
        } else {
            block.setPattern(oldPattern);
        }
    }

    public void fall() {
        while(move(0,1));
    }

    public boolean move(int dx, int dy) {
        int newX = centerX + dx;
        int newY = centerY + dy;
        if (checkCollisionsAt(newX, newY)) {
            placeBlockAt(newX, newY);
            return true;
        } else {
            return false;
        }
    }

    private boolean checkCollisionsAt(int x, int y) {

        for (int[] point : block.getPattern()) {
            int checkingX = x + point[0];
            int checkingY = y + point[1];
            if (!field.isFineX(checkingX) || !field.isFineY(checkingY)) {
                return false;
            }
            if (!isPointBelongsToMe(checkingX, checkingY) && field.getPointAt(checkingX, checkingY).getType() != null) {
                return false;
            }
        }
        return true;
    }

    private void placeBlockAt(int x, int y) {
        if (points != null) {
            for (Point point : points) {
                point.setType(null);
            }
            field.updatePoints(points);
        }
        points = new ArrayList<>();
        for (int[] point : block.getPattern()) {
            points.add(new Point(x + point[0], y + point[1], block.getClass()));
        }
        centerX = x;
        centerY = y;
        field.updatePoints(points);
    }

    private boolean isPointBelongsToMe(int x, int y) {
        for (Point p : points) {
            if (p.getX() == x && p.getY() == y) {
                return true;
            }
        }
        return false;
    }
}
