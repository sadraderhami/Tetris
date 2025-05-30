package org.example.model;

import java.util.Random;

public class GameModel {
    boolean[][] boardCells = new boolean[20][10];

    int[][] LShapeX = {{1, 0, 0, 0}, {0, 1, 2, 2}, {1, 1, 1, 0}, {0, 0, 1, 2}};
    int[][] LShapeY = {{2, 2, 1, 0}, {1, 1, 1, 0}, {2, 1, 0, 0}, {1, 0, 0, 0}};
    int[][] SShapeX = {{0, 1, 1, 2}, {1, 1, 0, 0}, {0, 1, 1, 2}, {1, 1, 0, 0}};
    int[][] SShapeY = {{1, 1, 0, 0}, {2, 1, 1, 0}, {1, 1, 0, 0}, {2, 1, 1, 0}};
    int[][] ZShapeX = {{2, 1, 1, 0}, {1, 1, 0, 0}, {2, 1, 1, 0}, {1, 1, 0, 0}};
    int[][] ZShapeY = {{1, 1, 0, 0}, {0, 1, 1, 2}, {1, 1, 0, 0}, {0, 1, 1, 2}};
    int[][] RevLShapeX = {{0, 1, 1, 1}, {2, 2, 1, 0}, {0, 0, 0, 1}, {2, 1, 0, 0}};
    int[][] RevLShapeY = {{2, 2, 1, 0}, {1, 0, 0, 0}, {2, 1, 0, 0}, {1, 1, 1, 0}};
    int[][] TShapeX = {{1, 2, 1, 0}, {0, 0, 0, 1}, {2, 1, 0, 1}, {1, 1, 1, 0}};
    int[][] TShapeY = {{1, 0, 0, 0}, {2, 1, 0, 1}, {1, 1, 1, 0}, {2, 1, 0, 1}};
    int[][] SquareShapeX = {{1, 0, 1, 0}, {1, 0, 1, 0}, {1, 0, 1, 0}, {1, 0, 1, 0}};
    int[][] SquareShapeY = {{1, 1, 0, 0}, {1, 1, 0, 0}, {1, 1, 0, 0}, {1, 1, 0, 0}};
    int[][] IShapeX = {{0, 0, 0, 0}, {0, 1, 2, 3}, {0, 0, 0, 0}, {0, 1, 2, 3}};
    int[][] IShapeY = {{3, 2, 1, 0}, {0, 0, 0, 0}, {3, 2, 1, 0}, {0, 0, 0, 0}};

    int[] shapeStartingPosition = {4, 0};
    String shape;
    ShapeModel currentShape = new ShapeModel(ShapeType.S);
    int angle;
    boolean shapeReachedBottom = false;
    boolean newShapeGenerated = false;
    boolean paused = false;
    boolean started = false;
    int panelScore = 0;
//    Timer timer;



    public GameModel() {
//        timer = new Timer(300, e -> {
//            dropShape();
////            labelScoreUpdateLocal.setText(String.valueOf(panelScore));
//        });
    }

    public ShapeModel getCurrentShape() {
        return currentShape;
    }

    public boolean isGameOver(){
        return false;
    }

    public boolean[][] getBoardCells() {
        return boardCells;
    }

    public void update(){
        dropShape();
    }

    public void startGame() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                boardCells[i][j] = false;
            }
        }
        generateNewShape();
        started = true;
        paused = false;
//        timer.start();
    }

    public void resetGame() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                boardCells[i][j] = false;
            }
        }
        generateNewShape();
        panelScore = 0;
