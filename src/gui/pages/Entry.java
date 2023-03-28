package gui.pages;

import javax.swing.*;
import java.awt.*;

public class Entry extends JPanel {
    final Color boxColor = new Color(200,200,200);
    GridBagConstraints c = new GridBagConstraints();
    JPanel nitrogen;
    JPanel carbon;
    JPanel ratio;
    JPanel input = new JPanel();
    JButton submit;

    public Entry(){
        initialize();
        setLayout(new GridBagLayout());
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.insets = new Insets(10,10,10,10);

        setSize(2,1);
        setPosition(0,0);
        add(ratio,c);
        setSize(1,1);

        setPosition(0,1);
        add(carbon,c);

        c.weightx = 0.3;
        setPosition(1,1);
        add(nitrogen,c);

        setSize(2,1);
        c.weighty = 1;
        setPosition(0,2);
        add(input,c);
        reset();

        c.weighty = 0;
        c.weightx = 0.3;
        setPosition(1,3);
        add(submit,c);
    }

    public void setPosition(int x, int y){
        c.gridx = x;
        c.gridy = y;
    }

    public void setSize(int x, int y){
        c.gridwidth = x;
        c.gridheight = y;
    }

    public void reset(){
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1;
        c.weighty = 0.5;
        c.insets = new Insets(10,10,10,10);
    }

    public void initialize(){
        nitrogen = new JPanel();
        nitrogen.setBackground(boxColor);
        carbon = new JPanel();
        carbon.setBackground(boxColor);
        ratio = new JPanel();
        ratio.setBackground(boxColor);
        ratio.setFont(new Font("Big",Font.PLAIN,20));
        initializeInput();
        submit = new JButton("Submit");
        submit.setBorderPainted(false);
        submit.setBackground(boxColor);
    }

    public void initializeInput(){
        input.setBackground(boxColor);
        GridBagConstraints inputC = new GridBagConstraints();
        inputC.fill = GridBagConstraints.HORIZONTAL;
        inputC.weightx = 1;
        inputC.weighty = 1;
        inputC.insets = new Insets(10,10,10,10);
        input.setLayout(new GridBagLayout());
        for (int i=0; i<4; i++){
            inputC.gridx = i%2;
            inputC.gridy = i-(i - 2*(i/2));
            System.out.println();
            input.add(new JTextField(),inputC);
        }
    }
}
