package turebekov;

public class Main {
    public static void main(String[] args) {
        String filename = "";
        if (args.length != 0)
            filename = args[0];

        IPFileReader reader = new IPFileReader();
        var result = reader.read(filename, new AVLTree());
        System.out.println("Unique IP addresses " + result);

    }
}
