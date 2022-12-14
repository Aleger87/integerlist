package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class IntegerList implements IntegerListInterface{
    private int size;
    private Integer[] integers;
    private boolean result = false;

    public IntegerList(int size) {
        this.size = size;
        this.integers = new Integer[size];
    }

    @Override
    public Integer add(Integer item) {
        for (int i = 0; i < integers.length; i++) {
            if (integers[i] == null) {
                integers[i] = item;
                return item;
            }
        }
        throw new IndexOutOfBoundsException("В массиве нет свободной ячейки воспользуйтесь методом addPlus");
    }


    public Integer addPlus(Integer item) {
        try {
            add(item);
        } catch (IndexOutOfBoundsException e) {
            integers = refactorArrayAdd(integers, item);
        }

        return item;
    }

    private Integer[] refactorArrayAdd(Integer[] Integers, Integer item) {
        Integer[] array = Arrays.copyOf(Integers, Integers.length + 1);
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                array[i] = item;
            }
        }
        return array;
    }

    @Override
    public Integer add(int index, Integer item) {
        checkIndexOutOfBoundsException(index);
        this.integers[index] = item;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        item = add(index, item);
//        checkIndexOutOfBoundsException(index);
//        this.Integers[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        for (int i = 0; i < integers.length; i++) {
            if (integers[i].equals(item)) {
                integers[i] = null;
                return item;
            }
        }
        throw new IllegalArgumentException(item + " элемент отсутствует в списке");
    }

    public Integer removeMinus(Integer item) {
        remove(item);
        integers = refactorArrayRemove(item);
        return item;
    }

    private Integer[] refactorArrayRemove(Integer item) {
        List<Integer> arrayList = new ArrayList<>(Arrays.stream(integers).toList());
        arrayList.removeIf(Objects::isNull);
        Integer[] array = new Integer[arrayList.size()];
        arrayList.toArray(array);
        return array;
    }

    @Override

    public Integer remove(int index) {
        checkIndexOutOfBoundsException(index);
        Integer item = integers[index];
        integers[index] = null;
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        Integer[] integersCopy = toArray();
        sortInsertion(integersCopy);
        return binarySearch(integersCopy, item);
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < integers.length; i++) {
            if (integers[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = integers.length - 1; i > 0; i--) {
            if (integers[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        checkIndexOutOfBoundsException(index);
        return integers[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (otherList != null || otherList.equals(integers)) {
            return true;
        }
        throw new NullPointerException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        Arrays.fill(integers, null);
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(integers, size);
    }


    @Override
    public String toString() {
        return Arrays.toString(integers);
    }

    private void checkIndexOutOfBoundsException(int index) {
        if (index > size - 1) {
            throw new IndexOutOfBoundsException("Индекс за пределами массива");
        }
    }

   public static void sortInsertion(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    private boolean binarySearch(Integer[] arr, Integer item) {
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item.intValue() == arr[mid]) {
                return true;
            }

            if (item < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }
}
