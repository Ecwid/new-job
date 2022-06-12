package turebekov;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AVLTreeTest {

    private AVLTree tree = new AVLTree();

    @Test
    void sizeIsThree() {
        List<String> ips = Arrays.asList(
                "191.221.48.132",
                "191.221.48.131",
                "191.221.48.130"
        );

        for (var ip : ips)
            tree.insert(ip);

        assertEquals(3, tree.size());

    }

}
