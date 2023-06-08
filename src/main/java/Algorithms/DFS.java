package main.java.Algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import main.java.GridWorld;
import main.java.Position;
import main.java.Algorithms.AlgorithmHelper;

public class DFS implements Algorithm{
    
    @Override
    public void findPath(GridWorld gridworld) {
        System.out.println("DFS Path");

        Position start = gridworld.start;

        int[][] grid = gridworld.grid;

        // node, prev
        Map<Position, Position> visited = new LinkedHashMap<>();
        ArrayList<Position> stack = new ArrayList<>();

        Position goal = null;

        boolean goalFound = false;  
        stack.add(start);
        visited.put(start, null);

        while (!stack.isEmpty() && !goalFound) {
            Position curr = stack.get(0);
            stack.remove(0);
            System.out.println(curr);
            for (Position p : AlgorithmHelper.getSurroundingBoxes(gridworld, curr)) {
                if (p != null && !visited.containsKey(p) && grid[p.getX()][p.getY()] != 1) {
                    if (grid[p.getX()][p.getY()] == 2) {
                        goalFound = true;
                        goal = p;
                    }
                    stack.add(0, p);
                    visited.put(p, curr);
                }
            }
        }

        if (goalFound && goal != null) {
            System.out.println("Goal Found");
            Position p = goal;
            ArrayList<Position> result = new ArrayList<>();
            while (p != null) {
                result.add(p);
                p = visited.get(p);
            }
            Collections.reverse(result);
            for (Position pos : result) {
                System.out.print(pos + " -> ");
            }
        }
    }  
}
