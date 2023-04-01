package gui.pages;

import data.Editor;
import data.processes.CompostCalculator;
import data.processes.GridCalculator;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

public class Entry extends JPanel implements Open{
    final Color boxColor = new Color(200,200,200);
    GridBagConstraints c = new GridBagConstraints();
    JPanel nitrogen;
    JPanel carbon;
    JPanel ratio;
    JPanel input = new JPanel();
    JButton submit;
    JButton calculate;
    JTextField[] entryData = new JTextField[5];
    String[] fields = {"Fruits", "Vegetables", "Mixed", "Leaves", "Paper"};
    JFormattedTextField date;
    CompostCalculator calculator;
    double[] suggested;

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

        setPosition(0,3);
        add(calculate,c);
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
        calculate = new JButton("Calculate");
        calculate.addActionListener(e -> calculate());
        calculate.setBorderPainted(false);
        calculate.setBackground(boxColor);
        calculator = new CompostCalculator();
        calculator.addMaterial("leaves",.4,.005);
        calculator.addMaterial("paper",.38125,.001);
    }

    public void initializeInput(){
        input.setBackground(boxColor);
        GridBagConstraints inputC = new GridBagConstraints();
        inputC.fill = GridBagConstraints.HORIZONTAL;
        inputC.weightx = 1;
        inputC.weighty = 1;
        inputC.insets = new Insets(10,10,10,10);
        input.setLayout(new GridBagLayout());
        input.add(new JLabel("Date"),inputC);
        c.gridx = 1;
        try {
            date = new JFormattedTextField(new MaskFormatter("##/##/##"));
            Calendar now = Calendar.getInstance();
            String month = String.valueOf(now.get(Calendar.MONTH));
            month = (month.length()==2)?month:"0"+month;
            date.setValue((month+ "/" + now.get(Calendar.DAY_OF_MONTH) + "/" + String.valueOf(now.get(Calendar.YEAR)).substring(2)));
            input.add(date,inputC);
        } catch (ParseException e){

        }
        for (int i=0; i<5; i++){
            inputC.gridx = i%2;
            inputC.gridy = 2*(i-(i - 2*(i/2)))+2;
            entryData[i] = new JTextField();
            input.add(entryData[i],inputC);
            inputC.gridy--;
            input.add(new JLabel(fields[i]),inputC);
        }
    }

    public void addEntry(File file) throws IOException {
        Editor editor = new Editor();
        editor.setTable(file);
        String addition = date.getText() + " ";
        for (int i=0; i<5; i++){
            String val = entryData[i].getText();
            addition += (val.trim().equals(""))?"0":val;
            addition += " ";
            entryData[i].setText("");
        }
        addition += "\n";
        editor.append(addition);
    }

    public void calculate(){
        double[] start = calculator.getStartValues();
        suggested = calculator.calculate(start[0],start[1]);
        double add = 0;
        if (!entryData[0].getText().isEmpty()){
            add = Double.parseDouble(entryData[0].getText());
        }
        double fruit = calculator.getFruit();
        calculator.setFruit(fruit+add);
        add = 0;
        if (!entryData[1].getText().isEmpty()){
            add = Double.parseDouble(entryData[1].getText());
        }
        double veg = calculator.getVeg();
        calculator.setVeg(veg+add);
        add = 0;
        if (!entryData[2].getText().isEmpty()){
            add = Double.parseDouble(entryData[2].getText());
        }
        double mix = calculator.getMix();
        calculator.setMix(mix+add);
        for (int i=0; i< calculator.getMaterialCount(); i++){
            entryData[i+3].setText(String.valueOf((int)suggested[i]));
        }
        calculator.setFruit(fruit);
        calculator.setVeg(veg);
        calculator.setMix(mix);
    }

    public JButton getSubmit(){
        return submit;
    }

    @Override
    public void onOpened(List<String[]> table) {
        ratio.removeAll();
        calculator.setFruit(GridCalculator.sumColumn(table, 1));
        calculator.setVeg(GridCalculator.sumColumn(table, 2));
        calculator.setMix(GridCalculator.sumColumn(table, 3));
        double[] brown = new double[calculator.getMaterialCount()];
        for (int i = 0; i < calculator.getMaterialCount(); i++) {
            brown[i] = GridCalculator.sumColumn(table, 3 + i);
        }
        calculator.setBrown(brown);
        Double result = calculator.getRatio();
        ratio.add(new JLabel("Ratio: " + String.format("%.2f",result)));
    }
}
