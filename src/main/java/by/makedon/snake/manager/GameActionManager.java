package by.makedon.snake.manager;

import by.makedon.snake.domain.Apple;
import by.makedon.snake.domain.Pixel;
import by.makedon.snake.domain.PixelType;
import by.makedon.snake.util.Constants;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

/**
 * @author Yahor Makedon
 */
public final class GameActionManager implements ActionListener {
    private static GameActionManager instance;

    private final Timer timer;
    private boolean isPaused;
    private boolean isGameOver;
    private boolean isGameWin;

    private GameActionManager() {
        timer = new Timer(Constants.EMPTY_INT, this);
    }

    public static GameActionManager getInstance() {
        if (instance == null) {
            instance = new GameActionManager();
        }

        return instance;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        moveSnake();

        if (isGameOver) {
            gameOver();
            return;
        }

        updateApple();

        if (isGameWin) {
            gameWin();
            return;
        }

        GameDataManager.getInstance().updateSnakeDirection();
        GameWindowManager.getInstance().repaint();
    }

    public void start(int speed) {
        isPaused = false;
        isGameOver = false;
        isGameWin = false;

        timer.setDelay(speed);
        timer.start();
    }

    public void pause() {
        if (isPaused) {
            timer.start();
        } else {
            timer.stop();
        }

        isPaused = !isPaused;
    }

    private void moveSnake() {
        List<Pixel> snakePixelList = GameDataManager.getInstance().getSnakePixelList();
        Apple apple = GameDataManager.getInstance().getApple();

        Pixel nextPixel = getNextPixel(snakePixelList.get(0));

        if (snakePixelList.contains(nextPixel)) {
            isGameOver = true;
        } else if (nextPixel.equals(apple.getPixel())) {
            apple.setPixel(null);
            updateSnake(snakePixelList, nextPixel, true);
        } else {
            updateSnake(snakePixelList, nextPixel, false);
        }
    }

    private void updateApple() {
        Apple apple = GameDataManager.getInstance().getApple();

        if (apple.getPixel() == null) {
            Optional<Pixel> freePixelOptional = GameDataManager.getInstance().getFreePixel();

            if (freePixelOptional.isPresent()) {
                Pixel pixel = freePixelOptional.get();
                pixel.setPixelType(PixelType.APPLE);
                apple.setPixel(pixel);
            } else {
                isGameWin = true;
            }
        }
    }

    private void gameOver() {
        timer.stop();
        GameWindowManager.getInstance().reset("Game Over!");
    }

    private void gameWin() {
        timer.stop();
        GameWindowManager.getInstance().reset("You win!");
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    private Pixel getNextPixel(Pixel snakeHeadPixel) {
        int width = GameDataManager.getInstance().getWidth();
        int height = GameDataManager.getInstance().getHeight();
        int currentSnakeDirection = GameDataManager.getInstance().getCurrentSnakeDirection();

        int x = snakeHeadPixel.getX();
        int y = snakeHeadPixel.getY();

        if (currentSnakeDirection == Constants.SNAKE_DIRECTION_UP) {
            y = y - 1 >= 0 ? y - 1 : height - 1;
        } else if (currentSnakeDirection == Constants.SNAKE_DIRECTION_DOWN) {
            y = y + 1 <= height - 1 ? y + 1 : 0;
        } else if (currentSnakeDirection == Constants.SNAKE_DIRECTION_LEFT) {
            x = x - 1 >= 0 ? x - 1 : width - 1;
        } else if (currentSnakeDirection == Constants.SNAKE_DIRECTION_RIGHT) {
            x = x + 1 <= width - 1 ? x + 1 : 0;
        }

        return GameDataManager.getInstance().getPixel(x, y).get();
    }

    private void updateSnake(List<Pixel> snakePixelList, Pixel nextPixel, boolean isAppleGotten) {
        nextPixel.setPixelType(PixelType.SNAKE_HEAD);

        snakePixelList.get(0).setPixelType(PixelType.SNAKE_BODY);
        snakePixelList.add(0, nextPixel);

        if (!isAppleGotten) {
            Pixel lastPixel = snakePixelList.remove(snakePixelList.size() - 1);
            lastPixel.setPixelType(PixelType.FREE);
        }
    }
}
