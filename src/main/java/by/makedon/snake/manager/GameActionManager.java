package by.makedon.snake.manager;

import by.makedon.snake.util.Constants;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Yahor Makedon
 */
public final class GameActionManager implements ActionListener {
    private static GameActionManager instance;

    private final Timer timer;
    private boolean isPaused;

    private GameActionManager() {
        timer = new Timer(Constants.EMPTY_INT, this);
    }

    public static GameActionManager getInstance() {
        if (instance == null) {
            instance = new GameActionManager();
        }

        return instance;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO
        GameDataManager.getInstance().updateSnakeDirection();
        GameWindowManager.getInstance().repaint();
    }

    public void start(int speed) {
        isPaused = false;

        timer.setDelay(speed);
        timer.start();
    }

    public void pause() {
        if (isPaused) {
            timer.start();
        } else {
            timer.stop();
        }

        isPaused = !isPaused;
    }
}
