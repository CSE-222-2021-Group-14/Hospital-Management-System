import java.io.Serializable;
import java.util.*;

public class OrderedList<E extends Comparable<E> > implements Serializable, Iterable<E>, List<E> {
    private LinkedList<E> theData;

    public OrderedList() {
        theData = new LinkedList<>();
    }

    public int size() {
        return theData.size();
    }

    public boolean isEmpty() {
        return theData.isEmpty();
    }

    public boolean contains(Object o) {
        return theData.contains(o);
    }

    public Iterator<E> iterator() {
        return theData.iterator();
    }

    public Object[] toArray() {
        Object[] array = new Object[theData.size()];
        int i = 0;
        for(E data : theData){
            array[i++] = data;
        }
        return array;
    }

    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if(a.length < size()){
            a = (T[])new Object[size()];
        }
        int i = 0;
        for(E data : theData){
            a[i++] = (T) data;
        }
        if(a.length > size()){
            a[size()] = null;
        }
        return a;
    }

    @Override
    public boolean add(E e) {
        ListIterator<E> listIterator = theData.listIterator();

        if(size() == 0){
            theData.add(e);
            return true;
        }
        else if(e.compareTo(theData.getFirst()) <= 0){
            theData.addFirst(e);
        }
        else if(e.compareTo(theData.getLast()) >= 0){
            theData.addLast(e);
        }
        else {
            while (listIterator.hasNext()){
                if(e.compareTo(listIterator.next()) < 0){
                    System.out.println("4");
                    listIterator.previous();
                    listIterator.add(e);
                    return true;
                }
            }
        }

        return true;
    }

    @Override
    public boolean remove(Object o) {
        return theData.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return theData.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for(E data : c){
            add(data);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return theData.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return removeIf(e -> !c.contains(e));
    }

    @Override
    public void clear() {
        theData.clear();
    }

    @Override
    public E get(int index) {
        return theData.get(index);
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove(int index) {
        return theData.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return theData.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return theData.lastIndexOf(o);
    }

    @Override
    public ListIterator<E> listIterator() {
        return new OrderedListIter();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new OrderedListIter(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        OrderedList<E> subList = new OrderedList<>();
        ListIterator<E> iterator = listIterator(fromIndex);
        for(int i = fromIndex; i < toIndex; i++){
            subList.add(iterator.next());
        }
        return subList;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        ListIterator<E> iter = listIterator();
        if(!iter.hasNext()) {
            return "[]";
        }
        stringBuilder.append("[");
        while (iter.hasNext()){
            E e = iter.next();
            stringBuilder.append(e);
            if(!iter.hasNext()){
                stringBuilder.append("]");
            }
            else{
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.toString();
    }

    private class OrderedListIter implements ListIterator<E>{
        private ListIterator<E> iterator = theData.listIterator();

        public OrderedListIter(int index) {
            for(int i = 0; i < index; i++){
                iterator.next();
            }
        }

        public OrderedListIter() {
            this(0);
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public E next() {
            return iterator.next();
        }

        @Override
        public boolean hasPrevious() {
            return iterator.hasPrevious();
        }

        @Override
        public E previous() {
            return iterator.previous();
        }

        @Override
        public int nextIndex() {
            return iterator.nextIndex();
        }

        @Override
        public int previousIndex() {
            return iterator.previousIndex();
        }

        @Override
        public void remove() {
            iterator.remove();
        }

        @Override
        public void set(E e) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException();
        }
    }
}
