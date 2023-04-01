package data;

import java.io.*;

public class Editor {
    private File current;
    public void setTable(File file) throws IOException {
        current = file;
    }

    public void append(String text) throws IOException {
        RandomAccessFile target = new RandomAccessFile(current, "rw");
        target.seek(target.length());
        target.write(text.getBytes());
        target.close();
    }

    public void remove(int row) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader(current));
        String path = current.getPath();
        File temp = new File(path.substring(0,path.length()-current.getName().length()) + "temp.txt");
        RandomAccessFile target = new RandomAccessFile(temp,"rw");
        int line = 0;
        while(input.ready()){
            String selected = input.readLine();
            if (line != row){
                target.write(selected.getBytes());
                target.write(System.lineSeparator().getBytes());
            }
            line++;
        }
        input.close();
        target.close();
        current.delete();
        temp.renameTo(current);
    }
}
