package ru.spbu.apcyb.svp.tasks.task2;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.Test;

public class LinkedQueueTest {

  LinkedQueue queue = new LinkedQueue();
  @Test
  void sizeTest() {
    LinkedQueue queue = new LinkedQueue();

    queue.add(0);
    assertEquals(1, queue.size());

    queue.add(1);
    assertEquals(2, queue.size());
  }

  @Test
  void testQEmpty() {
    LinkedQueue queue = new LinkedQueue();
    queue.add(101);
    queue.add(19);
    queue.add(2);
    assertFalse(queue.isEmpty());
  }
  @Test
  void containsTest_UnsupportedOperationException() {
    assertThrows(UnsupportedOperationException.class, () -> queue.contains(0));
  }
  @Test
  void iteratorExceptionQTest() {
    assertThrows(UnsupportedOperationException.class, queue::iterator);
  }
  @Test
  void toArrayTest_UnsupportedOperationException() {
    assertThrows(UnsupportedOperationException.class, () -> queue.toArray(new Object[0]));
  }
  @Test
  void testQAdd() {
    LinkedQueue queue = new LinkedQueue();
    queue.add(1);
    queue.add(2);
    assertArrayEquals(new Object[]{1,2}, queue.toArray());
  }
  @Test
  void removeExceptionQTest() {
    assertThrows(UnsupportedOperationException.class, () -> queue.remove(0));
  }
  @Test
  void containsAllTest_UnsupportedOperationException() {
    List<Integer> s = List.of(1, 2);
    assertThrows(UnsupportedOperationException.class, () -> queue.containsAll(s));
  }
  @Test
  void addAllTest_UnsupportedOperationException() {
    List<Integer> s = List.of(1, 2);
    assertThrows(UnsupportedOperationException.class, () -> queue.addAll(s));
  }
  @Test
  void removeExceptionQTest2() {
    assertThrows(UnsupportedOperationException.class, queue::remove);
  }
  @Test
  void retainAllTest_UnsupportedOperationException() {
    List<Integer> s = List.of(1, 2);
    assertThrows(UnsupportedOperationException.class, () -> queue.retainAll(s));
  }
  @Test
  void clearExceptionQTest() {
    assertThrows(UnsupportedOperationException.class, queue::clear);
  }
  @Test
  void offerTest_UnsupportedOperationException() {
    assertThrows(UnsupportedOperationException.class, () -> queue.offer(0));
  }
  @Test
  void pollTest_UnsupportedOperationException() {
    assertThrows(UnsupportedOperationException.class, () -> queue.poll());
  }
  @Test
  void elementExceptionQTest() {
    assertThrows(UnsupportedOperationException.class, () -> queue.element());
  }
  @Test
  void testQPeek() {
    LinkedQueue queue = new LinkedQueue();
    queue.add(101);
    queue.add(19);
    queue.add(2);
    assertEquals(101, queue.peek());
  }

}
