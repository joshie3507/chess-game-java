package Chess.Board;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    public final int screenSize = 800;
    public final int numTiles = 10;
    public final int tileSize = screenSize / numTiles;

    int FPS = 20;

    Thread gameThread;

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

        g.setColor(Color.WHITE);

        for (int i = 0; i < numTiles; i++){
            for (int j = 0; j < numTiles; j++){
                if ((j + i) % 2 == 0){
                    g.fillRect(j * tileSize, i * tileSize, tileSize, tileSize);
                }
            }
        }


    }
}
