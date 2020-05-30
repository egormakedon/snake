package by.makedon.snake.manager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Yahor Makedon
 */
public final class GameActionManager implements ActionListener {
    private static GameActionManager instance;

    private final Timer timer;

    private GameActionManager() {
        timer = new Timer(Integer.MIN_VALUE, this);
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
        timer.setDelay(speed);
        timer.start();
    }
}
