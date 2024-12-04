package PacMan;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Ghost extends Player {
    private Random random = new Random();
    private MyJpanel myJpanel;
    BufferedImage ghostImage;
    private BufferedImage edibleImage;


    public Ghost(int startX, int startY, String image, String edibleImagePath, MyJpanel jpanel) {
        this.myJpanel = jpanel;
        this.startX = this.x = startX * size;
        this.startY = this.y = startY * size;
        loadImageG(image);
        loadEdibleImage(edibleImagePath);


        directionGhost = random.nextInt(4);
    }

    public void update(Map map) {
        int newX = x;
        int newY = y;

        switch (directionGhost) {
            case 0:
                newY -= speedGhost;
                break;
            case 1:
                newX += speedGhost;
                break;
            case 2:
                newY += speedGhost;
                break;
            case 3:
                newX -= speedGhost;
                break;
        }
        if (isValidMove(map, newX, newY)) {
            x = newX;
            y = newY;
        } else {
            directionGhost = random.nextInt(4);
        }
    }

    public void loadImageG(String path) {
        try {
            ghostImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadEdibleImage(String path) {
        try {
            edibleImage = ImageIO.read(new File("C:\\Users\\JBH\\IdeaProjects\\newPacMan\\src\\photos\\pacman-145845_1280.png"));
//            edibleImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        if (MyJpanel.pacmanGame.big) {
            g2.drawImage(edibleImage, x, y, size, size, myJpanel);
        } else {
            g2.drawImage(ghostImage, x, y, size, size, myJpanel);
        }
    }

    public boolean isCollidingWith(pacmanGame pacmanGame) {
        return Math.abs(x - pacmanGame.x) < size - 10 && Math.abs(y - pacmanGame.y) < size - 10;
    }

}
