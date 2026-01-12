package Chess.Piece;

import Chess.Game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class King extends Piece {

    public King(GamePanel gp, boolean isWhite, int tileX, int tileY, int pieceNumber) {
        this.gp = gp;
        this.isWhite = isWhite;
        this.tileX = tileX;
        this.tileY = tileY;
        this.pieceNumber = pieceNumber;

        getImage();
    }

    /**
     * method figures out available moves of the king based on the surrounding board
     *
     * @return -> array of all available co-ordinates that king can move to
     */
    int[][] getAvailableMoves() {
        ArrayList<int[]> moves = new ArrayList<>(); // arraylist of currently available tile co-ordinates
        boolean blocked = false; // is the current tile already occupied

        teamPieceLocations = isWhite ? gp.whiteMoveList : gp.blackMoveList; // locations of all teammates

        int[] currentMove; // initialising variable used for determining available moves

        // can the king move north
        currentMove = new int[]{tileX + 1, tileY};
        for (int[] location : teamPieceLocations) {
            if (Arrays.equals(location, currentMove)) {
                blocked = true;
                break;
            }
        }

        if (!blocked) {
            moves.add(currentMove);
        }
        blocked = false; // reset variables for next direction

        // all movement logic is the same just different variables being incremented / decremented

        //north-east direction
        currentMove = new int[]{tileX + 1, tileY + 1};
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

        // east direction
        currentMove = new int[]{tileX, tileY + 1};
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

        //south-east
        currentMove = new int[]{tileX - 1, tileY + 1};
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

        // south
        currentMove = new int[]{tileX - 1, tileY};
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

        // south-west
        currentMove = new int[]{tileX - 1, tileY - 1};
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

        // west
        currentMove = new int[]{tileX, tileY - 1};
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

        // north-west
        currentMove = new int[]{tileX + 1, tileY - 1};
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

        for (int i = 0; i < moves.size(); i++) {
            moveArray[i][0] = moves.get(i)[0];
            moveArray[i][1] = moves.get(i)[1];
        }

        return moveArray;
    }

    /**
     * method sets the image to a png in the resource folder
     */
    private void getImage() {
        try {
            if (isWhite) {
                image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/White/White-King.png"));
            } else {
                image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/Black/Black-King.png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * changes x and y co-ordinates to new tile
     */
    public void move() {
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

    /**
     * method handles when king is selected
     */
    public void update() {
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


