package main.java.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import main.java.GridWorld;
import main.java.Position;

import main.java.Algorithms.BFS;
import main.java.Algorithms.DFS;
import main.java.Algorithms.Dijkstra;
import main.java.Algorithms.RandomWalk;
import main.java.Algorithms.MST;

public class MainFrame extends JFrame implements MouseListener, ActionListener{

    JComboBox algChoose;
    JButton findPathButton;
    JButton changeDimensionWindow;
    JButton showCordsButton;
    JPanel center;
    JPanel south;

    private boolean mouseDown = false;
    private boolean isWall;
    private boolean isGoal = false;
    private boolean showCords = false;
    private GridWorld grid;
    private GridElement [][] gridPanels;

    public MainFrame(GridWorld grid) {
        this.grid = grid;
        this.setSize(900, 700);
        this.getContentPane().setBackground(new Color(200, 200, 200));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        Border border = BorderFactory.createLineBorder(Color.black, 1);

        JPanel north = new JPanel();
        north.setBackground(new Color(200, 200, 200));
        north.setPreferredSize(new Dimension(100, 40));
        north.setBorder(border);

        south = new JPanel();
        south.setBackground(new Color(200, 200, 200));
        south.setPreferredSize(new Dimension(100, 150));
        south.setBorder(border);

        // JPanel east = new JPanel();
        // east.setBackground(new Color(220, 220, 220));
        // east.setPreferredSize(new Dimension(50, 100));
        // east.setBorder(border);

        JPanel west = new JPanel();
        west.setBackground(new Color(200, 200, 200));
        west.setPreferredSize(new Dimension(200, 100));
        west.setBorder(border);

        center = new JPanel();
        center.setPreferredSize(new Dimension(100, 100));
        center.setBorder(border);

        JLabel title = new JLabel("Algorithm Simulator");
        title.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        north.add(title);

        // choose algorithm
        String[] algs = {"BFS", "DFS", "Dijkstra", "Random Walk","MST"};
        algChoose = new JComboBox<>(algs);
        algChoose.addActionListener(this);

        south.add(algChoose);

        // adding grid
        center.setBackground(Color.gray);
        this.initGridPanels();

        // find path button
        findPathButton = new JButton("Find Path");
        findPathButton.addActionListener(this);
        south.add(findPathButton);

        this.add(north, BorderLayout.NORTH);
        this.add(south, BorderLayout.SOUTH);
        // this.add(east, BorderLayout.EAST);
        this.add(west, BorderLayout.WEST);
        this.add(center, BorderLayout.CENTER);

        this.setVisible(true);

        // change dimension
        changeDimensionWindow = new JButton("Change Grid Dimensions", null);
        changeDimensionWindow.addActionListener(this);
        west.add(changeDimensionWindow);

        // show cords on grid
        showCordsButton = new JButton("Show Coords");
        showCordsButton.addActionListener(this);
        south.add(showCordsButton);
    }

    // initialises the grid panels
    private void initGridPanels() {

        center.removeAll();
        center.revalidate();
        center.repaint();

        center.setLayout(new GridLayout(this.grid.height, this.grid.width));

        this.gridPanels = new GridElement[this.grid.height][this.grid.width];
        for (int i = 0; i < this.grid.height; i++) {
            for (int j = 0; j < this.grid.width; j++) {
                gridPanels[i][j] = new GridElement(i, j, this.grid.grid[i][j]);
                gridPanels[i][j].addMouseListener(this);
                center.add(gridPanels[i][j]);
            }
        }
        this.gridPanels[this.grid.start.getX()][this.grid.start.getY()].setBackground(Color.red);
        this.gridPanels[this.grid.goal.getX()][this.grid.goal.getY()].setBackground(Color.green);

        if (showCords) {
            this.showCordsOnGrid();
        }
    }

