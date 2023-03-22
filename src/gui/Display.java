package gui;

import gui.pages.DataDisplay;
import gui.pages.Entry;

import javax.swing.*;
import java.awt.*;

public class Display extends JPanel{
    Menu menu;
    JPanel current;
    JPanel[] pages = new JPanel[2];

    public Display(){
        String[][] test = {{"12","",""},{"","",""},{"","",""}};
        setLayout(new BorderLayout());
        open(new DataDisplay(test));
    }

    public void open(JPanel panel){
        if (current != null){
            remove(current);
        }
        current = panel;
        add(current);
    }
}
