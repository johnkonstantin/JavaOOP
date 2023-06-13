public class LineBlock implements Block {

    int[][] pattern = {
            {-1, 0}, {0, 0}, {1, 0}, {2, 0}
    };

    @Override
    public int[][] getPattern() {
        return pattern;
    }

    @Override
    public void setPattern(int[][] newPattern) {
        this.pattern = newPattern;
    }

    @Override
    public int getSize() {
        return pattern.length;
    }
}
