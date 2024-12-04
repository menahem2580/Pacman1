package PacMan;

import java.awt.*;

public abstract class Element {
    public static  final int START = -1, UP =0,DOWN = 1, RIGHT = 2, LEFT = 3;
    int row = 31;
    int col = 28;
    protected int x;
    protected int y;
    int startX;
    int startY;
    int direction = START;
    int directionGhost;
    protected int size = 30;
    int speedGhost = 2;
    int speedPacMan = 3;
    Image image;
    int dir = LEFT;
    public boolean big;
    int score = 0;
    Map map;
}
