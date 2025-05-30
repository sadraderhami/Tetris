package org.example.view;

import org.example.model.GameModel;

import java.awt.*;

public class GameView {
//    private GameModel gameModel;
    private GamePanel gamePanel;

    public GameView(GameModel gameModel) {
        gamePanel = new GamePanel(gameModel);
    }

    public void repaint() {
        gamePanel.repaint();
//        paint((Graphics2D) gamePanel.getGraphics());
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

//    public void paint(Graphics2D g2d) {
////        gameModel.getBoardCells();
//        gamePanel.paintComponents(g2d);
//        g2d.setColor(Color.black);
//        g2d.fillRect(0, 0, gamePanel.getWidth(), gamePanel.getHeight());
//    }
}
