package main.java;
public class Player {

    private Position pos;
    private Algorithm alg;

    public Player(Position pos, Algorithm alg) {
        this.pos = pos;
        this.alg = alg;
    }

    public Position getPos() {
        return this.pos;
    }

    // moves the player to a specified new position
    public void setPos(Position newPosition) {
        this.pos = newPosition;
    }

    // move the player by translating by the given value on x and y
    public void doMove(int x, int y) {
        this.pos.setX(this.pos.getX() + x);
        this.pos.setY(this.pos.getY() + y);
    }
}
