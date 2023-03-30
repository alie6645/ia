package data.processes;

import java.util.List;

public class GridCalculator {
    public static double sumColumn(List<String[]> data, int col, int start){
        double sum = 0;
        for (int i = start; i<data.size(); i++){
            sum += Double.valueOf(data.get(i)[col]);
        }
        return sum;
    }

    public static double sumColumn(List<String[]> data, int col){
        return sumColumn(data,col,0);
    }

    public static double sumRow(List<String[]> data, int row, int start){
        double sum = 0;
        String[] rowData = data.get(row);
        for (int i=start; i< rowData.length; i++){
            sum += Double.valueOf(rowData[i]);
        }
        return sum;
    }

    public static double sumRow(List<String[]> data, int row){
        return sumRow(data,row,0);
    }
}
