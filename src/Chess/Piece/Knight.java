package Chess.Piece;

import Chess.Game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;

public class Knight extends Piece{

        public Knight(GamePanel gp, boolean isWhite, int tileX, int tileY){
            this.gp = gp;
            this.isWhite = isWhite;
            this.tileX = tileX;
            this.tileY = tileY;

            availableMoves = getAvailableMoves();

            getImage();
        }

    private int[][] getAvailableMoves() {
            return new int[][] {
                    {tileX + 2, tileY + 1},
                    {tileX + 2, tileY - 1},
                    {tileX + 1, tileY + 2},
                    {tileX -1, tileY + 2},
                    {tileX - 2, tileY + 1},
                    {tileX - 2, tileY - 1},
                    {tileX + 1, tileY - 2},
                    {tileX - 1, tileY - 2}
            };
    }

    private void getImage()  {
            try {
                if (isWhite) {
                    image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/White/White-Knight.png"));
                } else {
                    image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/Black/Black-Knight.png"));
                }
            } catch (IOException e){
                e.printStackTrace();
            }
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

        public void move(){
            tileX = gp.tileClicked[0];
            tileY = gp.tileClicked[1];
            availableMoves = getAvailableMoves();
            gp.isWhitesTurn = !gp.isWhitesTurn;

        }

        public void draw(Graphics2D g2){
            g2.drawImage(image, tileX * gp.tileSize, (gp.numTiles - tileY - 1) * gp.tileSize, gp.tileSize, gp.tileSize, null);
        }

    }


