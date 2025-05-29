package org.example.model;

import java.awt.*;

public class Shape {
    private ShapeType type;
    private int rotationIndex = 0;
    private boolean stable = false;

    public Shape(ShapeType type) {
        this.type = type;
    }

    public static Shape getRandomShape() {
        return null;
    }

    public Point[] getCurrentPoints() {
        return type.getRotation(rotationIndex);
    }

    public void rotateClockwise() {
        rotationIndex = (rotationIndex + 1) % 4;
    }

    public void rotateCounterClockwise() {
        rotationIndex = (rotationIndex + 3) % 4;
    }
    public ShapeType getType() {
        return type;
    }

//    public int[] getXCoordinates() {
//
//    }

}

