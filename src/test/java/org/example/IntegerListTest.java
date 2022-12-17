package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class integerListTest {

    private  IntegerList integerListImpl = new IntegerList(3);
    private  Integer[] integerList = new Integer[3];
    private boolean ifResult = false;

    @BeforeEach
    public  void setUp() {
        integerList[0] = 100;
        integerList[1] = 300;
        integerList[2] = 200;

        integerListImpl.add(100);
        integerListImpl.add(300);
        integerListImpl.add(200);
    }

    @Test
    void add() {
        Assertions.assertEquals(integerListImpl.toString(), Arrays.toString(integerList));
    }

    @Test
    void addPlus() {
        Integer integer = 999;
        Integer[] integers = Arrays.copyOf(integerList, integerList.length + 1);
        for (int i = 0; i < integers.length; i++) {
            if (integers[i] == null) {
                integers[i] = integer;
            }
        }
        integerListImpl.addPlus(integer);
        Assertions.assertEquals(integerListImpl.toString(), Arrays.toString(integers));
    }

    @Test
    void testAdd() {
        int index = 0;
        Integer integer = 100;
        integerListImpl.add(index, integer);
        integerList[index] = integer;
        Assertions.assertEquals(integerListImpl.toString(), Arrays.toString(integerList));
    }

    @Test()
    void testAddIndexOutOfBoundsException() {
        int index = 3;
        Integer integer = 300;
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            integerListImpl.add(index, integer);
        });

    }

    @Test
    void set() {
        testAdd();
    }

    @Test()
    void testSetIndexOutOfBoundsException() {
        testAddIndexOutOfBoundsException();
    }

    @Test
    void remove() {
        Integer integer = 300;
        for (int i = 0; i < integerList.length; i++) {
            if (integerList[i].equals(integer)) {
                integerList[i] = null;
            }
        }
        integerListImpl.remove(integer);
        Assertions.assertEquals(integerListImpl.toString(), Arrays.toString(integerList));
    }

    @Test
    void testRemoveIllegalArgumentException() {
        Integer integer = 400;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            integerListImpl.remove(integer);
        });
    }

    @Test
    void testRemove() {
        int index = 0;
        integerList[index] = null;
        integerListImpl.remove(0);
        Assertions.assertEquals(integerListImpl.toString(), Arrays.toString(integerList));

    }

    @Test
    void removeMinus() {
        Integer integer = 300;
        for (int i = 0; i < integerList.length; i++) {
            if (integerList[i] == integer.intValue()) {
                integerList[i] = null;
            }
        }
        List<Integer> arrayList = new ArrayList<>(Arrays.stream(integerList).toList());
        arrayList.removeIf(Objects::isNull);
        Integer[] integerListNew = new Integer[arrayList.size()];
        arrayList.toArray(integerListNew);
        integerListImpl.removeMinus(integer);
        Assertions.assertEquals(integerListImpl.toString(), Arrays.toString(integerListNew));

    }

    @Test
    void testRemoveIndexOutOfBoundsException() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            integerListImpl.remove(5);
        });

    }

    @ParameterizedTest
    @MethodSource("parametersForTests")
    void contains(Integer integer) {
        boolean expected = integerListImpl.contains(integer);
        Assertions.assertTrue(expected);
    }

    @ParameterizedTest
    @MethodSource("parametersForTests")
    void indexOf(Integer integer) {
        Integer actualIndex= -1;
        for (int i = 0; i < integerList.length; i++) {
            if (integerList[i].equals(integer)) {
                actualIndex = i;
                return;
            }
        }
        Integer expectedIndex = integerListImpl.indexOf(integer);

        Assertions.assertEquals(actualIndex, expectedIndex);

    }

    @ParameterizedTest
    @MethodSource("parametersForTests")
    void lastIndexOf(Integer integer) {
        Integer actualIndex= -1;
        for (int i = integerList.length-1; i >= 0; i--) {
            if (integerList[i].equals(integer)) {
                actualIndex = i;
                return;
            }
        }
        Integer expectedIndex = integerListImpl.lastIndexOf(integer);

        Assertions.assertEquals(actualIndex, expectedIndex);
    }

    @Test
    void get() {
        int index = 0;
        Integer actual = integerList[index];
        Integer expected = integerListImpl.get(index);
        Assertions.assertEquals(actual, expected);
    }

    @Test
    void testGetIndexOutOfBoundsException() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            integerListImpl.get(5);
        });
    }

    @Test
    void testEqualsIllegalArgumentException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            integerListImpl.equals(null);
        });
    }
    @Test
    void testEqualsTrue() {
        IntegerList otherList = new IntegerList(3);
        Assertions.assertTrue(integerListImpl.equals(otherList));
    }
    @Test
    void testEqualsFalse() {
        Assertions.assertFalse(integerListImpl.equals(integerList));
    }

    @Test
    void size() {
        int actual = integerList.length;
        int expected = integerListImpl.size();
        Assertions.assertEquals(actual, expected);
    }

    @Test
    void isEmptyFalse() {
        Assertions.assertFalse(integerListImpl.isEmpty());
    }



   @Test
    void clear() {
        Arrays.fill(integerList, null);
        integerListImpl.clear();
        Assertions.assertEquals(Arrays.toString(integerList), integerListImpl.toString());

    }

    @Test
    void toArray() {
        Integer[] IntegersActual = Arrays.copyOf(integerList, 3);
        Integer[] IntegerExpected = integerListImpl.toArray();
        Assertions.assertEquals(Arrays.toString(IntegersActual), Arrays.toString(IntegerExpected));

    }

    public static Stream<Arguments> parametersForTests() {
        return Stream.of(
                Arguments.of(100),
                Arguments.of(200),
                Arguments.of(300)
        );
    }
}