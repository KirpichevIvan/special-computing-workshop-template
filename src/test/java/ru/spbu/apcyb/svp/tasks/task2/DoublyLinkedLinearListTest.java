package ru.spbu.apcyb.svp.tasks.task2;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

public class DoublyLinkedLinearListTest {

  DoublyLinkedLinearList list = new DoublyLinkedLinearList();
  @Test
  void isEmptyTest() {
    assertTrue(list.isEmpty());
    list.add(101);
    assertFalse(list.isEmpty());
  }

  @Test
  void Contains_elemenntTest() {
    list.add(101);
    list.add(22);
    list.add(19);
    list.add(4);
    Object[] a = {101,22, 19, 4};
    assertArrayEquals(list.toArray(), a);
    assertTrue(list.contains(19));
    assertFalse(list.contains(1));
  }
  @Test
  void Add_elemenntTest() {
    list.add(101);
    list.add(22);
    list.add(19);
    list.add(4);
    assertArrayEquals(new Object[]{101, 22, 19, 4}, list.toArray());
    list.add(3,"qwerty");
    assertArrayEquals(new Object[]{101, 22, 19, "qwerty", 4}, list.toArray());
    assertThrows(IndexOutOfBoundsException.class, ()->list.add(-1, 1));
  }

  @Test
  void Add_by_indexTest() {
    list.add(1);
    list.add(1, 2);
    list.add(2,3);
    list.add(3,4);
    list.add(3,"qwerty");
    assertArrayEquals(new Object[]{1,2, 3, "qwerty", 4}, list.toArray());
    assertThrows(IndexOutOfBoundsException.class, ()->list.add(-1, 1));
  }
  @Test
  void Remove_by_indexTest() {
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(4);
    list.remove(1);
    assertArrayEquals(new Object[]{1, 3, 4}, list.toArray());
    list.remove(0);
    assertArrayEquals(new Object[]{3, 4}, list.toArray());
    assertThrows(IndexOutOfBoundsException.class, ()->list.remove(-3));
    assertThrows(IndexOutOfBoundsException.class, ()->list.remove(5));
  }


  @Test
  void Get_by_indexTest() {
    list.add(1);
    list.add("qwerty");
    list.add(101);
    assertThrows(IndexOutOfBoundsException.class, ()->list.get(-1));
    assertEquals(101, list.get(2));
    assertEquals("qwerty", list.get(1));
  }

  @Test
  void sizeTest() {
    assertEquals(0, list.size());
    list.add(1);
    list.add("qwerty");
    list.add(101);
    assertEquals(3, list.size());
  }

  @Test
  void iteratorTest_UnsupportedOperationException() {
    list.add(1);
    list.add("qwerty");
    list.add(101);
    assertThrows(UnsupportedOperationException.class, () -> list.iterator());
  }
  @Test
  void toArrayTest_UnsupportedOperationException() {
    list.add(1);
    list.add("qwerty");
    list.add(101);
    assertThrows(UnsupportedOperationException.class, () -> list.toArray(new Object[0]));
  }
  @Test
  void removeByObjectTest_UnsupportedOperationException() {
    assertThrows(UnsupportedOperationException.class, () -> list.remove("asd"));
  }

  @Test
  void containsAllTest_UnsupportedOperationException() {
    list.add(1);
    list.add(2);
    list.add("qwerty");
    list.add(101);
    List<Integer> s = List.of(1, 2);
    assertThrows(UnsupportedOperationException.class, () -> list.containsAll(s));
  }

  @Test
  void addAllTest_UnsupportedOperationException() {
    list.add(1);
    list.add(2);
    list.add("qwerty");
    list.add(101);
    List<Integer> s = List.of(1, 2);
    assertThrows(UnsupportedOperationException.class, () -> list.addAll(s));
  }

  @Test
  void addAllOnIndexTest_UnsupportedOperationException() {
    list.add(1);
    list.add(2);
    list.add("qwerty");
    list.add(101);
    List<Integer> s = List.of(1, 2);
    assertThrows(UnsupportedOperationException.class, () -> list.addAll(0, s));
  }

  @Test
  void removeAllTest_UnsupportedOperationException() {
    list.add(1);
    list.add(2);
    list.add("qwerty");
    list.add(101);
    List<Integer> s = List.of(1, 2);
    assertThrows(UnsupportedOperationException.class, () -> list.removeAll(s));
  }

  @Test
  void retainAllTest_UnsupportedOperationException() {
    list.add(1);
    list.add(2);
    list.add("qwerty");
    list.add(101);
    List<Integer> s = List.of(1, 2);
    assertThrows(UnsupportedOperationException.class, () -> list.retainAll(s));
  }
  @Test
  void iteratorExceptionTest() {
    assertThrows(UnsupportedOperationException.class, list::iterator);
    list.add("string");
    assertThrows(UnsupportedOperationException.class, () -> list.listIterator(0));
  }
  @Test
  void clearExceptionTest() {
    assertThrows(UnsupportedOperationException.class, list::clear);
  }


  @Test
  void setExceptionTest() {
    assertThrows(UnsupportedOperationException.class, () -> list.set(0, 3));
  }

  @Test
  void indexOfExceptionTest() {
    assertThrows(UnsupportedOperationException.class, () -> list.indexOf(0));
  }

  @Test
  void lastIndexOfExceptionTest() {
    assertThrows(UnsupportedOperationException.class, () -> list.lastIndexOf(0));
  }

  @Test
  void listIteratorExceptionTest() {
    assertThrows(UnsupportedOperationException.class, list::listIterator);
  }

  @Test
  void sublistExceptionTest() {
    assertThrows(UnsupportedOperationException.class, () -> list.subList(0, 2));
  }
}
