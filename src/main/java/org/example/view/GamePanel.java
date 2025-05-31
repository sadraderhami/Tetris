package org.example.view;

import org.example.model.GameModel;

import javax.swing.*;

import static org.example.config.ViewConstants.GameConstants.*;
import static org.example.config.ViewConstants.GameConstants.SidePanelConstants.*;
import static org.example.config.ViewConstants.GameConstants.TetrisPanelConstants.*;


public class GamePanel extends JPanel {
    private TetrisPanel tetrisPanel;
    private SidePanel sidePanel;
    private GameOverPanel gameOverPanel;

    public GamePanel(GameModel gameModel) {
        this.setLayout(null);
        sidePanel = new SidePanel();
        tetrisPanel = new TetrisPanel(gameModel);
        gameOverPanel = new GameOverPanel();

        initSidePanel();
        initTetrisPanel();
        initGameOverPanel();

        this.add(sidePanel);
        this.add(tetrisPanel);
        this.add(gameOverPanel);
    }


    public TetrisPanel getTetrisPanel() {
        return tetrisPanel;
    }

    public SidePanel getSidePanel() {
        return sidePanel;
    }

    public GameOverPanel getGameOverPanel() {
        return gameOverPanel;
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

    private void initGameOverPanel() {
        gameOverPanel.setSize(GAME_PANEL_WIDTH, GAME_PANEL_HEIGHT);
        gameOverPanel.setLocation(0, 0);
        this.setVisible(false);
    }

    public void reset(){
        hideGameOverPanel();
        sidePanel.reset();
        tetrisPanel.reset();
    }

    public void revalidateSizes(){
        tetrisPanel.setSize(TETRIS_PANEL_WIDTH, TETRIS_PANEL_HEIGHT);
        tetrisPanel.revalidate();
        sidePanel.setSize(SIDE_PANEL_WIDTH, SIDE_PANEL_HEIGHT);
        sidePanel.revalidate();
    }

    public void showGameOverPanel() {
        tetrisPanel.setVisible(false);
        sidePanel.setVisible(false);
        gameOverPanel.setVisible(true);
    }
    public void hideGameOverPanel() {
        tetrisPanel.setVisible(true);
        sidePanel.setVisible(true);
        gameOverPanel.setVisible(false);
    }
}
