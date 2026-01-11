package Chess.Board;

import Chess.Game.GamePanel;

import java.awt.*;
import java.util.Arrays;

public class TileManager {

    GamePanel gp;
    public Tile[][] tiles;

    public TileManager(GamePanel gp){
        this.gp = gp;
        tiles = new Tile[gp.numTiles][gp.numTiles];
        generateTiles();
    }

    private void generateTiles(){
        for (int row = 0; row < gp.numTiles; row++){
            for (int column = 0; column < gp.numTiles; column++){

                String colour = (row + column) % 2 == 0 ? "#e4d9c8" : "#5e5247";
                tiles[row][column] = new Tile(row, column, colour);
            }
        }
    }

    public void drawTiles(Graphics2D g2){
        for (int row = 0; row < gp.numTiles; row++){
            for (int col = 0; col < gp.numTiles; col++){
                Tile tile = tiles[row][col];
                g2.setColor(Color.decode(tile.colour));
                g2.fillRect(col * gp.tileSize, row * gp.tileSize, gp.tileSize, gp.tileSize);
            }
        }
    }

    public void highlightTiles(Graphics2D g2){
        if (gp.currentlySelected != null ) {
            for (int[] tile : gp.currentlySelected.availableMoves) {
                //System.out.print(Arrays.toString(tile) + " ");
                g2.setColor(Color.decode("#81befd"));
                g2.fillRect(tile[0]*gp.tileSize, (8 - tile[1])  * gp.tileSize, gp.tileSize, gp.tileSize);
                g2.setColor(Color.BLACK);
                g2.drawRect(tile[0]*gp.tileSize, (8 - tile[1]) * gp.tileSize, gp.tileSize, gp.tileSize);
            }
        }
    }
}
