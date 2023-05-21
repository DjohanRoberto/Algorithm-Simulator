package main.java.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.GridWorld;

public class MainFrame extends JFrame implements MouseListener{

    private boolean mouseDown = false;
    private boolean isWall;
    private GridWorld grid;

    public MainFrame(GridWorld grid) {
        this.grid = grid;
        this.setSize(500, 500);
        this.getContentPane().setBackground(new Color(200, 200, 200));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        JPanel north = new JPanel();
        north.setBackground(Color.red);
        north.setPreferredSize(new Dimension(100, 30));

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

        // adding grid
        center.setLayout(new GridLayout(this.grid.height, this.grid.width, 1, 1));
        center.setBackground(Color.gray);

        GridElement [][] gridPanels = new GridElement[this.grid.height][this.grid.width];

        for (int i = 0; i < this.grid.width; i++) {
            for (int j = 0; j < this.grid.height; j++) {
                gridPanels[i][j] = new GridElement(i, j, this.grid.grid[i][j]);
                gridPanels[i][j].addMouseListener(this);
                center.add(gridPanels[i][j]);
            }
        }

        this.add(north, BorderLayout.NORTH);
        this.add(south, BorderLayout.SOUTH);
        this.add(east, BorderLayout.EAST);
        this.add(west, BorderLayout.WEST);
        this.add(center, BorderLayout.CENTER);

        this.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseDown = true;
        GridElement element = (GridElement) e.getSource();
        if (element.getBackground() == Color.white) {
            isWall = false;
            element.setBackground(Color.black);
            this.grid.placeWall(element.cords);
        } else if (element.getBackground() == Color.black) {
            isWall = true;
            element.setBackground((Color.white));
            this.grid.removeWall(element.cords);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        mouseDown = false;
        this.grid.printGrid();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        GridElement element = (GridElement) e.getSource();
        if (mouseDown) {
            if (element.getBackground() == Color.white && !isWall) {
                element.setBackground(Color.black);
                grid.placeWall(element.cords);
            } else if (element.getBackground() == Color.black && isWall) {
                element.setBackground((Color.white));
                grid.removeWall(element.cords);
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }
    
}