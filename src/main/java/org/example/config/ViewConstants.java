package org.example.config;

import com.google.gson.JsonObject;

import java.awt.*;

import static org.example.config.ModelConstants.*;

public class ViewConstants {
    public static int CELL_SIZE = 40;
    public static int FRAME_WIDTH = 16 * CELL_SIZE;
    public static int FRAME_HEIGHT = NUMBER_OF_ROWS * CELL_SIZE;

    public static Color BUTTON_COLOR = new Color(255, 196, 0);
    public static Color BACKGROUND_TOP_COLOR = new Color(132, 255, 0);
    public static Color BACKGROUND_BOTTOM_COLOR = new Color(22, 104, 0);
    public static Color GLOWING_LINE_FIRST_SHADOW = new Color(35, 22, 0);
    public static Color GLOWING_LINE_SECOND_SHADOW = new Color(89, 64, 0);
    public static Color GLOWING_LINE_THIRD_SHADOW = new Color(161, 124, 0);

    public static class MenuConstants {
        public static int CELL_SIZE = ViewConstants.CELL_SIZE;
        public static int MENU_PANEL_WIDTH = ViewConstants.FRAME_WIDTH;
        public static int MENU_PANEL_HEIGHT = ViewConstants.FRAME_HEIGHT;
        public static int BUTTON_HEIGHT = CELL_SIZE;
        public static int BUTTON_MARGIN = CELL_SIZE;
        public static int BUTTON_WIDTH = 4 * CELL_SIZE;
        public static Color BUTTON_COLOR = ViewConstants.BUTTON_COLOR;
        public static Font BUTTON_FONT = new Font("Bauhaus 93", Font.BOLD, 20);
        public static GradientPaint MENU_PANEL_GRADIANT_PAINT = new GradientPaint(0, 0, BACKGROUND_TOP_COLOR, 0, MENU_PANEL_HEIGHT, BACKGROUND_BOTTOM_COLOR);
        public static Color GLOWING_LINE_FIRST_SHADOW = ViewConstants.GLOWING_LINE_FIRST_SHADOW;
        public static Color GLOWING_LINE_SECOND_SHADOW = ViewConstants.GLOWING_LINE_SECOND_SHADOW;
        public static Color GLOWING_LINE_THIRD_SHADOW = ViewConstants.GLOWING_LINE_THIRD_SHADOW;
    }

    public static class GameConstants {
        public static int CELL_SIZE = ViewConstants.CELL_SIZE;
        public static int GAME_PANEL_WIDTH = FRAME_WIDTH;
        public static int GAME_PANEL_HEIGHT = FRAME_HEIGHT;
        public static int BUTTON_HEIGHT = CELL_SIZE;
        public static int BUTTON_WIDTH = 4 * CELL_SIZE;
        public static Color BUTTON_COLOR = ViewConstants.BUTTON_COLOR;
        public static Font BUTTON_FONT = new Font("Bauhaus 93", Font.BOLD, 20);

        public static class TetrisPanelConstants {
            public static int CELL_SIZE = GameConstants.CELL_SIZE;
            public static int TETRIS_PANEL_WIDTH = NUMBER_OF_COLUMNS * CELL_SIZE;
            public static int TETRIS_PANEL_HEIGHT = GAME_PANEL_HEIGHT;
            public static Color GLOWING_LINE_FIRST_SHADOW = ViewConstants.GLOWING_LINE_FIRST_SHADOW;
            public static Color GLOWING_LINE_SECOND_SHADOW = ViewConstants.GLOWING_LINE_SECOND_SHADOW;
            public static Color GLOWING_LINE_THIRD_SHADOW = ViewConstants.GLOWING_LINE_THIRD_SHADOW;
            public static GradientPaint GAME_PANEL_GRADIANT_PAINT = new GradientPaint(0, 0, BACKGROUND_TOP_COLOR, 0, GAME_PANEL_HEIGHT, BACKGROUND_BOTTOM_COLOR);
            public static Color STABLE_TETROMINO_COLOR = new Color(0, 255, 111);
            public static Color ACTIVE_TETROMINO_COLOR = Color.MAGENTA;

            public static void reevaluateSizes(int numberOfRows, int numberOfColumns) {
                NUMBER_OF_ROWS = numberOfRows;
                NUMBER_OF_COLUMNS = numberOfColumns;
                TETRIS_PANEL_WIDTH = NUMBER_OF_ROWS * CELL_SIZE;
                TETRIS_PANEL_HEIGHT = GAME_PANEL_HEIGHT;
            }
        }

        public static class SidePanelConstants {
            public static int SIDE_PANEL_WIDTH = GAME_PANEL_WIDTH - TetrisPanelConstants.TETRIS_PANEL_WIDTH;
            public static int SIDE_PANEL_HEIGHT = GAME_PANEL_HEIGHT;

            public static void reevaluateSizes(int numberOfRows, int numberOfColumns) {
                NUMBER_OF_ROWS = numberOfRows;
                NUMBER_OF_COLUMNS = numberOfColumns;
                SIDE_PANEL_WIDTH = GAME_PANEL_WIDTH - TetrisPanelConstants.TETRIS_PANEL_WIDTH;
                SIDE_PANEL_HEIGHT = GAME_PANEL_HEIGHT;
            }
        }

