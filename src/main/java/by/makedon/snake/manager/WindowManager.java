package by.makedon.snake.manager;

import by.makedon.snake.util.Constants;
import by.makedon.snake.util.ResourceUtil;
import by.makedon.snake.window.GameWindow;

/**
 * @author Yahor Makedon
 */
public final class WindowManager {
    private static WindowManager instance;

    private GameWindow gameWindow;

    public static WindowManager getInstance() {
        if (instance == null) {
            instance = new WindowManager();
        }

        return instance;
    }

    public void createAndShowGameWindow() {
        int frameWidth = Integer.parseInt(ResourceUtil.getPropertyValue(Constants.FRAME_DEFAULT_WIDTH));
        int frameHeight = Integer.parseInt(ResourceUtil.getPropertyValue(Constants.FRAME_DEFAULT_HEIGHT));

        gameWindow = new GameWindow(frameWidth, frameHeight);
        gameWindow.showWindow();
    }

    public void destroyGameWindow() {
        gameWindow.dispose();
        gameWindow = null;
    }
}
