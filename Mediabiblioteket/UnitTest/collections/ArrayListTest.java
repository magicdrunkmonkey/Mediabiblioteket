package collections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {

    ArrayList Testarray= new ArrayList();
    Object[] objs = new Object[]{3,4};

    @Test
    void sortByTitle() {
    }

    @Test
    void test_add_addedElementIs_sameAsStaticallyAddedElement() {

        // Lägger in element i ArrayListen men ArrayList .add java-funktionen.
        try {
            Testarray.add(-5, objs);
        } catch (Exception e) {
            String test = e.toString();
            assertEquals("java.lang.IndexOutOfBoundsException", test);
        }
    }

    @Test
    void test_Add() {
    }

    @Test
    void addFirst() {
    }

    @Test
    void addLast() {
    }

    @Test
    void remove() {
    }

    @Test
    void removeFirst() {
    }

    @Test
    void removeLast() {
    }

    @Test
    void clear() {
    }

    @Test
    void get() {
    }

    @Test
    void set() {
    }

    @Test
    void getElements() {
    }

    @Test
    void setElements() {
    }

    @Test
    void indexOfFirst() {
    }

    @Test
    void indexOf() {
    }

    @Test
    void size() {
    }

    @Test
    void testToString() {
    }

    @Test
    void iterator() {
    }
}