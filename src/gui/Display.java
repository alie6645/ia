package gui;

import data.Loader;
import gui.pages.DataDisplay;
import gui.pages.Entry;
import gui.pages.Files;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Display extends JPanel{
    Menu menu;
    Entry entry = new Entry();
    public DataDisplay data = new DataDisplay(null);
    Files files = new Files();
    public JPanel current;
    public JPanel[] pages = new JPanel[3];

    public Display(Menu menu){
        this.menu = menu;
        pages[0] = entry;
        pages[1] = data;
        pages[2] = files;
        setLayout(new BorderLayout());
        open(1);
        initialize();
        List<String[]> test = new ArrayList<>();
        String[] entry = {"1", "2", "3", "4", "5", "6"};
        String[] entry2 = {"6", "5", "4", "3", "2", "1"};
        test.add(entry);
        test.add(entry2);
        data.display(test);
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
        openFile.addActionListener(e -> {
            try {
                openFile();
            } catch (FileNotFoundException ex) {
                open(new ErrorDisplay(ex.getMessage()));
            }
        });
    }

    public void openFile() throws FileNotFoundException {
        List<String[]> table = Loader.getTable(files.getFile());
        data.display(table);
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
        revalidate();
        repaint();
    }
}