    // changing the dimensions of the grid
    private void initChangeDimensionWindow() {
        JSpinner width = new JSpinner();
        JSpinner height = new JSpinner();

        Object[] message = {
            "Width", width,
            "Height", height,
            "Min = 4, Max = 30"
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Change Dimensions", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            int w = (int) width.getValue();
            int h = (int) height.getValue();

            if ((w > 3 && w <= 30) && (h > 3 && h <= 30)) {
                this.grid.changeDimensions(w, h);
                initGridPanels();
            } else {
                JOptionPane.showMessageDialog(this, "The numbers you entered are out of range", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // if show cords is on, then show the coordinates on each panel in the grid
    public void showCordsOnGrid() {
        for (GridElement [] row : this.gridPanels) {
            for (GridElement element : row) {
                int x = element.cords.getX();
                int y = element.cords.getY();
                JLabel cords = new JLabel("(" + x + ", " + y + ")");
                cords.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
                cords.setForeground(Color.black);
                element.add(cords);
            }
        }
    }

    // ------------------------- MouseListener Functions ------------------------------
    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        GridElement element = (GridElement) e.getSource();
        if (SwingUtilities.isRightMouseButton(e) && element.getBackground() != Color.green) {
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
            } else if (element.getBackground() == Color.green) {
                isGoal = true;
            }
        }    
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        mouseDown = false;
        if (isGoal) {
            GridElement element = null;
            int count = 0;
            for (int i = 0; i < this.grid.height; i++) {
                for (int j = 0; j < this.grid.width; j++) {
                    if (this.gridPanels[i][j].getBackground() == Color.green) {
                        element = this.gridPanels[i][j];
                        count++;
                    }
                }
            }
            // some possible error cases just in case
            if (count > 1) {
                JOptionPane.showMessageDialog(null, "There is more than 1 goal in the grid", "Goal error", JOptionPane.ERROR_MESSAGE);
            } 
            if (element == null) {
                JOptionPane.showMessageDialog(null, "No goals were found in the grid", "Goal error", JOptionPane.ERROR_MESSAGE);
            }

            System.out.println(element.cords.getX() + " " + element.cords.getY());
            this.grid.changeGoal(element.cords);
        }
        isGoal = false;
        this.grid.printGrid();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        GridElement element = (GridElement) e.getSource();
        if (mouseDown) {
            if (element.getBackground() == Color.white && !isWall && !isGoal) {
                element.setBackground(Color.black);
                grid.placeWall(element.cords);
            } else if (element.getBackground() == Color.black && isWall && !isGoal) {
                element.setBackground((Color.white));
                grid.removeWall(element.cords);
            } else if (isGoal) {
                if (element.getBackground() == Color.black) {
                    this.grid.removeWall(element.cords);
                }
                if (element.getBackground() == Color.red) {
                    JOptionPane.showMessageDialog(null, "Cannot replace player with goal", "Error", JOptionPane.ERROR_MESSAGE);
                    isGoal = false;
                    mouseDown = false;
                    this.gridPanels[this.grid.goal.getX()][this.grid.goal.getY()].setBackground(Color.green);
                } else {
                    element.setBackground(Color.green);
                }
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        GridElement element = (GridElement) e.getSource();
        if (mouseDown && isGoal) {
            element.setBackground(Color.white);
        }
    }

    // ------------------------- ActionListener ------------------------------
    @Override
    public void actionPerformed(ActionEvent e) {
        // choosing alg
        if (e.getSource() == algChoose) {
            String choice = (String) algChoose.getSelectedItem();

            if (choice == "BFS") {
                this.grid.getPlayer().setAlg(new BFS());
            }

            if (choice == "DFS") {
                this.grid.getPlayer().setAlg(new DFS());
            }

            if (choice == "Dijkstra") {
                this.grid.getPlayer().setAlg(new Dijkstra());
            }

            if (choice == "Random Walk") {
                this.grid.getPlayer().setAlg(new RandomWalk());
            }

            if (choice == "MST") {
                this.grid.getPlayer().setAlg(new MST());
            }
            

        }
        // find path button
        if (e.getSource() == findPathButton) {
            this.grid.findPath();

        }
        // change dimension button
        if (e.getSource() == changeDimensionWindow) {
            initChangeDimensionWindow();
        }

        // show cords
        if (e.getSource() == showCordsButton) {
            showCords = !showCords;
            initGridPanels();
        }
    }
}