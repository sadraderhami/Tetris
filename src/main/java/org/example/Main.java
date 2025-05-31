package org.example;

import org.example.config.ConfigManager;
import org.example.config.ResourcePaths;
import org.example.config.ViewConstants;
import org.example.controller.FrameController;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            ConfigManager config = ConfigManager.getInstance(ResourcePaths.CONFIG_FILE_PATH);
            ViewConstants.loadFromConfig(config);
            // If you have ModelConstants, load them here similarly
        } catch (IOException e) {
            e.printStackTrace();
            // fallback or exit
        }
        new FrameController();
    }
}