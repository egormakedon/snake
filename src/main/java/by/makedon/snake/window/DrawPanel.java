package by.makedon.snake.window;

import by.makedon.snake.domain.PixelType;
import by.makedon.snake.manager.GameDataManager;
import by.makedon.snake.util.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import static by.makedon.snake.manager.GameWindowManager.FRAME_SCALE;

/**
 * @author Yahor Makedon
 */
public class DrawPanel extends JComponent {
    public DrawPanel() {
        setFocusable(true);
        addKeyListener(new CustomKeyListener());
    }

    public void setPreferredSize(int frameWidth, int frameHeight) {
        super.setPreferredSize(new Dimension(frameWidth * FRAME_SCALE, frameHeight * FRAME_SCALE));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        GameDataManager.getInstance()
                .getGameMapPixelList()
                .forEach(pixel -> drawPixel(g, pixel.getX(), pixel.getY(), pixel.getPixelType()));
    }

    private void drawPixel(Graphics g, int x, int y, PixelType pixelType) {
        g.setColor(Color.BLACK);
        g.fillRect(x * FRAME_SCALE, y * FRAME_SCALE, FRAME_SCALE, FRAME_SCALE);

        switch (pixelType) {
            case SNAKE_HEAD:
                g.setColor(Color.WHITE);
                break;
            case SNAKE_BODY:
                g.setColor(Color.GREEN);
                break;
            case APPLE:
                g.setColor(Color.RED);
                break;
        }

        if (!pixelType.equals(PixelType.FREE)) {
            g.fillOval(x * FRAME_SCALE, y * FRAME_SCALE, FRAME_SCALE, FRAME_SCALE);
        }
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
