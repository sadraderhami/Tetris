package org.example;

import org.example.config.ConfigManager;
import org.example.config.ViewConstants;
import org.example.controller.FrameController;
import org.example.view.MyJFrame;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
//        try {
//            ConfigManager config = new ConfigManager("src/main/resources/config.json");
//            ViewConstants.loadFromConfig(config);
//            // If you have ModelConstants, load them here similarly
//        } catch (IOException e) {
//            e.printStackTrace();
//            // fallback or exit
//        }
        new FrameController();
    }
}