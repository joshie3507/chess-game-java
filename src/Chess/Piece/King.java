package Chess.Piece;

import Chess.Game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class King extends Piece{
        GamePanel gp;

        public King(GamePanel gp, boolean isWhite, int tileX, int tileY){
            this.gp = gp;
            this.upMoves = 1;
            this.sideMoves = 1;
            this.isWhite = isWhite;
            this.tileX = tileX;
            this.tileY = tileY;

            getImage();
        }

        private void getImage()  {
            try {
                if (isWhite) {
                    image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/White/White-King.png"));
                } else {
                    image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/Black/Black-King.png"));
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
            g2.drawImage(image, tileX * gp.tileSize, (gp.numTiles - tileY) * gp.tileSize, gp.tileSize, gp.tileSize, null);
        }

    }


