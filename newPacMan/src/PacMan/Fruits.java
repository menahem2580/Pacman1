package PacMan;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Fruits extends Player {
    BufferedImage fruitImage;
    boolean isVisible = false;
//    boolean isTimerStart = false;
    boolean isTimerFinished = false;
    Random random = new Random();
    long ms;
    long disMs;
    Map map;


    public Fruits(int x, int y, String path, int score, long ms, long disMs) {
        this.x = x * size;
        this.score = score;
        this.y = y * size;
        this.ms = ms;
        this.disMs = disMs;

        loadImage(path);
        startFruitTimer();
    }

    public void loadImage(String path) {
        try {
            fruitImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    private void setRandomPosition() {
//        int rows = map.map.length;
//        int cols = map.map[0].length;
//
//        while (true) {
//            int randomX = random.nextInt(cols);
//            int randomY = random.nextInt(rows);
//
//            if (map.map[randomY][randomX] == 1) { // תא חוקי (למשל, ללא קירות)
//                this.x = randomX * size;
//                this.y = randomY * size;
//                break;
//            }
//        }
//    }
    private void startFruitTimer() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                isVisible = true;
                startDisappearTimer();
            }
        }, ms + random.nextInt(5000));
    }

    private void startDisappearTimer() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                isVisible = false;
                startFruitTimer();
            }
        }, disMs);
    }

    public void draw(Graphics2D g2) {
        if (isVisible  && fruitImage != null) {
            g2.drawImage(fruitImage, x, y, size, size, null);

        }
    }

    public boolean isCollidingWith(pacmanGame pacman) {
        return isVisible &&
                Math.abs(x - pacman.x) < size &&
                Math.abs(y - pacman.y) < size;
    }

    public void collect() {
        isVisible = false;
        startFruitTimer();
        startFruitTimer();
    }

    public boolean isVisible() {
        return isVisible;
    }
}
