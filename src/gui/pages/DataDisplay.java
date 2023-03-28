package gui.pages;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DataDisplay extends JPanel{
    JTable table;
    JScrollPane scrollPane;
    String[] labels = {"Date", "Fruits", "Vegetables", "Mixed", "Leaves", "Paper"};

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

    public void display(List<String[]> input){
        remove(0);
        String[][] data = new String[input.size()][];
        for (int i=0; i<input.size(); i++){
            data[i] = input.get(i);
        }
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
        repaint();
    }
}