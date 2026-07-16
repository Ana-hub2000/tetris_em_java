package com.anaflavia.tetris.game;

import com.anaflavia.tetris.config.Config;
import com.googlecode.lanterna.TextColor;

public class Piece {

    private Tetromino tetromino;

    private int row;
    private int column;
    private int rotation;

    public Piece(Tetromino tetromino, int row, int column) {

        this.tetromino = tetromino;
        this.row = row;
        this.column = column;
        this.rotation = 0;

    }
    public void setRow(int row) {
    this.row = row;
}

public void setColumn(int column) {
    this.column = column;
}
    
    public int getId() {
    return tetromino.ordinal() + 1;
}
    public TextColor getColor() {
    return tetromino.getColor();
}
    public void rotate() {
    rotation = (rotation + 1) % 4;
}

    public Tetromino getTetromino() {
        return tetromino;
    }

    public int[][] getShape() {
    return tetromino.getShape(rotation);
}
    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getRotation() {
        return rotation;
    }

   public void moveDown() {
    if (row < Config.BOARD_HEIGHT - 1) {
        row++;
    }
}

    public void moveLeft() {
        column--;
    }

    public void moveRight() {
        column++;
    }

}