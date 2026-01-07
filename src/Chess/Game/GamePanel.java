package Chess.Game;

import Chess.Board.TileManager;
import Chess.Piece.BlackPieceManager;
import Chess.Piece.WhitePieceManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    public final int screenSize = 800;
    public final int numTiles = 8;
    public final int tileSize = screenSize / numTiles;


    int FPS = 20;

    Thread gameThread;

    public TileManager tileM = new TileManager(this);
    WhitePieceManager whitePieceManager = new WhitePieceManager(this);
    BlackPieceManager blackPieceManager = new BlackPieceManager(this);

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenSize, screenSize));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = (double) 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null){

            update();

            repaint();

            double remainingTime = nextDrawTime - System.nanoTime();

            if (remainingTime < 0){
                remainingTime = 0;
            }

            try{
                Thread.sleep( (long) (remainingTime / 1000000));
            } catch (InterruptedException e){
                e.printStackTrace();
            }

            nextDrawTime += drawInterval;
        }

    }

    private void update() {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tileM.drawTiles(g2);

        whitePieceManager.drawWhitePieces(g2);
        blackPieceManager.drawBlackPieces(g2);


    }
}
