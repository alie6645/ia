package data;

import java.io.*;
import java.util.Scanner;

public class Editor {
    private PrintWriter writer;
    private Scanner reader;
    private File current;
    public void setTable(File file) throws IOException {
        writer = new PrintWriter(file);
        current = file;
    }

    public void append(String text) throws IOException {
        writer.append(text);
        writer.flush();
        writer.close();
    }

    public void remove(int row) throws FileNotFoundException {
        reader = new Scanner(current);
        String removed = "";
        int line = 0;
        while (reader.hasNext()){
            if (line != row){
                removed += reader.nextLine();
            } else {
                reader.nextLine();
            }
            line++;
        }
        writer.write(removed);
        writer.flush();
        writer.close();
    }
}
