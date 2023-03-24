package gui;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Launcher{
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        frame.setLayout(layout);
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        Menu menu = null;
        try {
            menu = new Menu();
            frame.add(menu,c);
        } catch (IOException e) {
            frame.add(new ErrorDisplay("Image Resource not found"));
        }

        c.gridx = 1;
        c.weightx = 1;
        if (menu != null) {
            frame.add(new Display(menu), c);
        }
        frame.setSize(600,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
