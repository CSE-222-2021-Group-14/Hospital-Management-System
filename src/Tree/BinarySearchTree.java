package Tree;

import java.util.Comparator;

public class BinarySearchTree<E extends Comparable<? super E>> extends BinaryTree<E> implements SearchTree<E>{
    // Data Fields
    /** Return value from the public add method. */
    protected boolean addReturn;
    /** Return value from the public delete method. */
    protected E deleteReturn;
    /** Starter method add.
        pre: The object to insert must implement the
            Comparable interface.
       @param item The object being inserted
       @return true if the object is inserted, false
                if the object already exists in the tree
     */
    @Override
    public boolean add(E item) {
        root = add(root,item);
        if(addReturn)
            size++;
        return addReturn;
    }

    /** Recursive add method.
        post: The data field addReturn is set true if the item is added to
            the tree, false if the item is already in tree.
        @param localRoot The local root of the subtree
        @param  item The object to be inserted
        @return The new local root that now contains the
                inserted item
     */
    private Node<E> add(Node<E> localRoot, E item){
        if (localRoot == null){
            // item is not in the tree - insert it.
            addReturn = true;
            return new Node<E>(item);
        }
        else if (item.compareTo(localRoot.data) == 0){
            // item equal to localRoot.data
            addReturn = false;
            return localRoot;
        }
        else if (item.compareTo(localRoot.data) < 0){
            // item is less than localRoot.data
            localRoot.left = add(localRoot.left, item);
            localRoot.left.parent = localRoot;
            return localRoot;
        }
        else {
            // item is greater than localRoot.data
            localRoot.right = add(localRoot.right, item);
            localRoot.right.parent = localRoot;
            return localRoot;
        }
    }
    /** Method contains.
     @param target The Comparable object being sought
     @param target The Comparator to compare objects
     @return true if object found, otherwise false
     */
    public boolean containsByComparator(E target, Comparator<E> comparator) {
        if(root == null || find(target) == null)
            return false;
        if(root.data == null)
            return false;
        return (comparator.compare(target, find(target))==0);
    }
    /** Method contains.
         @param target The Comparable object being sought
         @return true if object found, otherwise false
     */
    @Override
    public boolean contains(E target) {
        if(root == null)
            return false;
        if(root.data == null)
            return false;
        return (target.compareTo(find(target)) == 0);
    }
    /** Started method find.
        pre: The target object must implement
            the Comparable interface.
        @param target The Comparable object being sought
        @return The object, if found, otherwise null
     */
    @Override
    public E find(E target) {
        return find(root,target);
    }
    /** Recursive find method.
        @param  localRoot The local subtree's root
        @param target The object being sought
        @return The object, if found, otherwise null
     */
    private E find(Node<E> localRoot, E target){
        if ((localRoot == null))
            return null;
        // Compare the target with the data field at the root.
        int compResult = target.compareTo(localRoot.data);
        if (compResult == 0)
            return localRoot.data;
        else if (compResult<0)
            return find(localRoot.left,target);
        else
            return find(localRoot.right, target);
    }
    /** Started method delete.
        post: The object is not in the tree.
        @param target The object to be deleted
        @return The object deleted from the tree
                or null if the object was not in the tree
        @throws ClassCastException if target does not implement
                Comparable
     */
    @Override
    public E delete(E target) {
        root = delete(root,target);
        root.parent = null;
        if(deleteReturn!=null)
            size--;
        return deleteReturn;
    }
    private Node<E> delete(Node<E> localRoot, E item){
        if (localRoot == null){
            // item is not in the tree.
            deleteReturn = null;
            return null;
        }

        // Search for item to delete.
        int compResult = item.compareTo(localRoot.data);
        if (compResult < 0){
            // item is smaller than localRoot.data
            localRoot.left = delete(localRoot.left, item);
            return localRoot;
        }
        else if (compResult>0){
            // item is larger than localRoot.data
            localRoot.right = delete(localRoot.right, item);
            return localRoot;
        }
        else {
            // item is at local root.
            deleteReturn = localRoot.data;
            if (localRoot.left == null){
                // If there is no left child, return right child
                // which can also be null.
                return localRoot.right;
            }
            else {
                // Search for inorder predecessor (ip )and
                // replace deleted node's data with ip.
                localRoot.data = findLargestChild(localRoot.left);
                return  localRoot;
            }
        }
    }
    /** Find the node that is the
        inorder predecessor and replace it
        with its left child (if any).
        post: The inorder predecessor is removed from the tree.
        @param parent The parent of possible inorder predecessor (ip)
        @return The data in the ip
     */
    private E findLargestChild(Node<E> parent){
        // If the right child has no right child, it is
        // the inorder predecessor.
        if (parent.right.right == null){
            E returnValue = parent.right.data;
            parent.right = parent.right.left;
            return returnValue;
        }
        else
            return findLargestChild(parent.right);
    }
    /**  Method remove.
         @param target The object to be removed
         @return The true if object removed from the tree
         or false if the object was not in the tree
         @throws ClassCastException if target does not implement
                Comparable
     */
    @Override
    public boolean remove(E target) {
        return (target.compareTo(delete(target)) == 0);
    }
}
