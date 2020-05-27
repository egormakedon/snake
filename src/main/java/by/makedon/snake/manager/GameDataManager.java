package by.makedon.snake.manager;

import by.makedon.snake.domain.Apple;
import by.makedon.snake.domain.Pixel;
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
    public static final Integer KEY_FREE_PIXEL_LIST = 0;
    public static final Integer KEY_SNAKE_PIXEL_LIST = 1;
    public static final Integer KEY_APPLE_PIXEL_LIST = 2;

    private static final List<Integer> snakeStartPositionCoordinateList;
    private static GameDataManager instance;

    static {
        snakeStartPositionCoordinateList = loadSnakeStartPosition();
    }

    private List<Pixel> gameMapPixelList;
    private Snake snake;
    private Apple apple;
    private Map<Integer, List<Pixel>> updatePixelMap;

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
        createUpdatePixelMap();
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

    private static List<Integer> loadSnakeStartPosition() {
        String snakeStartPosition = ResourceUtil.getPropertyValue(Constants.SNAKE_START_POSITION);
        String[] snakeStartPositionArray = snakeStartPosition.split(Constants.VERTICAL_SLASH);

        Validator.validateSnakeStartPosition(snakeStartPosition, snakeStartPositionArray);

        return Arrays.stream(snakeStartPositionArray)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private void createGameMapPixelList(int width, int height) {
        gameMapPixelList = new ArrayList<>(width * height);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                gameMapPixelList.add(new Pixel(i, j));
            }
        }

        Collections.shuffle(gameMapPixelList);
    }

    private void createSnake(int width, int height) {
        if (snake == null) {
            snake = new Snake();
        }

        List<Pixel> pixelList = new ArrayList<>(width * height);
        for (int i = 0; i + 1 < snakeStartPositionCoordinateList.size(); i+=2) {
            int x = snakeStartPositionCoordinateList.get(i);
            int y = snakeStartPositionCoordinateList.get(i + 1);

            Optional<Pixel> optionalPixel = getPixel(x, y);
            if (optionalPixel.isPresent()) {
                pixelList.add(optionalPixel.get());
            } else {
                throw new IllegalArgumentException("Snake start position doesn't match to the window size");
            }
        }

        snake.setPixelList(pixelList);
    }

    private void createApple() {
        if (apple == null) {
            apple = new Apple();
        }

        Optional<Pixel> optionalPixel = getFreePixel();
        if (optionalPixel.isPresent()) {
            apple.setPixel(optionalPixel.get());
        } else {
            throw new IllegalArgumentException("Snake start position occupy full window size");
        }
    }

    private void createUpdatePixelMap() {
        if (updatePixelMap == null) {
            updatePixelMap = new HashMap<>(3);
        }

        List<Pixel> freePixelList = gameMapPixelList.parallelStream()
                .filter(pixel -> !snake.getPixelList().contains(pixel) && !apple.getPixel().equals(pixel))
                .collect(Collectors.toList());
        List<Pixel> snakePixelList = snake.getPixelList();
        List<Pixel> applePixelList = Collections.singletonList(apple.getPixel());

        updatePixelMap.put(KEY_FREE_PIXEL_LIST, freePixelList);
        updatePixelMap.put(KEY_SNAKE_PIXEL_LIST, snakePixelList);
        updatePixelMap.put(KEY_APPLE_PIXEL_LIST, applePixelList);
    }
}
