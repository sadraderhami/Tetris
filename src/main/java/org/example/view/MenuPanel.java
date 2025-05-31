package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static org.example.config.ViewConstants.MenuConstants.*;

public class MenuPanel extends JPanel {

    private JPanel startPanel;
    private JButton startButton;
    private JButton levelsButton;
    private JButton settingsButton;
    private JButton exitButton;

    private JButton backFromSettingsButton;
    private JButton backFromLevelsButton;

    private JPanel settingsPanel;
    private VolumeSlider volumeSlider;
    private JComboBox<String> sizeSelector;
    private JComboBox<String> speedSelector;


    private int numberOfLevels = 2;
    private JPanel levelsPanel;
    private ArrayList<JButton> levelButtons;

    public MenuPanel() {
        this.setLayout(null);
        this.setBounds(0, 0, MENU_PANEL_WIDTH, MENU_PANEL_HEIGHT);
        setStartPanel();
        setSettingsPanel();
        setLevelsPanel();
        this.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        paintBackGroundGrid(g2d);
    }

    private void drawGlowingLine(Graphics2D g2d, int x1, int y1, int x2, int y2) {
        g2d.setColor(GLOWING_LINE_FIRST_SHADOW);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawLine(x1, y1, x2, y2);

        g2d.setColor(GLOWING_LINE_SECOND_SHADOW);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(x1, y1, x2, y2);

        g2d.setColor(GLOWING_LINE_THIRD_SHADOW);
        g2d.setStroke(new BasicStroke(1));
        g2d.drawLine(x1, y1, x2, y2);
    }

    public void paintBackGroundGrid(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        g2d.setPaint(MENU_PANEL_GRADIANT_PAINT);
        g2d.fillRect(0, 0, width, height);

        for (int x = 0; x <= width; x += CELL_SIZE) {
            drawGlowingLine(g2d, x, 0, x, height);
        }

        for (int y = 0; y <= height; y += CELL_SIZE) {
            drawGlowingLine(g2d, 0, y, width, y);
        }
    }

    public void setPanelAndButtons(JPanel panel, JButton[] buttons, String[] texts) {
        panel.setBounds(0, 0, MENU_PANEL_WIDTH, MENU_PANEL_HEIGHT);
        panel.setLayout(null);
        panel.setOpaque(false);
        for (int i = 0; i < buttons.length; i++) {
            setButtonForPanel(panel, buttons[i], texts[i], i - 2);
        }
        this.add(panel);
    }

    public void setPanelAndButtons(JPanel panel, ArrayList<JButton> buttons, String[] texts) {
        panel.setBounds(0, 0, MENU_PANEL_WIDTH, MENU_PANEL_HEIGHT);
        panel.setLayout(null);
        panel.setOpaque(false);
        for (int i = 0; i < buttons.size(); i++) {
            setButtonForPanel(panel, buttons.get(i), texts[i], i - 2);
        }
        this.add(panel);
    }

    public void setPanelAndButtons(JPanel panel, ArrayList<JButton> buttons) {
        panel.setBounds(0, 0, MENU_PANEL_WIDTH, MENU_PANEL_HEIGHT);
        panel.setLayout(null);
        panel.setOpaque(false);
        for (int i = 0; i < buttons.size(); i++) {
            setButtonForPanel(panel, buttons.get(i), i - 2);
        }
        this.add(panel);
    }

    public void setStartPanel() {
        startPanel = new JPanel();
        startButton = new JButton();
        levelsButton = new JButton();
        settingsButton = new JButton();
        exitButton = new JButton();
        setPanelAndButtons(startPanel, new JButton[]{startButton, levelsButton, settingsButton, exitButton},
                new String[]{"Start", "Levels", "Settings", "Exit"});
        startPanel.setVisible(true);
    }

    public void setSettingsPanel() {
        settingsPanel = new JPanel();
        backFromSettingsButton = new JButton();
        setPanelAndButtons(settingsPanel, new JButton[]{backFromSettingsButton}, new String[]{"Back"});

        sizeSelector = new JComboBox<>();
        initSizeSelector();

        speedSelector = new JComboBox<>();
        initSpeedSelector();

        settingsPanel.add(sizeSelector);
        settingsPanel.add(speedSelector);
        settingsPanel.setVisible(false);
    }

    private void initSpeedSelector() {
        String[] sizeOptions = {
                "Slow",
                "Medium",
                "Fast"
        };
        speedSelector.setModel(new DefaultComboBoxModel<>(sizeOptions));
        speedSelector.setSelectedIndex(1);
        speedSelector.setBounds((MENU_PANEL_WIDTH - 2 * BUTTON_WIDTH) / 2, BUTTON_MARGIN * 8, 2 * BUTTON_WIDTH, BUTTON_HEIGHT);
        speedSelector.setBackground(BUTTON_COLOR);
        speedSelector.setFont(BUTTON_FONT);
    }

