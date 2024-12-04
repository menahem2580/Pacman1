package PacMan;

import javax.swing.*;
import java.awt.*;
import java.beans.Visibility;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

public class MyJpanel extends JPanel implements Runnable {
    public ArrayList<Ghost> ghosts = new ArrayList<>();
    private ArrayList<Fruits> fruits = new ArrayList<>();
    Map map = new Map();
    public int w, h;
    public static int lives = 3;
    Random randomFruit = new Random();


    static pacmanGame pacmanGame;
    Thread start;
    MyKeyLes myKeyLes = new MyKeyLes();

    MyJpanel(int width, int height) {
        this.w = width;
        this.h = height;
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(myKeyLes);
        this.setFocusable(true);
        this.requestFocusInWindow();
        pacmanGame = new pacmanGame(this, myKeyLes, 1, 1);
        addGhosts();
        startGame();
        addFruits();

    }

    protected void printBoard(Graphics g) {

        int height = h / map.map.length;
        int width = w / map.map[0].length;

        height = 30;

        for (int i = 0; i < map.map.length; i++) {
            for (int j = 0; j < map.map[i].length; j++) {

                if (map.map[i][j] == 1) {
                    ImageIcon imageCoin = new ImageIcon("src/photos/Empty.jpg");
                    g.drawImage(imageCoin.getImage(), j * width, i * height, width, height, this);

                } else if (map.map[i][j] == 2) {
                    ImageIcon imageCoin = new ImageIcon("src/photos/istockphoto-1365160882-612x612.jpg");
                    g.drawImage(imageCoin.getImage(), j * width, i * height, width, height, this);

                } else if (map.map[i][j] == 3) {
                    ImageIcon imageCoin = new ImageIcon("src/photos/HD-wallpaper-dr-strange-circle-cool-dr-strange-fireworks-nlack-sparkler-sparks-yellow-thumbnail.jpg");
                    g.drawImage(imageCoin.getImage(), j * width, i * height, width, height, this);
                }
            }
            g.setColor(Color.RED);
            g.setFont(new Font("", Font.BOLD, 35));
            g.drawString("lives: " + lives, 650, 910);

        }
    }

    public void startGame() {
        start = new Thread(this);
        start.start();
    }

    @Override
    public void run() {
        while (start != null) {
            update();
            repaint();
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void addGhosts() {
        ghosts.add(new Ghost(12, 13, "/photos/ghost-blinky-down.png", "/photos/ghost-Apple.png", this));
        ghosts.add(new Ghost(14, 13, "/photos/ghost-clyde-down.png", "/photos/ghost-blinky-down.png", this));
        ghosts.add(new Ghost(15, 13, "/photos/ghost-inky-left.png", "/photos/ghost-clyde-down.png", this));
        ghosts.add(new Ghost(13, 13, "/photos/ghost-pinky-down.png", "/photos/ghost-blinky-down.png", this));

    }

    public void addFruits() {

        fruits.add(new Fruits(5, 5, "/photos/תפוז.png", 500, 4000,3000));
        fruits.add(new Fruits(21, 20, "/photos/תות.png", 300,  1000, 2000));
        fruits.add(new Fruits(6, 27, "/photos/Apple.png", 700, 3 * 1000, 5000));
        fruits.add(new Fruits(25, 8, "/photos/cherry-removebg-preview.png", 100, 5 * 1000, 10 * 1000));
    }

    public void update() {
        pacmanGame.update(map);

        for (Ghost ghost : ghosts) {
            ghost.update(map);
            if (ghost.isCollidingWith(pacmanGame) && lives >= 0) {
                if (pacmanGame.big) {
                    ghost.x = ghost.startX;
                    ghost.y = ghost.startY;
                }
                if (!pacmanGame.big) {
                    lives--;
                    massgeLives();
                    for (Ghost ghost1 : ghosts) {
                        ghost1.x = ghost1.startX;
                        ghost1.y = ghost1.startY;
                    }
                    pacmanGame.myKeyLes.dir = -1;
                    pacmanGame.x = pacmanGame.startX;
                    pacmanGame.y = pacmanGame.startY;
                }
            }
            if (lives == 0) {
                JOptionPane.showMessageDialog(new JOptionPane(), "GAME OVER!!! " + "your score = " + pacmanGame.score);
                System.exit(0);
            }
        }
        for (Fruits fruit : fruits) {
            if (fruit.isCollidingWith(pacmanGame)) {
                pacmanGame.score += fruit.score;
                fruit.collect();

            //pacmanGame.increaseScore(100);
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        printBoard(g);

        Graphics2D g2 = (Graphics2D) g;
        pacmanGame.draw(g2);

        for (Ghost ghost : ghosts) {
            ghost.draw(g2);
        }
        for (Fruits fruit : fruits) {
            fruit.draw((Graphics2D) g);
        }
    }

    public int getLives() {
        return lives;
    }

    public static void massgeLives() {
        JOptionPane.showMessageDialog(new JOptionPane(), "Life remains: = " + lives);

    }
}
