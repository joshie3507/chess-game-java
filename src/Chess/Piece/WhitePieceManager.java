package Chess.Piece;

import Chess.Game.GamePanel;

import java.awt.*;

public class WhitePieceManager {

    GamePanel gp;
    public int numPawns = 10,
            numRooks = 2,
            numKnights = 2,
            numBishops = 2;

    private Pawn[] pawnArray = new Pawn[numPawns];



    public WhitePieceManager(GamePanel gp){
        this.gp = gp;
        initialisePieces();
    }

    private void initialisePieces(){
        initialisePawns();
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
    }
}
