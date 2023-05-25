package main.java.Algorithms;

import main.java.GridWorld;
import main.java.Position;

public class AlgorithmHelper {
    public static Position [] getSurroundingBoxes(GridWorld gridworld, Position position) {

        Position [] result = new Position[4];

        int x = position.getX();
        int y = position.getY();

        int height = gridworld.height;
        int width = gridworld.width;

        // in order of up, left, down, right
        // if any of the directions are the edge of the map, fill with null
        
        // up
        if (y + 1 >= height) {
            result[0] = null;
        } else {
            result[0] = new Position(x, y + 1);
        }

        // right
        if (x + 1 >= width) {
            result[1] = null;
        } else {
            result[1] = new Position(x + 1, y);
        }

        // down
        if (y - 1 < 0) {
            result[2] = null;
        } else {
            result[2] = new Position(x, y - 1);
        }

        // left
        if (x - 1 < 0) {
            result[3] = null;
        } else {
            result[3] = new Position(x - 1, y);
        }

        return result;
    }
}
