package by.makedon.snake.manager;

import by.makedon.snake.util.Constants;
import by.makedon.snake.util.ResourceUtil;
import by.makedon.snake.validator.Validator;

/**
 * @author Yahor Makedon
 */
public final class GameActionManager {
    private static final int snakeStartDirection;
    private static GameActionManager instance;

    static {
        snakeStartDirection = loadSnakeStartDirection();
    }

    private GameActionManager() {
    }

    public static GameActionManager getInstance() {
        if (instance == null) {
            instance = new GameActionManager();
        }

        return instance;
    }

    private static int loadSnakeStartDirection() {
        String snakeStartDirectionString = ResourceUtil.getPropertyValue(Constants.SNAKE_START_DIRECTION);

        Validator.validateSnakeStartDirection(snakeStartDirectionString);

        int snakeStartDirection = Integer.MIN_VALUE;
        switch (snakeStartDirectionString) {
            case Constants.SNAKE_START_DIRECTION_U:
                snakeStartDirection = Constants.SNAKE_DIRECTION_UP;
                break;
            case Constants.SNAKE_START_DIRECTION_D:
                snakeStartDirection = Constants.SNAKE_DIRECTION_DOWN;
                break;
            case Constants.SNAKE_START_DIRECTION_L:
                snakeStartDirection = Constants.SNAKE_DIRECTION_LEFT;
                break;
            case Constants.SNAKE_START_DIRECTION_R:
                snakeStartDirection = Constants.SNAKE_DIRECTION_RIGHT;
                break;
        }

        return snakeStartDirection;
    }
}