//        timer.stop();
    }

    public void generateNewShape() {
        shapeReachedBottom = false;
        shapeStartingPosition[0] = 4;
        shapeStartingPosition[1] = 0;
        angle = new Random().nextInt(4);
        String[] shapes = {"L", "S", "Z", "RL", "T", "SQ", "I"};
        shape = shapes[new Random().nextInt(shapes.length)];
//        shape = Shape.getRandomShape();
        newShapeGenerated = true;
    }

    boolean gameOver() {
        for (int j = 0; j < 10; j++) {
            if (boardCells[0][j])
                return true;
        }
        return false;
    }

    public void dropShape() {
        int maxY = getMax(getYCoordinates());
        int bottom = shapeStartingPosition[1] + maxY;
        boolean collision = false;
        int[] xs = getXCoordinates();
        int[] ys = getYCoordinates();
        for (int i = 0; i < 4; i++) {
            int x = shapeStartingPosition[0] + xs[i];
            int y = shapeStartingPosition[1] + ys[i];
            if (y + 1 < 20 && boardCells[y + 1][x]) {
                collision = true;
                break;
            }
        }
        if (bottom < 20 - 1 && !collision) {
            shapeStartingPosition[1]++;
        } else {
            for (int i = 0; i < 4; i++) {
                boardCells[shapeStartingPosition[1] + ys[i]][shapeStartingPosition[0] + xs[i]] = true;
            }
            clearFullLines();
            newShapeGenerated = false;
            if (!gameOver()) {
                generateNewShape();
            } else {
//                timer.stop();
            }
        }
    }

    public void moveRight() {
        int maxX = getMax(getXCoordinates());
        int right = shapeStartingPosition[0] + maxX;
        boolean collision = false;
        int[] xs = getXCoordinates();
        int[] ys = getYCoordinates();
        for (int i = 0; i < 4; i++) {
            int x = shapeStartingPosition[0] + xs[i];
            int y = shapeStartingPosition[1] + ys[i];
            if (x + 1 < 10 && boardCells[y][x + 1]) {
                collision = true;
                break;
            }
        }
        if (!collision && right < 10 - 1) {
            shapeStartingPosition[0]++;
        }
    }

    public void moveLeft() {
        int minX = getMin(getXCoordinates());
        int left = shapeStartingPosition[0] + minX;
        boolean collision = false;
        int[] xs = getXCoordinates();
        int[] ys = getYCoordinates();
        for (int i = 0; i < 4; i++) {
            int x = shapeStartingPosition[0] + xs[i];
            int y = shapeStartingPosition[1] + ys[i];
            if (x - 1 >= 0 && boardCells[y][x - 1]) {
                collision = true;
                break;
            }
        }
        if (!collision && left > 0) {
            shapeStartingPosition[0]--;
        }
    }

    public void rotate() {
        int oldAngle = angle;
        angle = (angle == 0 ? 3 : angle - 1);
        int[] xs = getXCoordinates();
        int[] ys = getYCoordinates();
        for (int i = 0; i < 4; i++) {
            int x = shapeStartingPosition[0] + xs[i];
            int y = shapeStartingPosition[1] + ys[i];
            if (x < 0 || x >= 10 || (y < 20 && boardCells[y][x])) {
                angle = oldAngle;
                break;
            }
        }
    }

    public void clearFullLines() {
        int linesCleared = 0;
        for (int i = 20 - 1; i >= 0; i--) {
            boolean full = true;
            for (int j = 0; j < 10; j++) {
                if (!boardCells[i][j]) {
                    full = false;
                    break;
                }
            }
            if (full) {
                linesCleared++;
                for (int k = i; k > 0; k--) {
                    boardCells[k] = boardCells[k - 1].clone();
                }
                boardCells[0] = new boolean[10];
                i++;
            }
        }
        switch (linesCleared) {
            case 1:
                panelScore += 1;
                break;
            case 2:
                panelScore += 3;
                break;
            case 3:
                panelScore += 5;
                break;
            case 4:
                panelScore += 10;
                break;
        }
    }

    int[] getXCoordinates() {
        return switch (shape) {
            case "L" -> LShapeX[angle];
            case "S" -> SShapeX[angle];
            case "Z" -> ZShapeX[angle];
            case "RL" -> RevLShapeX[angle];
            case "T" -> TShapeX[angle];
            case "SQ" -> SquareShapeX[angle];
            case "I" -> IShapeX[angle];
            default -> LShapeX[angle];
        };

    }

    int[] getYCoordinates() {
        return switch (shape) {
            case "L" -> LShapeY[angle];
            case "S" -> SShapeY[angle];
            case "Z" -> ZShapeY[angle];
            case "RL" -> RevLShapeY[angle];
            case "T" -> TShapeY[angle];
            case "SQ" -> SquareShapeY[angle];
            case "I" -> IShapeY[angle];
            default -> LShapeY[angle];
        };
    }

    int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max)
                max = arr[i];
        }
        return max;
    }

    int getMin(int[] arr) {
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min)
                min = arr[i];
        }
        return min;
    }
}
