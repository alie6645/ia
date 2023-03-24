package gui;

import gui.pages.DataDisplay;
import gui.pages.Entry;

import javax.swing.*;
import java.awt.*;

public class Display extends JPanel{
    Menu menu;
    public JPanel current;
    public JPanel[] pages = new JPanel[2];

    public Display(Menu menu){
        String[][] test = {{"12","",""},{"","",""},{"","",""}};
        this.menu = menu;
        pages[0] = new Entry();
        pages[1] = new DataDisplay(test);
        setLayout(new BorderLayout());
        open(1);
        initialize();
    }

    public void initialize(){
        int index = 0;
        for (int i=0; i<menu.getComponentCount(); i++){
            if (index >= pages.length){
                break;
            }
            if (menu.getComponent(i) instanceof JButton){
                JButton button = (JButton) menu.getComponent(i);
                final int j = index;
                button.addActionListener(e -> open(j));
                index++;
            }
        }
    }

    public void open(int n){
        if (current != null){
            remove(current);
        }
        current = pages[n];
        add(current);
        revalidate();
        repaint();
    }
}
