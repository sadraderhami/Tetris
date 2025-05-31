package org.example.controller;

import org.example.config.ModelConstants;
import org.example.config.ResourcePaths;
import org.example.model.MyLevel;
import org.example.model.Speed;
import org.example.view.MenuPanel;


import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class MenuController {
    private FrameController frameController;
    private MenuPanel menuPanel;
    private ActionListener actionListener;
    private ChangeListener changeListener;

    public MenuController(FrameController frameController) {
        this.frameController = frameController;
        menuPanel = new MenuPanel();
        newListeners();
        addListeners();
        play();
    }

    private void newListeners() {
        newActionListeners();
        newChangeListeners();
    }

    private void addListeners() {
        menuPanel.addActionListeners(actionListener);
        for (JButton button : menuPanel.getLevelButtons()) {
            button.addActionListener(actionListener);
        }
        menuPanel.getVolumeSlider().addChangeListener(changeListener);
        menuPanel.getSizeSelector().addActionListener(actionListener);
        menuPanel.getSpeedSelector().addActionListener(actionListener);
    }

    private void play() {
        // Somewhere in your frameController or frame setup code
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
            AudioInputStream inputStream = null;
//            inputStream = AudioSystem.getAudioInputStream(new File("src\\main\\resources\\1-second-of-silence.wav"));
            inputStream = AudioSystem.getAudioInputStream(new File(ResourcePaths.BACKGROUND_MUSIC_PATH));

            clip.open(inputStream);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }

        clip.loop(Clip.LOOP_CONTINUOUSLY); // Or start()
        menuPanel.getVolumeSlider().attachClip(clip);
    }

    public MenuPanel getMenuPanel() {
        return menuPanel;
    }

    private void levels() {
        menuPanel.showLevelsPanel();
    }

    private void startLevel(MyLevel level) {
        System.out.println(level);
        frameController.showGameModel(level);
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
        return MyLevel.DEFAULT; // default fallback
    }

    private void newActionListeners() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if (source == menuPanel.getStartButton()) {
                    startLevel(MyLevel.DEFAULT); // or however you define default
                } else if (source == menuPanel.getLevelsButton()) {
                    levels();
                } else if (source == menuPanel.getSettingsButton()) {
                    goToSettings();
                } else if (source == menuPanel.getBackFromSettingsButton() ||
                        source == menuPanel.getBackFromLevelsButton()) {
                    goToMenu();
                } else if (menuPanel.getLevelButtons().contains(source)) {
                    String buttonText = ((JButton) source).getText();
                    MyLevel selectedLevel = extractLevelFromButton(buttonText);
                    startLevel(selectedLevel);
                } else if (source == menuPanel.getExitButton()) {
                    System.exit(0);
                } else if (source == menuPanel.getSizeSelector()) {
                    switch (menuPanel.getSizeSelector().getSelectedIndex()) {
                        case (0) -> {
//                            ModelConstants.setSizeOfTetris(8,8);
                            frameController.revalidateSizes();

                        }
                        case (1) -> {

                        }
                        case (2) -> {

                        }
                    }
                } else if (source == menuPanel.getSpeedSelector()) {
                    switch (menuPanel.getSpeedSelector().getSelectedIndex()) {
                        case (0) -> {
                            ModelConstants.setDropInterval(Speed.SLOW.getTimeInterval());
                        }
                        case (1) -> {
                            ModelConstants.setDropInterval(Speed.MEDIUM.getTimeInterval());
                        }
                        case (2) -> {
                            ModelConstants.setDropInterval(Speed.FAST.getTimeInterval());
                        }
                    }
                }
            }
        };
    }

    private void newChangeListeners() {
        changeListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                menuPanel.getVolumeSlider().updateVolume();
            }
        };
    }
}

