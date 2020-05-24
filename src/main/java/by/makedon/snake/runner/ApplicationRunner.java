package by.makedon.snake.runner;

import by.makedon.snake.window.Window;
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
                new Window().showWindow();
            } catch (Exception e) {
                logger.error(e.getLocalizedMessage(), e);
            }
        });
    }
}
