package data;

import data.processes.CompostCalculator;


import java.io.IOException;

public class FileTester {
    public static void main(String[] args) throws IOException {
        CompostCalculator calculator = new CompostCalculator();
        calculator.addMaterial("leaves",.4,.005);
        calculator.addMaterial("paper",.38125,.001);
        double[] combo = calculator.calculate(200,2);
        for (int i=0; i< combo.length; i++){
            System.out.println(combo[i]);
        }
    }
}
