package Chess.Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {

    GamePanel gp;

    MouseHandler(GamePanel gp){
        this.gp = gp;
    }

    public int xClicked, yClicked;

    @Override
    public void mouseClicked(MouseEvent e) {
        xClicked = e.getX();
        yClicked = e.getY();


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
