package SortAlgorithms;

import java.io.Serializable;

@SuppressWarnings("unchecked")
public class MergeSort implements Serializable {
    public static <T extends Comparable<T>> T[] mergeSort(T[] array){
        if(array.length == 1){
            return array;
        }

        int half = array.length/2;
        T[] arrayOne = (T[]) new Comparable[half];
        T[] arrayTwo = (T[]) new Comparable[array.length - half];

        System.arraycopy(array, 0, arrayOne, 0, arrayOne.length);
        System.arraycopy(array, arrayOne.length, arrayTwo, 0, arrayTwo.length);

        arrayOne = mergeSort(arrayOne);
        arrayTwo = mergeSort(arrayTwo);

        return merge(arrayOne, arrayTwo);
    }

    private static <T extends Comparable<T>> T[] merge(T[] a, T[] b){
        T[] c = (T[]) new Comparable[a.length + b.length];
        int posB = 0;
        int posA = 0;
        int posC = 0;

        /*while (a[a.length - 1] != null && b[b.length - 1] != null){
            if(a[posA].compareTo(b[posB]) > 0){
                c[posC] = b[posB];
                b[posB] = null;
                posB++;
                posC++;
            }
            else{
                c[posC] = a[posA];
                a[posA] = null;
                posA++;
                posC++;
            }
        }

        while (a[a.length - 1] != null){
            c[posC] = a[posA];
            a[posA] = null;
            posA++;
            posC++;
        }

        while (b[b.length - 1] != null){
            c[posC] = b[posB];
            b[posB] = null;
            posB++;
            posC++;
        }*/
        while (posA != a.length && posB != b.length){
            if(a[posA].compareTo(b[posB]) < 0){
                c[posC++] = a[posA++];
            }
            else{
                c[posC++] = b[posB++];
            }
        }

        while (posA != a.length){
            c[posC++] = a[posA++];
        }

        while (posB != b.length){
            c[posC++] = b[posB++];
        }

        return c;
    }
}
