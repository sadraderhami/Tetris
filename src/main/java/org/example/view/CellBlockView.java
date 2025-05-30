package org.example.view;

import org.example.model.CellBlockModel;

import java.awt.*;


import static org.example.config.ViewConstants.GameConstants.TetrisPanelConstants.*;

public class CellBlockView {
    private CellBlockModel cellBlockModel;

    public CellBlockView(CellBlockModel cellBlockModel) {
        this.cellBlockModel = cellBlockModel;
    }

    public void paint(Graphics2D g2d) {
        g2d.fillRect(cellBlockModel.getX(), cellBlockModel.getY(), CELL_SIZE, CELL_SIZE);
    }
}
