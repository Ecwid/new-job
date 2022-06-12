package turebekov;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class IPFileReader {
    public long read(String file, AVLTree tree) {
        try (FileReader fileReader = new FileReader(file);
             BufferedReader reader = new BufferedReader(fileReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    tree.insert(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("File " + file + " is not found!");
        }

        return tree.size();
    }

}
