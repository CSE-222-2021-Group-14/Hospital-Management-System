package SortAlgorithms;
public class BubbleSort {
    public static <T extends Comparable<T>> void bubbleSort(T[] array){
        boolean exchange;
        int round = 1;
        do{
            exchange = false;
            for(int i = 0; i < array.length - round; i++){
                if(array[i].compareTo(array[i + 1]) > 0){
                    T temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    exchange = true;
                }
            }
            round++;
        } while (exchange);
    }
}
