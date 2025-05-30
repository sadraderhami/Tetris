package org.example.controller;

import org.example.model.GameModel;
import org.example.model.MyLevel;
import org.example.view.GameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.example.config.ModelConstants.UPDATE_INTERVAL;

public class GameController {
    private transient FrameController frameController;
    private GameModel gameModel;
    private GameView gameView;
    private Timer timer;

    public GameController(FrameController frameController) {
        this.frameController = frameController;
        gameModel = new GameModel();
        gameView = new GameView(gameModel);
    }


    public void startGame(MyLevel level) {
        gameModel.startGame();
        startGameLoop();
    }

    public GameView getGameView() {
        return gameView;
    }

    public void startGameLoop() {
        timer = new Timer(UPDATE_INTERVAL, new ActionListener() {
            @Override
            public synchronized void actionPerformed(ActionEvent e) {
//                SwingUtilities.convertPointFromScreen(MouseInfo.getPointerInfo().getLocation(), gameView.getGamePanel());
                updatingLoop();
            }
        });
        timer.start();
    }

    public void updatingLoop() {
        gameModel.update();
        if (gameModel.isGameOver()) {
            GameController.this.handleGameOVer();
        }
        gameView.repaint();
    }

    public void handleGameOVer() {

    }
}
