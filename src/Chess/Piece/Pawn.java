package Chess.Piece;

import Chess.Game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Pawn extends Piece{

    boolean isFirstMove = true;

    int[][] getAvailableMoves() {
        ArrayList<int[]> moves = new ArrayList<>();
        int numMoves = isFirstMove ? 2 : 1;

        int[] currentMove;

        teamPieceLocations = isWhite ? gp.whiteMoveList : gp.blackMoveList;

        if (isWhite) {
            int currentY = tileY + 1, limit = tileY + numMoves;

            outer:
            while (currentY <= limit) {
                currentMove = new int[]{tileX, currentY};
                for (int[] location : teamPieceLocations) {
                    if (Arrays.equals(location, currentMove)) {
                        break outer;
                    }
                }

                moves.add(currentMove);
                currentY++;
            }
        } else {
            int currentY = tileY - 1, limit = tileY - numMoves;

            outer:
            while (currentY >= limit) {
                currentMove = new int[]{tileX, currentY};
                for (int[] location : teamPieceLocations) {
                    if (Arrays.equals(location, currentMove)) {
                        break outer;
                    }
                }

                moves.add(currentMove);
                currentY--;
            }
        }

        int[][] moveArray = new int[moves.size()][2];

        for (int i = 0; i < moves.size(); i++){
            moveArray[i][0] = moves.get(i)[0];
            moveArray[i][1] = moves.get(i)[1];
        }

        return moveArray;

    }

    public Pawn(GamePanel gp, boolean isWhite, int tileX, int tileY, int pieceNumber){
        this.gp = gp;
        this.isWhite = isWhite;
        this.tileX = tileX;
        this.tileY = tileY;
        this.pieceNumber = pieceNumber;

        getImage();
    }

    private void getImage()  {
        try {
            if (isWhite) {
                image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/White/White-Pawn.png"));
            } else {
                image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/Black/Black-Pawn.png"));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void move(){
        tileX = gp.tileClicked[0];
        tileY = gp.tileClicked[1];
        isFirstMove = false;

        availableMoves = getAvailableMoves();
        gp.isWhitesTurn = !gp.isWhitesTurn;

        gp.whiteMoveList.set(pieceNumber, new int[] {tileX, tileY});

    }

    public void draw(Graphics2D g2){
        g2.drawImage(image, tileX * gp.tileSize, (gp.numTiles - tileY - 1) * gp.tileSize, gp.tileSize, gp.tileSize, null);
    }

    public void update(){
        selected = checkClickedOn();

        if (selected) {
            gp.currentlySelected = this;
        }

        if (gp.currentlySelected == this){
            for (int[] move : availableMoves){
                if (Arrays.equals(gp.tileClicked, move)) {
                    move();
                    break;
                }
            }
        }
    }
}
