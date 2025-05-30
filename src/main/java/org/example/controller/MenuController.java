package org.example.controller;

import org.example.model.MyLevel;
import org.example.view.MenuPanel;


import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MenuController {
    private FrameController frameController;
    private MenuPanel menuPanel;
    private ActionListener actionListener;

    public MenuController(FrameController frameController) {
        this.frameController = frameController;
        menuPanel = new MenuPanel();
        initActionListener();
        menuPanel.setActionListeners(actionListener);
        for (JButton button : menuPanel.getLevelButtons()) {
            button.addActionListener(actionListener);
        }
//        play();
    }

    private void play() {
        // Somewhere in your frameController or frame setup code
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
            AudioInputStream inputStream = null;
//            inputStream = AudioSystem.getAudioInputStream(new File("src\\main\\resources\\1-second-of-silence.wav"));
            inputStream = AudioSystem.getAudioInputStream(new File("src\\main\\resources\\My_Azizam.wav"));

            clip.open(inputStream);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }

        clip.loop(Clip.LOOP_CONTINUOUSLY); // Or start()
//        menuPanel.getVolumeSlider().attachClip(clip);
    }

    public MenuPanel getMenuPanel() {
        return menuPanel;
    }

    public void setMenuPanel(MenuPanel menuPanel) {
        this.menuPanel = menuPanel;
    }

    private void levels() {
        menuPanel.showLevelsPanel();
    }

    private void startLevel(MyLevel level) {
        System.out.println(level);
        frameController.startGame(level);
    }

    private void goToSettings() {
        menuPanel.showSettingsPanel();
    }

    private void goToMenu() {
        menuPanel.showStartPanel();
    }

    private MyLevel extractLevelFromButton(String buttonText) {
        // Adjust this based on how your level buttons are labeled
        if (buttonText.startsWith("Level ")) {
            int levelNumber = Integer.parseInt(buttonText.substring(6));
            // Convert int to MyLevel enum - adjust based on your MyLevel implementation
            return MyLevel.values()[levelNumber - 1]; // assuming 1-based indexing
        }
        return MyLevel.ONE; // default fallback
    }

    private void initActionListener() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();

                if (source == menuPanel.getStartButton()) {
                    // You need to specify which level to start - maybe default level?
                    startLevel(MyLevel.ONE); // or however you define default
                } else if (source == menuPanel.getLevelsButton()) {
                    levels();
                } else if (source == menuPanel.getSettingsButton()) {
                    goToSettings();
                } else if (source == menuPanel.getBackFromSettingsButton() ||
                        source == menuPanel.getBackFromLevelsButton()) {
                    goToMenu();
                } else if (menuPanel.getLevelButtons().contains(source)) {
                    // Extract level from button - adjust this based on your button setup
                    String buttonText = ((JButton) source).getText();
                    MyLevel selectedLevel = extractLevelFromButton(buttonText);
                    startLevel(selectedLevel);
                } else if (source == menuPanel.getExitButton()) {
                    System.exit(0);
                }
            }
        };
    }


//    public void actionPerformed(ActionEvent e) {
//        Object source = e.getSource();
//        if (source == menuPanel.getStartButton()) {
//            startLevel(1);
//        } else if (source == menuPanel.getLevelsButton()) {
//            levels();
//        } else if (source == menuPanel.getSettingsButton()) {
//            goToSettings();
//        } else if (source == menuPanel.getBackFromSettingsButton() || source == menuPanel.getBackFromLevelsButton()) {
//            goToMenu();
//        } else if (menuPanel.getLevelButtons().contains(source)) {
//            int level = Integer.parseInt(((JButton) source).getText().substring(6));
//            startLevel(level);
//        } else if (source == menuPanel.getExitButton()) {
//            System.exit(0);
//        }
//    }
}

