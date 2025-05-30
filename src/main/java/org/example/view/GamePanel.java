package org.example.view;

import org.example.model.GameModel;

import javax.swing.*;

import static org.example.config.ViewConstants.GameConstants.*;
import static org.example.config.ViewConstants.GameConstants.SidePanelConstants.*;
import static org.example.config.ViewConstants.GameConstants.TetrisPanelConstants.*;


public class GamePanel extends JPanel {
    private TetrisPanel tetrisPanel;
    private SidePanel sidePanel;

    public GamePanel(GameModel gameModel) {
        this.setLayout(null);
        sidePanel = new SidePanel();
        initSidePanel();
        tetrisPanel = new TetrisPanel(gameModel);
        initTetrisPanel();
        this.add(tetrisPanel);
        this.add(sidePanel);
    }

    private void initSidePanel() {
        sidePanel.setSize(SIDE_PANEL_WIDTH, SIDE_PANEL_HEIGHT);
        sidePanel.setLocation(GAME_PANEL_WIDTH - SIDE_PANEL_WIDTH, 0);
        sidePanel.setLayout(null);
    }

    private void initTetrisPanel() {
        tetrisPanel.setSize(TETRIS_PANEL_WIDTH, TETRIS_PANEL_HEIGHT);
        tetrisPanel.setLocation(0, 0);
        tetrisPanel.setLayout(null);
    }
}
