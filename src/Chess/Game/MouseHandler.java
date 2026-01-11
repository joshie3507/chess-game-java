package Chess.Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {

    GamePanel gp;

    MouseHandler(GamePanel gp){
        this.gp = gp;
    }

    public int xTileClicked, yTileClicked;
    private int xClicked, yClicked;

    @Override
    public void mouseClicked(MouseEvent e) {
        xClicked = e.getX();
        yClicked = e.getY();

        xTileClicked = xClicked / gp.tileSize;
        yTileClicked = 8 - (yClicked / gp.tileSize);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
