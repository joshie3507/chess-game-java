package Chess.Piece;

import Chess.Game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Pawn extends Piece{

    boolean isFirstMove = true;

    /**
     * method figures out available moves of the pawn based on board around it
     *
     * @return -> array of all available moves
     */
    @Override
    Move[] getAvailableMoves() {
        ArrayList<Move> moves = new ArrayList<>(); // arrayList representing available moves
        int numMoves = isFirstMove ? 2 : 1; // pawns can move 2 squares forward on their first move

        Position currentMove; // variable used for determining available moves

        teamPieces = isWhite ? gp.whitePieces : gp.blackPieces; // all teammate locations
        opponents = isWhite ? gp.blackPieces : gp.whitePieces;

        Piece teamPieceAtLocation, oppAtLocation;

        // logic is same for black and white, but they move in opposite directions
        if (isWhite) {
            int currentY = tileY + 1, limit = tileY + numMoves;

            while (currentY <= limit) {
                currentMove = new Position(tileX, currentY);

                teamPieceAtLocation = teamPieces.get(currentMove);
                oppAtLocation = opponents.get(currentMove);

                if (teamPieceAtLocation != null || oppAtLocation != null){
                    break;
                }

                moves.add(new Move(currentMove, false));
                currentY++;
            }

            currentMove = new Position(tileX + 1, tileY + 1);
            oppAtLocation = opponents.get(currentMove);
            if (oppAtLocation != null){
                moves.add(new Move(currentMove, true));
            }

            currentMove = new Position(tileX - 1, tileY + 1);
            oppAtLocation = opponents.get(currentMove);
            if (oppAtLocation != null){
                moves.add(new Move(currentMove, true));
            }

        } else {
            int currentY = tileY - 1, limit = tileY - numMoves;

            while (currentY >= limit) {
                currentMove = new Position(tileX, currentY);

                teamPieceAtLocation = teamPieces.get(currentMove);
                oppAtLocation = opponents.get(currentMove);

                if (teamPieceAtLocation != null || oppAtLocation != null){
                    break;
                }

                moves.add(new Move(currentMove, false));
                currentY--;
            }

            currentMove = new Position(tileX + 1, tileY - 1);
            oppAtLocation = opponents.get(currentMove);
            if (oppAtLocation != null){
                moves.add(new Move(currentMove, true));
            }

            currentMove = new Position(tileX - 1, tileY - 1);
            oppAtLocation = opponents.get(currentMove);
            if (oppAtLocation != null){
                moves.add(new Move(currentMove, true));
            }

        }

        // convert ArrayList to 2D array
        Move[] moveArray = new Move[moves.size()];

        for (int i = 0; i < moves.size(); i++){
            moveArray[i] = moves.get(i);
        }

        return moveArray;

    }

    public Pawn(GamePanel gp, boolean isWhite, int tileX, int tileY){
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
     * method sets the image to a png in the resource folder
     */
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

    /**
     * changes x and y co-ordinates to new tile
     */
    public void move(Position clicked){
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

    /**
     * method checks if pawn took an opponent piece on last move
     */
    public void checkOpponentPieceTaken(Position clicked) {
        Piece opponent = opponents.get(clicked);
        if (opponent != null){
            opponents.remove(clicked);
        }
    }

    /**
     * method handles when pawn is selected
     */
    @Override
    public void update(){
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

    private void updatePosition(int tileX, int tileY) {
        position = new Position(tileX, tileY);
    }
}
