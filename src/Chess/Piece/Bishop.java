package Chess.Piece;

import Chess.Game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Bishop extends Piece {
    public Bishop(GamePanel gp, boolean isWhite, int tileX, int tileY, int pieceNumber) {
        this.gp = gp;
        this.isWhite = isWhite;
        this.tileX = tileX;
        this.tileY = tileY;
        this.pieceNumber = pieceNumber;

        getImage();
    }

    /**
     * method figures out available moves of the bishop based on the surrounding board
     *
     * @return -> array of all available co-ordinates that bishop can move to
     */
    int[][] getAvailableMoves() {
        ArrayList<int[]> moves = new ArrayList<>(); // arraylist of currently available moves

        teamPieceLocations = isWhite ? gp.whiteMoveList : gp.blackMoveList; // locations of all pieces on the same team as the bishop

        int currentX, currentY; // initialising variables used for determining available move
        int[] currentMove;

        // available moves in the north-east direction
        currentX = tileX + 1;
        currentY = tileY + 1;
        outer: while (currentX < gp.numTiles && currentX >= 0 && currentY < gp.numTiles && currentY >= 0){
            currentMove = new int[] {currentX, currentY};
            for (int[] location : teamPieceLocations) {
                if (Arrays.equals(location, currentMove)) {
                    break outer; // outer while loop breaks so that the piece is blocked when it encounters a teammate
                }
            }

            moves.add(currentMove);
            currentX++;
            currentY++;
        }

        //logic for movement in all directions is the same, just different values incremented / decremented
        //south-east direction
        currentX = tileX + 1;
        currentY = tileY - 1;
        outer: while (currentX < gp.numTiles && currentX >= 0 && currentY < gp.numTiles && currentY >= 0){
            currentMove = new int[] {currentX, currentY};
            for (int[] location : teamPieceLocations) {
                if (Arrays.equals(location, currentMove)) {
                    break outer;
                }
            }

            moves.add(currentMove);
            currentX++;
            currentY--;
        }

        // north-west direction
        currentX = tileX - 1;
        currentY = tileY + 1;
        outer: while (currentX < gp.numTiles && currentX >= 0 && currentY < gp.numTiles && currentY >= 0){
            currentMove = new int[] {currentX, currentY};
            for (int[] location : teamPieceLocations) {
                if (Arrays.equals(location, currentMove)) {
                    break outer;
                }
            }

            moves.add(currentMove);
            currentX--;
            currentY++;
        }

        // south-west
        currentX = tileX - 1;
        currentY = tileY - 1;
        outer: while (currentX < gp.numTiles && currentX >= 0 && currentY < gp.numTiles && currentY >= 0){
            currentMove = new int[] {currentX, currentY};
            for (int[] location : teamPieceLocations) {
                if (Arrays.equals(location, currentMove)) {
                    break outer;
                }
            }

            moves.add(currentMove);
            currentX--;
            currentY--;
        }


        //convert ArrayList to 2D array

        int[][] moveArray = new int[moves.size()][2];

        for (int k = 0; k < moves.size(); k++) {
            moveArray[k][0] = moves.get(k)[0];
            moveArray[k][1] = moves.get(k)[1];
        }

        return moveArray;
    }

    /**
     * method sets the image to a png in the resources file
     */
    private void getImage() {
        try {
            if (isWhite) {
                image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/White/White-Bishop.png"));
            } else {
                image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/Black/Black-Bishop.png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * method handles when bishop is selected
     */
    public void update() {
        if (isAlive) {
            selected = checkClickedOn();

            if (selected) {
                gp.currentlySelected = this;
            }

            if (gp.currentlySelected == this) {
                for (int[] move : availableMoves) {
                    if (Arrays.equals(gp.tileClicked, move)) {
                        move();
                        checkOpponentPieceTaken();
                        break;
                    }
                }
            }
        }
    }

    /**
     * method checks if bishop took an opponent piece on last move
     */
    private void checkOpponentPieceTaken() {
        opponentLocations = isWhite ? gp.whiteMoveList : gp.blackMoveList;
        opponentPieces = isWhite ? gp.whitePieces : gp.blackPieces;

        for (int i = 0; i < opponentLocations.size(); i++){
            if (Arrays.equals(opponentLocations.get(i), new int[] {tileX, tileY}) && opponentPieces[i].isAlive){
                opponentPieces[i].isAlive = false; // "kills" opponent piece
                break;
            }
        }
    }


    /**
     * changes x and y co-ordinates to new tile
     */
    private void move() {
        tileX = gp.tileClicked[0];
        tileY = gp.tileClicked[1];

        gp.whiteMoveList.set(pieceNumber, new int[] {tileX, tileY});
        availableMoves = getAvailableMoves();

        gp.isWhitesTurn = !gp.isWhitesTurn;

    }

    /**
     * method draws piece if it is alive
     *
     * @param g2 -> Graphics2D class used to draw on JFrame
     */
    public void draw(Graphics2D g2) {
        if (isAlive) {
            g2.drawImage(image, tileX * gp.tileSize, (gp.numTiles - tileY - 1) * gp.tileSize, gp.tileSize, gp.tileSize, null);
        }
    }
}

