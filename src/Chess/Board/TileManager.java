package Chess.Board;

import Chess.Game.GamePanel;

import java.awt.*;

public class TileManager {

    GamePanel gp;
    public Tile[][] tiles;

    public TileManager(GamePanel gp){
        this.gp = gp;
        tiles = new Tile[gp.numTiles][gp.numTiles];
        generateTiles();
    }

    private void generateTiles(){
        int tileNum = 1;

        for (int row = 0; row < gp.numTiles; row++){
            for (int column = 0; column < gp.numTiles; column++){
                String colour = (row + column) % 2 == 0 ? "#e4d9c8" : "#5e5247";
                tiles[row][column] = new Tile(tileNum, colour);

                tileNum++;

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
}
