package main.java.Algorithms;

import main.java.GridWorld;
import main.java.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Map;

import main.java.Algorithms.AlgorithmHelper;

public class BFS implements Algorithm {

    @Override
    public void findPath(GridWorld gridworld) {
        System.out.println("BFS Path");

        boolean goalFound = false;

        Position start = gridworld.start;
        int[][] grid = gridworld.grid;
        Position goal = null;

        // node, prev
        HashMap<Position, Position> visited = new LinkedHashMap<>();
        Queue<Position> queue = new LinkedList<>();

        queue.offer(start);
        visited.put(start, null);
        
        while(!queue.isEmpty() && !goalFound) {
            Position curr = queue.poll();
            
            for (Position p : AlgorithmHelper.getSurroundingBoxes(gridworld, curr)) {
                // if not null, not in visited, not a wall
                if (p != null && !visited.containsKey(p) && grid[p.getX()][p.getY()] != 1 ) {
                    if (grid[p.getX()][p.getY()] == 2) {
                        visited.put(p,curr); // might need to deleted TODO
                        goal = p;
                        goalFound = true;
                    }
                    queue.offer(p);
                    visited.put(p, curr);
                }
            }
        }

        // printing the path
        // for (Map.Entry<Position, Position> p : visited.entrySet()) {
        //     System.out.println(p.getKey() + " " + p.getValue());
        // }

        if (goalFound && goal != null) {
            System.out.println("Goal Found");
            Position p = goal;
            ArrayList<Position> result = new ArrayList<>();
            System.out.println("Shortest path to goal: ");
            while (p != null) {
                System.out.println(p);
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


