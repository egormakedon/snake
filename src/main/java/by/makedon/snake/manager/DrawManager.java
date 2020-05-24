package by.makedon.snake.manager;

import javax.swing.*;

/**
 * @author Yahor Makedon
 */
public final class DrawManager {
    private static DrawManager instance;
    private JComponent drawPanel;

    private DrawManager() {
        drawPanel = new DrawPanel();
    }

    public static DrawManager getInstance() {
        if (instance == null) {
            instance = new DrawManager();
        }

        return instance;
    }

    public JComponent getDrawPanel() {
        return drawPanel;
    }

    private final class DrawPanel extends JComponent {

    }
}
