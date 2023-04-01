package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Loader {
    private static final String ROOT = "src/data/tables/";

    public static Map<String,String> getTables(){
        Scanner in = null;
        Map<String, String> tables = new HashMap<>();
        try {
            in = new Scanner(new File(ROOT + "main.txt"));
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

    public static int getIndex(String table){
        int i = 0;
        Iterator<String> keys = getTables().keySet().iterator();
        while (keys.hasNext()){
            if (getTables().get(keys.next()).equals(table)){
                return i;
            }
            i++;
        }
        return 0;
    }

    public static File getFile(String filename){
        return new File(ROOT + filename);
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

    public static List<String[]> getDefault() throws FileNotFoundException {
        Iterator<String> keys = getTables().keySet().iterator();
        return getTable(getTables().get(keys.next()));
    }

    public static void createTable(String name) throws IOException {
        if (name.isBlank()){
            return;
        }
        File table = new File(ROOT + name.strip() + ".txt");
        table.createNewFile();
        Editor editor = new Editor();
        editor.setTable(new File(ROOT + "main.txt"));
        editor.append("\n" + name + " " + name.strip() + ".txt");
    }

    public static void setDefault(String table) throws IOException {
        Editor editor = new Editor();
        editor.setTable(new File(ROOT + "main.txt"));
    }
}