    private void initSizeSelector() {
        String[] sizeOptions = {
                "Small (10×20)",
                "Medium (15×25)",
                "Large (20×30)"
        };
        sizeSelector.setModel(new DefaultComboBoxModel<>(sizeOptions));
        sizeSelector.setSelectedIndex(1);
        sizeSelector.setBounds((MENU_PANEL_WIDTH - 2 * BUTTON_WIDTH) / 2, BUTTON_MARGIN * 6, 2 * BUTTON_WIDTH, BUTTON_HEIGHT);
        sizeSelector.setBackground(BUTTON_COLOR);
        sizeSelector.setFont(BUTTON_FONT);
    }

    public void setLevelsPanel() {
        levelsPanel = new JPanel();
        levelButtons = new ArrayList<>();
        addLevelButtonsToList(numberOfLevels);
        backFromLevelsButton = new JButton();
        setButtonForPanel(levelsPanel, backFromLevelsButton, "Back", numberOfLevels - 2);
        setPanelAndButtons(levelsPanel, levelButtons);

        // Create and position the volume slider
        volumeSlider = new VolumeSlider();
        volumeSlider.setBounds((MENU_PANEL_WIDTH - 250) / 2, BUTTON_MARGIN * 10, 250, 70); // adjust position as needed
        settingsPanel.add(volumeSlider);

        levelsPanel.setVisible(false);
    }

    public void showStartPanel() {
        startPanel.setVisible(true);
        settingsPanel.setVisible(false);
        levelsPanel.setVisible(false);
    }

    public void showSettingsPanel() {
        startPanel.setVisible(false);
        settingsPanel.setVisible(true);
        levelsPanel.setVisible(false);
    }

    public void showLevelsPanel() {
        startPanel.setVisible(false);
        settingsPanel.setVisible(false);
        levelsPanel.setVisible(true);
    }

    public void setButtonOrder(JButton button, int fromTheTop, int buttonWidth, int buttonHeight, int buttonMargin) {
        button.setBounds((MENU_PANEL_WIDTH - buttonWidth) / 2, (MENU_PANEL_HEIGHT - buttonHeight) / 2 + fromTheTop * (buttonHeight + buttonMargin), buttonWidth, buttonHeight);
    }

    public void setButtonForPanel(JPanel panel, JButton button, String text, int fromTheTop) {
        button.setText(text);
        button.setOpaque(true);
        button.setFont(BUTTON_FONT);
        button.setBackground(BUTTON_COLOR);
        setButtonOrder(button, fromTheTop, BUTTON_WIDTH, BUTTON_HEIGHT, BUTTON_MARGIN);
        button.setFocusable(true);
        button.setFont(BUTTON_FONT);
        button.setFocusPainted(false);
        panel.add(button);
    }

    public void setButtonForPanel(JPanel panel, JButton button, int fromTheTop) {
        button.setOpaque(true);
        button.setFont(BUTTON_FONT);
        button.setBackground(BUTTON_COLOR);
        setButtonOrder(button, fromTheTop, BUTTON_WIDTH, BUTTON_HEIGHT, BUTTON_MARGIN);
        button.setFocusable(true);
        button.setFocusPainted(false);
        panel.add(button);
    }

    public void addLevelButtonsToList(int numberOfLevels) {
        for (int i = 0; i < numberOfLevels; i++) {
            JButton tempButton = new JButton();
            tempButton.setText("Level " + (i + 1));
            tempButton.setOpaque(true);
            tempButton.setFont(BUTTON_FONT);
            tempButton.setBackground(BUTTON_COLOR);
            setButtonOrder(tempButton, i, BUTTON_WIDTH, BUTTON_HEIGHT, BUTTON_MARGIN);
            tempButton.setFocusable(true);
            tempButton.setFont(BUTTON_FONT);
            tempButton.setFocusPainted(false);
            levelButtons.add(tempButton);
        }
    }

    public JButton getLevelsButton() {
        return levelsButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public JButton getSettingsButton() {
        return settingsButton;
    }

    public JButton getBackFromSettingsButton() {
        return backFromSettingsButton;
    }

    public JButton getBackFromLevelsButton() {
        return backFromLevelsButton;
    }

    public ArrayList<JButton> getLevelButtons() {
        return levelButtons;
    }

    public JButton getStartButton() {
        return startButton;
    }

    public void setStartButton(JButton startButton) {
        this.startButton = startButton;
    }

    public void addActionListeners(ActionListener actionListener) {
        levelsButton.addActionListener(actionListener);
        settingsButton.addActionListener(actionListener);
        exitButton.addActionListener(actionListener);
        backFromSettingsButton.addActionListener(actionListener);
        backFromLevelsButton.addActionListener(actionListener);
        startButton.addActionListener(actionListener);
    }


    public VolumeSlider getVolumeSlider() {
        return volumeSlider;
    }

    public JComboBox<String> getSizeSelector() {
        return sizeSelector;
    }

    public JComboBox<String> getSpeedSelector() {
        return speedSelector;
    }
}

