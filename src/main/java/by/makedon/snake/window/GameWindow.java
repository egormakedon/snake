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
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new DrawPanel());
    }

    @Override
    public void setSize(int frameWidth, int frameHeight) {
        super.setSize(frameWidth * GameWindowManager.FRAME_SCALE, frameHeight * GameWindowManager.FRAME_SCALE);
    }

    public void showWindow() {
        setVisible(true);
    }

    public void hideWindow() {
        setVisible(false);
    }
}
