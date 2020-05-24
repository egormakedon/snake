package by.makedon.snake.window;

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
        int frameMinimumWidth = Integer.parseInt(ResourceUtil.getPropertyValue(Constants.FRAME_MINIMUM_WIDTH));
        int frameMinimumHeight = Integer.parseInt(ResourceUtil.getPropertyValue(Constants.FRAME_MINIMUM_HEIGHT));

        setTitle(frameName);
        setMinimumSize(new Dimension(frameMinimumWidth, frameMinimumHeight));
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setJMenuBar(new MenuBar());
        add(new JScrollPane(DrawManager.getInstance().getDrawPanel()), BorderLayout.CENTER);
    }

    public void showWindow() {
        setVisible(true);
    }
}
