package org.example.model;

import java.awt.*;

public class CellBlockModel {
//    private final Point origin = new Point(4, 0);
    private final Point positionInShape;
//    private int traveledX = 0;
//    private int traveledY = 0;

    public CellBlockModel(int x, int y) {
        this.positionInShape = new Point(x, y);
    }
    public CellBlockModel(Point positionInShape) {
        this.positionInShape = positionInShape;
    }
    public Point getPositionInShape() {
        return positionInShape;
    }
//    public Point getOrigin() {
//        return origin;
//    }


//    public Point getOrigin() {
//        return origin;
//    }
//
//    public void setOrigin(Point origin) {
//        this.origin = origin;
//    }

//    public int getTraveledX() {
//        return traveledX;
//    }
//
//    public int getTraveledY() {
//        return traveledY;
//    }
//
//    public int getAbsoluteX() {
//        return origin.x + traveledX;
//    }
//
//    public int getAbsoluteY() {
//        return origin.y + traveledY;
//    }
//
//    public Point getPosition() {
//        return new Point(traveledX, traveledY);
//    }
//
//    public void setTraveledX(int traveledX) {
//        this.traveledX = traveledX;
//    }
//
//    public void setTraveledY(int traveledY) {
//        this.traveledY = traveledY;
//    }
//
//    public void setPosition(Point p) {
//        this.traveledX = p.x;
//        this.traveledY = p.y;
//    }
//
//
//    public void move(int dx, int dy) {
//        this.traveledX += dx;
//        this.traveledY += dy;
//    }
//
//    public void moveRight() {
//        traveledX++;
//    }
//
//    public void moveLeft() {
//        traveledX--;
//    }
//
//    public void moveDown() {
//        traveledY++;
//    }
}

