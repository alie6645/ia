package gui.pages;

import javax.swing.*;
import java.awt.*;

public class DataDisplay extends JPanel{
    JTable table;
    JScrollPane panel = new JScrollPane();
    String[] labels = {"Date", "Fruits", "Vegetables"};

    public DataDisplay(String[][] data){
        setLayout(new BorderLayout());
        data[0] = labels;
        table = new JTable(data,labels);
        add(table);
    }

    public void display(String[][] data){
        table = new JTable(data,labels);
        repaint();
    }
}
