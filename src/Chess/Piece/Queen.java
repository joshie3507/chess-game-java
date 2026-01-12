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

    int[][] getAvailableMoves() {
        ArrayList<int[]> moves = new ArrayList<>();

        teamPieceLocations = isWhite ? gp.whiteMoveList : gp.blackMoveList;

        System.out.println();

        int currentX, currentY;
        int[] currentMove;

        currentX = tileX + 1;
        currentY = tileY + 1;
        outer: while (currentX < gp.numTiles && currentX >= 0 && currentY < gp.numTiles && currentY >= 0){
            currentMove = new int[] {currentX, currentY};
            for (int[] location : teamPieceLocations) {
                if (Arrays.equals(location, currentMove)) {
                    break outer;
                }
            }

            moves.add(currentMove);

            currentX++;
            currentY++;
        }

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
            currentX++;
            currentY++;
        }

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


        int[][] moveArray = new int [moves.size()][2];
        for (int k = 0; k< moves.size(); k++){
            moveArray[k][0] = moves.get(k)[0];
            moveArray[k][1] = moves.get(k)[1];
        }

        return moveArray;
    }

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

    private void move(){
        tileX = gp.tileClicked[0];
        tileY = gp.tileClicked[1];
        availableMoves = getAvailableMoves();
        gp.isWhitesTurn = !gp.isWhitesTurn;

    }

    public void draw(Graphics2D g2){
        g2.drawImage(image, tileX * gp.tileSize, (gp.numTiles - tileY - 1) * gp.tileSize, gp.tileSize, gp.tileSize, null);
    }

    public void update() {
        selected = checkClickedOn();

        if (selected) {
            gp.currentlySelected = this;

        }

        if (gp.currentlySelected == this){
            for (int[] move : availableMoves) {
                if (Arrays.equals(gp.tileClicked, move)) {
                    move();
                    break;
                }
            }
        }
    }

    ArrayList<int[]> getTeamPieces() {
        ArrayList<int[]> moves = isWhite ? gp.whiteMoveList : gp.blackMoveList;
        moves.remove(pieceNumber);
        return moves;
    }
}
