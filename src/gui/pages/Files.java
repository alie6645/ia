package gui.pages;

import data.Loader;
import gui.ErrorDisplay;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class Files extends JPanel{
    Map<String,String> tables = Loader.getTables();
    String[] names = new String[tables.size()];

    JButton open;
    JButton create;
    JComboBox fileChooser;
    JTextField nameBox;
    GridBagConstraints c = new GridBagConstraints();

    public Files(){
        initialize();
        setLayout(new GridBagLayout());
        c.anchor = GridBagConstraints.LINE_END;
        c.weighty = 0.5;
        c.weightx = 0.5;
        c.insets = new Insets(10,10,10,10);
        c.gridx = 0;
        add(fileChooser, c);

        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 1;
        add(open, c);

        c.gridy = 1;
        add(create, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        add(nameBox, c);
    }

    public void initialize(){
        Iterator<String> keys = tables.keySet().iterator();
        for (int i=0; i<tables.size(); i++){
            names[i] = keys.next();
        }
        open = new JButton("open");
        create = new JButton("new");
        fileChooser = new JComboBox(names);
        nameBox = new JTextField();
        create.addActionListener(e -> addTable());
    }

    public void addTable(){
        try {
            Loader.createTable(nameBox.getText());
        } catch (IOException e) {
            add(new ErrorDisplay(e.getMessage()));
        }
        Map<String,String> tables = Loader.getTables();
        String[] names = new String[tables.size()];
        Iterator<String> keys = tables.keySet().iterator();
        for (int i=0; i<tables.size(); i++){
            names[i] = keys.next();
        }
        remove(fileChooser);
        c.gridx = 0;
        c.gridy = 0;
        fileChooser = new JComboBox(names);
        add(fileChooser, c);
        revalidate();
        repaint();
    }

    public String getFile(){
        return tables.get(fileChooser.getSelectedItem());
    }
}
