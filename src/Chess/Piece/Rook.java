package Chess.Piece;

import Chess.Game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Rook extends Piece{

    GamePanel gp;

    public Rook(GamePanel gp, boolean isWhite, int tileX, int tileY){
        this.gp = gp;
        this.upMoves = 10;
        this.sideMoves = 10;
        this.isWhite = isWhite;
        this.tileX = tileX;
        this.tileY = tileY;

        getImage();
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
        g2.drawImage(image, tileX * gp.tileSize, (gp.numTiles - tileY) * gp.tileSize, gp.tileSize, gp.tileSize, null);
    }
}
