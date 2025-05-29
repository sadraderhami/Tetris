package org.example.controller;

import org.example.view.MenuPanel;

import java.awt.*;

public class MenuController {
    private transient FrameController frameController;
    private MenuPanel menuPanel;
    public MenuController(FrameController frameController) {
        this.frameController = frameController;
        menuPanel = new MenuPanel();

    }

    public MenuPanel getMenuPanel() {
        return menuPanel;
    }

    public void setMenuPanel(MenuPanel menuPanel) {
        this.menuPanel = menuPanel;
    }
}
