package Chess.Piece;

import Chess.Game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Queen extends Piece{


    public Queen(GamePanel gp, boolean isWhite, int tileX, int tileY){
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

        for (int row = 0; row < gp.numTiles; row++){
            for (int column = 0; column < gp.numTiles; column++){
                if (column == tileY || row == tileX || (Math.abs(row - tileX) == Math.abs(column - tileY))){
                    moves.add(new int[] {row, column});
                }
            }
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

    public void move(){
        tileY+=upMoves;
        if (upMoves == 2){
            upMoves--;
        }
    }

    public void draw(Graphics2D g2){
        g2.drawImage(image, tileX * gp.tileSize, (gp.numTiles - tileY - 1) * gp.tileSize, gp.tileSize, gp.tileSize, null);
    }

    public void update() {
        selected = checkClickedOn();

        if (selected){
            gp.currentlySelected = this;
        }
    }
}
