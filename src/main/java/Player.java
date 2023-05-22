package main.java;

import main.java.Algorithms.Algorithm;

public class Player {

    private Algorithm alg = null;
    private Position pos;

    public Player(Position pos, Algorithm alg) {
        this.pos = pos;
        this.alg = alg;
    }

    public Position getPos() {
        return this.pos;
    }

    public Algorithm getAlg() {
        return this.alg;
    }

    public void setAlg(Algorithm alg) {
        this.alg = alg; 
    }
    
    public void setPos(Position newPos) {
        this.pos = newPos;
    }

    public void findPath(GridWorld grid) {
        alg.findPath(grid);
    }

    // move the player by translating by the given value on x and y
    public void doMove(int x, int y) {

    }
}
