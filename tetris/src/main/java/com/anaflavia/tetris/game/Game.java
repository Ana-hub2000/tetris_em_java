package com.anaflavia.tetris.game;

import com.anaflavia.tetris.config.Config;
import com.anaflavia.tetris.render.Renderer;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import java.util.Random;




public class Game {
    private Piece nextPiece;
    private long lastFallTime;
    private final Board board;
    private final Renderer renderer;
    private Piece currentPiece;
    private boolean running;
    private final Random random;
    private int score;
    private GameState state;



    
    private void processInput() throws Exception {

    KeyStroke key = renderer.getScreen().pollInput();

    if (key == null) {
        return;
    }

    switch (key.getKeyType()) {

        case ArrowLeft:

    if (canMoveLeft()) {
        currentPiece.moveLeft();
    }

    break;

       case ArrowRight:

    if (canMoveRight()) {
        currentPiece.moveRight();
    }

    break;

        case ArrowDown:
            if (canMoveDown()) {
                currentPiece.moveDown();
            }
            break;

        case ArrowUp:
            currentPiece.rotate();
            break;

        case Escape:
            running = false;
            break;

        default:
            break;
    }
}
public Game() throws Exception {
    state = GameState.MENU;
    board = new Board();
    renderer = new Renderer();
    random = new Random();
    currentPiece = createRandomPiece();
nextPiece = createRandomPiece();
    score = 0;
    running = true;
    lastFallTime = System.currentTimeMillis();
    
}
private Piece createRandomPiece() {

    Tetromino[] tetrominoes = Tetromino.values();

    Tetromino randomTetromino =
            tetrominoes[random.nextInt(tetrominoes.length)];

    return new Piece(randomTetromino, 0, 4);
}
private boolean canMoveDown() {

    int[][] shape = currentPiece.getShape();

    for (int row = 0; row < shape.length; row++) {

        for (int column = 0; column < shape[row].length; column++) {

            if (shape[row][column] == 0) {
                continue;
            }

            int nextRow = currentPiece.getRow() + row + 1;
            int nextColumn = currentPiece.getColumn() + column;

            // bateu no chão
            if (nextRow >= Config.BOARD_HEIGHT) {
                return false;
            }

            // bateu em outra peça
            if (board.getCell(nextRow, nextColumn) != 0) {
                return false;
            }
        }
    }

    return true;
}
public void start() throws Exception {



    while (running) {

        switch (state) {

            case MENU:
                updateMenu();
                break;

            case PLAYING:
                updateGame();
                break;

            case GAME_OVER:
                updateGameOver();
                break;
        }

        Thread.sleep(16);
    }

    renderer.getScreen().stopScreen();
}
private void updateGame() throws Exception {

    processInput();

    long currentTime = System.currentTimeMillis();

    if (currentTime - lastFallTime >= 500) {

        if (canMoveDown()) {

            currentPiece.moveDown();

        } else {

            lockPiece();

            int cleared = board.clearLines();

            score += cleared * 100;

            spawnPiece();
        }

        lastFallTime = currentTime;
    }

   renderer.draw(board, currentPiece, nextPiece, score);
}
private boolean canMoveLeft() {

    int[][] shape = currentPiece.getShape();

    for (int row = 0; row < shape.length; row++) {

        for (int column = 0; column < shape[row].length; column++) {

            if (shape[row][column] == 0) {
                continue;
            }

            int nextRow = currentPiece.getRow() + row;
            int nextColumn = currentPiece.getColumn() + column - 1;

            // Parede
            if (nextColumn < 0) {
                return false;
            }

            // Outra peça
            if (board.getCell(nextRow, nextColumn) != 0) {
                return false;
            }
        }
    }

    return true;
}
private void lockPiece() {

    int[][] shape = currentPiece.getShape();

    for (int row = 0; row < shape.length; row++) {

        for (int column = 0; column < shape[row].length; column++) {

            if (shape[row][column] == 1) {

                int boardRow = currentPiece.getRow() + row;
                int boardColumn = currentPiece.getColumn() + column;

                board.setCell(boardRow, boardColumn, currentPiece.getId());
            }
        }
    }
}


private void spawnPiece() {

    currentPiece = new Piece(nextPiece.getTetromino(), 0, 4);

    nextPiece = createRandomPiece();

    if (isGameOver()) {
        state = GameState.GAME_OVER;
    }
}


private boolean canMoveRight() {

    int[][] shape = currentPiece.getShape();

    for (int row = 0; row < shape.length; row++) {

        for (int column = 0; column < shape[row].length; column++) {

            if (shape[row][column] == 0) {
                continue;
            }

            int nextRow = currentPiece.getRow() + row;
            int nextColumn = currentPiece.getColumn() + column + 1;

            // Parede
            if (nextColumn >= Config.BOARD_WIDTH) {
                return false;
            }

            // Outra peça
            if (board.getCell(nextRow, nextColumn) != 0) {
                return false;
            }
        }
    }

    return true;
}
private boolean isGameOver() {

    int[][] shape = currentPiece.getShape();

    for (int row = 0; row < shape.length; row++) {

        for (int column = 0; column < shape[row].length; column++) {

            if (shape[row][column] == 0) {
                continue;
            }

            int boardRow = currentPiece.getRow() + row;
            int boardColumn = currentPiece.getColumn() + column;

            if (board.getCell(boardRow, boardColumn) != 0) {
                return true;
            }
        }
    }

    return false;
}

    public Piece getCurrentPiece() {
        return currentPiece;
    }
   private void updateMenu() throws Exception {

    renderer.drawMenu();

    KeyStroke key = renderer.getScreen().pollInput();

    if (key == null) {
        return;
    }

    switch (key.getKeyType()) {

        case Enter:

            resetGame();

            state = GameState.PLAYING;

            break;

        case Escape:

            running = false;


            break;

        default:
            break;
    }
}
private void updateGameOver() throws Exception {

    renderer.drawGameOver(score);

    KeyStroke key = renderer.getScreen().pollInput();

    if (key == null) {
        return;
    }

    switch (key.getKeyType()) {

        case Enter:

            resetGame();

            state = GameState.PLAYING;

            break;

        case Escape:

            state = GameState.MENU;

            break;

        default:
            break;
    }
}private void resetGame() {

    board.clear();

    score = 0;

    spawnPiece();

    lastFallTime = System.currentTimeMillis();
}
}