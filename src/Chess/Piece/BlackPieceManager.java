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

    private final Pawn[] pawnArray = new Pawn[numPawns]; // array of all pawns
    private final Rook[] rookArray = new Rook[numRooks]; // array of all rooks
    private final Knight[] knightArray = new Knight[numKnights]; // array of all knights
    private final Bishop[] bishopArray = new Bishop[numBishops]; // array of all bishops
    private King king; // king and queen not in an array as there is only one of each
    private Queen queen;

    public ArrayList<int[]> blackPieceLocations = new ArrayList<>(); // arraylist used to store all locations of black pieces


    public BlackPieceManager(GamePanel gp){
        this.gp = gp;
        initialisePieces();
    }

    /**
     * method updates all pieces and is called in GamePanel update method
     * used to encapsulate separate parts of the code
     */
    public void updatePieces(){
        for (Pawn pawn : pawnArray){
            pawn.update();
        }

        for (Rook rook : rookArray){
            rook.update();
        }

        for (Knight knight : knightArray){
            knight.update();
        }

        for (Bishop bishop : bishopArray){
            bishop.update();
        }

        king.update();
        queen.update();
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
        blackPieceLocations.add(new int[]{4, 7});
        king = new King(gp, false, 4, 7, pieceNumber);
        pieceNumber++;

        blackPieceLocations.add(new int[]{3, 7});
        queen = new Queen(gp, false, 3, 7, pieceNumber);
        pieceNumber++;
    }

    /**
     * method initialises bishops
     */
    private void initialiseBishops() {
        blackPieceLocations.add(new int[] {2, 7});
        bishopArray[0] = new Bishop(gp, false, 2, 7, pieceNumber);
        pieceNumber++;

        blackPieceLocations.add(new int[] {5, 7});
        bishopArray[1] = new Bishop(gp, false, 5, 7, pieceNumber);
        pieceNumber++;

    }

    /**
     * method initialises knights
     */
    private void initialiseKnights() {
        blackPieceLocations.add(new int[] {1, 7});
        knightArray[0] = new Knight(gp, false, 1, 7, pieceNumber);
        pieceNumber++;

        blackPieceLocations.add(new int[] {6, 7});
        knightArray[1] = new Knight(gp, false, 6, 7, pieceNumber);
        pieceNumber++;

    }

    /**
     * method initialises rooks
     */
    private void initialiseRooks() {
        blackPieceLocations.add(new int[] {0, 7});
        rookArray[0] = new Rook(gp, false, 0, 7, pieceNumber);
        pieceNumber++;

        blackPieceLocations.add(new int[] {7, 7});
        rookArray[1] = new Rook(gp, false, 7, 7, pieceNumber);
        pieceNumber++;

    }

    /**
     * method initialises pawns
     */
    private void initialisePawns(){
        for (int i = 0; i < numPawns; i++){
            blackPieceLocations.add(new int[] {i, 6});
            pawnArray[i] = new Pawn(gp, false, i, 6, pieceNumber);
            pieceNumber++;

        }
    }

    /**
     * method draws all black pieces on the board
     *
     * @param g2 -> Graphics2D class draws on JFrame
     */
    public void drawBlackPieces(Graphics2D g2){
        for (Pawn pawn : pawnArray){
            pawn.draw(g2);
        }
        for (Rook rook : rookArray){
            rook.draw(g2);
        }
        for (Knight knight : knightArray){
            knight.draw(g2);
        }
        for (Bishop bishop : bishopArray){
            bishop.draw(g2);
        }
        king.draw(g2);
        queen.draw(g2);

    }

    /**
     * method updates available moves of all pieces
     */
    public void setAvailableMoves(){
        for (Pawn pawn : pawnArray){
            pawn.availableMoves = pawn.getAvailableMoves();
        }

        for (Rook rook : rookArray){
            rook.availableMoves = rook.getAvailableMoves();
        }

        for (Knight knight : knightArray){
            knight.availableMoves = knight.getAvailableMoves();
        }

        for (Bishop bishop : bishopArray){
            bishop.availableMoves = bishop.getAvailableMoves();
        }

        queen.availableMoves = queen.getAvailableMoves();

        king.availableMoves = king.getAvailableMoves();
    }
}
