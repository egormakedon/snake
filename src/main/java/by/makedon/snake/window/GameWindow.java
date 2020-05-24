package by.makedon.snake.window;

import by.makedon.snake.util.Constants;
import by.makedon.snake.util.ResourceUtil;

import javax.swing.*;
import java.awt.*;

/**
 * @author Yahor Makedon
 */
public class GameWindow extends JFrame {
    public GameWindow(int frameWidth, int frameHeight) {
        String frameName = ResourceUtil.getPropertyValue(Constants.FRAME_NAME);
        int frameScale = Integer.parseInt(ResourceUtil.getPropertyValue(Constants.FRAME_SCALE));

        setTitle(frameName);
        setSize(new Dimension(frameWidth * frameScale, frameHeight * frameScale));
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new DrawPanel());
    }

    public void showWindow() {
        setVisible(true);
    }
}
