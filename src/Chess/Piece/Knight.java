package Chess.Piece;

import Chess.Game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Knight extends Piece {

    public Knight(GamePanel gp, boolean isWhite, int tileX, int tileY, int pieceNumber) {
        this.gp = gp;
        this.isWhite = isWhite;
        this.tileX = tileX;
        this.tileY = tileY;
        this.pieceNumber = pieceNumber;


        getImage();
    }

    /**
     * method figures out available moves of the knight based on the surrounding board
     *
     * @return -> array of all available co-ordinates that knight can move to
     */
    @Override
    int[][] getAvailableMoves() {
        ArrayList<int[]> moves = new ArrayList<>(); // ArrayList of currently available moves
        boolean blocked = false; // is the current tile already occupied

        teamPieceLocations = isWhite ? gp.whiteLocations : gp.blackLocations; // locations of teammates

        int[] currentMove; // initialising variable for determining available moves

        // can the knight move north-north-east
        currentMove = new int[]{tileX + 2, tileY + 1};
        for (int[] location : teamPieceLocations) {
            if (Arrays.equals(location, currentMove)) {
                blocked = true;
                break;
            }
        }

        if (!blocked) {
            moves.add(currentMove);
        }
        blocked = false;// reset variables for next direction

        //all movement logic is the same, just different variables being incremented / decremented

        // north-north-west
        currentMove = new int[]{tileX + 2, tileY - 1};
        for (int[] location : teamPieceLocations) {
            if (Arrays.equals(location, currentMove)) {
                blocked = true;
                break;
            }
        }

        if (!blocked) {
            moves.add(currentMove);
        }
        blocked = false;

        // north-east-east
        currentMove = new int[]{tileX + 1, tileY + 2};
        for (int[] location : teamPieceLocations) {
            if (Arrays.equals(location, currentMove)) {
                blocked = true;
                break;
            }
        }

        if (!blocked) {
            moves.add(currentMove);
        }
        blocked = false;

        // south-east-east
        currentMove = new int[]{tileX - 1, tileY + 2};
        for (int[] location : teamPieceLocations) {
            if (Arrays.equals(location, currentMove)) {
                blocked = true;
                break;
            }
        }

        if (!blocked) {
            moves.add(currentMove);
        }
        blocked = false;

        // south-south-east
        currentMove = new int[]{tileX - 2, tileY + 1};
        for (int[] location : teamPieceLocations) {
            if (Arrays.equals(location, currentMove)) {
                blocked = true;
                break;
            }
        }

        if (!blocked) {
            moves.add(currentMove);
        }
        blocked = false;

        // south-south-west
        currentMove = new int[]{tileX - 2, tileY - 1};
        for (int[] location : teamPieceLocations) {
            if (Arrays.equals(location, currentMove)) {
                blocked = true;
                break;
            }
        }

        if (!blocked) {
            moves.add(currentMove);
        }
        blocked = false;

        // north-west-west
        currentMove = new int[]{tileX + 1, tileY - 2};
        for (int[] location : teamPieceLocations) {
            if (Arrays.equals(location, currentMove)) {
                blocked = true;
                break;
            }
        }

        if (!blocked) {
            moves.add(currentMove);
        }
        blocked = false;

        // south-west-west
        currentMove = new int[]{tileX - 1, tileY - 2};
        for (int[] location : teamPieceLocations) {
            if (Arrays.equals(location, currentMove)) {
                blocked = true;
                break;
            }
        }

        if (!blocked) {
            moves.add(currentMove);
        }

        // convert ArrayList to 2D array

        int[][] moveArray = new int[moves.size()][2];

        for (int k = 0; k < moves.size(); k++) {
            moveArray[k][0] = moves.get(k)[0];
            moveArray[k][1] = moves.get(k)[1];
        }

        return moveArray;

    }

    /**
     * method sets the image to a png in the resource folder
     */
    private void getImage() {
        try {
            if (isWhite) {
                image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/White/White-Knight.png"));
            } else {
                image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/Black/Black-Knight.png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * method handles when knight is selected
     */
    @Override
    public void update() {
        selected = checkClickedOn();

        if (selected) {
            gp.currentlySelected = this;
        }

        if (gp.currentlySelected == this) {
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
     * method checks if knight took an opponent piece on last move
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
     * changes x and y co-ordinates to new tile
     */
    public void move() {
        tileX = gp.tileClicked[0];
        tileY = gp.tileClicked[1];

        gp.whiteLocations.set(pieceNumber, new int[]{tileX, tileY});

        availableMoves = getAvailableMoves();
        gp.isWhitesTurn = !gp.isWhitesTurn;

    }

    /**
     * method draws piece if it is alive
     *
     * @param g2 -> Graphics2D class used to draw on JFrame
     */
    @Override
    public void draw(Graphics2D g2) {

        g2.drawImage(image, tileX * gp.tileSize, (gp.numTiles - tileY - 1) * gp.tileSize, gp.tileSize, gp.tileSize, null);

    }

}


