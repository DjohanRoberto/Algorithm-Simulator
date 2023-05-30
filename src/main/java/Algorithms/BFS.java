package main.java.Algorithms;

import main.java.GridWorld;
import main.java.Position;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Map;

import main.java.Algorithms.AlgorithmHelper;

public class BFS implements Algorithm {

    @Override
    public void findPath(GridWorld gridworld) {
        System.out.println("BFS Path");

        Position start = gridworld.start;

        int[][] grid = gridworld.grid;

        // <node, prev>
        Map<Position, Position> visited = new LinkedHashMap<>(); 
        Queue<Position> queue = new LinkedList<>();
        queue.add(start);

        boolean goalFound = false;
        
        Position curr = null;

        while (!queue.isEmpty() && !goalFound) {
            Position prev = curr;
            curr = queue.poll();

            visited.put(curr, prev);
            Position [] aroundCurr = AlgorithmHelper.getSurroundingBoxes(gridworld, curr);

            if (grid[curr.getX()][curr.getY()] == 2) {
                goalFound = true;
            }

            // adding surrounding positions to queue
            for (Position p : aroundCurr) {
                // if p is not null, a wall or already visited, then add to queue
                if (p != null && grid[p.getX()][p.getY()] != 1 && !visited.containsKey(p)) {
                    queue.add(p);
                }
            }
        }
        for (Map.Entry<Position, Position> element : visited.entrySet()) {
            Position node = element.getKey();
            Position prev = element.getValue();

            if (prev == null) {
                System.out.println("(start) => " + node.toString());
            } else {
                System.out.println(prev.toString() + " => " + node.toString() + ", ");
            }
        }
        if (goalFound) {
            System.out.println("Goal Found");
        } else {
            System.out.println("Goal Not Fount");
        }
        // return visited;
        // show final path to goal
    }
}
