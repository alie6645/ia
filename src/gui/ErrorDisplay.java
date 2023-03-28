package gui;

import javax.swing.*;

public class ErrorDisplay extends JPanel {
    public ErrorDisplay(String message){
        add(new JLabel(message));
    }

}