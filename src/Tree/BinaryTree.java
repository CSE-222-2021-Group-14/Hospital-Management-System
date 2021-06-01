package Tree;

import java.util.*;

public class BinaryTree<E> {
    /** Class to encapsulate a tree node. */
    protected  static class Node<E> {
        // Data Fields
        /** The information stored in this node. */
        protected E data;
        /** Reference to the left child. */
        protected Node<E> left;
        /** Reference to the right child. */
        protected Node<E> right;
        /** Reference to the parent. */
        protected Node<E> parent;


        // Constructors
        /** Construct a node with given data and no children.
            @param data The data to store in this node
         */
        public Node(E data) {
            this.data = data;
            left = null;
            right = null;
            parent = null;
        }
        public int size(){
            int leftS = 0;
            int rightS = 0;
            if(left!=null)
                leftS = left.size();
            if(right!=null)
                rightS = right.size();
            if(data!=null)
                return 1+leftS + rightS;
            else
                return leftS+rightS;
        }
        // Methods
        /** Return a string representation of the node.
            @return A string representation of the data fields
         */
        public String toString(){
            return data.toString();
        }
    }

    // Data Fields
    /** The root of the binary tree */
    protected Node<E> root;
    /** Size of Tree. */
    protected int size ;

    // Constructors
    /** Construct an empty tree */
    public BinaryTree() {
        root = null;
        size = 0;
    }
    /** Construct a new binary tree with given node.
        @param root The node to store in this tree
     */
    protected BinaryTree(Node<E> root) {
        this.root = root;
        size += root.size();
    }
    /** Constructs a new binary tree with data in its root leftTree
        as its left subtree and rightTree as its right subtree.
     */
    public BinaryTree(E data, BinaryTree<E> leftTree,
                      BinaryTree<E> rightTree){
        root = new Node<>(data);
        if (leftTree != null) {
            root.left = leftTree.root;
            root.left.parent = root;
            size += leftTree.size;
        }
        else
            root.left = null;
        if (rightTree != null) {
            root.right = rightTree.root;
            root.right.parent = root;
            size += rightTree.size;
        }
        else
            root.right = null;
    }

    /** Return the left subtree.
        @return The left subtree or null if either the root or
        the left subtree is null
     */
    public BinaryTree<E> getLeftSubtree(){
        if(root != null && root.left != null)
            return new BinaryTree<E>(root.left);
        else
            return null;
    }

    /** Return the right subtree.
     @return The right subtree or null if either the root or
     the right subtree is null
     */
    public BinaryTree<E> getRightSubtree(){
        if(root != null && root.right != null)
            return new BinaryTree<E>(root.right);
        else
            return null;
    }


    /** Determine whether this tree is a leaf.
        @return true if the root has no children
     */
    public  boolean isLeaf(){
        return (root.left == null && root.right == null );
    }

    /** Return a string representation of the tree.
     @return A string representation of the data fields
     */
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        preOrderTraverse(root,1,stringBuilder);
        return stringBuilder.toString();
    }

    /** Perform a preorder traversal.
        @param node The local root
        @param depth The depth
        @param sb The string buffer to save the output
     */
    private void preOrderTraverse(Node<E> node, int depth,
                                  StringBuilder sb){
        for (int i=1; i<depth; i++)
            sb.append(" ");
        if(node == null)
            sb.append("null\n");
        else{
            sb.append(node.toString());
            sb.append("\n");
            preOrderTraverse(node.left, depth+1, sb);
            preOrderTraverse(node.right, depth+1, sb);
        }
    }

    /** Method to read a binary tree.
        pre: The input consists of a preorder traversal
            of the binary tree. The line "null" indicates a null tree.
        @param scan the Scanner attached to the input file.
        @return The binary tree
     */
    public static BinaryTree<String> readBinaryTree(Scanner scan){
        // Read a line and trim leading and trailing spaces.
        String data = scan.next();
        if (data.equals("null"))
            return null;
        else{
            BinaryTree<String> leftTree = readBinaryTree(scan);
            BinaryTree<String> rightTree = readBinaryTree(scan);
            return new BinaryTree<String>(data,leftTree,rightTree);
        }
    }
    public Set<E> entrySet() {
        return new TreeSet<E>();
    }

    /** Inner class to implement the set view. */
    private class TreeSet<E> extends AbstractSet<E> {


        /**
         * Return the size of the set.
         */
        @Override
        public int size() {
            return size;
        }

        /** Return an iterator over the set. */
        @Override
        public Iterator<E> iterator() {
            return new TreeIterator(root);
        }
    }
    public static class TreeIterator<E> implements Iterator{
        private Node<E> next;

        public TreeIterator(Node<E> root) {
            next = root;
            if(next == null)
                return;

            while (next.left != null)
                next = next.left;
        }

        public boolean hasNext(){
            return next != null;
        }

        public E next(){
            if(!hasNext()) throw new NoSuchElementException();
            Node<E> r = next;

            // If you can walk right, walk right, then fully left.
            // otherwise, walk up until you come from left.
            if(next.right != null) {
                next = next.right;
                while (next.left != null)
                    next = next.left;
                return r.data;
            }

            while(true) {
                if(next.parent == null) {
                    next = null;
                    return r.data;
                }
                if(next.parent.left == next) {
                    next = next.parent;
                    return r.data;
                }
                next = next.parent;
            }
        }
    }
}
