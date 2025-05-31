package org.example.view;

import org.example.model.GameModel;

import java.awt.*;

import static org.example.config.ViewConstants.GameConstants.*;

public class GameView {
    private GamePanel gamePanel;

    public GameView(GameModel gameModel) {
        gamePanel = new GamePanel(gameModel);
        gamePanel.setPreferredSize(new Dimension(GAME_PANEL_WIDTH, GAME_PANEL_HEIGHT));
    }

    public void repaint() {
        gamePanel.repaint();
    }

    public void revalidate() {
        gamePanel.revalidate();
    }

    public void reset() {
        this.revalidate();
        this.repaint();
        this.getGamePanel().reset();
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void handleGameOver(GameModel gameModel) {
        gamePanel.getGameOverPanel().setScore(gameModel.getScore());
        gamePanel.showGameOverPanel();
    }
}
