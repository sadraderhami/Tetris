package org.example.view;

import org.example.controller.GameController;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyFrame extends JFrame {
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
    int angle;
    boolean shapeReachedBottom = false;
    boolean newShapeGenerated = false;
    boolean paused = false;
    boolean started = false;
//    JLabel labelScore;
    JButton startButton;
    JButton pauseButton;
    int panelScore = 0;
    Timer timer;
    KeyListener listener;
    GamePanel gamePanel;
    JPanel sidePanel;

    private static final Logger logger = Logger.getLogger(GameController.class.getName());


    static {
        logger.setLevel(Level.ALL); // Show everything
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);
        logger.addHandler(handler);
    }

    CardLayout cardLayout;
    JPanel mainPanel;

    public MyFrame() {
        setSize(430, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);


        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);




//        listener = new CustomKeyListener();
        gamePanel = new GamePanel();
        initGamePanel();

        sidePanel = new SidePanel();
        initSidePanel();





        startButton = new JButton("Start");
        startButton.setBounds(20, 120, 70, 30);
        sidePanel.add(startButton);
        pauseButton = new JButton("Pause");
        pauseButton.setBounds(100, 120, 70, 30);
        sidePanel.add(pauseButton);

        startButton.addActionListener(e -> {
            if (!started) {
                startGame();
                startButton.setText("Reset");
                started = true;
            } else {
                resetGame();
                startButton.setText("Start");
                started = false;
            }
        });
        pauseButton.addActionListener(e -> {
            if (started && !gameOver()) {
                if (!paused) {
                    timer.stop();
                    pauseButton.setText("Resume");
                    paused = true;
                } else {
                    timer.start();
                    pauseButton.setText("Pause");
                    paused = false;
                }
            }
        });

        timer = new Timer(300, e -> {
            logger.log(Level.SEVERE, Arrays.toString(getXCoordinates()));
            dropShape();
            gamePanel.repaint();
//            labelScoreUpdateLocal.setText(String.valueOf(panelScore));
        });
        gamePanel.setFocusable(true);
        gamePanel.addKeyListener(listener);
        setVisible(true);
    }

    private void initSidePanel() {
        sidePanel.setBounds(230, 10, 180, 400);
        this.add(sidePanel);
    }

    private void initGamePanel() {
        gamePanel.setBounds(10, 10, 210, 400);
        gamePanel.setVisible(true);
        add(gamePanel);
    }

    void startGame() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                boardCells[i][j] = false;
            }
        }
        generateNewShape();
        started = true;
        paused = false;
        timer.start();
    }

    void resetGame() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                boardCells[i][j] = false;
            }
        }
        generateNewShape();
        panelScore = 0;
        timer.stop();
    }

    void generateNewShape() {
        shapeReachedBottom = false;
        shapeStartingPosition[0] = 4;
        shapeStartingPosition[1] = 0;
        angle = new Random().nextInt(4);
        String[] shapes = {"L", "S", "Z", "RL", "T", "SQ", "I"};
        shape = shapes[new Random().nextInt(shapes.length)];
        newShapeGenerated = true;
    }

    boolean gameOver() {
        for (int j = 0; j < 10; j++) {
            if (boardCells[0][j])
                return true;
        }
        return false;
    }

    void dropShape() {
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
                timer.stop();
            }
        }
    }

    void moveRight() {
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

    void moveLeft() {
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

    void rotate() {
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

    void clearFullLines() {
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

    public class GamePanel extends JPanel {
        public void paintComponent(Graphics g) {
            gamePanel.setFocusable(true);
            gamePanel.requestFocusInWindow();
            super.paintComponent(g);
            g.setColor(Color.BLUE);
            for (int i = 0; i <= 10; i++) {
                g.drawLine(i * 20, 0, i * 20, 400);
            }
            for (int i = 0; i <= 20; i++) {
                g.drawLine(0, i * 20, 200, i * 20);
            }
            if (newShapeGenerated) {
                int[] xs = getXCoordinates();
                int[] ys = getYCoordinates();
                g.setColor(Color.MAGENTA);
                for (int i = 0; i < 4; i++) {
                    int x = (shapeStartingPosition[0] + xs[i]) * 20;
                    int y = (shapeStartingPosition[1] + ys[i]) * 20;
                    g.fill3DRect(x, y, 19, 19, true);
                }
            }
            g.setColor(Color.GREEN);
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 10; j++) {
                    if (boardCells[i][j]) {
                        g.fill3DRect(j * 20, i * 20, 19, 19, true);
                    }
                }
            }
            if (gameOver()) {
                timer.stop();
                g.setColor(Color.MAGENTA);
                g.setFont(new Font("Arial", Font.BOLD, 20));
                g.drawString("GAME OVER", 30, 200);
            }
        }
    }

    private class CustomKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                rotate();
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                dropShape();
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                moveRight();
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                moveLeft();
            }
            repaint();
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }
}