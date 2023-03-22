package gui.pages;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Formatter;

public class Entry extends JPanel{
    final Color boxColor = new Color(200,200,200);
    GridBagConstraints c = new GridBagConstraints();
    JPanel nitrogen = new JPanel();
    JPanel carbon = new JPanel();
    JPanel ratio = new JPanel();
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

        setArea(2,1);
        setPosition(0,0);
        add(ratio,c);
        setArea(1,1);

        setPosition(0,1);
        add(carbon,c);

        c.weightx = 0.3;
        setPosition(1,1);
        add(nitrogen,c);

        setArea(2,1);
        c.weighty = 1;
        setPosition(0,2);
        add(input,c);
        reset();

        setArea(1,1);
        c.weighty = 0;
        c.weightx = 0;
        setPosition(1,3);
        add(submit,c);
    }

    public void initialize(){
        nitrogen.setBackground(boxColor);
        carbon.setBackground(boxColor);
        ratio.setBackground(boxColor);
        ratio.setFont(new Font("Big",Font.PLAIN,20));
        submit = new JButton("Submit");
        submit.setBackground(boxColor);
        submit.setBorderPainted(false);
        initializeInput();
    }

    public void initializeInput(){
        input.setBackground(boxColor);
        GridBagConstraints inputC = new GridBagConstraints();
        inputC.fill = GridBagConstraints.HORIZONTAL;
        inputC.anchor = GridBagConstraints.CENTER;
        inputC.weightx = 1;
        inputC.weighty = 1;
        inputC.insets = new Insets(10,10,10,10);
        input.setLayout(new GridBagLayout());
        try {
            JFormattedTextField date = new JFormattedTextField(new MaskFormatter("##/##/##"));
            Calendar now = Calendar.getInstance();
            String month = String.valueOf(now.get(Calendar.MONTH));
            month = (month.length()==2)?month:"0"+month;
            date.setValue((month+ "/" + now.get(Calendar.DAY_OF_MONTH) + "/" + String.valueOf(now.get(Calendar.YEAR)).substring(2)));
            input.add(date,inputC);
        } catch (ParseException e){

        }
        for (int i=0; i<4; i++){
            inputC.gridx = i%2;
            inputC.gridy = i-(i-2*(i/2)) + 1;
            input.add(new JTextField(),inputC);
        }
    }

    private void reset(){
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1;
        c.weighty = 0.5;
        c.insets = new Insets(10,10,10,10);
    }

    private void setPosition(int x, int y){
        c.gridx = x;
        c.gridy = y;
    }

    public void setArea(int x, int y){
        c.gridwidth = x;
        c.gridheight = y;
    }
}
