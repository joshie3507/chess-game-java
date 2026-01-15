package Chess.Piece;

import Chess.Game.GamePanel;

import java.awt.*;
import java.util.ArrayList;

public class BlackPieceManager {

    GamePanel gp;
    // initialising number of each piece
    public int numPawns = 8,
            numRooks = 2,
            numKnights = 2,
            numBishops = 2;

    // counter for how many pieces have been initialised.  Used as an index in pieces and blackPieceLocations
    private int pieceNumber = 0;



    public Piece[] pieces = new Piece[numPawns + numRooks + numBishops + numKnights + 2]; // array of all pieces

    public BlackPieceManager(GamePanel gp){
        this.gp = gp;
        initialisePieces();
    }

    /**
     * method updates all pieces and is called in GamePanel update method
     * used to encapsulate separate parts of the code
     */
    public void updatePieces(){
        for (Piece piece : pieces){
            if (piece != null) {
                piece.update();
            }
        }
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
        pieces[pieceNumber] = new King(gp, false, 4, 7, pieceNumber);
        pieceNumber++;

        pieces[pieceNumber] = new Queen(gp, false, 3, 7, pieceNumber);
        pieceNumber++;
    }

    /**
     * method initialises bishops
     */
    private void initialiseBishops() {
        pieces[pieceNumber] = new Bishop(gp, false, 2, 7, pieceNumber);
        pieceNumber++;

        pieces[pieceNumber] = new Bishop(gp, false, 5, 7, pieceNumber);
        pieceNumber++;

    }

    /**
     * method initialises knights
     */
    private void initialiseKnights() {
        pieces[pieceNumber] = new Knight(gp, false, 1, 7, pieceNumber);
        pieceNumber++;

        pieces[pieceNumber] = new Knight(gp, false, 6, 7, pieceNumber);
        pieceNumber++;

    }

    /**
     * method initialises rooks
     */
    private void initialiseRooks() {
        pieces[pieceNumber] = new Rook(gp, false, 0, 7, pieceNumber);
        pieceNumber++;

        pieces[pieceNumber] = new Rook(gp, false, 7, 7, pieceNumber);
        pieceNumber++;

    }

    /**
     * method initialises pawns
     */
    private void initialisePawns(){
        for (int i = 0; i < numPawns; i++){
            pieces[pieceNumber] = new Pawn(gp, false, i, 6, pieceNumber);
            pieceNumber++;

        }
    }

    /**
     * method draws all black pieces on the board
     *
     * @param g2 -> Graphics2D class draws on JFrame
     */
    public void drawBlackPieces(Graphics2D g2){
        for ( Piece piece : pieces){
            if (piece !=null) {
                piece.draw(g2);
            }
        }

    }

    /**
     * method updates available moves of all pieces
     */
    public void setAvailableMoves(){
        for (Piece piece : pieces){
            piece.availableMoves = piece.getAvailableMoves();
        }
    }
}
