package Chess.Board;

import Chess.Game.GamePanel;

public class Tile {
    public final int row, column;
    public String colour;

    public Tile(int row, int column, String colour){
        this.row = row;
        this.column = column;
        this.colour = colour;
    }

}
