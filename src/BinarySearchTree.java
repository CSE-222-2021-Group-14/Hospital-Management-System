import java.io.Serializable;
import java.util.Scanner;

public class BinarySearchTree<E extends Comparable<E> > implements Serializable {
    private Node<E> root;
    private E deletedItem;

    private static class Node<E> implements Serializable{
        E data;
        Node<E> left;
        Node<E> right;

        private Node(E data) {
            this.data = data;
            left = right = null;
        }
    }

    public BinarySearchTree() {
        root = null;
    }

    private BinarySearchTree(Node<E> root){
        this.root = root;
    }

    public BinarySearchTree(E data, BinarySearchTree<E> leftTree, BinarySearchTree<E> rightTree){
        this.root = new Node<>(data);

        if(leftTree != null){
            root.left = leftTree.root;
        }
        else {
            root.left = null;
        }

        if(rightTree != null){
            root.right = rightTree.root;
        }
        else {
            root.right = null;
        }
    }

    public boolean add(E item){
        try {
            root = add(item, root, 1);
            return true;
        } catch (AlreadyInTreeException e) {
            return false;
        }
    }

    private Node<E> add(E item, Node<E> ancestor, int level) throws AlreadyInTreeException {
        if(ancestor == null){
            ancestor = new Node<>(item);
            return ancestor;
        }
        else if(item.compareTo(ancestor.data) == 0){
            throw new AlreadyInTreeException();
        }
        else if(item.compareTo(ancestor.data) < 0){
            ancestor.left = add(item, ancestor.left, level + 1);
        }
        else{
            ancestor.right = add(item, ancestor.right, level + 1);
        }
        return ancestor;
    }

    public boolean contains(E item){
        return contains(item, root);
    }

    private boolean contains(E item, Node<E> ancestor){
        if(ancestor == null){
            return false;
        }
        else if(item.compareTo(ancestor.data) == 0){
            return true;
        }
        else if(item.compareTo(ancestor.data) < 0){
            return contains(item, ancestor.left);
        }
        else{
            return contains(item, ancestor.right);
        }
    }

    public E find(E item){
        return find(item, root);
    }

    private E find(E item, Node<E> ancestor){
        if(ancestor == null){
            return null;
        }
        else if(item.compareTo(ancestor.data) == 0){
            return ancestor.data;
        }
        else if(item.compareTo(ancestor.data) < 0){
            return find(item, ancestor.left);
        }
        else{
            return find(item, ancestor.right);
        }
    }

    public E getData(){
        if(root == null){
            return null;
        }
        return root.data;
    }

    public BinarySearchTree<E> getLeftSubtree(){
        if(root != null && root.left != null) {
            return new BinarySearchTree<>(root.left);
        }
        else{
            return null;
        }
    }

    public BinarySearchTree<E> getRightSubtree(){
        if (root != null && root.right != null) {
            return new BinarySearchTree<>(root.right);
        }
        else {
            return null;
        }
    }

    public boolean isLeaf(){
        if(root == null){
            return false;
        }
        else{
            return root.left == null && root.right == null;
        }
    }

    public boolean remove(E item){
        E result = delete(item);
        return result != null;
    }

    public E delete(E item){
        root = delete(item, root);
        return deletedItem;
    }

    private Node<E> delete(E item, Node<E> ancestor){
        if(ancestor == null){
            deletedItem = null;
            return null;
        }
        else if(item.compareTo(ancestor.data) < 0){
            ancestor.left = delete(item, ancestor.left);
        }
        else if(item.compareTo(ancestor.data) > 0){
            ancestor.right = delete(item, ancestor.right);
        }
        else{
            deletedItem = ancestor.data;
            if(ancestor.left != null && ancestor.right != null){
                ancestor.data = biggestValue(ancestor.left);
            }
            else if(ancestor.left != null){
                return ancestor.left;
            }
            else if(ancestor.right != null){
                return ancestor.right;
            }
            else{
                return null;
            }
        }
        return ancestor;
    }

    private E biggestValue(Node<E> startNode){
        if(startNode.right.right == null){
            E returnVal = startNode.right.data;
            startNode.right = startNode.right.left;
            return returnVal;
        }
        return biggestValue(startNode.right);
    }

    @Override
    public String toString() {
        return preOrderTraversal(root, new StringBuilder(), 1).toString();
    }

    private StringBuilder preOrderTraversal(Node<E> ancestor, StringBuilder s, int level) {
        for(int i = 1; i < level; i++){
            s.append(" ");
        }
        if (ancestor == null) {
            return s.append("null\n");
        }
        s.append(ancestor.data).append("\n");
        preOrderTraversal(ancestor.left, s, level + 1);
        preOrderTraversal(ancestor.right, s, level + 1);
        return s;
    }

    public String inOrderTraversal(){
        return inOrderTraversal(root, new StringBuilder()).toString();
    }

    private StringBuilder inOrderTraversal(Node<E> ancestor, StringBuilder s){
        if(ancestor == null){
            return s.append("");
        }
        inOrderTraversal(ancestor.left, s);
        s.append(ancestor.data).append(" ");
        inOrderTraversal(ancestor.right, s);
        return s;
    }

    public static BinarySearchTree<String> readBinaryTree(Scanner scanner){
        String data = scanner.next();
        if(data.equals("null")){
            return null;
        }
        BinarySearchTree<String> leftTree = readBinaryTree(scanner);
        BinarySearchTree<String> rightTree = readBinaryTree(scanner);
        return new BinarySearchTree<String >(data, leftTree, rightTree);
    }
}