package main.java;

import main.java.Algorithms.Algorithm;

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
    
    public int width;
    public int height;
    public Position start;
    public Player player;

    public int[][] grid;

    public GridWorld(int width, int height, Position start, Position goal, Position [] walls, Algorithm alg) {
        this.width = width;
        this.height = height;
        this.start = start;
        this.grid = new int[width][height];
        this.player = new Player(start, alg);
        
        this.initGrid();
        
        // put player in start position
        this.grid[start.getX()][start.getY()] = 3;
        // initialise goal
        this.grid[goal.getX()][goal.getY()] = 2;
        // place walls on the map
        if (walls != null) {
            for (Position wall : walls) {
                grid[wall.getX()][wall.getY()] = 1;
            }
        }
    }

    // initialise the grid to all 0
    public void initGrid() {
        // initialise grid as all zero
        for (int i = 0; i < this.width; i ++) {
            for (int j = 0; j < this.height; j++) {
                this.grid[i][j] = 0;
            }
        }
    }

    // changes and reinitialises grid
    public void changeDimensions(int x, int y) {
        this.width = x;
        this.height = y;
        this.initGrid();
    }

    // if the position given is empty, place a wall
    // do not place wall if not empty i.e player or goal occupying
    public void placeWall(Position newWall) {
        if (this.grid[newWall.getX()][newWall.getY()] == 0) {
            this.grid[newWall.getX()][newWall.getY()] = 1;
        }
    }

    // if the position given is a wall, remove it
    // do not replace if not a wall i.e player or goal occupying
    public void removeWall(Position wall) {
        if (this.grid[wall.getX()][wall.getY()] == 1) {
            this.grid[wall.getX()][wall.getY()] = 0;
        }
    }

    // change start position of the agent and places agent there
    // sets prev start to empty
    public void changeStart(Position newStart) {
        this.grid[this.start.getX()][this.start.getY()] = 0;
        this.start = newStart;
        this.player.setPos(newStart);
        this.grid[newStart.getX()][newStart.getY()] = 3;
    }

    public Position playerPos() {
        return this.player.getPos();
    }

    // resets all walls to empty and relocate player to starting position
    public void resetGrid() {
        for (int i = 0 ; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                this.grid[i][j] = 0;
            }
        }
        this.grid[this.start.getX()][this.start.getY()] = 3;
        this.player.setPos(this.start);
    }

    // development function to check grid
    public void printGrid() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                System.out.print(this.grid[i][j]);
            }
            System.out.println();
        }
        System.out.println("------------------------");
    }
}
