package com.anaflavia.tetris.game;

import com.anaflavia.tetris.config.Config;

public class Board {

    private final int[][] grid;

    public Board() {
        grid = new int[Config.BOARD_HEIGHT][Config.BOARD_WIDTH];
    }
    private void removeLine(int row) {

    for (int currentRow = row; currentRow > 0; currentRow--) {

        for (int column = 0; column < Config.BOARD_WIDTH; column++) {

            grid[currentRow][column] = grid[currentRow - 1][column];

        }
    }

    // Limpa a primeira linha
    for (int column = 0; column < Config.BOARD_WIDTH; column++) {
        grid[0][column] = 0;
    }
}
private boolean isLineFull(int row) {

    for (int column = 0; column < Config.BOARD_WIDTH; column++) {

        if (grid[row][column] == 0) {
            return false;
        }

    }

    return true;
}
public int clearLines() {

    int linesCleared = 0;

    for (int row = Config.BOARD_HEIGHT - 1; row >= 0; row--) {

        if (isLineFull(row)) {

            removeLine(row);

            linesCleared++;

            row++; // Verifica novamente a mesma linha

        }

    }

    return linesCleared;
}

    public int[][] getGrid() {
        return grid;
    }
    public int getCell(int row, int column) {
        return grid[row][column];
    }
    public void setCell(int row, int column, int value) {
        grid[row][column] = value;
    }
    public void clear() {
        for (int row = 0; row < Config.BOARD_HEIGHT; row++) {
            for (int column = 0; column < Config.BOARD_WIDTH; column++) {
                grid[row][column] = 0;
            }
        }
    }

}