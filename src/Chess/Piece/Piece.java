package Chess.Piece;

import Chess.Game.GamePanel;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

// parent class for all pieces.  Initialised variables used in all child classes
public class Piece {
    GamePanel gp;

    public BufferedImage image;
    public boolean isWhite;

    public int tileX, tileY;

    public int[][] availableMoves;
    public ArrayList<int[]> teamPieceLocations;
    public ArrayList<int[]> opponentLocations;
    public Piece[] opponentPieces;

    int pieceNumber;
    public boolean selected = false;
    public boolean isAlive = true;

    /**
     * method checks if the last clicked tile is the same as the tile the piece is current on
     *
     * @return -> boolean
     */
    boolean checkClickedOn() {
        return (gp.tileClicked[0] == tileX) && (gp.tileClicked[1] == tileY);
    }


}
