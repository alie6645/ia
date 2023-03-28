package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Loader {
    private static final String ROOT = "src/data/";

    public static Map<String,String> getTables(){
        Scanner in = null;
        Map<String, String> tables = new HashMap<>();
        try {
            in = new Scanner(new File("src/data/main.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (in.hasNextLine()){
            Scanner parser = new Scanner(in.nextLine());
            tables.put(parser.next(),parser.next());
            parser.close();
        }
        in.close();
        return tables;
    }

    public static List<String[]> getTable(String filename) throws FileNotFoundException {
        File table = new File(ROOT + filename);
        List<String[]> data = new ArrayList<>();
        Scanner reader = new Scanner(table);
        int line = 0;
        while (reader.hasNextLine()){
            String[] entry = new String[6];
            Scanner parser = new Scanner(reader.nextLine());
            int index = 0;
            while (parser.hasNext()){
                entry[index] = parser.next();
                index++;
            }
            data.add(entry);
            line++;
            parser.close();
        }
        reader.close();
        return data;
    }
}
