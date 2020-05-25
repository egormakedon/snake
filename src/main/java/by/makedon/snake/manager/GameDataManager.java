package by.makedon.snake.manager;

import by.makedon.snake.domain.Apple;
import by.makedon.snake.domain.Pixel;
import by.makedon.snake.domain.Snake;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Yahor Makedon
 */
public final class GameDataManager {
    private static GameDataManager instance;

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
        pixelList.add(new Pixel(2, 1));
        pixelList.add(new Pixel(1, 1));

        snake.setPixelList(pixelList);
    }

    private void createApple() {
        if (apple == null) {
            apple = new Apple();
        }

        apple.setPixel(getFreePixel().get());
    }
}
