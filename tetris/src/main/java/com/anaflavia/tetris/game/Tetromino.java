package com.anaflavia.tetris.game;

import com.googlecode.lanterna.TextColor;


public enum Tetromino {
    

    I(
    new int[][][]{
        {
            {1, 1, 1, 1}
        },
        {
            {1},
            {1},
            {1},
            {1}
        },
        {
            {1, 1, 1, 1}
        },
        {
            {1},
            {1},
            {1},
            {1}
        }
    },
    TextColor.ANSI.CYAN
    
),

   O(
    new int[][][]{
        {
            {1, 1},
            {1, 1}
        },
        {
            {1, 1},
            {1, 1}
        },
        {
            {1, 1},
            {1, 1}
        },
        {
            {1, 1},
            {1, 1}
        }
    },
    TextColor.ANSI.YELLOW
),
    T(new int[][][]{
        {
            {0, 1, 0},
            {1, 1, 1}
        },
        {
            {1, 0},
            {1, 1},
            {1, 0}
        },
        {
            {1, 1, 1},
            {0, 1, 0}
        },
        {
            {0, 1},
            {1, 1},
            {0, 1}
        }
    },
    TextColor.ANSI.MAGENTA 
),

    S(new int[][][]{
        {
            {0, 1, 1},
            {1, 1, 0}
        },
        {
            {1, 0},
            {1, 1},
            {0, 1}
        },
        {
            {0, 1, 1},
            {1, 1, 0}
        },
        {
            {1, 0},
            {1, 1},
            {0, 1}
        }
    },
    TextColor.ANSI.MAGENTA
),

    Z(new int[][][]{
        {
            {1, 1, 0},
            {0, 1, 1}
        },
        {
            {0, 1},
            {1, 1},
            {1, 0}
        },
        {
            {1, 1, 0},
            {0, 1, 1}
        },
        {
            {0, 1},
            {1, 1},
            {1, 0}
        }
    },
    TextColor.ANSI.GREEN
),

    J(new int[][][]{
        {
            {1, 0, 0},
            {1, 1, 1}
        },
        {
            {1, 1},
            {1, 0},
            {1, 0}
        },
        {
            {1, 1, 1},
            {0, 0, 1}
        },
        {
            {0, 1},
            {0, 1},
            {1, 1}
        }
    },
    TextColor.ANSI.BLUE
),

    L(new int[][][]{
        {
            {0, 0, 1},
            {1, 1, 1}
        },
        {
            {1, 0},
            {1, 0},
            {1, 1}
        },
        {
            {1, 1, 1},
            {1, 0, 0}
        },
        {
            {1, 1},
            {0, 1},
            {0, 1}
        }
    },
    TextColor.ANSI.RED
);

   
    private final int[][][] shapes;
    private final TextColor color;

    Tetromino(int[][][] shapes, TextColor color) {
        this.shapes = shapes;
        this.color = color;     
    }
    public TextColor getColor() {
    return color;
}

    public int[][] getShape(int rotation) {
        return shapes[rotation];
    }
}