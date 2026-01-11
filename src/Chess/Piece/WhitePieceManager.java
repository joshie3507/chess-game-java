package Chess.Piece;

import Chess.Game.GamePanel;

import java.awt.*;

public class WhitePieceManager {

    GamePanel gp;
    public int numPawns = 10,
            numRooks = 2,
            numKnights = 2,
            numBishops = 2;

    private final Pawn[] pawnArray = new Pawn[numPawns];
    private final Rook[] rookArray = new Rook[numRooks];
    private final Knight[] knightArray = new Knight[numKnights];
    private final Bishop[] bishopArray = new Bishop[numBishops];
    private King king;
    private Queen queen;


    public WhitePieceManager(GamePanel gp){
        this.gp = gp;
        initialisePieces();
    }

    public void updatePieces(){
        for (Pawn pawn : pawnArray){
            pawn.update();
        }
    }

    private void initialisePieces(){
        initialisePawns();
        initialiseRooks();
        initialiseKnights();
        initialiseBishops();
        initialiseRoyals();
    }

    private void initialiseRoyals() {
        king = new King(gp, true, 4, 1);
        queen = new Queen(gp, true, 3, 1);
    }

    private void initialiseBishops() {
        bishopArray[0] = new Bishop(gp, true, 2, 1);
        bishopArray[1] = new Bishop(gp, true, 5, 1);
    }

    private void initialiseKnights() {
        knightArray[0] = new Knight(gp, true, 1, 1);
        knightArray[1] = new Knight(gp, true, 6, 1);
    }

    private void initialiseRooks() {
        rookArray[0] = new Rook(gp, true, 0, 1);
        rookArray[1] = new Rook(gp, true, 7, 1);
    }

    private void initialisePawns(){
        for (int i = 0; i < numPawns; i++){
            pawnArray[i] = new Pawn(gp, true, i, 2);
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
}
