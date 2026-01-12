package Chess.Piece;

import Chess.Game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Pawn extends Piece{

    boolean isFirstMove = true;

    /**
     * method figures out available moves of the pawn based on board around it
     *
     * @return -> array of all available moves
     */
    int[][] getAvailableMoves() {
        ArrayList<int[]> moves = new ArrayList<>(); // arrayList representing available moves
        int numMoves = isFirstMove ? 2 : 1; // pawns can move 2 squares forward on their first move

        int[] currentMove; // variable used for determining available moves

        teamPieceLocations = isWhite ? gp.whiteMoveList : gp.blackMoveList; // all teammate locations

        // logic is same for black and white, but they move in opposite directions
        if (isWhite) {
            int currentY = tileY + 1, limit = tileY + numMoves;

            outer: while (currentY <= limit) {
                currentMove = new int[]{tileX, currentY};
                for (int[] location : teamPieceLocations) {
                    if (Arrays.equals(location, currentMove)) {
                        break outer; // don't show any available moves after where the pawn is blocked
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

        // convert ArrayList to 2D array
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

    /**
     * method sets the image to a png in the resource folder
     */
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

    /**
     * changes x and y co-ordinates to new tile
     */
    public void move(){
        tileX = gp.tileClicked[0];
        tileY = gp.tileClicked[1];
        isFirstMove = false;

        gp.whiteMoveList.set(pieceNumber, new int[] {tileX, tileY});

        availableMoves = getAvailableMoves();
        gp.isWhitesTurn = !gp.isWhitesTurn;
    }

    /**
     * method draws piece if it is alive
     *
     * @param g2 -> Graphics2D class used to draw on JFrame
     */
    public void draw(Graphics2D g2){
        if (isAlive) {
            g2.drawImage(image, tileX * gp.tileSize, (gp.numTiles - tileY - 1) * gp.tileSize, gp.tileSize, gp.tileSize, null);
        }
    }

    /**
     * method handles when pawn is selected
     */
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
