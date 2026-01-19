package Chess.Piece;

import Chess.Game.GamePanel;

import javax.management.openmbean.TabularData;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WhitePieceManager {


    GamePanel gp;

    public ArrayList<Position> piecesToRemove = new ArrayList<>();
    public HashMap<Position, Piece>  piecesToAdd = new HashMap<>();

    public HashMap<Position, Piece> pieces = new HashMap<>();

    public WhitePieceManager(GamePanel gp){
        this.gp = gp;
        initialisePieces();
    }

    /**
     * method sets hashMaps in each piece that represents the black and white pieces
     */
    public void initPieceTeams(){
        for (Piece piece : pieces.values()){
            piece.initPiece();
        }
    }

    /**
     * method updates all pieces and is called in GamePanel update method
     * used to encapsulate separate parts of the code
     */
    public void updatePieces(){

        for (Piece piece : pieces.values()){
            if (piece != null) {
                piece.update();
            }
        }

        for (Piece piece : piecesToAdd.values()){
            pieces.put(piece.getPosition(), piece);
        }

        for (Position position : piecesToRemove){
            pieces.remove(position);
        }

        piecesToRemove.clear();
        piecesToAdd.clear();
    }

    /**
     * method initialises every piece.
     * calls to separate methods to keep each piece separate and easier to find errors
     */
    private void initialisePieces(){
        initialisePawns();
        initialiseRooks();
        initialiseKnights();
        initialiseBishops();
        initialiseRoyals();
    }

    /**
     * method initialises king and queen
     */
    private void initialiseRoyals() {
        gp.whiteKing = new King(gp, true, 4, 0);
        pieces.put(new Position(4, 0), gp.whiteKing);

        pieces.put(new Position(3, 0), new Queen(gp, true, 3, 0));
    }

    /**
     * method initialises bishops
     */
    private void initialiseBishops() {
        pieces.put(new Position(2, 0), new Bishop(gp, true, 2, 0));

        pieces.put(new Position(5, 0), new Bishop(gp, true, 5, 0));
    }

    /**
     * method initialises knights
     */
    private void initialiseKnights() {
        pieces.put(new Position(1, 0), new Knight(gp, true, 1, 0));

        pieces.put(new Position(6, 0), new Knight(gp, true, 6, 0));
    }

    /**
     * method initialises rooks
     */
    private void initialiseRooks() {
        pieces.put(new Position(0, 0), new Rook(gp, true, 0, 0));

        pieces.put(new Position(7, 0), new Rook(gp, true, 7, 0));
    }

    /**
     * method initialises pawns
     */
    private void initialisePawns(){
        for (int i = 0; i < 8; i++){
            pieces.put(new Position(i, 1), new Pawn(gp, true, i, 1));
        }
    }

    /**
     * method draws all white pieces on the board
     *
     * @param g2 -> Graphics2D class used to draw on JFrame
     */
    public void drawWhitePieces(Graphics2D g2){
        for (Piece piece : pieces.values()){
            if (piece != null) {
                piece.draw(g2);
            }
        }


    }

    /**
     * method updates available moves of all pieces
     */
    public void setAvailableMoves() {
        for (Piece piece : pieces.values()) {
            if (piece != null) {
                piece.availableMoves = piece.getAvailableMoves();
            }
        }
    }

}
