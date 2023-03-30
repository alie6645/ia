package gui;

import data.Loader;
import gui.pages.DataDisplay;
import gui.pages.Entry;
import gui.pages.Files;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Display extends JPanel{
    Menu menu;
    Entry entry = new Entry();
    public DataDisplay data = new DataDisplay(null);
    Files files = new Files();
    public JPanel current;
    public JPanel[] pages = new JPanel[3];
    List<String[]> table;

    public Display(Menu menu){
        this.menu = menu;
        pages[0] = entry;
        pages[1] = data;
        pages[2] = files;
        try {
            table = Loader.getDefault();
        } catch (FileNotFoundException e) {
            open(new ErrorDisplay(e.getMessage()));
        }
        setLayout(new BorderLayout());
        open(0);
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
        JButton openFile = (JButton) files.getComponent(1);
        openFile.addActionListener(e -> openFile());
        entry.getSubmit().addActionListener(e -> addEntry());
    }

    public void openFile(){
        try{
            table = Loader.getTable(files.getFile());
        } catch (FileNotFoundException ex) {
            open(new ErrorDisplay(ex.getMessage()));
        }
    }

    public void addEntry(){
        try {
            entry.addEntry(Loader.getFile(files.getFile()));
        } catch (IOException ex) {
            open(new ErrorDisplay(ex.getMessage()));
        }
        openFile();
    }

    public void open(int n){
        open(pages[n]);
    }

    public void open(JPanel panel){
        if (current != null){
            remove(current);
        }
        current = panel;
        add(current);
        if (current instanceof DataDisplay){
            ((DataDisplay) current).display(table);
        }
        revalidate();
        repaint();
    }
}