package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static org.example.config.ViewConstants.GameConstants.*;

public class GameOverPanel extends JPanel {
    private final JLabel gameOverLabel;
    private final JButton restartButton;
    private final JButton backToMenuButton;
    private final JLabel scoreLabel;


    public GameOverPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(0, 0, 0));
        setAlignmentX(CENTER_ALIGNMENT);

        gameOverLabel = new JLabel();
        restartButton = new JButton("Restart");
        backToMenuButton = new JButton("Menu");
        scoreLabel = new JLabel();

        initGameOverLabel();
        initRestartButton();
        initBackToMenuButton();
        initScoreLabel();

        add(Box.createVerticalGlue());
        add(gameOverLabel);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(scoreLabel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(restartButton);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(backToMenuButton);
        add(Box.createVerticalGlue());
    }

    public JButton getRestartButton() {
        return restartButton;
    }

    public JButton getBackToMenuButton() {
        return backToMenuButton;
    }

    public void initGameOverLabel() {
        gameOverLabel.setText("GAME OVER");
        gameOverLabel.setForeground(Color.RED);
        gameOverLabel.setFont(new Font("Bauhaus 93", Font.BOLD, 50));
        gameOverLabel.setAlignmentX(CENTER_ALIGNMENT);
    }

    public void initRestartButton() {
        restartButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        restartButton.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        restartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        restartButton.setFocusable(false);
        restartButton.setBackground(BUTTON_COLOR);
        restartButton.setFont(BUTTON_FONT);
    }

    public void initBackToMenuButton() {
        backToMenuButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        backToMenuButton.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        backToMenuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backToMenuButton.setFocusable(false);
        backToMenuButton.setBackground(BUTTON_COLOR);
        backToMenuButton.setFont(BUTTON_FONT);

    }

    public void initScoreLabel() {
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        setScore(0);
        scoreLabel.setFont(new Font("Bauhaus 93", Font.BOLD, 50));
        scoreLabel.setForeground(BUTTON_COLOR);
    }

    public void setScore(int score) {
        scoreLabel.setText("Score: " + score);
    }
    public void addActionListenerToButtons(ActionListener listener) {
        addRestartListener(listener);
        addBackToMenuListener(listener);
    }

    public void addRestartListener(ActionListener listener) {
        restartButton.addActionListener(listener);
    }

    public void addBackToMenuListener(ActionListener listener) {
        backToMenuButton.addActionListener(listener);
    }

//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Game Over");
//        frame.setPreferredSize(new Dimension(800, 600));
//
//        GameOverPanel gameOverPanel = new GameOverPanel();
//        frame.add(gameOverPanel, BorderLayout.CENTER);
//
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setLocationRelativeTo(null); // ✅ Centers the frame on screen
//        frame.setVisible(true);            // ✅ Makes the window visible
//    }

}

