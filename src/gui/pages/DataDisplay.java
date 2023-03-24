package gui.pages;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DataDisplay extends JPanel{
    JTable table;
    JScrollPane scrollPane;
    String[] labels = {"Date", "Fruits", "Vegetables"};

    public DataDisplay(String[][] data){
        setLayout(new BorderLayout());
        table = new JTable();
        table.setModel(new DefaultTableModel(data,labels){

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        table.setSelectionBackground(Color.orange);
        scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    public void display(String[][] data){
        table = new JTable(data,labels);
        repaint();
    }
}
