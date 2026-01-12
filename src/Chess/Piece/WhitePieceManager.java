package Chess.Piece;

import Chess.Game.GamePanel;

import java.awt.*;
import java.util.ArrayList;

public class WhitePieceManager {

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

    public ArrayList<int[]> whitePieceLocations = new ArrayList<>();


    public WhitePieceManager(GamePanel gp){
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
        whitePieceLocations.add(new int[]{4, 0});
        king = new King(gp, true, 4, 0, pieceNumber);
        pieceNumber++;

        whitePieceLocations.add(new int[]{3, 0});
        queen = new Queen(gp, true, 3, 0, pieceNumber);
        pieceNumber++;
    }

    private void initialiseBishops() {
        whitePieceLocations.add(new int[] {2, 0});
        bishopArray[0] = new Bishop(gp, true, 2, 0, pieceNumber);
        pieceNumber++;

        whitePieceLocations.add(new int[] {5, 0});
        bishopArray[1] = new Bishop(gp, true, 5, 0, pieceNumber);
        pieceNumber++;

    }

    private void initialiseKnights() {
        whitePieceLocations.add(new int[] {1, 0});
        knightArray[0] = new Knight(gp, true, 1, 0, pieceNumber);
        pieceNumber++;

        whitePieceLocations.add(new int[] {6, 0});
        knightArray[1] = new Knight(gp, true, 6, 0, pieceNumber);
        pieceNumber++;

    }

    private void initialiseRooks() {
        whitePieceLocations.add(new int[] {0, 0});
        rookArray[0] = new Rook(gp, true, 0, 0, pieceNumber);
        pieceNumber++;

        whitePieceLocations.add(new int[] {7, 0});
        rookArray[1] = new Rook(gp, true, 7, 0, pieceNumber);
        pieceNumber++;

    }

    private void initialisePawns(){
        for (int i = 0; i < numPawns; i++){
            whitePieceLocations.add(new int[] {i, 1});
            pawnArray[i] = new Pawn(gp, true, i, 1, pieceNumber);
            pieceNumber++;

        }
    }

    public void drawWhitePieces(Graphics2D g2){
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
