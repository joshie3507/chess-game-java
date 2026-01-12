package Chess.Piece;

import Chess.Game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;

public class Pawn extends Piece{

    boolean isFirstMove = true;

    private int[][] getAvailableMoves() {
        if (isWhite) return isFirstMove ? new int[][]{{tileX, tileY + 1}, {tileX, tileY + 2}} : new int[][]{{tileX, tileY + 1}};
        return isFirstMove ? new int[][] {{tileX, tileY-1}, {tileX, tileY - 2}} : new int[][]{{tileX, tileY - 1}};
    }

    public Pawn(GamePanel gp, boolean isWhite, int tileX, int tileY){
        this.gp = gp;
        this.upMoves = 2;
        this.sideMoves = 0;
        this.isWhite = isWhite;
        this.tileX = tileX;
        this.tileY = tileY;

        getImage();
        availableMoves = getAvailableMoves();
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
        availableMoves = getAvailableMoves();
        gp.isWhitesTurn = !gp.isWhitesTurn;

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
