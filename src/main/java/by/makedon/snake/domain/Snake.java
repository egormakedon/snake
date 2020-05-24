package by.makedon.snake.domain;

import by.makedon.snake.util.Constants;

/**
 * @author Yahor Makedon
 */
public class Snake {
    public static final int DEFAULT_LENGTH = 2;

    private int length;
    private int direction;

    public Snake() {
        this.length = DEFAULT_LENGTH;
        this.direction = Constants.DIRECTION_DOWN;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
