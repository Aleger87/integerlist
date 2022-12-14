package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class IntegerList implements IntegerListInterface{
    private int size;
    private Integer[] Integers;
    private boolean result = false;

    public IntegerList(int size) {
        this.size = size;
        this.Integers = new Integer[size];
    }

    @Override
    public Integer add(Integer item) {
        for (int i = 0; i < Integers.length; i++) {
            if (Integers[i] == null) {
                Integers[i] = item;
                return item;
            }
        }
        throw new IndexOutOfBoundsException("В массиве нет свободной ячейки воспользуйтесь методом addPlus");
    }


    public Integer addPlus(Integer item) {
        try {
            add(item);
        } catch (IndexOutOfBoundsException e) {
            Integers = refactorArrayAdd(Integers, item);
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
        this.Integers[index] = item;
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
        for (int i = 0; i < Integers.length; i++) {
            if (Integers[i].equals(item)) {
                Integers[i] = null;
                return item;
            }
        }
        throw new IllegalArgumentException(item + " элемент отсутствует в списке");
    }

    public Integer removeMinus(Integer item) {
        remove(item);
        Integers = refactorArrayRemove(item);
        return item;
    }

    private Integer[] refactorArrayRemove(Integer item) {
        List<Integer> arrayList = new ArrayList<>(Arrays.stream(Integers).toList());
        arrayList.removeIf(Objects::isNull);
        Integer[] array = new Integer[arrayList.size()];
        arrayList.toArray(array);
        return array;
    }

    @Override

    public Integer remove(int index) {
        checkIndexOutOfBoundsException(index);
        Integer item = Integers[index];
        Integers[index] = null;
        return item;
    }

    @Override
    public boolean contains(Integer item) {

        Integer[] storageCopy = toArray();
        Arrays.stream(storageCopy).sorted();
        return binarySearch(storageCopy, item);
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < Integers.length; i++) {
            if (Integers[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = Integers.length - 1; i > 0; i--) {
            if (Integers[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        checkIndexOutOfBoundsException(index);
        return Integers[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (otherList != null || otherList.equals(Integers)) {
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
        Arrays.fill(Integers, null);
    }

    @Override
    public Integer[] toArray() {
        return new Integer[0];
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

            if (item == arr[mid]) {
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
