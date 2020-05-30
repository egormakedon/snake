package by.makedon.snake.runner;

import by.makedon.snake.manager.GameActionManager;
import by.makedon.snake.manager.GameDataManager;
import by.makedon.snake.manager.GameWindowManager;
import by.makedon.snake.util.Constants;
import by.makedon.snake.util.ResourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

/**
 * @author Yahor Makedon
 */
public class ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationRunner.class);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                int width = Integer.parseInt(ResourceUtil.getPropertyValue(Constants.FRAME_DEFAULT_WIDTH));
                int height = Integer.parseInt(ResourceUtil.getPropertyValue(Constants.FRAME_DEFAULT_HEIGHT));

                GameDataManager.getInstance().createData(width, height);
                GameWindowManager.getInstance().show(width, height);
                GameActionManager.getInstance().start(1_000);
            } catch (Exception e) {
                logger.error(e.getLocalizedMessage(), e);
            }
        });
    }
}
