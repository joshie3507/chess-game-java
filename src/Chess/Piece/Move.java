package Chess.Piece;

public class Move {
    boolean takeablePiece;
    Position position;

    Move(Position position, boolean takeablePiece){
        this.position = position;
        this.takeablePiece = takeablePiece;
    }

    public boolean getTakeablePiece(){
        return takeablePiece;
    }

    public Position getPosition(){
        return position;
    }
}
