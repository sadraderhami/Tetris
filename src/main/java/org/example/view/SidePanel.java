package org.example.view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

import org.example.config.ViewConstants.*;

import static org.example.config.ViewConstants.GameConstants.*;
import static org.example.config.ViewConstants.GameConstants.SidePanelConstants.*;

public class SidePanel extends JPanel {
    private JLabel labelScore;
    private JLabel labelScoreUpdateLocal;

    public SidePanel() {
        this.setVisible(true);
        this.setLayout(null);
        this.setBackground(new Color(0, 0, 50));
        this.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.WHITE),
                "", TitledBorder.CENTER, TitledBorder.TOP,
                new Font("Arial", Font.PLAIN, 12),
                Color.WHITE));

        initLabelScore();
        initLabelScoreUpdateLocal();
    }

    public void initLabelScore() {
        labelScore = new JLabel("Score", SwingConstants.CENTER);
        labelScore.setForeground(Color.WHITE);
        labelScore.setFont(BUTTON_FONT);
        labelScore.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        labelScore.setLocation((SIDE_PANEL_WIDTH - BUTTON_WIDTH) / 2, BUTTON_HEIGHT);
        this.add(labelScore);
    }

    public void initLabelScoreUpdateLocal() {
        labelScoreUpdateLocal = new JLabel("0", SwingConstants.CENTER);
        labelScoreUpdateLocal.setOpaque(true);
        labelScoreUpdateLocal.setBackground(BUTTON_COLOR);
        labelScoreUpdateLocal.setFont(BUTTON_FONT);
        labelScoreUpdateLocal.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        labelScoreUpdateLocal.setLocation((SIDE_PANEL_WIDTH - BUTTON_WIDTH) / 2, 2 * BUTTON_HEIGHT);
        this.add(labelScoreUpdateLocal);
    }
}
