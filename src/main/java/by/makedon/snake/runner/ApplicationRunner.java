package by.makedon.snake.runner;

import by.makedon.snake.manager.WindowManager;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

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
