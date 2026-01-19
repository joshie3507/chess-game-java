package Chess.Game;

import Chess.Board.TileManager;
import Chess.Piece.BlackPieceManager;
import Chess.Piece.*;
import Chess.Piece.WhitePieceManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class GamePanel extends JPanel implements Runnable{

    public final int screenSize = 800; // screen length and height in pixels - screen is a square
    public final int numTiles = 8; // number of tiles on the board
    public final int tileSize = screenSize / numTiles; // the size of each tile in pixels
    public int[] tileClicked; // the tile that was last clicked by the user
    public Piece currentlySelected = null; // the piece that was on the last selected tile
    public boolean isWhitesTurn = true; // used to determine if it is white's or black's turn


    int FPS = 60; // Frames Per Second

    public TileManager tileM = new TileManager(this); // handles all logic to do with tiles
    public WhitePieceManager whitePieceManager = new WhitePieceManager(this); // handles all logic with white pieces
    public BlackPieceManager blackPieceManager = new BlackPieceManager(this); // handles all logic with black pieces
    MouseHandler mouseH = new MouseHandler(this); // handles mouse clicks

    public HashMap<Position, Piece> whitePieces;
    public HashMap<Position, Piece> blackPieces;

    Thread gameThread;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenSize, screenSize));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addMouseListener(mouseH);

        whitePieces = whitePieceManager.pieces;
        blackPieces = blackPieceManager.pieces;

        whitePieceManager.initPieceTeams();
        blackPieceManager.initPieceTeams();

        whitePieceManager.setAvailableMoves();
        blackPieceManager.setAvailableMoves();

    }


    /**
     * method initialises the game thread and starts it
     */
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
        System.out.println("got here");
    }

    /**
     * method runs each frame of the game and ensures a maximum of set FPS
     */
    @Override
    public void run() {


        double drawInterval = (double) 1000000000/FPS; // set interval between each frame
        double nextDrawTime = System.nanoTime() + drawInterval; // the exact time when the next frame should be drawn

        boolean isWhiteTurnBefore, isWhiteTurnAfter; // used to determine if the current player has made their turn so each piece's
                                                     // available moves are only update when a piece is moved

        while (gameThread != null){

            isWhiteTurnBefore = isWhitesTurn;

            update();

            isWhiteTurnAfter = isWhitesTurn;

            if (isWhiteTurnBefore != isWhiteTurnAfter){
                whitePieceManager.setAvailableMoves();
                blackPieceManager.setAvailableMoves();
            }

            repaint();

            // figure out how long is left until next frame
            double remainingTime = nextDrawTime - System.nanoTime();

            // cap remaining time at 0
            if (remainingTime < 0){
                remainingTime = 0;
            }

            // sleep until next frame is to be drawn
            try{
                Thread.sleep( (long) (remainingTime / 1000000));
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            // calculate when next frame should be drawn
            nextDrawTime += drawInterval;
        }

    }

    /**
     * method runs core game logic, updating all pieces and only allowing pieces to move if it is their turn
     */
    private void update() {
        tileClicked = getTileClicked();


        if (isWhitesTurn) {
            whitePieceManager.updatePieces();
        } else {
            blackPieceManager.updatePieces();
        }

    }

    /**
     * method checks which tile has been clicked by the mouse
     *
     * @return -> the co-ordinates of the tile clicked
     */
    private int[] getTileClicked() {
        int x = mouseH.xClicked / tileSize;
        int y = 7 - (mouseH.yClicked / tileSize);
        return new int[] {x, y};
    }

    /**
     * method draws all pieces and tiles to the screen
     *
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tileM.drawTiles(g2);

        tileM.highlightTiles(g2);

        whitePieceManager.drawWhitePieces(g2);
        blackPieceManager.drawBlackPieces(g2);
    }
}
