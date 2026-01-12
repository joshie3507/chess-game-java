package Chess.Piece;

import Chess.Game.GamePanel;

import java.awt.image.BufferedImage;

public class Piece {
    GamePanel gp;

    public BufferedImage image;
    public boolean isWhite;

    public int tileX, tileY;

    public int upMoves, sideMoves;
    public int[][] availableMoves;
    public boolean selected = false;
    public boolean isAlive = true;

    boolean checkClickedOn() {
        return (gp.tileClicked[0] == tileX) && (gp.tileClicked[1] == tileY);
    }


}
