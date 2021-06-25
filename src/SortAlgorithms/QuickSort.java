package SortAlgorithms;

import java.io.Serializable;

public class QuickSort implements Serializable {
    public static <T extends Comparable<T>> void quickSort(T[] array, int low, int high){
        if(low < high){
            int pivotPos = partition(array, low, high);
            quickSort(array, low, pivotPos - 1);
            quickSort(array, pivotPos + 1, high);
        }
    }

    /*private static <T extends Comparable<T>> int partition(T[] array, int low, int high){
        T pivot = array[low];
        T temp;
        int leftWall = low;
        int pivotIndex = low;

        for(int i = low + 1; i < high; i++){
            if(array[i].compareTo(pivot) < 0){
                if(array[leftWall].equals(pivot)){
                    pivotIndex = i;
                }
                temp = array[i];
                array[i] = array[leftWall];
                array[leftWall] = temp;
                leftWall++;
            }
        }

        array[pivotIndex] = array[leftWall];
        array[leftWall] = pivot;

        return leftWall;
    }*/
    private static <T extends Comparable<T>> int partition(T[] array, int low, int high){
        T pivot = array[high];
        T temp;
        int smallerIndex = low;

        for(int i = low; i < high; i++){
            if(array[i].compareTo(pivot) < 0){
                temp = array[i];
                array[i] = array[smallerIndex];
                array[smallerIndex] = temp;
                smallerIndex++;
            }
        }

        array[high] = array[smallerIndex];
        array[smallerIndex] = pivot;
        return smallerIndex;
    }
}
