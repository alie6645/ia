package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

public class Menu extends JPanel {
    GridBagConstraints c = new GridBagConstraints();
    JLabel logo;
    JLabel current;
    JButton entry;
    JButton data;
    JButton files;

    private Color backgroundColor = new Color(208,196,170);
    private Color logoColor = new Color(248,236,210);

    public Menu() throws IOException, URISyntaxException {
        initialize();

        setLayout(new GridBagLayout());
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.LINE_START;
        c.weighty = 0;
        c.weightx = 1;
        c.gridy = 0;
        add(logo, c);

        c.gridy = 1;
        add(current, c);

        c.gridy = 2;
        add(entry, c);

        c.gridy = 3;
        add(data,c);

        c.gridy = 4;
        add(files,c);

        c.gridy = 6;
        c.weighty = 1;
        c.gridheight = 5;
        JPanel spacer = new JPanel();
        spacer.setBackground(logoColor);
        add(spacer,c);
    }

    public void initialize() throws IOException, URISyntaxException {
        InputStream img = getClass().getResourceAsStream("resources/logo_bar.png");
        Image compostUT = ImageIO.read(img);
        Icon logoBar = new ImageIcon(compostUT.getScaledInstance(100,50,Image.SCALE_DEFAULT));
        logo = new JLabel(logoBar);
        logo.setBackground(backgroundColor);

        current = new JLabel();
        current.setOpaque(true);
        current.setBackground(logoColor);
        current.setHorizontalAlignment(SwingConstants.CENTER);

        entry = new JButton("Entry");
        entry.setBorderPainted(false);
        entry.setBackground(backgroundColor);

        data = new JButton("View Data");
        data.setBorderPainted(false);
        data.setBackground(backgroundColor);

        files = new JButton("Files");
        files.setBorderPainted(false);
        files.setBackground(backgroundColor);
    }

    public void setCurrent(String text){
        current.setText(text);
        repaint();
    }
}