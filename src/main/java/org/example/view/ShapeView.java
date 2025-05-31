package org.example.view;

import org.example.model.CellBlockModel;
import org.example.model.ShapeModel;

import java.awt.*;

public class ShapeView {
    private ShapeModel shapeModel;

    public ShapeView(ShapeModel shapeModel) {
        this.shapeModel = shapeModel;
    }

    public void paint(Graphics2D g2d) {
        for (CellBlockModel cellBlockModel : shapeModel.getCellBlocks()) {
            double absoluteX = shapeModel.getOrigin().getX() +
                    cellBlockModel.getPositionInShape().getX() +
                    shapeModel.getTraveledX();
            double absoluteY = shapeModel.getOrigin().getY() +
                    cellBlockModel.getPositionInShape().getY() +
                    shapeModel.getTraveledY();
            CellBlockView cellBlockView = new CellBlockView(cellBlockModel);
            cellBlockView.paint(g2d, (int) absoluteX, (int) absoluteY);
        }
    }
}
