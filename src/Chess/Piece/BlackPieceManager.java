package Chess.Piece;

import Chess.Game.GamePanel;

import java.awt.*;

public class BlackPieceManager {

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


    public BlackPieceManager(GamePanel gp){
        this.gp = gp;
        initialisePieces();
    }

    private void initialisePieces(){
        initialisePawns();
        initialiseRooks();
        initialiseKnights();
        initialiseBishops();
        initialiseRoyals();
    }

    private void initialiseRoyals() {
        king = new King(gp, false, 4, 8);
        queen = new Queen(gp, false, 3, 8);
    }

    private void initialiseBishops() {
        bishopArray[0] = new Bishop(gp, false, 2, 8);
        bishopArray[1] = new Bishop(gp, false, 5, 8);
    }

    private void initialiseKnights() {
        knightArray[0] = new Knight(gp, false, 1, 8);
        knightArray[1] = new Knight(gp, false, 6, 8);
    }

    private void initialiseRooks() {
        rookArray[0] = new Rook(gp, false, 0, 8);
        rookArray[1] = new Rook(gp, false, 7, 8);
    }

    private void initialisePawns(){
        for (int i = 0; i < numPawns; i++){
            pawnArray[i] = new Pawn(gp, false, i, 7);
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
}
