package main.java;

import main.java.Algorithms.Algorithm;
import main.java.JFrame.MainFrame;

import main.java.Algorithms.BFS;
import main.java.Algorithms.DFS;
import main.java.Algorithms.Dijkstra;
import main.java.Algorithms.RandomWalk;
import main.java.Algorithms.MST;

public class Main {
    public static void main(String[] args) {
        GridWorld grid = new GridWorld(7, 7);
        
        MainFrame main = new MainFrame(grid);
    }
}
