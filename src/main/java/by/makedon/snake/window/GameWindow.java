package by.makedon.snake.window;

import by.makedon.snake.util.Constants;
import by.makedon.snake.util.ResourceUtil;

import javax.swing.*;

/**
 * @author Yahor Makedon
 */
public class GameWindow extends JFrame {
    public GameWindow() {
        setTitle(ResourceUtil.getPropertyValue(Constants.FRAME_NAME));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void showWindow() {
        setVisible(true);
    }

    public void hideWindow() {
        setVisible(false);
    }
}
