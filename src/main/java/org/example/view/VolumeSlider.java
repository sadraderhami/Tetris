package org.example.view;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

import static org.example.config.ViewConstants.MenuConstants.BUTTON_COLOR;
import static org.example.config.ViewConstants.MenuConstants.BUTTON_FONT;

public class VolumeSlider extends JPanel {
    private final JSlider slider;
    private FloatControl volumeControl;

    public VolumeSlider() {
        setLayout(new BorderLayout());
        slider = new JSlider(0, 100, 0); // min=0, max=100, initial=50
        slider.setMajorTickSpacing(25);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setBackground(BUTTON_COLOR);
        slider.setFont(BUTTON_FONT);
        add(new JLabel("Volume"), BorderLayout.NORTH);
        add(slider, BorderLayout.CENTER);

//        slider.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                updateVolume();
//            }
//        });
    }

    // Call this method to attach a Clip for volume control
    public void attachClip(Clip clip) {
        try {
            if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                updateVolume();
            } else {
                System.err.println("MASTER_GAIN not supported on this clip.");
            }
        } catch (IllegalArgumentException ex) {
            System.err.println("Error accessing volume control: " + ex.getMessage());
        }
    }

    public void updateVolume() {
        if (volumeControl != null) {
            float min = volumeControl.getMinimum(); // e.g. -80.0f
            float max = volumeControl.getMaximum(); // e.g. 6.0f
            float value = slider.getValue();        // 0 to 100
            float gain = min + (value / 100f) * (max - min);
            volumeControl.setValue(gain);
        }
    }
    public void addChangeListener(ChangeListener listener) {
        slider.addChangeListener(listener);
    }
}


