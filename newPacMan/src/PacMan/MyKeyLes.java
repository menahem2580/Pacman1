package PacMan;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyLes extends Element implements KeyListener {


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_UP:
                dir = UP;
                break;
            case KeyEvent.VK_DOWN:
                dir = DOWN;
                break;
            case KeyEvent.VK_LEFT:
                dir = LEFT;
                break;
            case KeyEvent.VK_RIGHT:
                dir = RIGHT;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }


}