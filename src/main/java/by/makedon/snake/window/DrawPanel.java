package by.makedon.snake.window;

import by.makedon.snake.domain.Apple;
import by.makedon.snake.domain.Snake;
import by.makedon.snake.util.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;

/**
 * @author Yahor Makedon
 */
public class DrawPanel extends JComponent implements ActionListener {
    private Apple apple;
    private Snake snake;

    public DrawPanel() {
        setFocusable(true);
        addKeyListener(getKeyListener());
    }

    @Override
    public void paint(Graphics g) {
        //TODO
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO
    }

    private KeyListener getKeyListener() {
        return new KeyAdapter() {
            private final List<Integer> upDownDirectionList = Arrays.asList(Constants.DIRECTION_UP, Constants.DIRECTION_DOWN);
            private final List<Integer> leftRightDirectionList = Arrays.asList(Constants.DIRECTION_LEFT, Constants.DIRECTION_RIGHT);
            private final List<Integer> keyCodeUpList = Arrays.asList(KeyEvent.VK_W, KeyEvent.VK_UP);
            private final List<Integer> keyCodeDownList = Arrays.asList(KeyEvent.VK_S, KeyEvent.VK_DOWN);
            private final List<Integer> keyCodeLeftList = Arrays.asList(KeyEvent.VK_A, KeyEvent.VK_LEFT);
            private final List<Integer> keyCodeRightList = Arrays.asList(KeyEvent.VK_D, KeyEvent.VK_RIGHT);

            @Override
            public void keyPressed(KeyEvent e) {
                int currentDirection = snake.getDirection();
                int keyCode = e.getKeyCode();

                if (!upDownDirectionList.contains(currentDirection) && keyCodeUpList.contains(keyCode)) {
                    snake.setDirection(Constants.DIRECTION_UP);
                }
                if (!upDownDirectionList.contains(currentDirection) && keyCodeDownList.contains(keyCode)) {
                    snake.setDirection(Constants.DIRECTION_DOWN);
                }
                if (!leftRightDirectionList.contains(currentDirection) && keyCodeLeftList.contains(keyCode)) {
                    snake.setDirection(Constants.DIRECTION_LEFT);
                }
                if (!leftRightDirectionList.contains(currentDirection) && keyCodeRightList.contains(keyCode)) {
                    snake.setDirection(Constants.DIRECTION_RIGHT);
                }
            }
        };
    }
}
