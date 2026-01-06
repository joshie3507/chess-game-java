package Chess.Piece;

import java.awt.image.BufferedImage;

public class Piece {
    public BufferedImage image;
    public boolean isWhite;

    public int tileX, tileY;

    public int upMoves, sideMoves;
    public boolean clickedOn = false;
    public boolean isAlive = true;
}
