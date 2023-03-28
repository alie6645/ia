package data.processes;

public class GridCalculator {
    public static double sumColumn(String[][] data, int col, int start){
        double sum = 0;
        for (int i = start; i<data.length; i++){
            sum += Double.valueOf(data[i][col]);
        }
        return sum;
    }

    public static double sumColumn(String[][] data, int col){
        return sumColumn(data,col,0);
    }

    public static double sumRow(String[][] data, int row, int start){
        double sum = 0;
        String[] rowData = data[row];
        for (int i=start; i< rowData.length; i++){
            sum += Double.valueOf(rowData[i]);
        }
        return sum;
    }

    public static double sumRow(String[][] data, int row){
        return sumRow(data,row,0);
    }
}
