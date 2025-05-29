package org.example.controller;

import org.example.model.GameModel;
import org.example.view.GamePanel;
import org.example.view.GameView;
import org.example.view.SidePanel;

public class GameController {
    private transient FrameController frameController;
    private GameModel gameModel;
    private GameView gameView;
    private GamePanel gamePanel;

    public GameController(FrameController frameController) {
        this.frameController = frameController;
        gameModel = new GameModel();
        gameView = new GameView();
        gamePanel = new GamePanel();
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}
