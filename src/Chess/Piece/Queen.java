package Chess.Piece;

import Chess.Game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Queen extends Piece{


    public Queen(GamePanel gp, boolean isWhite, int tileX, int tileY, int pieceNumber){
        this.gp = gp;
        this.isWhite = isWhite;
        this.tileX = tileX;
        this.tileY = tileY;
        this.pieceNumber = pieceNumber;

        getImage();
    }

    /**
     * method figures out available moves of the queen based on surrounding board
     *
     * @return -> array of all available co-ordinates that queen can move to
     */
    @Override
    int[][] getAvailableMoves() {
        ArrayList<int[]> moves = new ArrayList<>(); // ArrayList of available moves

        teamPieceLocations = isWhite ? gp.whiteLocations : gp.blackLocations; // teammate locations

        int currentX, currentY; // initialising variables used in logic
        int[] currentMove;

        // north-east direction
        currentX = tileX + 1;
        currentY = tileY + 1;
        outer: while (currentX < gp.numTiles && currentX >= 0 && currentY < gp.numTiles && currentY >= 0){
            currentMove = new int[] {currentX, currentY};
            for (int[] location : teamPieceLocations) {
                if (Arrays.equals(location, currentMove)) {
                    break outer; // outer while loop breaks so that piece is blocked by other pieces
                }
            }

            moves.add(currentMove);

            currentX++;
            currentY++;
        }

        // logic for movement in the same for all directions, just different values incremented / decremented

        // south-east direction
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

        // north-west
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

        // east
        currentX = tileX + 1;
        outer: while (currentX < gp.numTiles){
            currentMove = new int[] {currentX, tileY};
            for (int[] location : teamPieceLocations) {
                if (Arrays.equals(location, currentMove)) {
                    break outer;
                }
            }

            moves.add(currentMove);
            currentX++;
        }

        // west
        currentX = tileX - 1;
        outer: while (currentX >= 0){
            currentMove = new int[] {currentX, tileY};
            for (int[] location : teamPieceLocations) {
                if (Arrays.equals(location, currentMove)) {
                    break outer;
                }
            }

            moves.add(currentMove);
            currentX--;
        }

        // north
        currentY = tileY + 1;
        outer: while (currentY < gp.numTiles){
            currentMove = new int[] {tileX, currentY};
            for (int[] location : teamPieceLocations) {
                if (Arrays.equals(location, currentMove)) {
                    break outer;
                }
            }

            moves.add(currentMove);
            currentY++;
        }

        // south
        currentY = tileY - 1;
        outer: while(currentY >= 0){
            currentMove = new int[] {tileX, currentY};
            for (int[] location : teamPieceLocations) {
                if (Arrays.equals(location, currentMove)) {
                    break outer;
                }
            }

            moves.add(currentMove);
            currentY--;
        }


        // convert ArrayList to 2D array
        int[][] moveArray = new int [moves.size()][2];
        for (int k = 0; k< moves.size(); k++){
            moveArray[k][0] = moves.get(k)[0];
            moveArray[k][1] = moves.get(k)[1];
        }

        return moveArray;
    }

    /**
     * method sets the image to a png in the resource files
     */
    private void getImage()  {
        try {
            if (isWhite) {
                image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/White/White-Queen.png"));
            } else {
                image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/Black/Black-Queen.png"));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * changes x and y co-ordinates to new tile
     */
    private void move(){
        tileX = gp.tileClicked[0];
        tileY = gp.tileClicked[1];

        gp.whiteLocations.set(pieceNumber, new int[] {tileX, tileY});

        availableMoves = getAvailableMoves();

        gp.isWhitesTurn = !gp.isWhitesTurn;

    }

    /**
     * draws piece if it is alice
     *
     * @param g2 -> Graphics2D class used to draw on JFrame
     */
    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(image, tileX * gp.tileSize, (gp.numTiles - tileY - 1) * gp.tileSize, gp.tileSize, gp.tileSize, null);
    }

    /**
     * method checks if pawn took an opponent piece on last move
     */
    private void checkOpponentPieceTaken() {
        opponentLocations = isWhite ?  gp.blackLocations : gp.whiteLocations;
        opponentPieces = isWhite ?  gp.blackPieces: gp.whitePieces;

        for (int i = 0; i < opponentLocations.size(); i++){
            if (opponentPieces[i] != null && Arrays.equals(opponentLocations.get(i), new int[] {tileX, tileY}) ) {
                opponentPieces[i] = null; // "kills" opponent piece
                break;
            }
        }
    }

    /**
     * method handles when queen is selected
     */
    @Override
    public void update() {
        selected = checkClickedOn();

        if (selected) {
            gp.currentlySelected = this;
        }


        if (gp.currentlySelected == this){
            for (int[] move : availableMoves) {
                if (Arrays.equals(gp.tileClicked, move)) {
                    move();
                    checkOpponentPieceTaken();
                    gp.currentlySelected = null;
                    break;
                }
            }
        }
    }


}
