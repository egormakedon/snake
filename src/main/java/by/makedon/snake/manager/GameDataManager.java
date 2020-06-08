package by.makedon.snake.manager;

import by.makedon.snake.domain.Apple;
import by.makedon.snake.domain.Pixel;
import by.makedon.snake.domain.PixelType;
import by.makedon.snake.domain.Snake;
import by.makedon.snake.util.Constants;
import by.makedon.snake.util.ResourceUtil;
import by.makedon.snake.validator.Validator;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Yahor Makedon
 */
public final class GameDataManager {
    private static final List<Integer> snakeStartPositionCoordinateList;
    private static final int snakeStartDirection;
    private static GameDataManager instance;

    static {
        snakeStartPositionCoordinateList = loadSnakeStartPosition();
        snakeStartDirection = loadSnakeStartDirection();
    }

    private List<Pixel> gameMapPixelList;
    private Snake snake;
    private Apple apple;
    private int currentSnakeDirection;
    private int newSnakeDirection;

    private GameDataManager() {
    }

    public static GameDataManager getInstance() {
        if (instance == null) {
            instance = new GameDataManager();
        }

        return instance;
    }

    public void createData(int width, int height) {
        createGameMapPixelList(width, height);
        createSnake(width, height);
        createApple();
        createSnakeDirection();
    }

    public Optional<Pixel> getPixel(int x, int y) {
        return gameMapPixelList.parallelStream()
                .filter(pixel -> pixel.getX() == x && pixel.getY() == y)
                .findAny();
    }

    public Optional<Pixel> getFreePixel() {
        return gameMapPixelList.parallelStream()
                .filter(pixel -> !snake.getPixelList().contains(pixel))
                .findAny();
    }

    public void updateSnakeDirection() {
        currentSnakeDirection = newSnakeDirection;
    }

    public List<Pixel> getGameMapPixelList() {
        return gameMapPixelList;
    }

    public List<Pixel> getSnakePixelList() {
        return snake.getPixelList();
    }

    public Apple getApple() {
        return apple;
    }

    public int getCurrentSnakeDirection() {
        return currentSnakeDirection;
    }

    public void setNewSnakeDirection(int newSnakeDirection) {
        this.newSnakeDirection = newSnakeDirection;
    }

    private static List<Integer> loadSnakeStartPosition() {
        String snakeStartPosition = ResourceUtil.getPropertyValue(Constants.SNAKE_START_POSITION);
        String[] snakeStartPositionArray = snakeStartPosition.split(Constants.VERTICAL_SLASH);

        Validator.validateSnakeStartPosition(snakeStartPosition, snakeStartPositionArray);

        return Arrays.stream(snakeStartPositionArray)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static int loadSnakeStartDirection() {
        String snakeStartDirectionString = ResourceUtil.getPropertyValue(Constants.SNAKE_START_DIRECTION);

        Validator.validateSnakeStartDirection(snakeStartDirectionString);

        int snakeStartDirection = Constants.EMPTY_INT;
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

    private void createGameMapPixelList(int width, int height) {
        gameMapPixelList = new ArrayList<>(width * height);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                gameMapPixelList.add(new Pixel(i, j, PixelType.FREE));
            }
        }

        Collections.shuffle(gameMapPixelList);
    }

    private void createSnake(int width, int height) {
        if (snake == null) {
            snake = new Snake();
        }

        List<Pixel> pixelList = new ArrayList<>(width * height);

        for (int i = 0; i + 1 < snakeStartPositionCoordinateList.size(); i += 2) {
            int x = snakeStartPositionCoordinateList.get(i);
            int y = snakeStartPositionCoordinateList.get(i + 1);
            Optional<Pixel> optionalPixel = getPixel(x, y);

            if (optionalPixel.isPresent()) {
                Pixel pixel = optionalPixel.get();
                pixel.setPixelType(PixelType.SNAKE_BODY);
                pixelList.add(pixel);
            } else {
                throw new IllegalArgumentException("Snake start position doesn't match to the window size");
            }
        }

        pixelList.get(0).setPixelType(PixelType.SNAKE_HEAD);

        snake.setPixelList(pixelList);
    }

    private void createApple() {
        if (apple == null) {
            apple = new Apple();
        }

        Optional<Pixel> optionalPixel = getFreePixel();

        if (optionalPixel.isPresent()) {
            Pixel pixel = optionalPixel.get();
            pixel.setPixelType(PixelType.APPLE);
            apple.setPixel(pixel);
        } else {
            throw new IllegalArgumentException("Snake start position occupy full window size");
        }
    }

    private void createSnakeDirection() {
        currentSnakeDirection = snakeStartDirection;
        newSnakeDirection = snakeStartDirection;
    }
}
