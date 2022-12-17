package org.example;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        IntegerList integerList = new IntegerList(2);
        integerList.add(0);
        integerList.add(2);
        System.out.println(integerList.size());
        integerList.toExpand();
        System.out.println(integerList.size());
        integerList.add(5);
        System.out.println(integerList.size());
        integerList.remove(2);
        System.out.println(integerList.size());
        integerList.addPlus(-1);
        System.out.println(integerList.size());
        integerList.addPlus(-5);
        System.out.println(integerList.size());


        System.out.println(integerList);
        integerList.sortInsertion();
        System.out.println(integerList);

        System.out.println(integerList.contains(-6));

        /*Integer indexes = 100_000;
        Integer[] arrBubble = createArr(indexes);
        Integer[] arrInsertion = Arrays.copyOf(arrBubble,indexes);
        Integer[] arrSelection = Arrays.copyOf(arrBubble,indexes);
        sortBubble(arrBubble);
        sortSelection(arrInsertion);*/
        //sortInsertion(arrSelection);
    }

    private static Integer[] createArr(int i) {
        Integer[] arr = new Integer[i];
        for (int j = 0; j < arr.length; j++) {
            arr[j] = getRandomIntegerBetweenRange(0, i);
        }
        return arr;
    }
    public static Integer getRandomIntegerBetweenRange(Integer min, Integer max){
        Integer x = (int)(Math.random()*((max-min)+1))+min;
        return x;
    }

   /* private static void sortInsertion(Integer[] arr) {
        long start = System.currentTimeMillis();
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
        System.out.println("Вставкой");
        System.out.println(System.currentTimeMillis() - start);
    }*/

    private static void sortSelection(Integer[] arr) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
        System.out.println("Выбором");
        System.out.println(System.currentTimeMillis() - start);
    }

    private static void  sortBubble(Integer[] arr) {
        long start = System.currentTimeMillis();
        for (Integer i = 0; i < arr.length - 1; i++) {
            for (Integer j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
        System.out.println("Пузырьком");
        System.out.println(System.currentTimeMillis() - start);
    }

    private static void swapElements(Integer[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }
}