package org.example.view;

import org.example.model.CellBlockModel;

import java.awt.*;


import static org.example.config.ViewConstants.GameConstants.TetrisPanelConstants.*;

public class CellBlockView {
    private CellBlockModel cellBlockModel;

    public CellBlockView(CellBlockModel cellBlockModel) {
        this.cellBlockModel = cellBlockModel;
    }

    public void paint(Graphics2D g2d, int absoluteX, int absoluteY) {
        g2d.setColor(ACTIVE_TETROMINO_COLOR);
        int cellBlockSize = (int) (CELL_SIZE * 0.9);
        g2d.fillRect(absoluteX * CELL_SIZE,
                absoluteY * CELL_SIZE,
                cellBlockSize, cellBlockSize);
    }
}
