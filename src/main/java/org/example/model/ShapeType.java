package org.example.model;

import java.awt.Point;

public enum ShapeType {
    L(new Point[][] {
            {p(1,2), p(0,2), p(0,1), p(0,0)},
            {p(0,1), p(1,1), p(2,1), p(2,0)},
            {p(1,2), p(1,1), p(1,0), p(0,0)},
            {p(0,1), p(0,0), p(1,0), p(2,0)}
    }),

    S(new Point[][] {
            {p(0,1), p(1,1), p(1,0), p(2,0)},
            {p(1,2), p(1,1), p(0,1), p(0,0)},
            {p(0,1), p(1,1), p(1,0), p(2,0)},
            {p(1,2), p(1,1), p(0,1), p(0,0)}
    }),

    Z(new Point[][] {
            {p(2,1), p(1,1), p(1,0), p(0,0)},
            {p(1,0), p(1,1), p(0,1), p(0,2)},
            {p(2,1), p(1,1), p(1,0), p(0,0)},
            {p(1,0), p(1,1), p(0,1), p(0,2)}
    }),

    REVERSE_L(new Point[][] {
            {p(0,2), p(1,2), p(1,1), p(1,0)},
            {p(2,1), p(2,0), p(1,0), p(0,0)},
            {p(0,2), p(0,1), p(0,0), p(1,0)},
            {p(2,1), p(1,1), p(0,1), p(0,0)}
    }),

    T(new Point[][] {
            {p(1,1), p(2,0), p(1,0), p(0,0)},
            {p(0,2), p(0,1), p(0,0), p(1,1)},
            {p(2,1), p(1,1), p(0,1), p(1,0)},
            {p(1,2), p(1,1), p(1,0), p(0,1)}
    }),

    SQUARE(new Point[][] {
            {p(1,1), p(0,1), p(1,0), p(0,0)},
            {p(1,1), p(0,1), p(1,0), p(0,0)},
            {p(1,1), p(0,1), p(1,0), p(0,0)},
            {p(1,1), p(0,1), p(1,0), p(0,0)}
    }),

    I(new Point[][] {
            {p(0,3), p(0,2), p(0,1), p(0,0)},
            {p(0,0), p(1,0), p(2,0), p(3,0)},
            {p(0,3), p(0,2), p(0,1), p(0,0)},
            {p(0,0), p(1,0), p(2,0), p(3,0)}
    });

    private final Point[][] rotations;

    ShapeType(Point[][] rotations) {
        this.rotations = rotations;
    }

    public Point[] getRotation(int index) {
        return rotations[index % 4];
    }

    public int rotationCount() {
        return rotations.length;
    }

    private static Point p(int x, int y) {
        return new Point(x, y);
    }
}

