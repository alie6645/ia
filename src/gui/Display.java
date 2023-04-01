package gui;

import data.Loader;
import gui.pages.DataDisplay;
import gui.pages.Entry;
import gui.pages.Files;
import gui.pages.Open;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Display extends JPanel{
    Menu menu;
    Entry entry = new Entry();
    public DataDisplay data = new DataDisplay();
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
        String name = files.getFile();
        menu.setCurrent(name.substring(0,name.length()-4));
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
        data.getDelete().addActionListener(e -> delete());
    }

    public void openFile(){
        String name = files.getFile();
        try{
            table = Loader.getTable(name);
            Loader.setDefault(name);
        } catch (FileNotFoundException ex) {
            open(new ErrorDisplay(ex.getMessage()));
        } catch (IOException e) {
            open(new ErrorDisplay(e.getMessage()));
        }
        menu.setCurrent(name.substring(0,name.length()-4));
    }

    public void addEntry(){
        try {
            entry.addEntry(Loader.getFile(files.getFile()));
            openFile();
        } catch (IOException ex) {
            open(new ErrorDisplay(ex.getMessage()));
        }
        open(entry);
    }

    public void delete(){
        try {
            data.delete(Loader.getFile(files.getFile()));
            openFile();
            open(data);
        } catch (IOException ex){
            open(new ErrorDisplay(ex.getMessage()));
        }
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
        if (current instanceof Open){
            ((Open) current).onOpened(table);
        }
        revalidate();
        repaint();
    }
}