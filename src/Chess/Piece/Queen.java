package Chess.Piece;

import Chess.Game.CheckState;
import Chess.Game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Queen extends Piece{


    public Queen(GamePanel gp, boolean isWhite, int tileX, int tileY){
        this.gp = gp;
        this.isWhite = isWhite;
        this.tileX = tileX;
        this.tileY = tileY;

        this.position = new Position(tileX, tileY);

        getImage();
    }

    public void checkForCheck() {
        Position kingPosition = null;

        for (Position position : opponents.keySet()) {
            if (opponents.get(position) instanceof King) {
                kingPosition = position;
            }
        }

        for (Move availableMove : availableMoves) {
            if (availableMove.position.equals(kingPosition)) {
                gp.checkState = isWhite ? CheckState.BLACK : CheckState.WHITE;
                break;
            }
        }
    }

    public void initPiece(){
        teamPieces = isWhite ? gp.whitePieces : gp.blackPieces;
        opponents = isWhite ? gp.blackPieces : gp.whitePieces;
    }

    /**
     * method figures out available moves of the queen based on surrounding board
     *
     * @return -> array of all available co-ordinates that queen can move to
     */
    @Override
    Move[] getAvailableMoves() {
        ArrayList<Move> moves = new ArrayList<>(); // ArrayList of available moves

        int currentX, currentY; // initialising variables used in logic
        Position currentMove;

        switch (gp.checkState) {
            case NONE :
                // north-east direction
                currentX = tileX + 1;
                currentY = tileY + 1;
                while (currentX < gp.numTiles && currentX >= 0 && currentY < gp.numTiles && currentY >= 0) {
                    currentMove = new Position(currentX, currentY);

                    Piece oppPieceAtLocation = opponents.get(currentMove);
                    Piece teamPieceAtLocation = teamPieces.get(currentMove);

                    if (oppPieceAtLocation != null) {
                        moves.add(new Move(currentMove, true));
                        break;
                    } else if (teamPieceAtLocation != null) {
                        break;
                    }

                    moves.add(new Move(currentMove, false));

                    currentX++;
                    currentY++;
                }

                // logic for movement in the same for all directions, just different values incremented / decremented

                // south-east direction
                currentX = tileX + 1;
                currentY = tileY - 1;
                while (currentX < gp.numTiles && currentX >= 0 && currentY < gp.numTiles && currentY >= 0) {
                    currentMove = new Position(currentX, currentY);

                    Piece oppPieceAtLocation = opponents.get(currentMove);
                    Piece teamPieceAtLocation = teamPieces.get(currentMove);

                    if (oppPieceAtLocation != null) {
                        moves.add(new Move(currentMove, true));
                        break;
                    } else if (teamPieceAtLocation != null) {
                        break;
                    }

                    moves.add(new Move(currentMove, false));
                    currentX++;
                    currentY--;
                }

                // north-west
                currentX = tileX - 1;
                currentY = tileY + 1;
                while (currentX < gp.numTiles && currentX >= 0 && currentY < gp.numTiles && currentY >= 0) {
                    currentMove = new Position(currentX, currentY);

                    Piece oppPieceAtLocation = opponents.get(currentMove);
                    Piece teamPieceAtLocation = teamPieces.get(currentMove);

                    if (oppPieceAtLocation != null) {
                        moves.add(new Move(currentMove, true));
                        break;
                    } else if (teamPieceAtLocation != null) {
                        break;
                    }

                    moves.add(new Move(currentMove, false));
                    currentX--;
                    currentY++;
                }

                // south-west
                currentX = tileX - 1;
                currentY = tileY - 1;
                while (currentX < gp.numTiles && currentX >= 0 && currentY < gp.numTiles && currentY >= 0) {
                    currentMove = new Position(currentX, currentY);

                    Piece oppPieceAtLocation = opponents.get(currentMove);
                    Piece teamPieceAtLocation = teamPieces.get(currentMove);

                    if (oppPieceAtLocation != null) {
                        moves.add(new Move(currentMove, true));
                        break;
                    } else if (teamPieceAtLocation != null) {
                        break;
                    }

                    moves.add(new Move(currentMove, false));
                    currentX--;
                    currentY--;
                }

                // east
                currentX = tileX + 1;
                while (currentX < gp.numTiles) {
                    currentMove = new Position(currentX, tileY);

                    Piece oppPieceAtLocation = opponents.get(currentMove);
                    Piece teamPieceAtLocation = teamPieces.get(currentMove);

                    if (oppPieceAtLocation != null) {
                        moves.add(new Move(currentMove, true));
                        break;
                    } else if (teamPieceAtLocation != null) {
                        break;
                    }

                    moves.add(new Move(currentMove, false));
                    currentX++;
                }

                // west
                currentX = tileX - 1;
                while (currentX >= 0) {
                    currentMove = new Position(currentX, tileY);

                    Piece oppPieceAtLocation = opponents.get(currentMove);
                    Piece teamPieceAtLocation = teamPieces.get(currentMove);

                    if (oppPieceAtLocation != null) {
                        moves.add(new Move(currentMove, true));
                        break;
                    } else if (teamPieceAtLocation != null) {
                        break;
                    }

                    moves.add(new Move(currentMove, false));
                    currentX--;
                }

                // north
                currentY = tileY + 1;
                while (currentY < gp.numTiles) {
                    currentMove = new Position(tileX, currentY);

                    Piece oppPieceAtLocation = opponents.get(currentMove);
                    Piece teamPieceAtLocation = teamPieces.get(currentMove);

                    if (oppPieceAtLocation != null) {
                        moves.add(new Move(currentMove, true));
                        break;
                    } else if (teamPieceAtLocation != null) {
                        break;
                    }

                    moves.add(new Move(currentMove, false));
                    currentY++;
                }

                // south
                currentY = tileY - 1;
                while (currentY >= 0) {
                    currentMove = new Position(tileX, currentY);

                    Piece oppPieceAtLocation = opponents.get(currentMove);
                    Piece teamPieceAtLocation = teamPieces.get(currentMove);

                    if (oppPieceAtLocation != null) {
                        moves.add(new Move(currentMove, true));
                        break;
                    } else if (teamPieceAtLocation != null) {
                        break;
                    }

                    moves.add(new Move(currentMove, false));
                    currentY--;
                }
                break;

            case BLACK:
            case WHITE:
                // north-east direction
                currentX = tileX + 1;
                currentY = tileY + 1;
                while (currentX < gp.numTiles && currentX >= 0 && currentY < gp.numTiles && currentY >= 0) {
                    currentMove = new Position(currentX, currentY);

                    Piece oppPieceAtLocation = opponents.get(currentMove);
                    Piece teamPieceAtLocation = teamPieces.get(currentMove);

                    if (oppPieceAtLocation != null && oppPieceAtLocation.getClass() == gp.checkingPiece.getClass()) {
                        moves.add(new Move(currentMove, true));
                        break;
                    } else if (teamPieceAtLocation != null || oppPieceAtLocation != null) {
                        break;
                    }

                    for (Move move : gp.checkingPiece.availableMoves) {
                        if (move.position.equals(currentMove) && getSquaresBetween().contains(move.position)) {
                            moves.add(new Move(currentMove, false));
                        }
                    }

                    currentX++;
                    currentY++;
                }

                // logic for movement in the same for all directions, just different values incremented / decremented

                // south-east direction
                currentX = tileX + 1;
                currentY = tileY - 1;
                while (currentX < gp.numTiles && currentX >= 0 && currentY < gp.numTiles && currentY >= 0) {
                    currentMove = new Position(currentX, currentY);

                    Piece oppPieceAtLocation = opponents.get(currentMove);
                    Piece teamPieceAtLocation = teamPieces.get(currentMove);

                    if (oppPieceAtLocation != null && oppPieceAtLocation.getClass() == gp.checkingPiece.getClass()) {
                        moves.add(new Move(currentMove, true));
                        break;
                    } else if (teamPieceAtLocation != null || oppPieceAtLocation != null) {
                        break;
                    }

                    for (Move move : gp.checkingPiece.availableMoves) {
                        if (move.position.equals(currentMove) && getSquaresBetween().contains(move.position)) {
                            moves.add(new Move(currentMove, false));
                        }
                    }

                    currentX++;
                    currentY--;
                }

                // north-west
                currentX = tileX - 1;
                currentY = tileY + 1;
                while (currentX < gp.numTiles && currentX >= 0 && currentY < gp.numTiles && currentY >= 0) {
                    currentMove = new Position(currentX, currentY);

                    Piece oppPieceAtLocation = opponents.get(currentMove);
                    Piece teamPieceAtLocation = teamPieces.get(currentMove);

                    if (oppPieceAtLocation != null && oppPieceAtLocation.getClass() == gp.checkingPiece.getClass()) {
                        moves.add(new Move(currentMove, true));
                        break;
                    } else if (teamPieceAtLocation != null || oppPieceAtLocation != null) {
                        break;
                    }

                    for (Move move : gp.checkingPiece.availableMoves) {
                        if (move.position.equals(currentMove) && getSquaresBetween().contains(move.position)) {
                            moves.add(new Move(currentMove, false));
                        }
                    }

                    currentX--;
                    currentY++;
                }

                // south-west
                currentX = tileX - 1;
                currentY = tileY - 1;
                while (currentX < gp.numTiles && currentX >= 0 && currentY < gp.numTiles && currentY >= 0) {
                    currentMove = new Position(currentX, currentY);

                    Piece oppPieceAtLocation = opponents.get(currentMove);
                    Piece teamPieceAtLocation = teamPieces.get(currentMove);

                    if (oppPieceAtLocation != null && oppPieceAtLocation.getClass() == gp.checkingPiece.getClass()) {
                        moves.add(new Move(currentMove, true));
                        break;
                    } else if (teamPieceAtLocation != null || oppPieceAtLocation != null) {
                        break;
                    }

                    for (Move move : gp.checkingPiece.availableMoves) {
                        if (move.position.equals(currentMove) && getSquaresBetween().contains(move.position)) {
                            moves.add(new Move(currentMove, false));
                        }
                    }

                    currentX--;
                    currentY--;
                }

                // east
                currentX = tileX + 1;
                while (currentX < gp.numTiles) {
                    currentMove = new Position(currentX, tileY);

                    Piece oppPieceAtLocation = opponents.get(currentMove);
                    Piece teamPieceAtLocation = teamPieces.get(currentMove);

                    if (oppPieceAtLocation != null && oppPieceAtLocation.getClass() == gp.checkingPiece.getClass()) {
                        moves.add(new Move(currentMove, true));
                        break;
                    } else if (teamPieceAtLocation != null || oppPieceAtLocation != null) {
                        break;
                    }

                    for (Move move : gp.checkingPiece.availableMoves) {
                        if (move.position.equals(currentMove) && getSquaresBetween().contains(move.position)) {
                            moves.add(new Move(currentMove, false));
                        }
                    }

                    currentX++;
                }

                // west
                currentX = tileX - 1;
                while (currentX >= 0) {
                    currentMove = new Position(currentX, tileY);

                    Piece oppPieceAtLocation = opponents.get(currentMove);
                    Piece teamPieceAtLocation = teamPieces.get(currentMove);

                    if (oppPieceAtLocation != null && oppPieceAtLocation.getClass() == gp.checkingPiece.getClass()) {
                        moves.add(new Move(currentMove, true));
                        break;
                    } else if (teamPieceAtLocation != null || oppPieceAtLocation != null) {
                        break;
                    }

                    for (Move move : gp.checkingPiece.availableMoves) {
                        if (move.position.equals(currentMove) && getSquaresBetween().contains(move.position)) {
                            moves.add(new Move(currentMove, false));
                        }
                    }

                    currentX--;
                }

                // north
                currentY = tileY + 1;
                while (currentY < gp.numTiles) {
                    currentMove = new Position(tileX, currentY);

                    Piece oppPieceAtLocation = opponents.get(currentMove);
                    Piece teamPieceAtLocation = teamPieces.get(currentMove);

                    if (oppPieceAtLocation != null && oppPieceAtLocation.getClass() == gp.checkingPiece.getClass()) {
                        moves.add(new Move(currentMove, true));
                        break;
                    } else if (teamPieceAtLocation != null || oppPieceAtLocation != null) {
                        break;
                    }

                    for (Move move : gp.checkingPiece.availableMoves) {
                        if (move.position.equals(currentMove) && getSquaresBetween().contains(move.position)) {
                            moves.add(new Move(currentMove, false));
                        }
                    }

                    currentY++;
                }

                // south
                currentY = tileY - 1;
                while (currentY >= 0) {
                    currentMove = new Position(tileX, currentY);

                    Piece oppPieceAtLocation = opponents.get(currentMove);
                    Piece teamPieceAtLocation = teamPieces.get(currentMove);

                    if (oppPieceAtLocation != null && oppPieceAtLocation.getClass() == gp.checkingPiece.getClass()) {
                        moves.add(new Move(currentMove, true));
                        break;
                    } else if (teamPieceAtLocation != null || oppPieceAtLocation != null) {
                        break;
                    }

                    for (Move move : gp.checkingPiece.availableMoves) {
                        if (move.position.equals(currentMove) && getSquaresBetween().contains(move.position)) {
                            moves.add(new Move(currentMove, false));
                        }
                    }

                    currentY--;
                }

        }

        // convert ArrayList to 2D array
        Move[] moveArray = new Move[moves.size()];

        for (int i = 0; i < moves.size(); i++){
            moveArray[i] = moves.get(i);
        }

        return moveArray;
    }

    /**
     * gets the list of squares the checking piece must travel to get to the king
     * @return
     */
    private ArrayList<Position> getSquaresBetween() {
        ArrayList<Position> squaresBetween = new ArrayList<>();

        if (gp.checkingPiece instanceof Bishop ||
                gp.checkingPiece instanceof Rook ||
                gp.checkingPiece instanceof  Queen) {

            King oppKing = isWhite ? gp.blackKing : gp.whiteKing;

            int dx = Integer.signum(gp.checkingPiece.position.x - oppKing.position.x);
            int dy = Integer.signum(gp.checkingPiece.position.y - oppKing.position.y);


            int x = oppKing.position.x + dx;
            int y = oppKing.position.y + dy;


            while (x != gp.checkingPiece.position.x || y != gp.checkingPiece.position.y) {
                squaresBetween.add(new Position(x, y));
                x += dx;
                y += dy;
            }


        }
        return squaresBetween;
    }

    /**
     * method sets the image to a png in the resource files
     */
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

    /**
     * changes x and y co-ordinates to new tile
     */
    private void move(Position clicked){
        checkOpponentPieceTaken(clicked);
        if (isWhite) {
            gp.whitePieceManager.piecesToRemove.add(new Position(tileX, tileY));
            gp.whitePieceManager.piecesToAdd.put(clicked, this);
        } else {
            gp.blackPieceManager.piecesToRemove.add(new Position(tileX, tileY));
            gp.blackPieceManager.piecesToAdd.put(clicked, this);
        }

        tileX = clicked.x;
        tileY = clicked.y;
        updatePosition(tileX, tileY);

        availableMoves = getAvailableMoves();
        gp.isWhitesTurn = !gp.isWhitesTurn;

        checkForCheck();
    }

    /**
     * draws piece if it is alice
     *
     * @param g2 -> Graphics2D class used to draw on JFrame
     */
    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(image, tileX * gp.tileSize, (gp.numTiles - tileY - 1) * gp.tileSize, gp.tileSize, gp.tileSize, null);
    }

    /**
     * method checks if queen took an opponent piece on last move
     */
    public void checkOpponentPieceTaken(Position clicked) {
        Piece opponent = opponents.get(clicked);
        if (opponent != null){
            opponents.remove(clicked);
        }
    }

    /**
     * method handles when queen is selected
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

    private void updatePosition(int tileX, int tileY) {
        position = new Position(tileX, tileY);
    }
}
