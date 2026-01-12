package Chess.Piece;

import Chess.Game.GamePanel;

import java.awt.*;
import java.util.ArrayList;

public class BlackPieceManager {

    GamePanel gp;
    public int numPawns = 8,
            numRooks = 2,
            numKnights = 2,
            numBishops = 2;

    private int pieceNumber = 0;

    private final Pawn[] pawnArray = new Pawn[numPawns];
    private final Rook[] rookArray = new Rook[numRooks];
    private final Knight[] knightArray = new Knight[numKnights];
    private final Bishop[] bishopArray = new Bishop[numBishops];
    private King king;
    private Queen queen;

    public ArrayList<int[]> blackPieceLocations = new ArrayList<>();


    public BlackPieceManager(GamePanel gp){
        this.gp = gp;
        initialisePieces();
    }

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

    private void initialisePieces(){
        initialisePawns();
        initialiseRooks();
        initialiseKnights();
        initialiseBishops();
        initialiseRoyals();
    }

    private void initialiseRoyals() {
        blackPieceLocations.add(new int[]{4, 7});
        king = new King(gp, false, 4, 7, pieceNumber);
        pieceNumber++;

        blackPieceLocations.add(new int[]{3, 7});
        queen = new Queen(gp, false, 3, 7, pieceNumber);
        pieceNumber++;
    }

    private void initialiseBishops() {
        blackPieceLocations.add(new int[] {2, 7});
        bishopArray[0] = new Bishop(gp, false, 2, 7, pieceNumber);
        pieceNumber++;

        blackPieceLocations.add(new int[] {5, 7});
        bishopArray[1] = new Bishop(gp, false, 5, 7, pieceNumber);
        pieceNumber++;

    }

    private void initialiseKnights() {
        blackPieceLocations.add(new int[] {1, 7});
        knightArray[0] = new Knight(gp, false, 1, 7, pieceNumber);
        pieceNumber++;

        blackPieceLocations.add(new int[] {6, 7});
        knightArray[1] = new Knight(gp, false, 6, 7, pieceNumber);
        pieceNumber++;

    }

    private void initialiseRooks() {
        blackPieceLocations.add(new int[] {0, 7});
        rookArray[0] = new Rook(gp, false, 0, 7, pieceNumber);
        pieceNumber++;

        blackPieceLocations.add(new int[] {7, 7});
        rookArray[1] = new Rook(gp, false, 7, 7, pieceNumber);
        pieceNumber++;

    }

    private void initialisePawns(){
        for (int i = 0; i < numPawns; i++){
            blackPieceLocations.add(new int[] {i, 6});
            pawnArray[i] = new Pawn(gp, false, i, 6, pieceNumber);
            pieceNumber++;

        }
    }

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
