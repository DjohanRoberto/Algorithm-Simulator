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
    public Position playerPos;
    public Agent player;

    public int[][] grid;

    public GridWorld(int x, int y, Position start, Position goal, Position [] walls) {
        this.x = x;
        this.y = y;
        this.start = start;
        this.playerPos = start;
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

    // change start position of the agent and places agent there
    // sets prev start to empty
    public void changeStart(Position newStart) {
        this.grid[this.start.x][this.start.y] = 0;
        this.start = newStart;
        this.playerPos = newStart;
        this.grid[this.start.x][this.start.y] = 3;
    }

    // moves player to new position by adding x and y to current player position
    // sets previous player position to empty
    public void movePlayer(int x , int y) {
        this.grid[this.playerPos.x][this.playerPos.y] = 0;
        this.playerPos = new Position(this.playerPos.x + x, this.playerPos.y + y);
        this.grid[this.playerPos.x][this.playerPos.y] = 3;
    }

    // resets all walls to empty and relocate player to starting position
    public void resetGrid() {
        for (int i = 0 ; i < this.x; i++) {
            for (int j = 0; j < this.y; j++) {
                this.grid[i][j] = 0;
            }
        }
        this.grid[this.start.x][this.start.y] = 3;
    }
}
