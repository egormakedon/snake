package by.makedon.snake.util;

/**
 * @author Yahor Makedon
 */
public final class Constants {
    public static final String APPLICATION_PROPERTIES_FILENAME = "application.properties";

    public static final String FRAME_NAME = "frame.name";
    public static final String FRAME_SCALE = "frame.scale";
    public static final String FRAME_DEFAULT_WIDTH = "frame.default.width";
    public static final String FRAME_DEFAULT_HEIGHT = "frame.default.height";

    public static final String SNAKE_START_POSITION = "snake.startPosition";
    public static final String SNAKE_START_DIRECTION = "snake.startDirection";

    public static final String SNAKE_START_DIRECTION_U = "u";
    public static final String SNAKE_START_DIRECTION_D = "d";
    public static final String SNAKE_START_DIRECTION_L = "l";
    public static final String SNAKE_START_DIRECTION_R = "r";

    public static final int SNAKE_DIRECTION_UP = 0;
    public static final int SNAKE_DIRECTION_DOWN = 1;
    public static final int SNAKE_DIRECTION_LEFT = 2;
    public static final int SNAKE_DIRECTION_RIGHT = 3;

    public static final String VERTICAL_SLASH = "\\|";

    public static final int PAUSE = -1;

    public static final int EMPTY_INT = Integer.MIN_VALUE;

    private Constants() {
    }
}
