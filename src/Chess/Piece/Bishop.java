package Chess.Piece;

import Chess.Game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Bishop extends Piece {
    public Bishop(GamePanel gp, boolean isWhite, int tileX, int tileY) {
        this.gp = gp;
        this.upMoves = 8;
        this.sideMoves = 8;
        this.isWhite = isWhite;
        this.tileX = tileX;
        this.tileY = tileY;

        getImage();
        availableMoves = getAvailableMoves();
    }

    private int[][] getAvailableMoves() {
        ArrayList<int[]> moves = new ArrayList<>();

        for (int row = 0; row <= 7; row++) {
            for (int column = 0; column <= 7; column++) {
                if (Math.abs(row - tileX) == Math.abs(column - tileY) && !(row == tileX && column == tileY)) {
                    moves.add(new int[]{row, column});
                }
            }
        }

        int[][] moveArray = new int[moves.size()][2];

        for (int k = 0; k < moves.size(); k++) {
            moveArray[k][0] = moves.get(k)[0];
            moveArray[k][1] = moves.get(k)[1];
        }

        return moveArray;
    }

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


    public void update() {
        selected = checkClickedOn();

        if (selected) {
            gp.currentlySelected = this;
        }

        if (gp.currentlySelected == this) {
            for (int[] move : availableMoves) {
                if (Arrays.equals(gp.tileClicked, move)) {
                    move();
                    break;
                }
            }
        }
    }


    private void move() {
        tileX = gp.tileClicked[0];
        tileY = gp.tileClicked[1];
        availableMoves = getAvailableMoves();
        gp.isWhitesTurn = !gp.isWhitesTurn;

    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, tileX * gp.tileSize, (gp.numTiles - tileY - 1) * gp.tileSize, gp.tileSize, gp.tileSize, null);
    }

}

