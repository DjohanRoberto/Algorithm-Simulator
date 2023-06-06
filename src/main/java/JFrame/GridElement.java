package main.java.JFrame;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import main.java.Position;

public class GridElement extends JPanel{
    
    public Position cords;
    Border border = BorderFactory.createLineBorder(Color.gray, 1);

    public GridElement(int i, int j, int value) {
        this.cords = new Position(i , j);
        this.setPanelColor(value);
        this.setBorder(border);
    }


    // 0 = empty 
    // 1 = wall
    // 2 = goal
    // 3 = player
    public void setPanelColor(int value) {
        if (value == 0) {
            this.setBackground(Color.white);
        }
        if (value == 1) {
            this.setBackground(Color.black);
        }
        if (value == 2) {
            this.setBackground(Color.green);
        }
        if (value == 3) {
            this.setBackground(Color.red);
        }
    }   
}
