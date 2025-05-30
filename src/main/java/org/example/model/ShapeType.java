package org.example.model;

import java.awt.Point;

public enum ShapeType {
    L(new CellBlockModel[][] {
            {new CellBlockModel(1,2), new CellBlockModel(0,2), new CellBlockModel(0,1), new CellBlockModel(0,0)},
            {new CellBlockModel(0,1), new CellBlockModel(1,1), new CellBlockModel(2,1), new CellBlockModel(2,0)},
            {new CellBlockModel(1,2), new CellBlockModel(1,1), new CellBlockModel(1,0), new CellBlockModel(0,0)},
            {new CellBlockModel(0,1), new CellBlockModel(0,0), new CellBlockModel(1,0), new CellBlockModel(2,0)}
    }),

    S(new CellBlockModel[][] {
            {new CellBlockModel(0,1), new CellBlockModel(1,1), new CellBlockModel(1,0), new CellBlockModel(2,0)},
            {new CellBlockModel(1,2), new CellBlockModel(1,1), new CellBlockModel(0,1), new CellBlockModel(0,0)},
            {new CellBlockModel(0,1), new CellBlockModel(1,1), new CellBlockModel(1,0), new CellBlockModel(2,0)},
            {new CellBlockModel(1,2), new CellBlockModel(1,1), new CellBlockModel(0,1), new CellBlockModel(0,0)}
    }),

    Z(new CellBlockModel[][] {
            {new CellBlockModel(2,1), new CellBlockModel(1,1), new CellBlockModel(1,0), new CellBlockModel(0,0)},
            {new CellBlockModel(1,0), new CellBlockModel(1,1), new CellBlockModel(0,1), new CellBlockModel(0,2)},
            {new CellBlockModel(2,1), new CellBlockModel(1,1), new CellBlockModel(1,0), new CellBlockModel(0,0)},
            {new CellBlockModel(1,0), new CellBlockModel(1,1), new CellBlockModel(0,1), new CellBlockModel(0,2)}
    }),

    REVERSE_L(new CellBlockModel[][] {
            {new CellBlockModel(0,2), new CellBlockModel(1,2), new CellBlockModel(1,1), new CellBlockModel(1,0)},
            {new CellBlockModel(2,1), new CellBlockModel(2,0), new CellBlockModel(1,0), new CellBlockModel(0,0)},
            {new CellBlockModel(0,2), new CellBlockModel(0,1), new CellBlockModel(0,0), new CellBlockModel(1,0)},
            {new CellBlockModel(2,1), new CellBlockModel(1,1), new CellBlockModel(0,1), new CellBlockModel(0,0)}
    }),

    T(new CellBlockModel[][] {
            {new CellBlockModel(1,1), new CellBlockModel(2,0), new CellBlockModel(1,0), new CellBlockModel(0,0)},
            {new CellBlockModel(0,2), new CellBlockModel(0,1), new CellBlockModel(0,0), new CellBlockModel(1,1)},
            {new CellBlockModel(2,1), new CellBlockModel(1,1), new CellBlockModel(0,1), new CellBlockModel(1,0)},
            {new CellBlockModel(1,2), new CellBlockModel(1,1), new CellBlockModel(1,0), new CellBlockModel(0,1)}
    }),

    SQUARE(new CellBlockModel[][] {
            {new CellBlockModel(1,1), new CellBlockModel(0,1), new CellBlockModel(1,0), new CellBlockModel(0,0)},
            {new CellBlockModel(1,1), new CellBlockModel(0,1), new CellBlockModel(1,0), new CellBlockModel(0,0)},
            {new CellBlockModel(1,1), new CellBlockModel(0,1), new CellBlockModel(1,0), new CellBlockModel(0,0)},
            {new CellBlockModel(1,1), new CellBlockModel(0,1), new CellBlockModel(1,0), new CellBlockModel(0,0)}
    }),

    I(new CellBlockModel[][] {
            {new CellBlockModel(0,3), new CellBlockModel(0,2), new CellBlockModel(0,1), new CellBlockModel(0,0)},
            {new CellBlockModel(0,0), new CellBlockModel(1,0), new CellBlockModel(2,0), new CellBlockModel(3,0)},
            {new CellBlockModel(0,3), new CellBlockModel(0,2), new CellBlockModel(0,1), new CellBlockModel(0,0)},
            {new CellBlockModel(0,0), new CellBlockModel(1,0), new CellBlockModel(2,0), new CellBlockModel(3,0)}
    });

    private final CellBlockModel[][] rotations;

    ShapeType(CellBlockModel[][] rotations) {
        this.rotations = rotations;
    }

    public CellBlockModel[] getRotation(int index) {
        return rotations[index % 4];
    }

    public int rotationCount() {
        return rotations.length;
    }

}

