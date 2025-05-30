package org.example.view;

import org.example.model.GameModel;

import javax.swing.*;

import java.awt.*;


import static org.example.config.ViewConstants.GameConstants.TetrisPanelConstants.*;


public class TetrisPanel extends JPanel {
    //    private GameController gameController;
//    private GameView gameView;
    private GameModel gameModel;

    public TetrisPanel(GameModel gameModel) {
        this.gameModel = gameModel;
        this.setLayout(null);
//        this.gameView = gameView;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        paintBackGroundGrid(g2d);
        if (gameModel.getCurrentShape() != null) {
            ShapeView shapeView = new ShapeView(gameModel.getCurrentShape());
            shapeView.paint(g2d);
        }
    }

    private void drawGlowingLine(Graphics2D g2d, int x1, int y1, int x2, int y2) {
        g2d.setColor(GLOWING_LINE_FIRST_SHADOW);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawLine(x1, y1, x2, y2);

        g2d.setColor(GLOWING_LINE_SECOND_SHADOW);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(x1, y1, x2, y2);

        g2d.setColor(GLOWING_LINE_THIRD_SHADOW);
        g2d.setStroke(new BasicStroke(1));
        g2d.drawLine(x1, y1, x2, y2);
    }

    public void paintBackGroundGrid(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        g2d.setPaint(GAME_PANEL_GRADIANT_PAINT);
        g2d.fillRect(0, 0, width, height);

        for (int x = 0; x <= width; x += CELL_SIZE) {
            drawGlowingLine(g2d, x, 0, x, height);
        }

        for (int y = 0; y <= height; y += CELL_SIZE) {
            drawGlowingLine(g2d, 0, y, width, y);
        }
    }
}

