package Chess.Piece;

import java.util.Objects;

public class Position {
    public final int x;
    public final int y;

    Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Position o)) return false;
        return x == o.x && y == o.y;
    }

    @Override
    public int hashCode(){
        return Objects.hash(x, y);
    }

    public String toString(){
        return "Position -> [" + x + ", " + y + "]";
    }
}
