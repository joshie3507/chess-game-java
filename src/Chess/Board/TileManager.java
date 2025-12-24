package Chess.Board;

import Chess.Game.GamePanel;

import java.awt.*;

public class TileManager {

    GamePanel gp;
    public Tile[][] tiles;
    public final int numTiles = 10;
    public final int tileSize = GamePanel.screenSize / numTiles;


    public TileManager(GamePanel gp){
        this.gp = gp;
        tiles = new Tile[numTiles][numTiles];
        generateTiles();
    }

    private void generateTiles(){
        int tileNum = 1;

        for (int row = 0; row < numTiles; row++){
            for (int column = 0; column < numTiles; column++){
                String colour = (row + column) % 2 == 0 ? "#FFFFFF" : "#000000";
                tiles[row][column] = new Tile(tileNum, colour);

                tileNum++;

            }
        }
    }

    public void drawTiles(Graphics2D g2){


        for (int row = 0; row < numTiles; row++){
            for (int col = 0; col < numTiles; col++){
                Tile tile = tiles[row][col];
                g2.setColor(Color.decode(tile.colour));
                g2.fillRect(col * tileSize, row * tileSize, tileSize, tileSize);
                System.out.println(tile.colour + "" + tile.number);
            }
        }
    }
}
