package SortAlgorithms;

import java.io.Serializable;

public class InsertionSort implements Serializable {
    public static <T extends Comparable<T>> void insertionSort(T[] array){
        for(int i = 1; i < array.length; i++){
            int nextPos = i;
            T nextVal = array[nextPos];
            while (nextPos > 0 && array[nextPos - 1].compareTo(nextVal) > 0){
                array[nextPos] = array[nextPos - 1];
                nextPos--;
            }
            array[nextPos] = nextVal;
        }
    }
}
