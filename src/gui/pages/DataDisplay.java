package gui.pages;

import data.Editor;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class DataDisplay extends JPanel implements Open{
    JTable table = new JTable();
    JScrollPane scrollPane;
    JButton delete = new JButton("delete selected");
    String[] labels = {"Date", "Fruits", "Vegetables", "Mixed", "Leaves", "Paper"};


    public DataDisplay(){
        add(delete);
        add(table);
        delete.setEnabled(false);
    }

    public void display(List<String[]> input){
        remove(1);
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
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i=0; i<table.getColumnCount(); i++){
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
        table.setShowVerticalLines(false);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (table.getSelectedRow()!=-1){
                    delete.setEnabled(true);
                } else {
                    delete.setEnabled(false);
                }
            }
        });
        scrollPane = new JScrollPane(table);
        add(scrollPane);
        repaint();
    }

    public void delete(File file) throws IOException {
        Editor editor = new Editor();
        editor.setTable(file);
        editor.remove(table.getSelectedRow());
    }

    @Override
    public void onOpened(List<String[]> table) {
        display(table);
    }

    public JButton getDelete(){
        return delete;
    }
}