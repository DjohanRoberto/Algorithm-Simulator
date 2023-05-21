package main.java.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import main.java.GridWorld;
import main.java.Position;

public class MainFrame extends JFrame implements MouseListener{

    private boolean mouseDown = false;
    private boolean isWall;
    private GridWorld grid;
    private GridElement [][] gridPanels;

    public MainFrame(GridWorld grid) {
        this.grid = grid;
        this.setSize(500, 500);
        this.getContentPane().setBackground(new Color(200, 200, 200));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        Border border = BorderFactory.createLineBorder(Color.black, 2);

        JPanel north = new JPanel();
        north.setBackground(new Color(200, 200, 200));
        north.setPreferredSize(new Dimension(100, 30));
        north.setBorder(border);

        JPanel south = new JPanel();
        south.setBackground(Color.blue);
        south.setPreferredSize(new Dimension(100, 100));
        south.setBorder(border);

        JPanel east = new JPanel();
        east.setBackground(new Color(220, 220, 220));
        east.setPreferredSize(new Dimension(50, 100));
        east.setBorder(border);

        JPanel west = new JPanel();
        west.setBackground(new Color(220, 220, 220));
        west.setPreferredSize(new Dimension(50, 100));
        west.setBorder(border);

        JPanel center = new JPanel();
        center.setBackground(new Color(230, 230, 230));
        center.setPreferredSize(new Dimension(100, 100));
        center.setBorder(border);

        JLabel title = new JLabel("Algorithm Simulator");
        north.add(title);

        // adding grid
        center.setLayout(new GridLayout(this.grid.height, this.grid.width));
        center.setBackground(Color.gray);

        this.gridPanels = new GridElement[this.grid.height][this.grid.width];

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
        GridElement element = (GridElement) e.getSource();
        if (SwingUtilities.isRightMouseButton(e)) {
            // change start position
            Position startPos = this.grid.start;

            this.gridPanels[startPos.getX()][startPos.getY()].setBackground(Color.white);
            this.grid.changeStart(element.cords);
            this.gridPanels[element.cords.getX()][element.cords.getY()].setBackground(Color.red);

        } else {
            mouseDown = true;
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