package main.java.Algorithms;

import main.java.GridWorld;
import main.java.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BFS implements Algorithm {

    @Override
    public void findPath(GridWorld grid) {
        System.out.println("BFS Path");

        Position start = grid.start;

        Map<Position, Integer> visited = new HashMap<>(); 
        List<Position> queue = new ArrayList<>();

        
    }
}
