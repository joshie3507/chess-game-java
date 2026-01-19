package Chess.Board;

import Chess.Game.GamePanel;
import Chess.Piece.Move;

import java.awt.*;

public class TileManager {

    GamePanel gp;
    public Tile[][] tiles; // 2D array of tiles, representing the board to be played on

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tiles = new Tile[gp.numTiles][gp.numTiles];
        generateTiles();
    }

    /**
     * method initializes a 2D array of custom Tile class
     */
    private void generateTiles() {
        for (int row = 0; row < gp.numTiles; row++) {
            for (int column = 0; column < gp.numTiles; column++) {

                String colour = (row + column) % 2 == 0 ? "#e4d9c8" : "#5e5247"; // alternates between cream and dark brown.  Was originally black and white,
                // but these colours are easier to see
                tiles[row][column] = new Tile(row, column, colour);
            }
        }
    }

    /**
     * method draws all tiles on a JFrame
     *
     * @param g2 -> Graphics2D class used to draw each tile
     */
    public void drawTiles(Graphics2D g2) {
        for (int row = 0; row < gp.numTiles; row++) {
            for (int col = 0; col < gp.numTiles; col++) {
                Tile tile = tiles[row][col];
                g2.setColor(Color.decode(tile.colour));
                g2.fillRect(col * gp.tileSize, row * gp.tileSize, gp.tileSize, gp.tileSize);
            }
        }
    }

    /**
     * method highlights all tiles from the currently selected piece's available moves
     *
     * @param g2 -> Graphics2D class used to draw the highlight over original tile
     */
    public void highlightTiles(Graphics2D g2) {
        if (gp.currentlySelected != null) {
            {
                if (gp.currentlySelected != null) {
                    for (Move move : gp.currentlySelected.availableMoves) {
                        if (move.getTakeablePiece()) {
                            g2.setColor(Color.decode("#FF746C"));
                            g2.fillRect(move.getPosition().x * gp.tileSize, (7 - move.getPosition().y) * gp.tileSize, gp.tileSize, gp.tileSize);
                            g2.setColor(Color.BLACK);
                            g2.drawRect(move.getPosition().x * gp.tileSize, (7 - move.getPosition().y) * gp.tileSize, gp.tileSize, gp.tileSize);
                        } else {
                            g2.setColor(Color.decode("#81befd"));
                            g2.fillRect(move.getPosition().x * gp.tileSize, (7 - move.getPosition().y) * gp.tileSize, gp.tileSize, gp.tileSize);
                            g2.setColor(Color.BLACK);
                            g2.drawRect(move.getPosition().x * gp.tileSize, (7 - move.getPosition().y) * gp.tileSize, gp.tileSize, gp.tileSize);
                        }

                    }
                }
            }
        }

    }

}

