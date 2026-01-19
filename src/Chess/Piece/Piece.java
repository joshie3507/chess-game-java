package Chess.Piece;

import Chess.Game.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

// parent class for all pieces.  Initialised variables used in all child classes
public abstract class Piece {
    GamePanel gp;

    public BufferedImage image;
    public boolean isWhite;

    public int tileX, tileY;
    Position position;

    public Move[] availableMoves;

    public HashMap<Position, Piece> teamPieces;
    public HashMap<Position, Piece> opponents;

    public boolean selected = false;

    /**
     * method checks if the last clicked tile is the same as the tile the piece is current on
     *
     * @return -> boolean
     */
    boolean checkClickedOn() {
        return (gp.tileClicked[0] == tileX) && (gp.tileClicked[1] == tileY);
    }

    public abstract void update();

    public abstract void initPiece();

    public abstract void draw(Graphics2D g2);

    abstract Move[] getAvailableMoves();

    public abstract void checkOpponentPieceTaken(Position clicked);

    public Position getPosition() {
        return position;
    }
}
