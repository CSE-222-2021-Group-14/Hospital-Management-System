package Tree;

/** Self-balancing binary search tree.*/
public class BalancedBinarySearchTree<E extends Comparable<? super E>>
        extends BinarySearchTreeWithRotate<E> {

    /** Class to represent an AVL Node. It extends the
        BinaryTree.Node by adding the balance field. */
    private static class BBSTNode<E> extends BinaryTree.Node<E> {
        /** Constant to indicate left-heavy */
        public static final int LEFT_HEAVY = -1;
        /** Constant to indicate balanced */
        public static final int BALANCED = 0;
        /** Constant to indicate right-heavy */
        public static final int RIGHT_HEAVY = 1;
        /** balance is right subtree height - left subtree height */
        private int balance;

        // Methods

        /** Construct a node with the given item as the data field.
           @param item The data field
         */
        public BBSTNode(E item) {
            super(item);
            this.balance = BALANCED;
        }

        /** Return a string representation of this object.
            The balance value is appended to the contents.
            @return String representation of this object
         */
        @Override
        public String toString() {
            return balance + ": " + super.toString();
        }
    }
    // Data Fields
    /** Flag to indicate that height of tree has increased. */
    private boolean increase;

    /** add started method.
        pre: the item to insert implements the Comparable interface.
        @param item The item being inserted.
        @return true if the object is inserted; false
                if the object already exists in the tree
        @throws ClassCastException if item is not Comparable
     */
    @Override
    public boolean add(E item) {
        increase = false;
        root = add((BBSTNode<E>) root,item);
        return addReturn;
    }

    /** Recursive add method. Inserts the given object into the tree.
        post: addReturn is set true if the item is inserted,
            false if the item is already in the tree.
        @param localRoot The local root of the subtree.
        @param item The object to be inserted
        @return The new local root of the subtree with the item
                inserted
     */
    private BBSTNode<E> add(BBSTNode<E> localRoot, E item){
        if (localRoot == null){
            addReturn = true;
            increase = true;
            return new BBSTNode<E>(item);
        }
        if (item.compareTo(localRoot.data) == 0){
            // Item is already in the tree.
            increase = false;
            addReturn = false;
            return localRoot;
        }
        else if (item.compareTo(localRoot.data) < 0){
            // item < data
            localRoot.left = add((BBSTNode<E>) localRoot.left, item);
            if (increase) {
                decrementBalance(localRoot);
                if (localRoot.balance < BBSTNode.LEFT_HEAVY){
                    increase = false;
                    return rebalanceLeft(localRoot);
                }
            }
            return localRoot; // Rebalance not needed.
        }
        else {
            // item > data
            localRoot.right = add((BBSTNode<E>) localRoot.right, item);
            if (increase) {
                incrementBalance(localRoot);
                if (localRoot.balance > BBSTNode.RIGHT_HEAVY){
                    increase = true;
                    return rebalanceRight(localRoot);
                }
            }
            return localRoot; // Rebalance not needed.
        }
    }

    /** Method to rebalance left.
        pre: localRoot is the root of an AVL subtree that is
            critically left-heavy.
        post: Balance is restored.
        @param localRoot Root of the AVL subtree
                that needs rebalancing
        @return a new localRoot
     */
    private BBSTNode<E> rebalanceLeft(BBSTNode<E> localRoot){
        // Obtain reference to left child.
        BBSTNode<E> leftChild = (BBSTNode<E>) localRoot.left;
        // See whether left-right heavy.
        if (leftChild.balance > BBSTNode.BALANCED){
            // Obtain reference to left-right child.
            BBSTNode<E> leftRightChild = (BBSTNode<E>) leftChild.right;
            /** Adjust the balances to be their new values after
                the rotations are performed.
             */
            if (leftRightChild.balance < BBSTNode.BALANCED){
                leftChild.balance = BBSTNode.BALANCED;
                leftRightChild.balance = BBSTNode.BALANCED;
                localRoot.balance = BBSTNode.RIGHT_HEAVY;
            }
            else {
                leftChild.balance = BBSTNode.LEFT_HEAVY;
                leftRightChild.balance = BBSTNode.BALANCED;
                localRoot.balance = BBSTNode.BALANCED;
            }
            // Perform left rotation.
            localRoot.left = rotateLeft(leftChild);
        }else { // Left-Left case
            /** In this case the leftChild (the new root)
                and the root (new right child) will both be balanced
                after the rotation.
             */
            leftChild.balance = BBSTNode.BALANCED;
            localRoot.balance = BBSTNode.BALANCED;
        }
        // Now rotate the local right.
        return (BBSTNode<E>) rotateRight(localRoot);
    }

    /** Method to rebalance right.
     pre: localRoot is the root of an AVL subtree that is
     critically right-heavy.
     post: Balance is restored.
     @param localRoot Root of the AVL subtree
     that needs rebalancing
     @return a new localRoot
     */
    private BBSTNode<E> rebalanceRight(BBSTNode<E> localRoot){
        // Obtain reference to right child.
        BBSTNode<E> rightChild = (BBSTNode<E>) localRoot.right;
        // See whether right-left heavy.
        if (rightChild.balance < BBSTNode.BALANCED){
            // Obtain reference to right-left child.
            BBSTNode<E> rightLeftChild = (BBSTNode<E>) rightChild.left;
            /** Adjust the balances to be their new values after
             the rotations are performed.
             */
            if (rightLeftChild.balance > BBSTNode.BALANCED){
                rightChild.balance = BBSTNode.BALANCED;
                rightLeftChild.balance = BBSTNode.BALANCED;
                localRoot.balance = BBSTNode.LEFT_HEAVY;
            }
            else {
                rightChild.balance = BBSTNode.RIGHT_HEAVY;
                rightLeftChild.balance = BBSTNode.BALANCED;
                localRoot.balance = BBSTNode.BALANCED;
            }
            // Perform right rotation.
            localRoot.right = rotateRight(rightChild);
        }else { // Right-Right case
            /** In this case the rightChild (the new root)
             and the root (new left child) will both be balanced
             after the rotation.
             */
            rightChild.balance = BBSTNode.BALANCED;
            localRoot.balance = BBSTNode.BALANCED;
        }
        // Now rotate the local left.
        return (BBSTNode<E>) rotateLeft(localRoot);
    }

    /** Method to decrement balance.
        @param node Node that need to its balance be decremented
     */
    private void decrementBalance(BBSTNode<E> node){
        // Decrement the balance.
        node.balance--;
        if (node.balance == BBSTNode.BALANCED){
            /** If now balanced, overall height has not increased. */
            increase = false;
        }
    }
    /** Method to increment balance.
     @param node Node that need to its balance be incremented
     */
    private void incrementBalance(BBSTNode<E> node){
        // Increment the balance.
        node.balance++;
        if (node.balance == BBSTNode.BALANCED){
            /** If now balanced, overall height has not increased. */
            increase = false;
        }
    }

    /** remove started method.
     pre: the target to remove implements the Comparable interface.
     @param target The target being removed.
     @return true if the object is removed; false
        if the object is not exists in the tree
     @throws ClassCastException if item is not Comparable
     */
    @Override
    public E delete(E target) {
        increase = false;
        root = delete((BBSTNode<E>) root,target);
        return deleteReturn;
    }
    public BBSTNode<E> delete(BBSTNode<E> localRoot, E target){
        if (localRoot == null){
            increase = false;
            deleteReturn = null;
            return null;
        }
        if (target.compareTo(localRoot.data) == 0){
            // target is at local root.
            increase = false;
            deleteReturn = localRoot.data;
            if (localRoot.left == null){
                // If there is no left child, return right child
                // which can also be null.
                return (BBSTNode<E>) localRoot.right;
            }
            else {
                // Search for inorder predecessor (ip )and
                // replace deleted node's data with ip.
                localRoot.data = findLargestChild(localRoot.left);
                return  localRoot;
            }
        }
        else if (target.compareTo(localRoot.data) < 0){
            // target < data
            localRoot.left = delete((BBSTNode<E>) localRoot.left, target);
            if (!increase) {
                incrementBalance(localRoot);
                if (localRoot.balance < BBSTNode.LEFT_HEAVY){
                    increase = true;
                    return rebalanceLeft(localRoot);
                }
            }
            return localRoot; // Rebalance not needed.
        }
        else{
            // item > data
            localRoot.right = delete((BBSTNode<E>) localRoot.right, target);
            if (!increase) {
                decrementBalance(localRoot);
                if (localRoot.balance > BBSTNode.RIGHT_HEAVY){
                    increase = false;
                    return rebalanceRight(localRoot);
                }
            }
            return localRoot; // Rebalance not needed.
        }
    }
}
