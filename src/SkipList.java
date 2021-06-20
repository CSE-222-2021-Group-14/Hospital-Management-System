import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Class that represent a SkipList.
 * @param <E> type of elements in this SkipList
 */
@SuppressWarnings("unchecked")
public class SkipList<E extends Comparable<E>> implements Iterable<E>{
    private static final double LOG2 = Math.log(2.0);
    private int maxLevel;
    private int maxCap;
    private int size;
    private SLNode<E> head;

    /**
     * Constructs an empty level-4 SkipList with 15 initial capacity.
     */
    public SkipList() {
        maxLevel = 4;
        maxCap = (int) (Math.pow(2, maxLevel) - 1);
        size = 0;
        head = new SLNode<>(maxLevel, null);
    }

    /**
     * Returns an iterator over the elements in this SkipList.
     * @return an iterator over the elements in this SkipList
     */
    public Iterator<E> iterator() {
        return new SkipListIter();
    }

    /**
     * Class to represent a SkipList node.
     * @param <E> type of elements in this SkipList
     */
    private static class SLNode<E>{
        SLNode<E>[] links;
        E data;

        /**
         * Constructs a SLNode with given element as its data and given integer value as its order value.
         * @param m order value
         * @param data data
         */
        public SLNode(int m, E data) {
            links = (SLNode<E>[]) new SLNode[m];
            this.data = data;
        }
    }

    /**
     * Randomly calculates the level of the new node.
     * @return level of the new node
     */
    private int logRandom(){
        Random rand = new Random();
        int r = rand.nextInt(maxCap);
        int k = (int) (Math.log(r + 1) / LOG2);
        if (k > maxLevel - 1) {
            k = maxLevel - 1;
        }
        return maxLevel - k;
    }

    /**
     * Searches the given element in this SkipList.
     * @param target element to search
     * @return an array consists the path to the given element
     */
    private SLNode<E>[] search (E target) {
        SLNode<E>[] pred = (SLNode<E>[]) new SLNode[maxLevel]; SLNode<E> current = head;
        for (int i = current.links.length - 1; i >= 0; i--) {
            while (current.links[i] != null
                    && current.links[i].data.compareTo(target) < 0) {
                current = current.links[i];
            }
            pred[i] = current;
        }
        return pred;
    }

    /**
     * Finds the given element in this SkipList.
     * @param target element to find
     * @return given element if it is present in this SkipList, otherwise null
     */
    public E find(E target) {
        SLNode<E>[] pred = search(target);
        if (pred[0].links[0] != null && pred[0].links[0].data.compareTo(target) == 0) {
            return pred[0].links[0].data;
        } else {
            return null;
        }
    }

    /**
     * Removes the given element from this SkipList.
     * @param target element to remove
     * @return given element if it is present in this SkipList, otherwise null
     */
    public E remove(E target){
        SLNode<E>[] pred = search(target);
        if(pred[0].links[0] != null && pred[0].links[0].data.compareTo(target) == 0){
            E returnVal = pred[0].links[0].data;
            int level = pred[0].links[0].links.length;
            for(int i = 0; i < level; i++){
                pred[i].links[i] = pred[i].links[i].links[i];
            }
            size--;
            return returnVal;
        }
        else{
            return null;
        }
    }

    /**
     * Adds the given element to this SkipList.
     * @param item element to add
     */
    public void add(E item){
        int level = logRandom();
        SLNode<E>[] pred = search(item);
        SLNode<E> newNode = new SLNode<>(level, item);
        for(int i = 0; i < level; i++){
            newNode.links[i] = pred[i].links[i];
            pred[i].links[i] = newNode;
        }
        size++;
        if (size > maxCap) {
            maxLevel++;
            maxCap = (int) (Math.pow(2, maxLevel) - 1);
            head.links = Arrays.copyOf(head.links, maxLevel);
        }
    }

    /**
     * Returns the number of elements in this SkipList.
     * @return the number of elements in this SkipList
     */
    public int size(){
        return size;
    }

    /**
     * Checks if the given element is present in this SkipList.
     * @param target element to check
     * @return true if the element is present in this SkipList
     */
    public boolean contains(E target){
        return find(target) != null;
    }

    /**
     * Returns a string representation of this SkipList.
     * @return a string representation of this SkipList
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        Iterator<E> iterator = new SkipListIter();
        int count = 0;
        if(size == 0){
            return "[]";
        }
        s.append("[");
        while (iterator.hasNext()){
            if (count == size - 1) {
                s.append(iterator.next()).append("]");
            }
            else {
                s.append(iterator.next()).append(", ");
            }
            count++;
        }
        return s.toString();
    }

    /**
     * Class that represents a SkipList iterator.
     */
    private class SkipListIter implements Iterator<E>{
        private SLNode<E> current = head;

        /**
         * Returns true if the iteration has more elements.
         * @return  true if the iteration has more elements
         */
        public boolean hasNext() {
            return current.links[0] != null;
        }

        /**
         * Returns the next element in the iteration.
         * @return the next element in the iteration
         */
        public E next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            return (current = current.links[0]).data;
        }
    }
}
