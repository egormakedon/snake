package by.makedon.snake.manager;

import by.makedon.snake.domain.Apple;
import by.makedon.snake.domain.Pixel;
import by.makedon.snake.domain.Snake;
import by.makedon.snake.util.Constants;
import by.makedon.snake.util.ResourceUtil;
import by.makedon.snake.validator.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Yahor Makedon
 */
public final class GameDataManager {
    private static final List<Pixel> snakeStartPositionPixelList;
    private static GameDataManager instance;

    static {
        snakeStartPositionPixelList = loadSnakeStartPosition();
    }

    private List<Pixel> mapPixelList;
    private Snake snake;
    private Apple apple;

    private GameDataManager() {
    }

    public static GameDataManager getInstance() {
        if (instance == null) {
            instance = new GameDataManager();
        }

        return instance;
    }

    public void createData(int width, int height) {
        createMapPixelList(width, height);
        createSnake(width, height);
        createApple();
    }

    public Optional<Pixel> getFreePixel() {
        return mapPixelList.parallelStream()
                .filter(pixel -> !snake.getPixelList().contains(pixel))
                .findAny();
    }

    private static List<Pixel> loadSnakeStartPosition() {
        String snakeStartPosition = ResourceUtil.getPropertyValue(Constants.SNAKE_START_POSITION);
        String[] snakeStartPositionArray = snakeStartPosition.split(Constants.VERTICAL_SLASH);

        Validator.validateSnakeStartPosition(snakeStartPosition, snakeStartPositionArray);

        List<Pixel> snakeStartPositionPixelList = new ArrayList<>(snakeStartPositionArray.length / 2);
        for (int i = 0; i + 1 < snakeStartPositionArray.length; i+=2) {
            int x = Integer.parseInt(snakeStartPositionArray[i]);
            int y = Integer.parseInt(snakeStartPositionArray[i + 1]);

            snakeStartPositionPixelList.add(new Pixel(x, y));
        }

        return snakeStartPositionPixelList;
    }

    private void createMapPixelList(int width, int height) {
        mapPixelList = new ArrayList<>(width * height);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                mapPixelList.add(new Pixel(i, j));
            }
        }

        Collections.shuffle(mapPixelList);
    }

    private void createSnake(int width, int height) {
        if (snake == null) {
            snake = new Snake();
        }

        List<Pixel> pixelList = new ArrayList<>(width * height);
        pixelList.addAll(snakeStartPositionPixelList);

        snake.setPixelList(pixelList);
    }

    private void createApple() {
        if (apple == null) {
            apple = new Apple();
        }

        apple.setPixel(getFreePixel().get());
    }
}
