package gui.pages;

import data.Loader;

import javax.swing.*;
import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Files extends JPanel {
    Map<String,String> tables = Loader.getTables();
    String[] names = new String[tables.size()];

    JButton open;
    JButton create;
    JComboBox fileChooser;

    public Files(){
        initialize();
        add(fileChooser);
        add(open);
        add(create);
    }

    public void initialize(){
        Iterator<String> keys = tables.keySet().iterator();
        for (int i=0; i<tables.size(); i++){
            names[i] = keys.next();
        }
        open = new JButton("open");
        create = new JButton("add");
        fileChooser = new JComboBox(names);
    }

    public String getFile(){
        return tables.get(fileChooser.getSelectedItem());
    }
}
