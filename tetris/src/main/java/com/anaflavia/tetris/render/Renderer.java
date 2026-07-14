package com.anaflavia.tetris.render;

import com.anaflavia.tetris.config.Config;
import com.anaflavia.tetris.game.Board;
import com.anaflavia.tetris.game.Piece;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.anaflavia.tetris.game.Piece;

public class Renderer {

    private static final TextCharacter HORIZONTAL = TextCharacter.fromCharacter('─')[0];
    private static final TextCharacter VERTICAL = TextCharacter.fromCharacter('│')[0];
    private static final TextCharacter TOP_LEFT = TextCharacter.fromCharacter('┌')[0];
    private static final TextCharacter TOP_RIGHT = TextCharacter.fromCharacter('┐')[0];
    private static final TextCharacter BOTTOM_LEFT = TextCharacter.fromCharacter('└')[0];
    private static final TextCharacter BOTTOM_RIGHT = TextCharacter.fromCharacter('┘')[0];
    private static final TextCharacter BLOCK = TextCharacter.fromCharacter('█')[0];
    private static final TextCharacter EMPTY = TextCharacter.fromCharacter(' ')[0];

    private final Screen screen;

    public Renderer() throws Exception {
        screen = new DefaultTerminalFactory().createScreen();
        screen.startScreen();
    }
    public void drawMenu() throws Exception {

    screen.clear();

    var graphics = screen.newTextGraphics();

    graphics.putString(18, 3, "████████╗███████╗████████╗██████╗ ██╗███████╗");
    graphics.putString(18, 4, "╚══██╔══╝██╔════╝╚══██╔══╝██╔══██╗██║██╔════╝");
    graphics.putString(18, 5, "   ██║   █████╗     ██║   ██████╔╝██║███████╗");
    graphics.putString(18, 6, "   ██║   ██╔══╝     ██║   ██╔══██╗██║╚════██║");
    graphics.putString(18, 7, "   ██║   ███████╗   ██║   ██║  ██║██║███████║");
    graphics.putString(18, 8, "   ╚═╝   ╚══════╝   ╚═╝   ╚═╝  ╚═╝╚═╝╚══════╝");

    graphics.putString(28, 12, "ENTER - Jogar");
    graphics.putString(28, 14, "ESC - Sair");

    graphics.putString(22, 18, "Projeto de Tetris - Java + Lanterna");

    screen.refresh();
}
public void drawGameOver(int score) throws Exception {

    screen.clear();

    var graphics = screen.newTextGraphics();

    graphics.putString(24, 5, "GAME OVER");

    graphics.putString(23, 8, "Pontuação: " + score);

    graphics.putString(18, 12, "ENTER - Jogar novamente");

    graphics.putString(20, 14, "ESC - Voltar ao menu");

    screen.refresh();
}

 public void draw(Board board, Piece piece, int score) throws Exception {

    screen.clear();

    drawBorder();
    drawCells(board);
    drawPiece(piece);
    screen.newTextGraphics().putString(
    Config.BOARD_WIDTH * Config.CELL_SIZE + 5,
    2,
    "Score: " + score
);
screen.newTextGraphics().putString(
    Config.BOARD_WIDTH * Config.CELL_SIZE + 5,
    4,
    "Linhas: " + (score / 100)
);

    screen.refresh();
}
private void drawPiece(Piece piece) {

    int[][] shape = piece.getShape();

    for (int row = 0; row < shape.length; row++) {

        for (int column = 0; column < shape[row].length; column++) {

            if (shape[row][column] == 1) {

                int x = (piece.getColumn() + column) * Config.CELL_SIZE + 1;
                int y = piece.getRow() + row + 1;

                screen.setCharacter(x, y, BLOCK);
                screen.setCharacter(x + 1, y, BLOCK);

            }

        }

    }

}

    private void drawBorder() {

        int boardWidth = Config.BOARD_WIDTH * Config.CELL_SIZE;

        // Laterais
        for (int row = 0; row < Config.BOARD_HEIGHT; row++) {
            screen.setCharacter(0, row + 1, VERTICAL);
            screen.setCharacter(boardWidth + 1, row + 1, VERTICAL);
        }

        // Superior e inferior
        for (int column = 0; column < boardWidth; column++) {
            screen.setCharacter(column + 1, 0, HORIZONTAL);
            screen.setCharacter(column + 1, Config.BOARD_HEIGHT + 1, HORIZONTAL);
        }

        // Cantos
        screen.setCharacter(0, 0, TOP_LEFT);
        screen.setCharacter(boardWidth + 1, 0, TOP_RIGHT);
        screen.setCharacter(0, Config.BOARD_HEIGHT + 1, BOTTOM_LEFT);
        screen.setCharacter(boardWidth + 1, Config.BOARD_HEIGHT + 1, BOTTOM_RIGHT);
    }

    private void drawCells(Board board) {

        for (int row = 0; row < Config.BOARD_HEIGHT; row++) {

            for (int column = 0; column < Config.BOARD_WIDTH; column++) {

                TextCharacter character = board.getCell(row, column) == 0 ? EMPTY : BLOCK;

                screen.setCharacter(column * Config.CELL_SIZE + 1, row + 1, character);
                screen.setCharacter(column * Config.CELL_SIZE + 2, row + 1, character);

            }

        }

    }

    public Screen getScreen() {
        return screen;
    }
}