package by.makedon.snake.window;

import by.makedon.snake.manager.DrawManager;
import by.makedon.snake.util.Constants;
import by.makedon.snake.util.ResourceUtil;

import javax.swing.*;
import java.awt.*;

/**
 * @author Yahor Makedon
 */
public class Window extends JFrame {
    public Window() {
        String frameName = ResourceUtil.getPropertyValue(Constants.FRAME_NAME);
        int frameWidth = Integer.parseInt(ResourceUtil.getPropertyValue(Constants.FRAME_WIDTH));
        int frameHeight = Integer.parseInt(ResourceUtil.getPropertyValue(Constants.FRAME_HEIGHT));
        int frameScale = Integer.parseInt(ResourceUtil.getPropertyValue(Constants.FRAME_SCALE));

        setTitle(frameName);
        setSize(new Dimension(frameWidth * frameScale, frameHeight * frameScale));
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(DrawManager.getInstance().getDrawPanel());
    }

    public void showWindow() {
        setVisible(true);
    }
}
