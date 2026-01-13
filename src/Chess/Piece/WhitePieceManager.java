package Chess.Piece;

import Chess.Game.GamePanel;

import java.awt.*;
import java.util.ArrayList;

public class WhitePieceManager {

    GamePanel gp;
    // initialising number of each piece
    public int numPawns = 8,
            numRooks = 2,
            numKnights = 2,
            numBishops = 2;

    // counter for how manu pieces have been initialised.  Used as an index in pieces and whitePieceLocations
    private int pieceNumber = 0;

    public Piece[] pieces = new Piece[numPawns + numRooks + numKnights + numBishops + 2]; // array of all Pieces


    public WhitePieceManager(GamePanel gp){
        this.gp = gp;
        initialisePieces();
    }

    /**
     * method updates all pieces and is called in GamePanel update method
     * used to encapsulate separate parts of the code
     */
    public void updatePieces(){
        for (Piece piece : pieces){
            piece.update();
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
        pieces[pieceNumber] = new King(gp, true, 4, 0, pieceNumber);
        pieceNumber++;

        pieces[pieceNumber] = new Queen(gp, true, 3, 0, pieceNumber);
        pieceNumber++;
    }

    /**
     * method initialises bishops
     */
    private void initialiseBishops() {
        pieces[pieceNumber] = new Bishop(gp, true, 2, 0, pieceNumber);
        pieceNumber++;

        pieces[pieceNumber] = new Bishop(gp, true, 5, 0, pieceNumber);
        pieceNumber++;

    }

    /**
     * method initialises knights
     */
    private void initialiseKnights() {
        pieces[pieceNumber] = new Knight(gp, true, 1, 0, pieceNumber);
        pieceNumber++;

        pieces[pieceNumber] = new Knight(gp, true, 6, 0, pieceNumber);
        pieceNumber++;

    }

    /**
     * method initialises rooks
     */
    private void initialiseRooks() {
        pieces[pieceNumber] = new Rook(gp, true, 0, 0, pieceNumber);
        pieceNumber++;

        pieces[pieceNumber] = new Rook(gp, true, 7, 0, pieceNumber);
        pieceNumber++;

    }

    /**
     * method initialises pawns
     */
    private void initialisePawns(){
        for (int i = 0; i < numPawns; i++){
            pieces[pieceNumber] = new Pawn(gp, true, i, 1, pieceNumber);
            pieceNumber++;

        }
    }

    /**
     * method draws all white pieces on the board
     *
     * @param g2 -> Graphics2D class used to draw on JFrame
     */
    public void drawWhitePieces(Graphics2D g2){
        for (Piece piece : pieces){
            piece.draw(g2);
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
