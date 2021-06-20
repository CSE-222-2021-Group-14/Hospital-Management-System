package SortAlgorithms;
public class ShellSort {
    /*public static <T extends Comparable<T>> void shellSort(T[] array){
        int interval = 1;

        while (interval < array.length/3){
            interval = interval*3 + 1;
        }

        while (interval > 0){
            for(int outer = interval; outer < array.length; outer++){
                T valueToInsert = array[outer];
                int inner = outer;

                while (inner > interval - 1 && array[inner - interval].compareTo(valueToInsert) >= 0){
                    array[inner] = array[inner - interval];
                    inner -= interval;
                }

                array[inner] = valueToInsert;
            }

            interval = (interval - 1)/3;
        }
    }*/
    public static <T extends Comparable<T>> void shellSort(T[] array){
        int gap = array.length/2;

        while (gap > 0) {
            for (int i = gap; i < array.length; i++) {
                int nextPos = i;
                T nextVal = array[nextPos];
                while (nextPos > gap - 1 && array[nextPos - gap].compareTo(nextVal) >= 0) {
                    array[nextPos] = array[nextPos - gap];
                    nextPos -= gap;
                }
                array[nextPos] = nextVal;
            }
            if(gap == 2){
                gap = 1;
            }
            else{
                gap /= 2.2;
            }
        }
    }
}
