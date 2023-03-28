package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Menu extends JPanel {
    GridBagConstraints c = new GridBagConstraints();
    JLabel logo;
    JButton entry;
    JButton data;
    JButton files;

    private Color backgroundColor = new Color(208,196,170);
    private Color logoColor = new Color(248,236,210);

    public Menu() throws IOException {
        initialize();

        setLayout(new GridBagLayout());
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.LINE_START;
        c.weighty = 0;
        c.weightx = 1;
        c.gridy = 0;
        add(logo, c);

        c.gridy = 1;
        add(entry, c);

        c.gridy = 2;
        add(data,c);

        c.gridy = 3;
        add(files,c);

        c.gridy = 4;
        c.weighty = 1;
        c.gridheight = 5;
        JPanel spacer = new JPanel();
        spacer.setBackground(logoColor);
        add(spacer,c);
    }

    public void initialize() throws IOException {
        Image compostUT = ImageIO.read(new File("src/gui/resources/logo_bar.png"));
        Icon logoBar = new ImageIcon(compostUT.getScaledInstance(100,50,Image.SCALE_DEFAULT));
        logo = new JLabel(logoBar);
        logo.setBackground(backgroundColor);

        entry = new JButton("Home");
        entry.setBorderPainted(false);
        entry.setBackground(backgroundColor);

        data = new JButton("Settings");
        data.setBorderPainted(false);
        data.setBackground(backgroundColor);

        files = new JButton("Files");
        files.setBorderPainted(false);
        files.setBackground(backgroundColor);
    }
}