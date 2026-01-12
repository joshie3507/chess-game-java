package Chess.Piece;

import Chess.Game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class King extends Piece{

    public King(GamePanel gp, boolean isWhite, int tileX, int tileY, int pieceNumber){
            this.gp = gp;
            this.isWhite = isWhite;
            this.tileX = tileX;
            this.tileY = tileY;
            this.pieceNumber = pieceNumber;

            getImage();
        }

    int[][] getAvailableMoves() {
        ArrayList<int[]> moves = new ArrayList<>();
        boolean blocked = false;

        teamPieceLocations = isWhite ? gp.whiteMoveList : gp.blackMoveList;

        int[] currentMove;

        currentMove = new int[] {tileX + 1, tileY};
        for (int[] location : teamPieceLocations){
            if (Arrays.equals(location, currentMove)){
                blocked = true;
                break;
            }
        }

        if (!blocked){
            moves.add(currentMove);
        }
        blocked = false;

        currentMove = new int[] {tileX + 1, tileY + 1};
        for (int[] location : teamPieceLocations){
            if (Arrays.equals(location, currentMove)){
                blocked = true;
                break;
            }
        }

        if (!blocked){
            moves.add(currentMove);
        }
        blocked = false;

        currentMove = new int[] {tileX, tileY + 1};
        for (int[] location : teamPieceLocations){
            if (Arrays.equals(location, currentMove)){
                blocked = true;
                break;
            }
        }

        if (!blocked){
            moves.add(currentMove);
        }
        blocked = false;

        currentMove = new int[] {tileX - 1, tileY + 1};
        for (int[] location : teamPieceLocations){
            if (Arrays.equals(location, currentMove)){
                blocked = true;
                break;
            }
        }

        if (!blocked){
            moves.add(currentMove);
        }
        blocked = false;

        currentMove = new int[] {tileX - 1, tileY};
        for (int[] location : teamPieceLocations){
            if (Arrays.equals(location, currentMove)){
                blocked = true;
                break;
            }
        }

        if (!blocked){
            moves.add(currentMove);
        }
        blocked = false;

        currentMove = new int[] {tileX - 1, tileY - 1};
        for (int[] location : teamPieceLocations){
            if (Arrays.equals(location, currentMove)){
                blocked = true;
                break;
            }
        }

        if (!blocked){
            moves.add(currentMove);
        }
        blocked = false;

        currentMove = new int[] {tileX, tileY - 1};
        for (int[] location : teamPieceLocations){
            if (Arrays.equals(location, currentMove)){
                blocked = true;
                break;
            }
        }

        if (!blocked){
            moves.add(currentMove);
        }
        blocked = false;

        currentMove = new int[] {tileX + 1, tileY - 1};
        for (int[] location : teamPieceLocations){
            if (Arrays.equals(location, currentMove)){
                blocked = true;
                break;
            }
        }

        if (!blocked){
            moves.add(currentMove);
        }
        blocked = false;

        int[][] moveArray = new int[moves.size()][2];

        for (int i = 0; i < moves.size(); i++){
            moveArray[i][0] = moves.get(i)[0];
            moveArray[i][1] = moves.get(i)[1];
        }

        return moveArray;
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
            for (int[] move : availableMoves){
                if (Arrays.equals(gp.tileClicked, move)) {
                    move();
                    break;
                }
            }
        }
    }
}


