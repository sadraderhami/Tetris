package org.example.view;

import javax.swing.*;

public class GamePanel extends JPanel {
    private GameView gameView;
    private SidePanel sidePanel;
    public GamePanel() {
        gameView = new GameView();
        sidePanel = new SidePanel();
    }
}
