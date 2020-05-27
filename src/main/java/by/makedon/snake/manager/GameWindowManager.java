package by.makedon.snake.manager;

import by.makedon.snake.util.Constants;
import by.makedon.snake.util.ResourceUtil;
import by.makedon.snake.window.GameWindow;

/**
 * @author Yahor Makedon
 */
public final class GameWindowManager {
    public static final int FRAME_SCALE = Integer.parseInt(ResourceUtil.getPropertyValue(Constants.FRAME_SCALE));

    private static GameWindowManager instance;

    private GameWindow gameWindow;
    private int width;
    private int height;

    private GameWindowManager() {
    }

    public static GameWindowManager getInstance() {
        if (instance == null) {
            instance = new GameWindowManager();
        }

        return instance;
    }

    public void show(int width, int height) {
        this.width = width;
        this.height = height;

        if (gameWindow == null) {
            gameWindow = new GameWindow();
        }

        gameWindow.setSize(width, height);
        gameWindow.showWindow();
    }

    public void hide() {
        gameWindow.hideWindow();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}