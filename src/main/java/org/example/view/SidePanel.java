package org.example.view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

import static org.example.config.ViewConstants.GameConstants.*;
import static org.example.config.ViewConstants.GameConstants.SidePanelConstants.*;

public class SidePanel extends JPanel {
    private JLabel scoreText;
    private JLabel scoreValue;
    private JButton pauseButton;
    private JButton restartButton;

    public SidePanel() {
        this.setVisible(true);
        this.setLayout(null);
        this.setBackground(new Color(0, 0, 50));
        this.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.WHITE),
                "", TitledBorder.CENTER, TitledBorder.TOP,
                new Font("Arial", Font.PLAIN, 12),
                Color.WHITE));

        initRestartButton();
        initPauseButton();
        initLabelScore();
        initLabelScoreUpdateLocal();
    }

    public void updateScore(int score) {
        scoreValue.setText(String.valueOf(score));
    }

    public void initRestartButton() {
        restartButton = new JButton("Restart");
        restartButton.setBackground(BUTTON_COLOR);
        restartButton.setFont(BUTTON_FONT);
        restartButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        restartButton.setLocation((SIDE_PANEL_WIDTH - BUTTON_WIDTH) / 2, 8 * BUTTON_HEIGHT);
        restartButton.setFocusable(true);
        restartButton.setFocusPainted(false);
        this.add(restartButton);
    }

    public void initPauseButton() {
        pauseButton = new JButton("Start");
        pauseButton.setBackground(BUTTON_COLOR);
        pauseButton.setFont(BUTTON_FONT);
        pauseButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        pauseButton.setLocation((SIDE_PANEL_WIDTH - BUTTON_WIDTH) / 2, 7 * BUTTON_HEIGHT);
        pauseButton.setFocusable(true);
        pauseButton.setFocusPainted(false);
        this.add(pauseButton);
    }

    public void addActionListenerToButtons(ActionListener actionListener) {
        pauseButton.addActionListener(actionListener);
        restartButton.addActionListener(actionListener);
    }

    public void switchPauseButtonText() {
        if (pauseButton.getText().equals("Start") || pauseButton.getText().equals("Resume")) {
            pauseButton.setText("Pause");
        } else {
            pauseButton.setText("Resume");
        }
        repaint();
    }

    public void reset() {
        this.setVisible(true);
        pauseButton.setText("Start");
        scoreValue.setText("0");
    }

    public JButton getPauseButton() {
        return pauseButton;
    }

    public JButton getRestartButton() {
        return restartButton;
    }
    //    public void initStartButton() {
//        startButton = new JButton("Start");
//        startButton.setBackground(BUTTON_COLOR);
//        startButton.setFont(BUTTON_FONT);
//        startButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
//        startButton.setLocation((SIDE_PANEL_WIDTH - BUTTON_WIDTH) / 2, 3 * BUTTON_HEIGHT);
//        this.add(startButton);
//    }

    public void initLabelScore() {
        scoreText = new JLabel("Score", SwingConstants.CENTER);
        scoreText.setForeground(Color.WHITE);
        scoreText.setFont(BUTTON_FONT);
        scoreText.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        scoreText.setLocation((SIDE_PANEL_WIDTH - BUTTON_WIDTH) / 2, BUTTON_HEIGHT);
        this.add(scoreText);
    }

    public void initLabelScoreUpdateLocal() {
        scoreValue = new JLabel("0", SwingConstants.CENTER);
        scoreValue.setOpaque(true);
        scoreValue.setBackground(BUTTON_COLOR);
        scoreValue.setFont(BUTTON_FONT);
        scoreValue.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        scoreValue.setLocation((SIDE_PANEL_WIDTH - BUTTON_WIDTH) / 2, 2 * BUTTON_HEIGHT);
        this.add(scoreValue);
    }
}
