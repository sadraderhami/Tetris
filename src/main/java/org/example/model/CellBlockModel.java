package org.example.model;

import java.awt.Color;
import java.awt.Point;

public class CellBlockModel {
    private int x;
    private int y;

    public CellBlockModel(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public CellBlockModel(Point position) {
        this.x = position.x;
        this.y = position.y;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point getPosition() {
        return new Point(x, y);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setPosition(Point p) {
        this.x = p.x;
        this.y = p.y;
    }


    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
}

