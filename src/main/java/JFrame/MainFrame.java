package main.java.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import main.java.Position;
import main.java.GridWorld;

public class MainFrame {
    public MainFrame() {
        JFrame main = new JFrame();
        main.setSize(500, 500);
        main.getContentPane().setBackground(new Color(200, 200, 200));
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setLayout(new BorderLayout());

        JPanel north = new JPanel();
        north.setBackground(Color.red);
        north.setPreferredSize(new Dimension(100, 50));

        JPanel south = new JPanel();
        south.setBackground(Color.blue);
        south.setPreferredSize(new Dimension(100, 100));

        JPanel east = new JPanel();
        east.setBackground(Color.yellow);
        east.setPreferredSize(new Dimension(50, 100));

        JPanel west = new JPanel();
        west.setBackground(Color.green);
        west.setPreferredSize(new Dimension(50, 100));

        JPanel center = new JPanel();
        center.setBackground(new Color(230, 230, 230));
        center.setPreferredSize(new Dimension(100, 100));

        JLabel title = new JLabel("Algorithm Simulator");
        north.add(title);


        main.add(north, BorderLayout.NORTH);
        main.add(south, BorderLayout.SOUTH);
        main.add(east, BorderLayout.EAST);
        main.add(west, BorderLayout.WEST);
        main.add(center, BorderLayout.CENTER);


        main.setVisible(true);
    }

    public void initGrid() {
        Position start =  new Position(0, 0);
        Position goal = new Position(4, 1);
        Position [] walls = null;
        GridWorld grid = new GridWorld(5, 5, start, goal, walls, null);
    }
}