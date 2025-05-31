package org.example.model;

import static org.example.config.ModelConstants.*;

public class GameModel {
    private boolean[][] boardCells = new boolean[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];
    private ShapeModel currentShape;
    private int score = 0;

    public ShapeModel getCurrentShape() {
        return currentShape;
    }

    public boolean[][] getBoardCells() {
        return boardCells;
    }

    public int getScore() {
        return score;
    }

    public void startGame() {
        resetTheBoard();
        generateNewShape();
    }

    private void resetTheBoard() {
        for (int i = 0; i < NUMBER_OF_ROWS; i++) {
            for (int j = 0; j < NUMBER_OF_COLUMNS; j++) {
                boardCells[i][j] = false;
            }
        }
    }
    private void resetTheScore(){
        score = 0;
    }

    public void restartGame() {
        resetTheScore();
        startGame();
    }

    public void generateNewShape() {
        currentShape = ShapeModel.getRandomShape();
    }

    public boolean isGameOver() {
        for (int j = 0; j < NUMBER_OF_COLUMNS; j++) {
            if (boardCells[0][j])
                return true;
        }
        return false;
    }

    public void handleGameOver() {

    }

    private void lockCurrentShapeAndProceed() {
        // 1. Lock shape into board
        for (CellBlockModel cellBlockModel : currentShape.getCellBlocks()) {
            int x = (int) (currentShape.getOrigin().getX() + cellBlockModel.getPositionInShape().getX() + currentShape.getTraveledX());
            int y = (int) (currentShape.getOrigin().getY() + cellBlockModel.getPositionInShape().getY() + currentShape.getTraveledY());
            boardCells[y][x] = true;
        }

        // 2. Clear lines
        clearFullLines();

        // 3. Check game over
        if (isGameOver()) {
            handleGameOver(); // Optional: centralize end-game logic
            return;
        }

        // 4. Generate next shape
        generateNewShape();
    }


    public void dropShapeIfPossible() {
//        int maxY = getMax(getYCoordinates());
//        int bottom = shapeStartingPosition[1] + maxY;
//        boolean collision = false;
//        int[] xs = getXCoordinates();
//        int[] ys = getYCoordinates();
//        for (int i = 0; i < 4; i++) {
//            int x = shapeStartingPosition[0] + xs[i];
//            int y = shapeStartingPosition[1] + ys[i];
//            if (y + 1 < NUMBER_OF_ROWS && boardCells[y + 1][x]) {
//                collision = true;
//                break;
//            }
//        }
//        if (bottom < NUMBER_OF_ROWS - 1 && !collision) {
//            shapeStartingPosition[1]++;
//            currentShape.moveDown();
//        } else {
//            for (int i = 0; i < 4; i++) {
//                boardCells[shapeStartingPosition[1] + ys[i]][shapeStartingPosition[0] + xs[i]] = true;
//            }
//            clearFullLines();
//            newShapeGenerated = false;
//            if (!gameOver()) {
//                generateNewShape();
//            } else {
////                timer.stop();
//            }
//        }
        currentShape.moveDown();
        if (!isStable(currentShape)) {
            currentShape.moveUp();
            lockCurrentShapeAndProceed();
        }

    }


    public void moveRightIfPossible() {
        currentShape.moveRight();
        if (!isStable(currentShape)) {
            currentShape.moveLeft();
        }
    }


    public void moveLeftIfPossible() {
        currentShape.moveLeft();
        if (!isStable(currentShape)) {
            currentShape.moveRight();
        }
    }

    public void rotateIfPossible() {
        currentShape.rotateCounterClockwise();
        if (!isStable(currentShape)) {
            currentShape.rotateClockwise();
        }
    }


    public boolean isStable(ShapeModel shapeModel) {
        for (CellBlockModel cellBlockModel : shapeModel.getCellBlocks()) {
            double absoluteX = shapeModel.getOrigin().getX() +
                    cellBlockModel.getPositionInShape().getX() +
                    shapeModel.getTraveledX();
            double absoluteY = shapeModel.getOrigin().getY() +
                    cellBlockModel.getPositionInShape().getY() +
                    shapeModel.getTraveledY();
            if (absoluteX < 0 || absoluteX >= NUMBER_OF_COLUMNS) {
                return false;
            }
            if (absoluteY < 0 || absoluteY >= NUMBER_OF_ROWS) {
                return false;
            }
            if (boardCells[(int) absoluteY][(int) absoluteX]) {
                return false;
            }
        }
        return true;
    }

//    public boolean isThisLineFull(int indexFromTheTop) {
//        for (int i = 0; i < NUMBER_OF_ROWS; i++) {
//            for (int j = 0; j < NUMBER_OF_COLUMNS; j++) {
//                if (!boardCells[i][j]) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }

    //    public void clearFullLines() {
//        int linesCleared = 0;
//        for (int i = NUMBER_OF_COLUMNS - 1; i >= 0; i--) {
//            boolean full = true;
//            for (int j = 0; j < NUMBER_OF_ROWS; j++) {
//                if (!boardCells[i][j]) {
//                    full = false;
//                    break;
//                }
//            }
//            if (full) {
//                linesCleared++;
//                for (int k = i; k > 0; k--) {
//                    boardCells[k] = boardCells[k - 1].clone();
//                }
//                boardCells[0] = new boolean[NUMBER_OF_COLUMNS];
//                i++;
//            }
//        }
//        switch (linesCleared) {
//            case 1:
//                score += 1;
//                break;
//            case 2:
//                score += 3;
//                break;
//            case 3:
//                score += 5;
//                break;
//            case 4:
//                score += 10;
//                break;
//        }
//    }
    public void clearFullLines() {
        int linesCleared = 0;

        for (int row = NUMBER_OF_ROWS - 1; row >= 0; row--) {
            if (isRowFull(row)) {
                removeRow(row);
                row++; // recheck same row after shifting down
                linesCleared++;
            }
        }

        updateScore(linesCleared);
    }

    private boolean isRowFull(int row) {
        for (int col = 0; col < NUMBER_OF_COLUMNS; col++) {
            if (!boardCells[row][col]) {
                return false;
            }
        }
        return true;
    }

    private void removeRow(int rowToRemove) {
        for (int row = rowToRemove; row > 0; row--) {
            boardCells[row] = boardCells[row - 1].clone();
        }
        boardCells[0] = new boolean[NUMBER_OF_COLUMNS];
    }

    private void updateScore(int linesCleared) {
        switch (linesCleared) {
            case 1 -> score += 1;
            case 2 -> score += 3;
            case 3 -> score += 5;
            case 4 -> score += 10;
        }
    }

}
