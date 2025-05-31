package org.example.controller;

import org.example.model.MyLevel;
import org.example.view.MyJFrame;

import javax.swing.*;
import java.awt.*;

public class FrameController {
    private MyJFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    private MenuController menuController;
    private GameController gameController;
    public static final String MENU = "menuPanel";
    public static final String GAME = "gamePanel";

    public FrameController() {
        frame = new MyJFrame();

        menuController = new MenuController(this);
        gameController = new GameController(this);
        mainPanel = frame.getMainPanel();
        cardLayout = frame.getCardLayout();


        mainPanel.add(menuController.getMenuPanel(), MENU);
        mainPanel.add(gameController.getGameView().getGamePanel(), GAME);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        showMenu();
    }

    public void showMenu() {
        cardLayout.show(mainPanel, MENU);
    }

    public void showGameModel(MyLevel level) {
        cardLayout.show(mainPanel, GAME);
    }


    public GameController getGameController() {
        return gameController;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public MyJFrame getFrame() {
        return frame;
    }
}
