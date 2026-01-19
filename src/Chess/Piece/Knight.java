package Chess.Piece;

import Chess.Game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Knight extends Piece {

    public Knight(GamePanel gp, boolean isWhite, int tileX, int tileY) {
        this.gp = gp;
        this.isWhite = isWhite;
        this.tileX = tileX;
        this.tileY = tileY;

        this.position = new Position(tileX, tileY);

        getImage();
    }

    public void initPiece(){
        teamPieces = isWhite ? gp.whitePieces : gp.blackPieces;
        opponents = isWhite ? gp.blackPieces : gp.whitePieces;
    }

    /**
     * method figures out available moves of the knight based on the surrounding board
     *
     * @return -> array of all available co-ordinates that knight can move to
     */
    @Override
    Move[] getAvailableMoves() {
        ArrayList<Move> moves = new ArrayList<>(); // ArrayList of currently available moves

        teamPieces = isWhite ? gp.whitePieces : gp.blackPieces;// locations of teammates
        opponents = isWhite ? gp.blackPieces : gp.whitePieces;


        Position currentMove; // initialising variable for determining available moves

        Piece teamPieceAtLocation, opponentPieceAtLocation;

        // can the knight move north-north-east
        currentMove = new Position(tileX + 2, tileY + 1);

        teamPieceAtLocation = teamPieces.get(currentMove);
        opponentPieceAtLocation = opponents.get(currentMove);

        if (teamPieceAtLocation == null){
            moves.add(new Move(currentMove, false));
        } else if (opponentPieceAtLocation != null){
            moves.add(new Move(currentMove, true));
        }
        //all movement logic is the same, just different variables being incremented / decremented

        // north-north-west
        currentMove = new Position(tileX + 2, tileY - 1);

        teamPieceAtLocation = teamPieces.get(currentMove);
        opponentPieceAtLocation = opponents.get(currentMove);

        if (teamPieceAtLocation == null){
            moves.add(new Move(currentMove, false));
        } else if (opponentPieceAtLocation != null){
            moves.add(new Move(currentMove, true));
        }

        // north-east-east
        currentMove = new Position(tileX + 1, tileY + 2);

        teamPieceAtLocation = teamPieces.get(currentMove);
        opponentPieceAtLocation = opponents.get(currentMove);

        if (teamPieceAtLocation == null){
            moves.add(new Move(currentMove, false));
        } else if (opponentPieceAtLocation != null){
            moves.add(new Move(currentMove, true));
        }

        // south-east-east
        currentMove = new Position(tileX - 1, tileY + 2);

        teamPieceAtLocation = teamPieces.get(currentMove);
        opponentPieceAtLocation = opponents.get(currentMove);

        if (teamPieceAtLocation == null){
            moves.add(new Move(currentMove, false));
        } else if (opponentPieceAtLocation != null){
            moves.add(new Move(currentMove, true));
        }

        // south-south-east
        currentMove = new Position(tileX - 2, tileY + 1);

        teamPieceAtLocation = teamPieces.get(currentMove);
        opponentPieceAtLocation = opponents.get(currentMove);

        if (teamPieceAtLocation == null){
            moves.add(new Move(currentMove, false));
        } else if (opponentPieceAtLocation != null){
            moves.add(new Move(currentMove, true));
        }

        // south-south-west
        currentMove = new Position(tileX - 2, tileY - 1);

        teamPieceAtLocation = teamPieces.get(currentMove);
        opponentPieceAtLocation = opponents.get(currentMove);

        if (teamPieceAtLocation == null){
            moves.add(new Move(currentMove, false));
        } else if (opponentPieceAtLocation != null){
            moves.add(new Move(currentMove, true));
        }

        // north-west-west
        currentMove = new Position(tileX + 1, tileY - 2);

        teamPieceAtLocation = teamPieces.get(currentMove);
        opponentPieceAtLocation = opponents.get(currentMove);

        if (teamPieceAtLocation == null){
            moves.add(new Move(currentMove, false));
        } else if (opponentPieceAtLocation != null){
            moves.add(new Move(currentMove, true));
        }

        // south-west-west
        currentMove = new Position(tileX - 1, tileY - 2);

        teamPieceAtLocation = teamPieces.get(currentMove);
        opponentPieceAtLocation = opponents.get(currentMove);

        if (teamPieceAtLocation == null){
            moves.add(new Move(currentMove, false));
        } else if (opponentPieceAtLocation != null){
            moves.add(new Move(currentMove, true));
        }

        // convert ArrayList to 2D array

        Move[] moveArray = new Move[moves.size()];

        for (int k = 0; k < moves.size(); k++) {
            moveArray[k] = moves.get(k);
        }

        return moveArray;

    }

    /**
     * method sets the image to a png in the resource folder
     */
    private void getImage() {
        try {
            if (isWhite) {
                image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/White/White-Knight.png"));
            } else {
                image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Sprites/Black/Black-Knight.png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * method handles when knight is selected
     */
    @Override
    public void update() {
        selected = checkClickedOn();

        if (selected) {
            gp.currentlySelected = this;
        }

        if (gp.currentlySelected == this){
            Position clicked = new Position(gp.tileClicked[0], gp.tileClicked[1]);

            for (Move availableMove : availableMoves){
                if (clicked.equals( availableMove.position)){
                    move( clicked );

                    gp.currentlySelected = null;
                    break;
                }
            }
        }
    }

    /**
     * method checks if knight took an opponent piece on last move
     */
    public void checkOpponentPieceTaken(Position clicked) {
        Piece opponent = opponents.get(clicked);
        if (opponent != null){
            opponents.remove(clicked);
        }
    }

    /**
     * changes x and y co-ordinates to new tile
     */
    public void move(Position clicked) {
        checkOpponentPieceTaken(clicked);

        if (isWhite) {
            gp.whitePieceManager.piecesToRemove.add(new Position(tileX, tileY));
            gp.whitePieceManager.piecesToAdd.put(clicked, this);
            tileX = clicked.x;
            tileY = clicked.y;
            updatePosition(tileX, tileY);
        } else {
            gp.blackPieceManager.piecesToRemove.add(new Position(tileX, tileY));
            gp.blackPieceManager.piecesToAdd.put(clicked, this);
        }

        tileX = clicked.x;
        tileY = clicked.y;
        updatePosition(tileX, tileY);

        availableMoves = getAvailableMoves();
        gp.isWhitesTurn = !gp.isWhitesTurn;
    }

    /**
     * method draws piece if it is alive
     *
     * @param g2 -> Graphics2D class used to draw on JFrame
     */
    @Override
    public void draw(Graphics2D g2) {

        g2.drawImage(image, tileX * gp.tileSize, (gp.numTiles - tileY - 1) * gp.tileSize, gp.tileSize, gp.tileSize, null);

    }

    private void updatePosition(int tileX, int tileY) {
        position = new Position(tileX, tileY);
    }

}


