package Chess;

import Chess.Game.GamePanel;

import javax.swing.*;

public class Main {

    /**
     * runnable file that brings all parts together
     * @param args -> none needed
     */
    public static void main(String[] args){
         JFrame window = new JFrame();
         window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         window.setResizable(false);
         window.setTitle("Chess");

        GamePanel gp = new GamePanel();
        window.add(gp);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gp.startGameThread();
    }
}
