package Chess.Board;

import Chess.Game.GamePanel;

public class Tile { // class for each tile to be displayed on the board
    public final int row, column; // tile Y and X co-ordinates
    public String colour; // colour of the tile

    public Tile(int row, int column, String colour){
        this.row = row;
        this.column = column;
        this.colour = colour;
    }

}
