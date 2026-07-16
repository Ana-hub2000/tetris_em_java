package com.anaflavia.tetris.render;

import com.anaflavia.tetris.config.Config;
import com.anaflavia.tetris.game.Board;
import com.anaflavia.tetris.game.Piece;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.TextColor;
import com.anaflavia.tetris.game.Tetromino;


public class Renderer {
    private final Screen screen;

    private boolean blink = true;
    private long lastBlink = System.currentTimeMillis();
    private static final TextCharacter HORIZONTAL = TextCharacter.fromCharacter('─')[0];
    private static final TextCharacter VERTICAL = TextCharacter.fromCharacter('│')[0];
    private static final TextCharacter TOP_LEFT = TextCharacter.fromCharacter('┌')[0];
    private static final TextCharacter TOP_RIGHT = TextCharacter.fromCharacter('┐')[0];
    private static final TextCharacter BOTTOM_LEFT = TextCharacter.fromCharacter('└')[0];
    private static final TextCharacter BOTTOM_RIGHT = TextCharacter.fromCharacter('┘')[0];
    private static final TextCharacter BLOCK = TextCharacter.fromCharacter('█')[0];
    private static final TextCharacter EMPTY = TextCharacter.fromCharacter(' ')[0];
   
    public Renderer() throws Exception {
        screen = new DefaultTerminalFactory().createScreen();
        screen.startScreen();
    }
   private void updateBlink() {

    if (System.currentTimeMillis() - lastBlink >= 350) {

        blink = !blink;
        lastBlink = System.currentTimeMillis();

    }
}
    public void drawMenu() throws Exception {
        screen.clear();
        updateBlink();

        var graphics = screen.newTextGraphics();
       graphics.setForegroundColor(
        blink ? TextColor.ANSI.CYAN : TextColor.ANSI.YELLOW
);


    graphics.putString(18, 3, "████████╗███████╗████████╗██████╗ ██╗███████╗");
    graphics.putString(18, 4, "╚══██╔══╝██╔════╝╚══██╔══╝██╔══██╗██║██╔════╝");
    graphics.putString(18, 5, "   ██║   █████╗     ██║   ██████╔╝██║███████╗");
    graphics.putString(18, 6, "   ██║   ██╔══╝     ██║   ██╔══██╗██║╚════██║");
    graphics.putString(18, 7, "   ██║   ███████╗   ██║   ██║  ██║██║███████║");
    graphics.putString(18, 8, "   ╚═╝   ╚══════╝   ╚═╝   ╚═╝  ╚═╝╚═╝╚══════╝");
    graphics.setForegroundColor(
        blink ? TextColor.ANSI.GREEN : TextColor.ANSI.WHITE
);
    graphics.putString(28,12,"ENTER - Jogar");
    graphics.setForegroundColor(TextColor.ANSI.RED);

graphics.putString(28,14,"ESC - Sair");

   graphics.setForegroundColor(TextColor.ANSI.BLUE);

graphics.putString(18,18,"Projeto de Tetris");
graphics.putString(24,19,"Java + Lanterna");
graphics.putString(26,20,"Ana Flávia");

    screen.refresh();
}
public void drawGameOver(int score) throws Exception {
    updateBlink();
    screen.clear();

    var graphics = screen.newTextGraphics();

    graphics.putString(24, 5, "GAME OVER");

    graphics.putString(23, 8, "Pontuação: " + score);

    graphics.putString(18, 12, "ENTER - Jogar novamente");

    graphics.putString(20, 14, "ESC - Voltar ao menu");

    screen.refresh();
}
public void draw(Board board,
                 Piece currentPiece,
                 Piece nextPiece,
                 int score) throws Exception {

    screen.clear();

    var graphics = screen.newTextGraphics();

    drawBorder();
    drawCells(board);
    drawPiece(currentPiece);

    graphics.putString(
        Config.BOARD_WIDTH * Config.CELL_SIZE + 5,
        2,
        "Score: " + score
    );

    graphics.putString(
        Config.BOARD_WIDTH * Config.CELL_SIZE + 5,
        4,
        "Linhas: " + (score / 100)
    );

    graphics.setForegroundColor(TextColor.ANSI.WHITE);

    graphics.putString(
        Config.BOARD_WIDTH * Config.CELL_SIZE + 5,
        7,
        "Próxima:"
    );

    drawNextPieceBox();
    drawNextPiece(nextPiece);

    screen.refresh();
}
private void drawNextPiece(Piece piece) {

    int[][] shape = piece.getShape();
    
int boxX = Config.BOARD_WIDTH * Config.CELL_SIZE + 5;
int boxY = 8;

int boxWidth = 8;   // largura interna da caixa
int boxHeight = 4;  // altura interna da caixa

int pieceWidth = shape[0].length * 2;
int pieceHeight = shape.length;

int startX = boxX + 1 + (boxWidth - pieceWidth) / 2;
int startY = boxY + 1 + (boxHeight - pieceHeight) / 2;

    TextCharacter block = new TextCharacter(
            '█',
            piece.getColor(),
            TextColor.ANSI.DEFAULT
    );

    for (int row = 0; row < shape.length; row++) {

        for (int column = 0; column < shape[row].length; column++) {

            if (shape[row][column] == 1) {

                int x = startX + column * 2;
                int y = startY + row;

                screen.setCharacter(x, y, block);
                screen.setCharacter(x + 1, y, block);

            }

        }

    }
}
private void drawNextPieceBox() {

    var graphics = screen.newTextGraphics();

    graphics.setForegroundColor(TextColor.ANSI.BLUE_BRIGHT);

    int x = Config.BOARD_WIDTH * Config.CELL_SIZE + 5;
    int y = 8;

    graphics.putString(x, y,     "╔════════╗");
    graphics.putString(x, y + 1, "║        ║");
    graphics.putString(x, y + 2, "║        ║");
    graphics.putString(x, y + 3, "║        ║");
    graphics.putString(x, y + 4, "║        ║");
    graphics.putString(x, y + 5, "╚════════╝");
}
private void drawPiece(Piece piece) {

    int[][] shape = piece.getShape();

    for (int row = 0; row < shape.length; row++) {

        for (int column = 0; column < shape[row].length; column++) {

            if (shape[row][column] == 1) {

                int x = (piece.getColumn() + column) * Config.CELL_SIZE + 1;
                int y = piece.getRow() + row + 1;

                TextCharacter block = new TextCharacter(
                    '█',
                    piece.getColor(),
                    TextColor.ANSI.DEFAULT
                );

                screen.setCharacter(x, y, block);
                screen.setCharacter(x + 1, y, block);

            }

        }

    }

}
private TextColor getColor(int id) {

    switch (id) {

        case 1:
            return TextColor.ANSI.CYAN;

        case 2:
            return TextColor.ANSI.YELLOW;

        case 3:
            return TextColor.ANSI.MAGENTA;

        case 4:
            return TextColor.ANSI.GREEN;

        case 5:
            return TextColor.ANSI.RED;

        case 6:
            return TextColor.ANSI.BLUE;

        case 7:
            return TextColor.ANSI.WHITE;

        default:
            return TextColor.ANSI.DEFAULT;
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

            int value = board.getCell(row, column);

            TextCharacter character = getBlock(value);

            screen.setCharacter(column * Config.CELL_SIZE + 1, row + 1, character);
            screen.setCharacter(column * Config.CELL_SIZE + 2, row + 1, character);

        }

    }
}
    private TextCharacter getBlock(int id) {

    if (id == 0) {
        return EMPTY;
    }

    return new TextCharacter(
        '█',
        Tetromino.values()[id - 1].getColor(),
        TextColor.ANSI.DEFAULT
    );
}

    public Screen getScreen() {
        return screen;
    }
}