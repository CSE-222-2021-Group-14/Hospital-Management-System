package SortAlgorithms;
public class HeapSort {
    public static <T extends Comparable<T>> void heapSort(T[] array){
        T temp;
        buildMaxHeap(array);
        for(int i = array.length - 1; i > 0; i--){
            temp = array[i];
            array[i] = array[0];
            array[0] = temp;
            heapify(array, i, 0);
        }
    }

    private static <T extends Comparable<T>> void buildMaxHeap(T[] array){
        for(int i = array.length/2 - 1; i >= 0; i--){
            heapify(array, array.length, i);
        }
    }

    private static <T extends Comparable<T>> void heapify(T[] array, int length, int parent){
        int child1 = parent*2 + 1;
        int child2 = parent*2 + 2;
        int check1 = 0;
        int check2 = 0;

        if(child1 < length && array[parent].compareTo(array[child1]) < 0){
            check1++;
        }
        if(child2 < length && array[parent].compareTo(array[child2]) < 0){
            check2++;
        }
        if(check1 == 1 || check2 == 1){
            if(check1 == 1 && check2 == 1){
                if(array[child1].compareTo(array[child2]) < 0){
                    T temp = array[parent];
                    array[parent] = array[child2];
                    array[child2] = temp;
                    heapify(array, length, child2);
                }
                else{
                    T temp = array[parent];
                    array[parent] = array[child1];
                    array[child1] = temp;
                    heapify(array, length, child1);
                }
            }
            else if(check1 == 1){
                T temp = array[parent];
                array[parent] = array[child1];
                array[child1] = temp;
                heapify(array, length, child1);
            }
            else{
                T temp = array[parent];
                array[parent] = array[child2];
                array[child2] = temp;
                heapify(array, length, child2);
            }
        }
    }
}
