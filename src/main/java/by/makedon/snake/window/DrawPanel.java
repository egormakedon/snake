package by.makedon.snake.window;

import by.makedon.snake.domain.Pixel;
import by.makedon.snake.manager.GameDataManager;
import by.makedon.snake.util.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yahor Makedon
 */
public class DrawPanel extends JComponent {
    public DrawPanel() {
        setFocusable(true);
        addKeyListener(new CustomKeyListener());
    }

    @Override
    public void paint(Graphics g) {
        GameDataManager.getInstance()
                .getUpdatePixelList()
                .forEach(pixel -> drawPixel(g, pixel));
        GameDataManager.getInstance().flushUpdatePixelList();
    }

    private void drawPixel(Graphics g, Pixel pixel) {
        switch (pixel.getPixelType()) {
            case FREE:
                drawFreePixel(g, pixel.getX(), pixel.getY());
                break;
            case SNAKE_HEAD:
                drawSnakeHeadPixel(g, pixel.getX(), pixel.getY());
                break;
            case SNAKE_BODY:
                drawSnakeBodyPixel(g, pixel.getX(), pixel.getY());
                break;
            case APPLE:
                drawApplePixel(g, pixel.getX(), pixel.getY());
                break;
        }
    }

    private void drawFreePixel(Graphics g, int x, int y) {
    }

    private void drawSnakeHeadPixel(Graphics g, int x, int y) {
    }

    private void drawSnakeBodyPixel(Graphics g, int x, int y) {
    }

    private void drawApplePixel(Graphics g, int x, int y) {
    }

    private static final class CustomKeyListener extends KeyAdapter {
        private static final Map<Integer, Integer> map;

        static {
            map = new HashMap<>(8);

            map.put(KeyEvent.VK_W, Constants.SNAKE_DIRECTION_UP);
            map.put(KeyEvent.VK_UP, Constants.SNAKE_DIRECTION_UP);

            map.put(KeyEvent.VK_S, Constants.SNAKE_DIRECTION_DOWN);
            map.put(KeyEvent.VK_DOWN, Constants.SNAKE_DIRECTION_DOWN);

            map.put(KeyEvent.VK_A, Constants.SNAKE_DIRECTION_LEFT);
            map.put(KeyEvent.VK_LEFT, Constants.SNAKE_DIRECTION_LEFT);

            map.put(KeyEvent.VK_D, Constants.SNAKE_DIRECTION_RIGHT);
            map.put(KeyEvent.VK_RIGHT, Constants.SNAKE_DIRECTION_RIGHT);
        }

        private CustomKeyListener() {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int oldDirection = GameDataManager.getInstance().getCurrentSnakeDirection();
            int newDirection = map.getOrDefault(e.getKeyCode(), oldDirection);

            if (oldDirection != newDirection) {
                if (newDirection == Constants.SNAKE_DIRECTION_UP && oldDirection != Constants.SNAKE_DIRECTION_DOWN) {
                    GameDataManager.getInstance().setNewSnakeDirection(newDirection);
                } else if (newDirection == Constants.SNAKE_DIRECTION_DOWN && oldDirection != Constants.SNAKE_DIRECTION_UP) {
                    GameDataManager.getInstance().setNewSnakeDirection(newDirection);
                } else if (newDirection == Constants.SNAKE_DIRECTION_LEFT && oldDirection != Constants.SNAKE_DIRECTION_RIGHT) {
                    GameDataManager.getInstance().setNewSnakeDirection(newDirection);
                } else if (newDirection == Constants.SNAKE_DIRECTION_RIGHT && oldDirection != Constants.SNAKE_DIRECTION_LEFT) {
                    GameDataManager.getInstance().setNewSnakeDirection(newDirection);
                }
            }
        }
    }
}
