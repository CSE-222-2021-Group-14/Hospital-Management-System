package SortAlgorithms;
public class SelectionSort {
    public static <T extends Comparable<T>> void selectionSort(T[] array){
        for(int fill = 0; fill < array.length - 1; fill++){
            int posMin = fill;
            for(int next = fill + 1; next < array.length; next++){
                if(array[next].compareTo(array[posMin]) < 0){
                    posMin = next;
                }
            }
            T temp = array[fill];
            array[fill] = array[posMin];
            array[posMin] = temp;
        }
    }
}
