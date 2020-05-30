package by.makedon.snake.manager;

/**
 * @author Yahor Makedon
 */
public final class GameActionManager {
    private static GameActionManager instance;

    private GameActionManager() {
    }

    public static GameActionManager getInstance() {
        if (instance == null) {
            instance = new GameActionManager();
        }

        return instance;
    }

    public void start() {
        GameWindowManager.getInstance().repaint();
        GameDataManager.getInstance().flushUpdatePixelList();

        //TODO

        GameDataManager.getInstance().updateSnakeDirection();
    }
}
