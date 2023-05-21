package main.java;

import main.java.JFrame.MainFrame;

public class Main {
    public static void main(String[] args) {
        
        Position start =  new Position(0, 0);
        Position goal = new Position(4, 1);
        Position [] walls = null;
        GridWorld grid = new GridWorld(5, 5, start, goal, walls, null);

        MainFrame main = new MainFrame(grid);
    }
}
