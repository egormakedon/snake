package by.makedon.snake.window;

import by.makedon.snake.manager.GameWindowManager;
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

    @Override
    public void setSize(int frameWidth, int frameHeight) {
        super.setSize(frameWidth * GameWindowManager.FRAME_SCALE, frameHeight * GameWindowManager.FRAME_SCALE);
        setLocationRelativeTo(null);
    }

    public void showWindow() {
        setVisible(true);
    }

    public void hideWindow() {
        setVisible(false);
    }
}
