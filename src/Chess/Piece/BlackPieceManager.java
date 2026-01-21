package Chess.Piece;

import Chess.Game.GamePanel;

import javax.management.AttributeList;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class BlackPieceManager {

    public ArrayList<Position> piecesToRemove = new ArrayList<>();
    public HashMap<Position, Piece>  piecesToAdd = new HashMap<>();
    public HashSet<Position> availableMoves = new HashSet<>();
    GamePanel gp;

    public HashMap<Position, Piece> pieces = new HashMap<>();

    public BlackPieceManager(GamePanel gp){
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
        gp.blackKing = new King(gp, false, 4, 7);
        pieces.put(new Position(4, 7), gp.blackKing);

        pieces.put(new Position(3, 7), new Queen(gp, false, 3, 7));
    }

    /**
     * method initialises bishops
     */
    private void initialiseBishops() {
        pieces.put( new Position(2, 7), new Bishop(gp, false, 2, 7));

        pieces.put(new Position(5, 7), new Bishop(gp, false, 5, 7));
    }

    /**
     * method initialises knights
     */
    private void initialiseKnights() {
        pieces.put(new Position(1,7), new Knight(gp, false, 1, 7));

        pieces.put(new Position(6, 7), new Knight(gp, false, 6, 7));
    }

    /**
     * method initialises rooks
     */
    private void initialiseRooks() {
        pieces.put(new Position(0, 7), new Rook(gp, false, 0, 7));

        pieces.put(new Position(7, 7), new Rook(gp, false, 7, 7));
    }

    /**
     * method initialises pawns
     */
    private void initialisePawns(){
        for (int i = 0; i < 8; i++){
            pieces.put(new Position(i, 6), new Pawn(gp, false, i, 6));
        }
    }

    /**
     * method draws all black pieces on the board
     *
     * @param g2 -> Graphics2D class draws on JFrame
     */
    public void drawBlackPieces(Graphics2D g2){
        for (Piece piece : pieces.values()){
            if (piece !=null) {
                piece.draw(g2);
            }
        }

    }

    /**
     * method updates available moves of all pieces
     */
    public void setAvailableMoves(){
        for (Piece piece : pieces.values()){
            piece.availableMoves = piece.getAvailableMoves();
        }
    }
}
