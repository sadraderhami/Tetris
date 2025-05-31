package org.example.config;

public class ModelConstants {
    public static int UPDATE_INTERVAL = 16; // in millis
    public static int DROP_INTERVAL = 500;
    public static int NUMBER_OF_ROWS = 17;
    public static int NUMBER_OF_COLUMNS = 10; // for TetrisModel

    public static void setSizeOfTetris(int numberOfRows, int numberOfColumns) {
        NUMBER_OF_ROWS = numberOfRows;
        NUMBER_OF_COLUMNS = numberOfColumns;
        ViewConstants.reevaluateSizes(numberOfRows, numberOfColumns);
    }
    public static void setDropInterval(int dropInterval) {
        DROP_INTERVAL = dropInterval;
    }
}
