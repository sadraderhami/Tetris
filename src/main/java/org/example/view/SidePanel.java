package org.example.view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

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
        labelScore.setFont(new Font("Arial", Font.BOLD, 15));
        labelScore.setBounds(40, 30, 100, 24);
        this.add(labelScore);
    }

    public void initLabelScoreUpdateLocal() {
        labelScoreUpdateLocal = new JLabel("0", SwingConstants.CENTER);
        labelScoreUpdateLocal.setOpaque(true);
        labelScoreUpdateLocal.setBackground(new Color(150, 150, 255));
        labelScoreUpdateLocal.setFont(new Font("Arial", Font.BOLD, 10));
        labelScoreUpdateLocal.setBounds(40, 60, 100, 38);
        this.add(labelScoreUpdateLocal);
    }
}
