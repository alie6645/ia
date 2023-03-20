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
    JButton home;
    JButton settings;

    private Color backgroundColor = new Color(208,196,170);

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
        add(home, c);

        c.gridy = 2;
        add(settings,c);

        c.gridy = 3;
        c.weighty = 1;
        c.gridheight = 5;
        JPanel spacer = new JPanel();
        spacer.setBackground(Color.BLACK);
        add(spacer,c);
    }

    public void initialize() throws IOException {
        Image compostUT = ImageIO.read(new File("src/gui/resources/logo_bar.png"));
        Icon logoBar = new ImageIcon(compostUT.getScaledInstance(200,100,Image.SCALE_DEFAULT));
        logo = new JLabel(logoBar);
        logo.setBackground(backgroundColor);

        home = new JButton("Home");
        home.setBorderPainted(false);
        home.setBackground(backgroundColor);

        settings = new JButton("Settings");
        settings.setBorderPainted(false);
        settings.setBackground(backgroundColor);
    }
}