        public static void reevaluateSizes(int numberOfRows, int numberOfColumns) {
            NUMBER_OF_ROWS = numberOfRows;
            NUMBER_OF_COLUMNS = numberOfColumns;
            GAME_PANEL_WIDTH = NUMBER_OF_ROWS * CELL_SIZE;
            GAME_PANEL_HEIGHT = NUMBER_OF_COLUMNS * CELL_SIZE;
            TetrisPanelConstants.reevaluateSizes(numberOfRows, numberOfColumns);
            SidePanelConstants.reevaluateSizes(numberOfRows, numberOfColumns);
        }
    }

    public static void reevaluateSizes(int numberOfRows, int numberOfColumns) {
        NUMBER_OF_ROWS = numberOfRows;
        NUMBER_OF_COLUMNS = numberOfColumns;
        GameConstants.reevaluateSizes(numberOfRows, numberOfColumns);
    }

    // --------- Loader method ---------

    /**
     * Call this once at startup with your ConfigManager or similar.
     * It rewrites all the static fields with config values.
     */
    public static void loadFromConfig(ConfigManager config) {
        CELL_SIZE = config.getInt("cellSize", CELL_SIZE);
        FRAME_WIDTH = config.getInt("frameWidth", 16 * CELL_SIZE);
        FRAME_HEIGHT = config.getInt("frameHeight", 21 * CELL_SIZE);

        BUTTON_COLOR = config.getColor("buttonColor", BUTTON_COLOR);
        BACKGROUND_TOP_COLOR = config.getColor("backgroundTopColor", BACKGROUND_TOP_COLOR);
        BACKGROUND_BOTTOM_COLOR = config.getColor("backgroundBottomColor", BACKGROUND_BOTTOM_COLOR);
        GLOWING_LINE_FIRST_SHADOW = config.getColor("glowingLineFirstShadow", GLOWING_LINE_FIRST_SHADOW);
        GLOWING_LINE_SECOND_SHADOW = config.getColor("glowingLineSecondShadow", GLOWING_LINE_SECOND_SHADOW);
        GLOWING_LINE_THIRD_SHADOW = config.getColor("glowingLineThirdShadow", GLOWING_LINE_THIRD_SHADOW);

        JsonObject menu = config.getJsonObject("menuConstants");
        if (menu != null) {
            MenuConstants.BUTTON_HEIGHT = menu.has("buttonHeight") ? menu.get("buttonHeight").getAsInt() : MenuConstants.BUTTON_HEIGHT;
            MenuConstants.BUTTON_WIDTH = menu.has("buttonWidth") ? menu.get("buttonWidth").getAsInt() : MenuConstants.BUTTON_WIDTH;
            MenuConstants.BUTTON_MARGIN = menu.has("buttonMargin") ? menu.get("buttonMargin").getAsInt() : MenuConstants.BUTTON_MARGIN;

            String fontName = menu.has("buttonFontName") ? menu.get("buttonFontName").getAsString() : "Bauhaus 93";
            int fontStyle = parseFontStyle(menu.has("buttonFontStyle") ? menu.get("buttonFontStyle").getAsString() : "BOLD");
            int fontSize = menu.has("buttonFontSize") ? menu.get("buttonFontSize").getAsInt() : 20;
            MenuConstants.BUTTON_FONT = new Font(fontName, fontStyle, fontSize);
        }

        JsonObject game = config.getJsonObject("gameConstants");
        if (game != null) {
            GameConstants.BUTTON_HEIGHT = game.has("buttonHeight") ? game.get("buttonHeight").getAsInt() : GameConstants.BUTTON_HEIGHT;
            GameConstants.BUTTON_WIDTH = game.has("buttonWidth") ? game.get("buttonWidth").getAsInt() : GameConstants.BUTTON_WIDTH;

            String fontName = game.has("buttonFontName") ? game.get("buttonFontName").getAsString() : "Bauhaus 93";
            int fontStyle = parseFontStyle(game.has("buttonFontStyle") ? game.get("buttonFontStyle").getAsString() : "BOLD");
            int fontSize = game.has("buttonFontSize") ? game.get("buttonFontSize").getAsInt() : 20;
            GameConstants.BUTTON_FONT = new Font(fontName, fontStyle, fontSize);

            JsonObject tetrisPanel = game.has("tetrisPanelConstants") ? game.get("tetrisPanelConstants").getAsJsonObject() : null;
            if (tetrisPanel != null) {
                GameConstants.TetrisPanelConstants.TETRIS_PANEL_WIDTH = tetrisPanel.has("tetrisPanelWidth") ?
                        tetrisPanel.get("tetrisPanelWidth").getAsInt() : GameConstants.TetrisPanelConstants.TETRIS_PANEL_WIDTH;
            }

            JsonObject sidePanel = game.has("sidePanelConstants") ? game.get("sidePanelConstants").getAsJsonObject() : null;
            if (sidePanel != null) {
                GameConstants.SidePanelConstants.SIDE_PANEL_WIDTH = sidePanel.has("sidePanelWidth") ?
                        sidePanel.get("sidePanelWidth").getAsInt() : GameConstants.SidePanelConstants.SIDE_PANEL_WIDTH;
            }
        }
    }

    private static int parseFontStyle(String style) {
        switch (style.toUpperCase()) {
            case "BOLD" -> {
                return Font.BOLD;
            }
            case "ITALIC" -> {
                return Font.ITALIC;
            }
            case "PLAIN" -> {
                return Font.PLAIN;
            }
//            case "GAME_THEME_FONT" -> {
//                return new Font("Bauhaus 93", Font.BOLD, 20);
//            }
            default -> {

                return Font.BOLD;
            }
        }
    }
}

