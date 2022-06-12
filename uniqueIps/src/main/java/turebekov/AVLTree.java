package turebekov;

public class AVLTree {
    private static class AVLNode {
        private final String value;
        private long height;
        private AVLNode leftChild;
        private AVLNode rightChild;

        public AVLNode(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node=" + value;
        }
    }

    private AVLNode root;
    private long size;

    public void insert(String value) {
        root = insert(root, value);
    }

    private AVLNode insert(AVLNode root, String value) {
        if (root == null) {
            size++;
            return new AVLNode(value);
        }

        if (value.compareTo(root.value) < 0)
            root.leftChild = insert(root.leftChild, value);
        else if (value.compareTo(root.value) > 0)
            root.rightChild = insert(root.rightChild, value);

        setHeight(root);

        return balance(root);
    }

    public long size(){
        return size;
    }

    private AVLNode rotateRight(AVLNode root) {
        var newRoot = root.leftChild;

        root.leftChild = newRoot.rightChild;
        newRoot.rightChild = root;

        setHeight(root);
        setHeight(newRoot);

        return newRoot;
    }

    private AVLNode rotateLeft(AVLNode root) {
        var newRoot = root.rightChild;

        root.rightChild = newRoot.leftChild;
        newRoot.leftChild = root;

        setHeight(root);
        setHeight(newRoot);

        return newRoot;
    }

    private void setHeight(AVLNode node) {
        node.height = Math.max(height(node.leftChild), height(node.rightChild)) + 1;
    }

    private AVLNode balance(AVLNode root) {
        if (isLeftHeavy(root)) {
            if (balanceFactor(root.leftChild) < 0)
                root.leftChild = rotateLeft(root.leftChild);
            return rotateRight(root);
        } else if (isRightHeavy(root)) {
            if (balanceFactor(root.rightChild) > 0)
                root.rightChild = rotateRight(root.rightChild);
            return rotateLeft(root);
        }

        return root;
    }

    private boolean isLeftHeavy(AVLNode node) {
        return balanceFactor(node) > 1;
    }

    private boolean isRightHeavy(AVLNode node) {
        return balanceFactor(node) < -1;
    }

    private long balanceFactor(AVLNode node) {
        return (node == null) ? 0 : height(node.leftChild) - height(node.rightChild);
    }

    private long height(AVLNode node) {
        return (node == null) ? -1 : node.height;
    }
}
