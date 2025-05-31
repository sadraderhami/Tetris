package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.example.config.ViewConstants.*;


public class MyJFrame extends JFrame {

    private static final Logger logger = Logger.getLogger(MyJFrame.class.getName());

    static {
        logger.setLevel(Level.ALL); // Show everything
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);
        logger.addHandler(handler);
    }

    CardLayout cardLayout;
    JPanel mainPanel;

    public MyJFrame() {
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        this.setContentPane(mainPanel);
        this.setResizable(false);
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}