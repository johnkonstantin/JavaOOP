import java.awt.event.WindowEvent;

public class TetrisController implements Runnable {

    private int width, height;
    private Field field;
    private Field preview;
    private BlockFactory factory;
    private Block currentBlock;
    private Block nextBlock;
    private BlockAdapter fieldAdapter;
    private BlockAdapter previewAdapter;
    private TetrisView view;
    private EventQueue<TetrisEvent> eventQueue;
    private Score score;

    private boolean isExit;
    private boolean isGameOver;

    public TetrisController(int width, int height) {
        this.width = width;
        this.height = height;
        createGame(width, height);
    }

    private void createGame(int width, int height) {
        score = new Score();
        field = new Field(width, height);
        preview = new Field(5, 5);
        factory = new BlockFactory();
        currentBlock = factory.createRandomBlock();
        nextBlock = factory.createRandomBlock();
        fieldAdapter = new BlockAdapter(currentBlock, field, field.getWidth() / 2, 0);
        previewAdapter = new BlockAdapter(nextBlock, preview, preview.getWidth() / 2, 0);
        eventQueue = new EventQueue<>();
    }

    public void run() {
        isExit = false;
        isGameOver = false;
        view = new TetrisView(field, preview, score, eventQueue);
        view.run();

        while (!isExit) {
            if (eventQueue.hasEvent()) {
                TetrisEvent tetrisEvent = eventQueue.getEvent();
                if (!isGameOver) {
                    switch (tetrisEvent) {
                        case NEW_GAME:
                            view.dispatchEvent(new WindowEvent(view, WindowEvent.WINDOW_CLOSING));
                            createGame(width, height);
                            this.run();
                            break;
                        case MOVE_LEFT:
                            moveBlockLeft();
                            break;
                        case MOVE_RIGHT:
                            moveBlockRight();
                            break;
                        case ROTATE_LEFT:
                            rotateBlockLeft();
                            break;
                        case ROTATE_RIGHT:
                            rotateBlockRight();
                            break;
                        case FALL:
                            fallBlock();
                            break;
                        case GAME_STEP:
                            makeStep();
                            break;
                        case GAME_CLOSED:
                            endGame();
                            break;
                    }
                }
                else {
                    if (tetrisEvent == TetrisEvent.NEW_GAME) {
                        view.dispatchEvent(new WindowEvent(view, WindowEvent.WINDOW_CLOSING));
                        createGame(width, height);
                        this.run();
                    }
                }
            }
        }

    }

    public void makeStep() {
        if (!fieldAdapter.move(0, 1)) {
            for (int i = 0; i < field.getWidth(); i++) {
                if (field.getPointAt(i, 0).getType() != null) {
                    endGame();
                    isGameOver = true;
                    isExit = false;
                    return;
                }
            }
            score.add(field.clearFilledRows());
            currentBlock = nextBlock;
            fieldAdapter = new BlockAdapter(currentBlock, field, field.getWidth() / 2, 0);
            nextBlock = factory.createRandomBlock();
            previewAdapter.setBlock(nextBlock);
        }
    }

    private void endGame() {
        isExit = true;
        Score.save(score.get());
    }

    public void moveBlockLeft() {
        fieldAdapter.move(-1, 0);
    }

    public void moveBlockRight() {
        fieldAdapter.move(1, 0);
    }

    public void fallBlock() {
        fieldAdapter.fall();
    }

    public void rotateBlockLeft() {
        fieldAdapter.rotateLeft();
    }

    public void rotateBlockRight() {
        fieldAdapter.rotateRight();
    }
}
