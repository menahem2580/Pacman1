package PacMan;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class pacmanGame extends Player {
    MyJpanel myJpanel;
    MyKeyLes myKeyLes;
    BufferedImage up, down, left, right, start;

    public pacmanGame(MyJpanel myJpanel, MyKeyLes myKeyLes, int startX, int startY) {
        this.myJpanel = myJpanel;
        this.myKeyLes = myKeyLes;
        this.startX = this.x = startX * size;
        this.startY = this.y = startY * size;
        getImage();

    }

    public void getImage() {
        try {
            up = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/photos/pacman-open-up.png")));
            down = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/photos/pacman-open-down.png")));
            left = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/photos/pacman-open-left.png")));
            right = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/photos/pacman-open-right.png")));
            start = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/photos/pacman-open-right.png")));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g2) {


        switch (direction) {
            case UP -> image = up;
            case DOWN -> image = down;
            case LEFT -> image = left;
            case RIGHT -> image = right;
            case START -> image = start;

        }
        g2.drawImage(image, x, y, size, size, null);
        g2.setColor(Color.BLUE);
        g2.setFont(new Font("Menahem", Font.BOLD, 35));
        g2.drawString("score: " + score, 50, 910);
    }

    public void changeDirection() {
        int newX = x;
        int newY = y;
        if (myKeyLes.dir == UP) newY -= speedPacMan;
        if (myKeyLes.dir == DOWN) newY += speedPacMan;
        if (myKeyLes.dir == LEFT) newX -= speedPacMan;
        if (myKeyLes.dir == RIGHT) newX += speedPacMan;
        if (!isValidMove(myJpanel.map, newX, newY)) {
            return;
        }
        direction = myKeyLes.dir;
    }

    public void move(Map map) {
        int newX = x;
        int newY = y;
        if (direction == UP) newY -= speedPacMan;
        if (direction == DOWN) newY += speedPacMan;
        if (direction == LEFT) newX -= speedPacMan;
        if (direction == RIGHT) newX += speedPacMan;

        if (newX < 0) { // יציאה מצד שמאל
            newX = map.map[0].length * size - size; // מעבר לצד ימין
        } else if (newX == (map.map[0].length - 1) * size) { // יציאה מצד ימין
            newX = 0; // מעבר לצד שמאל
        }

        if (isValidMove(map, newX, newY)) {
            x = newX;
            y = newY;
        }
    }

    public void update(Map map) {

        changeDirection();
        move(map);

        int mapX = (x + size / 2) / size;
        int mapY = (y + size / 2) / size;

        if (map.map[mapY][mapX] == 2) {
            map.map[mapY][mapX] = 0;
            score += 10;
        } else if (map.map[mapY][mapX] == 3) {
            map.map[mapY][mapX] = 0;
            score += 200;
            big = true;

            score += 500;
            new Timer(10000, e -> {
                big = false;
            }).start();
        }
    }

//    public int getScore() {
//        return score;
//    }

//    public void increaseScore(int points) {
//        score += points;
//    }
}
