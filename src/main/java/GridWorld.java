package main.java;
/*
 * Simulation Environment
 * Grid of size x, y
 * 
 * Content of grid
 * 0 = empty
 * 1 = wall
 * 2 = goal
 * 3 = player
 */
public class GridWorld {
    
    public int x;
    public int y;
    public Position start;

    public int[][] grid;

    public GridWorld(int x, int y, Position start, Position goal, Position [] walls) {
        this.x = x;
        this.y = y;
        this.grid = new int[x][y];
        for (int i = 0; i < x; i ++) {
            for (int j = 0; j < y; j++) {
                // initialise grid as all zero
                grid[i][j] = 0;
            }
        }
        // put player in start position
        this.grid[start.x][start.x] = 3;
        // initialise goal
        this.grid[goal.x][goal.y] = 2;
        // place walls on the map
        for (Position wall : walls) {
            grid[wall.x][wall.y] = 1;
        }
    }

    // if the position given is empty, place a wall
    // do not place wall if not empty i.e player or goal occupying
    public void placeWall(Position newWall) {
        if (this.grid[newWall.x][newWall.y] == 0) {
            this.grid[newWall.x][newWall.y] = 1;
        }
    }

    // if the position given is a wall, remove it
    // do not replace if not a wall i.e player or goal occupying
    public void removeWall(Position wall) {
        if (this.grid[wall.x][wall.y] == 1) {
            this.grid[wall.x][wall.y] = 0;
        }
    }

    public void changeStart() {

    }
}
