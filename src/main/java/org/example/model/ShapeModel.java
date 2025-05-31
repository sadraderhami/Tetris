package org.example.model;
import java.util.Random;

import java.awt.*;

public class ShapeModel {
    private ShapeType type;
    private int rotationIndex = 0;
    private final Point origin = new Point(4, 0);
    private int traveledX = 0;
    private int traveledY = 0;

    public ShapeModel(ShapeType type) {
        this.type = type;
    }


    public static ShapeModel getRandomShape() {
        ShapeType[] values = ShapeType.values();
        int index = new Random().nextInt(values.length);
        return new ShapeModel(values[index]);
    }


    public CellBlockModel[] getCellBlocks() {
        return type.getRotation(rotationIndex);
    }

    public Point getOrigin() {
        return origin;
    }

    public int getTraveledX() {
        return traveledX;
    }

    public int getTraveledY() {
        return traveledY;
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

    public void moveRight() {
        traveledX++;
    }

    public void moveLeft() {
        traveledX--;
    }

    public void moveDown() {
        traveledY++;
    }

    public void moveUp() {
        traveledY--;
    }
}

