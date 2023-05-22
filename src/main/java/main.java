package main.java;

import main.java.JFrame.MainFrame;

public class Main {
    public static void main(String[] args) {
        GridWorld grid = new GridWorld(7, 7);

        MainFrame main = new MainFrame(grid);
    }
}
