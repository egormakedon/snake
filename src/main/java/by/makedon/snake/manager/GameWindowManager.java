package by.makedon.snake.manager;

import by.makedon.snake.util.Constants;
import by.makedon.snake.util.ResourceUtil;
import by.makedon.snake.window.DrawPanel;
import by.makedon.snake.window.GameWindow;

/**
 * @author Yahor Makedon
 */
public final class GameWindowManager {
    public static final int FRAME_SCALE = Integer.parseInt(ResourceUtil.getPropertyValue(Constants.FRAME_SCALE));

    private static GameWindowManager instance;

    private GameWindow gameWindow;
    private DrawPanel drawPanel;

    private GameWindowManager() {
    }

    public static GameWindowManager getInstance() {
        if (instance == null) {
            instance = new GameWindowManager();
        }

        return instance;
    }

    public void show(int width, int height) {
        if (drawPanel == null) {
            drawPanel = new DrawPanel();
        }

        if (gameWindow == null) {
            gameWindow = new GameWindow();
            gameWindow.add(drawPanel);
        }

        drawPanel.setPreferredSize(width, height);

        gameWindow.pack();
        gameWindow.setLocationRelativeTo(null);
        gameWindow.showWindow();

        repaint();
    }

    public void hide() {
        gameWindow.hideWindow();
    }

    public void repaint() {
        drawPanel.repaint();
    }
}
