package PacMan;

public abstract class Player extends Element {

    public Player() {
    }

    public boolean isValidMove(Map map, int newX, int newY) {

        int endX = newX + 29, endY = newY + 29;// גודל הפקמן במיקום הבא
        int mapX = newX / size; // מיקום התחלת הפקמן במיקום הבא
        int mapY = newY / size;

        int mapEndX = endX / size; // מיקום סוף הפקמן במיקום הבא
        int mapEndY = endY / size;

        if (newX < 0 || newY < 0 || newX >= map.map[0].length * size || newY >= map.map.length * size) {
            return true; // מעבר חוקי במקרה של מעבר קצוות
        } else if (mapY >= 0 && mapY <= map.map.length &&
                mapX >= 0 && mapX < map.map[0].length &&
                map.map[mapY][mapX] != 1 && map.map[mapEndY][mapEndX] != 1 &&
                map.map[mapY][mapEndX] != 1 && map.map[mapEndY][mapX] != 1) {
            return true;
        }
        return false;
    }

}
