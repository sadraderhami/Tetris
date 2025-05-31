package org.example.controller;

import org.example.model.GameModel;
import org.example.view.GameView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;

import static org.example.config.InputConfig.*;
import static org.example.config.ModelConstants.DROP_INTERVAL;

public class GameController {
    private transient FrameController frameController;
    private GameModel gameModel;
    private GameView gameView;
    private Timer timer;
    private KeyListener keyListener;
    private ActionListener sidePanelActionListener;
    private ActionListener gameOverPanelActionListener;

    public GameController(FrameController frameController) {
        this.frameController = frameController;
        gameModel = new GameModel();
        gameView = new GameView(gameModel);

        newKeyListeners();
        newActionListeners();

        addListeners();
    }

    public void addListeners() {
        gameView.getGamePanel().getTetrisPanel().addKeyListener(keyListener);
        gameView.getGamePanel().getSidePanel().addActionListenerToButtons(sidePanelActionListener);
        gameView.getGamePanel().getGameOverPanel().addActionListenerToButtons(gameOverPanelActionListener);
    }

    public GameView getGameView() {
        return gameView;
    }

    public void startGameLoop() {
        SwingUtilities.invokeLater(() -> {
            giveFocusToTetrisPanel();
            gameModel.startGame();
            gameView.revalidate();
        });
        timer = new Timer(DROP_INTERVAL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameModelLoopUpdate();
                gameViewLoopUpdate();
            }
        });
        timer.start();
    }

    public void gameViewLoopUpdate() {
        gameView.repaint();
        gameView.getGamePanel().getSidePanel().updateScore(gameModel.getScore());
    }

    public void gameModelLoopUpdate() {
        dropShapeIfPossible();
        if (gameModel.isGameOver()) {
            timer.stop();
            handleGameOver();
        }
    }

    private void giveFocusToTetrisPanel() {
        gameView.getGamePanel().getTetrisPanel().requestFocusInWindow();
    }

    public void handleGameOver() {
        timer.stop();
        gameView.handleGameOver(gameModel);
        gameModel.handleGameOver();
    }


    public void dropShapeIfPossible() {
        gameModel.dropShapeIfPossible();
    }

    public void newKeyListeners() {
        keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case DROP_KEY -> {
                        gameModel.dropShapeIfPossible();
                    }
                    case MOVE_LEFT_KEY -> {
                        gameModel.moveLeftIfPossible();
                    }
                    case MOVE_RIGHT_KEY -> {
                        gameModel.moveRightIfPossible();
                    }
                    case ROTATE_KEY -> {
                        gameModel.rotateIfPossible();
                    }
                }
                gameView.repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
    }

    public void pauseGameLoop() {
        timer.stop();
    }

    private void resumeGameLoop() {
        timer.start();
        giveFocusToTetrisPanel();
    }

    private void restartGameLoop() {
        gameModel.restartGame();
        gameView.reset();
        giveFocusToTetrisPanel();
    }

    private void goBackToMenu(){
        gameModel.restartGame();
        gameView.reset();
        frameController.showMenu();
    }

    public void newActionListeners() {
        sidePanelActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == gameView.getGamePanel().getSidePanel().getPauseButton()) {
                    if (Objects.equals(e.getActionCommand(), "Start")) {
                        startGameLoop();
                    } else if (Objects.equals(e.getActionCommand(), "Pause")) {
                        pauseGameLoop();
                    } else if (Objects.equals(e.getActionCommand(), "Resume")) {
                        resumeGameLoop();
                    }
                    gameView.getGamePanel().getSidePanel().switchPauseButtonText();
                } else if (e.getSource() == gameView.getGamePanel().getSidePanel().getRestartButton()) {
                    restartGameLoop();
                }
            }
        };
        gameOverPanelActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == gameView.getGamePanel().getGameOverPanel().getRestartButton()) {
                    restartGameLoop();
                } else if (e.getSource() == gameView.getGamePanel().getGameOverPanel().getBackToMenuButton()) {
                    goBackToMenu();
                }
            }
        };
    }
}
