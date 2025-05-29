package org.example.controller;

import org.example.view.MyFrame;

import javax.swing.*;
import java.awt.*;

public class FrameController {
    private MyFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    private MenuController menuController;
    private GameController gameController;
    public static final String MENU = "menuPanel";
    public static final String GAME = "gamePanel";
    public FrameController() {
        frame = new MyFrame();

        menuController = new MenuController(this);
        gameController = new GameController(this);
        mainPanel = frame.getMainPanel();
        cardLayout = frame.getCardLayout();
//        menuController.getMenuPanel().setMenuController(menuController);
//        gameController.getGamePanel().setGameController(gameController);

        mainPanel.add(menuController.getMenuPanel(), MENU);
        mainPanel.add(gameController.getGamePanel(), GAME);
    }
    public void showMenu() {
        cardLayout.show(mainPanel, MENU);
    }

    public void startGame(int level) {
//        gameController.initializeLevel(MyLevel.level1);
        cardLayout.show(mainPanel, GAME);

    }


    public GameController getGameController() {
        return gameController;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public MyFrame getFrame() {
        return frame;
    }
}
