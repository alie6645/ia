package data;

import java.io.File;
import java.io.IOException;

public class FileTester {
    public static void main(String[] args) throws IOException {
        String key = Loader.getTables().keySet().iterator().next();
        String filename = Loader.getTables().get(key);
        Editor editor = new Editor();
        editor.setTable(new File("src/data/" + filename));
        editor.append("adding things");
        editor.remove(0);
    }
}
