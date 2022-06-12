package turebekov;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IPFileReaderTest {

    private IPFileReader reader = new IPFileReader();
    private AVLTree tree = new AVLTree();
    private String filename = "src/main/resources/test.txt";

    @Test
    void isFileExisted(){
        var result = reader.read(filename,tree);
        assertEquals(73602,result);
    }



}
