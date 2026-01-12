package Chess.Piece;

import Chess.Game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Rook extends Piece{


    public Rook(GamePanel gp, boolean isWhite, int tileX, int tileY){
        this.gp = gp;
        this.isWhite = isWhite;
        this.tileX = tileX;
        this.tileY = tileY;

        getImage();
        availableMoves = getAvailableMoves();
    }

    private int[][] getAvailableMoves() {
        ArrayList<int[]> moves = new ArrayList<>();
        for (int i = 1; i < gp.numTiles; i++){
            moves.add(new int[] {(tileX + i) % gp.numTiles, tileY});
            moves.add(new int[] {tileX, (tileY + i) % gp.numTiles});
        }

        int[][] moveArray = new int[moves.size()][2];

        for (int k = 0; k <moves.size(); k++){
            moveArray[k][0] = moves.get(k)[0];
            moveArray[k][1] = moves.get(k)[1];
        }

        return moveArray;
    }

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

    private void move(){
        tileX = gp.tileClicked[0];
        tileY = gp.tileClicked[1];
        availableMoves = getAvailableMoves();
        gp.isWhitesTurn = !gp.isWhitesTurn;
    }
}
