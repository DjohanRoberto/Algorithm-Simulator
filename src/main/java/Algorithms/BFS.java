package main.java.Algorithms;

import main.java.GridWorld;
import main.java.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
import java.util.Map;

import main.java.Algorithms.AlgorithmHelper;

public class BFS implements Algorithm {

    @Override
    public void findPath(GridWorld gridworld) {
        System.out.println("BFS Path");

        Position start = gridworld.start;

        int[][] grid = gridworld.grid;

        List<Position> visited = new ArrayList<>(); 
        Queue<Position> queue = new LinkedList<>();
        queue.add(start);

        boolean goalFound = false;
        
        while (!queue.isEmpty() && !goalFound) {
            Position curr = queue.poll();
            if (visited.contains(curr)) {
                continue;
            }

            visited.add(curr);
            Position [] aroundCurr = AlgorithmHelper.getSurroundingBoxes(gridworld, curr);

            if (grid[curr.getX()][curr.getY()] == 2) {
                goalFound = true;
            }

            // adding surrounding positions to queue
            for (Position p : aroundCurr) {
                // if p is not null, a wall or already visited, then add to queue
                if (p != null && grid[p.getX()][p.getY()] != 1 && !visited.contains(p)) {
                    queue.add(p);
                }
            }


        }
        visited.toArray();
        for (Position p : visited) {
            System.out.print("(" + p.getX() + ", " + p.getY() + "), ");
        }
        // return visited;
    }
}
