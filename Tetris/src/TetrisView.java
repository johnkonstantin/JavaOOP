import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TetrisView extends JFrame implements ActionListener {
    private EventQueue<TetrisEvent> eventQueue;
    private Timer timer;

    public TetrisView(Field field, Field preview, Score score, EventQueue<TetrisEvent> eventQueue) {

        timer = new Timer(400, this);
        this.eventQueue = eventQueue;
        addKeyListener(new TetrisInputHandler(eventQueue));

        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Menu");
        menuBar.add(menu);

        JMenuItem newGameMenuItem = new JMenuItem("New game");
        newGameMenuItem.addActionListener(e -> {
            //new TetrisView(field, preview, score, eventQueue);
            eventQueue.addEvent(TetrisEvent.NEW_GAME);

        });
        menu.add(newGameMenuItem);

        JMenuItem scoresMenuItem = new JMenuItem("Scores");
        scoresMenuItem.addActionListener(e -> new ScoresView());
        menu.add(scoresMenuItem);

        JMenuItem aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.addActionListener(e -> new AboutView());
        menu.add(aboutMenuItem);

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(e -> System.exit(0));
        menu.add(exitMenuItem);

        setJMenuBar(menuBar);

        createPanels(field, preview, score);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                eventQueue.addEvent(TetrisEvent.GAME_CLOSED);
            }
        });
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setFocusable(true);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);

    }

    void createPanels(Field field, Field preview, Score score) {
        FieldView fieldView = new FieldView(field);
        fieldView.setPreferredSize(new Dimension(200, 440));
        FieldView previewView = new FieldView(preview);
        previewView.setPreferredSize(new Dimension(100, 100));
        ScoreView scoreView = new ScoreView(score);
        scoreView.setPreferredSize(new Dimension(100, 50));

        JPanel gamePanel = new JPanel();
        gamePanel.setPreferredSize(new Dimension(200, 440));
        gamePanel.add(fieldView);

        JPanel previewPanel = new JPanel();
        previewPanel.setPreferredSize(new Dimension(100, 100));
        previewPanel.add(previewView);

        JPanel pointsPanel = new JPanel();
        pointsPanel.setPreferredSize(new Dimension(100, 50));
        pointsPanel.add(scoreView);

        JPanel backPanel = new JPanel();
        backPanel.setLayout(new BoxLayout(backPanel, BoxLayout.Y_AXIS));
        backPanel.setPreferredSize(new Dimension(100, 440));
        backPanel.add(previewPanel);
        backPanel.add(pointsPanel);

        add(gamePanel, BorderLayout.CENTER);
        add(backPanel, BorderLayout.EAST);
    }

    public void run() {
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        eventQueue.addEvent(TetrisEvent.GAME_STEP);
    }
}
