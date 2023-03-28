package data;

import java.io.*;
import java.util.Scanner;

public class Editor {
    private FileWriter writer;
    private Scanner reader;
    private File current;
    public void setTable(File file) throws IOException {
        current = file;
    }

    public void append(String text) throws IOException {
        writer = new FileWriter(current, true);
        writer.append(text);
        writer.flush();
        writer.close();
    }

    public void remove(int row) throws IOException {
        reader = new Scanner(current);
        String removed = "";
        int line = 0;
        while (reader.hasNext()){
            if (line != row){
                removed += reader.nextLine();
                if (reader.hasNext()){
                    removed += "\n";
                }
            } else {
                reader.nextLine();
            }
            line++;
        }
        writer = new FileWriter(current);
        writer.write(removed);
        writer.flush();
        writer.close();
    }
}
