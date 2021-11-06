package lesson6;

import java.util.NoSuchElementException;

public class MyTreeMap<K extends Comparable<K>, V> {
    private Node root;
    private boolean isBalanced = true;

    private class Node {
        K key;
        V value;
        Node left;
        Node right;
        int size;
        int height;


        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.size = 1;
            this.height = 0;
        }
    }

    public int size() {
        return size(root);
    }

    public int height() { return height(root); }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return node.size;
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    public boolean isEmpty() {
        return root == null;
    }

    private void checkKeyNotNull(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key null");
        }
    }

    public boolean contains(K key) {
        return get(key) != null;
    }

    public V get(K key) {
        checkKeyNotNull(key);
        return get(root, key);
    }

    private V get(Node node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            return node.value;
        } else if (cmp < 0) {
            return get(node.left, key);
        } else {
            return get(node.right, key);
        }
    }

    public void put(K key, V value) {
        checkKeyNotNull(key);
        if (value == null) {
            // delete(key)
            return;
        }
        root = put(root, key, value);
        // Обновляем значение isBalance
        isBalanced = true;
    }

    private Node put(Node node, K key, V value) {
        if (node == null) {
            return new Node(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            node.value = value;
        } else if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else {
            node.right = put(node.right, key, value);
        }
        node.size = 1 + size(node.left) + size(node.right);

        if( node.left == null && node.right == null){
            node.height = 0;
        } else {
            node.height = height(node.left) >= height(node.right) ? height(node.left) + 1 : height(node.right) + 1;
        }

        return node;
    }

    public K minKey() {
        return min(root).key;
    }

    private Node min(Node node) {
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    public void deleteMin() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        deleteMin(root);
        // Обновляем значение isBalance
        isBalanced = true;
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        node.size = 1 + size(node.left) + size(node.right);

        if( node.left == null && node.right == null){
            node.height = 0;
        } else {
            node.height = height(node.left) >= height(node.right) ? height(node.left) + 1 : height(node.right) + 1;
        }

        return node;
    }

    public void delete(K key) {
        checkKeyNotNull(key);
        root = delete(root, key);
        // Обновляем значение isBalance
        isBalanced = true;
    }

    public void trimLastTreeLevel() {
       trimLastLevel(root);
        // Обновляем значение isBalance
        isBalanced = true;
    }

    private Node delete(Node node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            Node temp = node;
            node = min(node.right);
            node.right = deleteMin(temp.right);
            node.left = temp.left;
        }
        node.size = 1 + size(node.left) + size(node.right);

        if( node.left == null && node.right == null){
            node.height = 0;
        } else {
            node.height = height(node.left) >= height(node.right) ? height(node.left) + 1 : height(node.right) + 1;
        }

        return node;
    }

    private void trimLastLevel(Node node) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            delete(node.key);
        }
        if (node.left == null || node.right == null){
            if(node.left == null && node.right != null && node.right.height >= 0 ){
                trimLastLevel(node.right);
            }
            if(node.right == null && node.left != null && node.left.height >= 0 ){
                trimLastLevel(node.left);
            }
        } else {
            if(node.left.height > node.right.height){
                trimLastLevel(node.left);
            }
            else if (node.left.height < node.right.height){
                trimLastLevel(node.right);
            } else {
                trimLastLevel(node.left);
                trimLastLevel(node.right);
            }
        }
    }

    public boolean isBalanced(){
       return isBalanced(root);
    }

    private boolean isBalanced(Node node){
        if (node == null) { return true; }
        if (!isBalanced) { return false; }

        if (node.left == null && node.right == null){
            return true;
        }
        else if (node.left == null){
            return isBalanced = node.right.height == 0;
        }
        else if (node.right == null){
            return isBalanced = node.left.height == 0;
        } else {
            isBalanced(node.left);
            isBalanced(node.right);
        }
        return isBalanced = isBalanced && Math.abs(node.left.height - node.right.height) <= 1;
    }
    
    @Override
    public String toString() {
        return toString(root);
    }

    private String toString(Node node) {
        if (node == null) {
            return "";
        }
        return toString(node.left) + " " +
                node.key + " = " + node.value + " " +
                toString(node.right);
    }
}
