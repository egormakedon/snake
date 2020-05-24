package by.makedon.snake.runner;

import by.makedon.snake.manager.WindowManager;
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
                WindowManager.getInstance().createAndShowGameWindow();
            } catch (Exception e) {
                logger.error(e.getLocalizedMessage(), e);
            }
        });
    }
}
