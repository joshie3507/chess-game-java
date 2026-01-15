package Chess.Piece;

import Chess.Game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Rook extends Piece{


    public Rook(GamePanel gp, boolean isWhite, int tileX, int tileY, int pieceNumber){
        this.gp = gp;
        this.isWhite = isWhite;
        this.tileX = tileX;
        this.tileY = tileY;
        this.pieceNumber = pieceNumber;

        getImage();
    }



    /**
     * method figures out available moves of rook based on board around it
     *
     * @return -> array of all available co-ordinate that rook can move to
     */
    @Override
    int[][] getAvailableMoves() {
        ArrayList<int[]> moves = new ArrayList<>(); // ArrayList of all available tiles

        teamPieceLocations = isWhite ? gp.whiteLocations : gp.blackLocations; // teammate locations
        opponentLocations = isWhite ? gp.blackLocations : gp.whiteLocations; // opponent locations\


        int currentX, currentY; // variables used in logic
        int[] currentMove;

        // east direction
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

        // logic for movement in all directions is the same, just different values changed

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
        int[][] moveArray = new int[moves.size()][2];

        for (int k = 0; k <moves.size(); k++){
            moveArray[k][0] = moves.get(k)[0];
            moveArray[k][1] = moves.get(k)[1];
        }

        return moveArray;
    }

    /**
     * sets image to a png in resource files
     */
    private void getImage(){
        try {
            if (isWhite) {
                image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/White/White-Rook.png"));
            } else {
                image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/Black/Black-Rook.png"));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * draws piece if it is alive
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
     * method handles when rook is selected
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

    /**
     * changes x and y co-ordinate to new tile
     */
    private void move(){
        tileX = gp.tileClicked[0];
        tileY = gp.tileClicked[1];

        gp.whiteLocations.set(pieceNumber, new int[] {tileX, tileY});

        availableMoves = getAvailableMoves();
        gp.isWhitesTurn = !gp.isWhitesTurn;
    }
}
