package lesson2;

import java.util.Arrays;
import java.util.Random;

public class Sorting {

    private static  <T> void swap(int index1, int index2, T[] list) {
        T temp = list[index1];
        list[index1] = list[index2];
        list[index2] = temp;
    }

    private static <T extends Comparable> boolean less(T item1, T item2) {
        return item1.compareTo(item2) < 0;
    }

    public static <T extends Comparable> void selectionSort(T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int iMin = i;
            for (int j = i; j < array.length; j++) {
                if (less(array[j], array[iMin])) {
                    iMin = j;
                }
            }
            swap(i, iMin, array);
        }
    }


    public static <T extends Comparable> void insertionSort(T[] array) {
        T key;
        for (int i = 1; i < array.length; i++) {
            int j = i;
            key = array[i];
            while (j > 0 && less(key, array[j - 1])) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = key;
        }
    }

    public static <T extends Comparable> void bubbleSort(T[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (less(array[j + 1], array[j])) {
                    swap(j + 1, j, array);
                }
            }
        }
    }

    public static <T extends Comparable> void bubbleSortOpt(T[] array) {
        boolean isSwap;
        for (int i = array.length - 1; i > 0; i--) {
            isSwap = false;
            for (int j = 0; j < i; j++) {
                if (less(array[j + 1], array[j])) {
                    swap(j + 1, j, array);
                    isSwap = true;
                }
            }

            if (!isSwap) {
                return;
            }
        }
    }

    private static void fillIntegerArray (Integer[] array){
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = (Integer) random.nextInt(100);
        }
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[100000];
        fillIntegerArray(array);

        Integer[] array1 = Arrays.copyOf(array, array.length);
        Integer[] array2 = Arrays.copyOf(array, array.length);
        Integer[] array3 = Arrays.copyOf(array, array.length);

        // 1.
        System.out.println("***BubbleSort***");
        long timeBefore = System.currentTimeMillis();
        bubbleSort(array);
        long timeAfter = System.currentTimeMillis();
        System.out.println("BubbleSort time: " + (timeAfter - timeBefore) + " ms");
        System.out.println();

        // 1.1
        System.out.println("***BubbleSortOpt***");
        timeBefore = System.currentTimeMillis();
        bubbleSortOpt(array1);
        timeAfter = System.currentTimeMillis();
        System.out.println("BubbleSortOpt time: " + (timeAfter - timeBefore) + " ms");
        System.out.println();

        // 2.
        System.out.println("***InsertionSort***");
        timeBefore = System.currentTimeMillis();
        insertionSort(array2);
        timeAfter = System.currentTimeMillis();
        System.out.println("InsertionSort time: " + (timeAfter - timeBefore) + " ms");
        System.out.println();

        // 3.
        System.out.println("***SelectionSort***");
        timeBefore = System.currentTimeMillis();
        selectionSort(array3);
        timeAfter = System.currentTimeMillis();
        System.out.println("SelectionSort time: " + (timeAfter - timeBefore) + " ms");
    }
}

