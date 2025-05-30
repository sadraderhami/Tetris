package org.example.view;

import org.example.model.ShapeModel;

import java.awt.*;

public class ShapeView {
    private ShapeModel ShapeModel;
    public ShapeView(ShapeModel ShapeModel) {
        this.ShapeModel = ShapeModel;
    }
    public void paint(Graphics2D g2d) {
        g2d.setColor(Color.black);
        g2d.fillRect(100,100,100,100);
    }
}
