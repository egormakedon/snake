package by.makedon.snake.manager;

/**
 * @author Yahor Makedon
 */
public final class DrawManager {
    private static DrawManager instance;

    public static DrawManager getInstance() {
        if (instance == null) {
            instance = new DrawManager();
        }

        return instance;
    }
}
