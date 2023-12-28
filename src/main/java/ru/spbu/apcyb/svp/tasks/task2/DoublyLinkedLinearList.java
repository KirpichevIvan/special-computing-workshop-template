package ru.spbu.apcyb.svp.tasks.task2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DoublyLinkedLinearList implements List<Object> {

  private static class Node{
    Object value;
    Node next;
    Node prev;

    Node (Object value){
      this.value = value;
    }
  }

  private Node head;
  private int size;

  @Override
  public boolean isEmpty() {
    return this.head == null;
  }
  @Override
  public boolean contains(Object value) {
    for (Node current = head; current != null; current = current.next) {
      if (current.value.equals(value)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean add(Object value) {
    Node newnode = new Node(value);
    if (this.head != null) {
      Node current = this.head;
      while (current.next != null) {
        current = current.next;
      }
      current.next = newnode;
      newnode.prev = current;
    } else {
      this.head = newnode;
    }
    ++this.size;

    return true;
  }


  @Override
  public void add(int index, Object value) {
    if (index < 0 || index > this.size) {
      throw new IndexOutOfBoundsException();
    }
    Node newnode = new Node(value);
    if (this.size == 0 || this.size == index) {
      add(value);
    } else {
      Node current = this.head;
      for (int i = 0; i < index; i++) {
        current = current.next;
      }
      newnode.prev = current.prev;
      newnode.next = current;
      current.prev = newnode;
      if (index != 0) {
        newnode.prev.next = newnode;
      } else {
        this.head = newnode;
      }

      ++this.size;
    }
  }

  @Override
  public Object remove(int index) {
    Node current = this.head;
    if (index < 0 || index >= this.size) {
      throw new IndexOutOfBoundsException("this index is not included in the list");
    }
    if (index == 0) {
      this.head = this.head.next;
      this.head.prev = null;
    } else {
      for (int i = 0; i < index; i++) {
        current = current.next;
      }
      if (index != (this.size - 1)) {
        current.next.prev = current.prev;
      }
      current.prev.next = current.next;
    }
    --this.size;

    return current.value;
  }

  @Override
  public Object get(int index) {
    if (index < 0 || index > this.size) {
      throw new IndexOutOfBoundsException("this index is not included in the list");
    }

    Node current = this.head;
    for (int i = 0; current.next != null && i < index; i++) {
      current = current.next;
    }

    return current.value;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public Iterator<Object> iterator() {
    throw new UnsupportedOperationException("Метод iterator не переопределен для списка");
  }

  @Override
  public Object[] toArray() {
    ArrayList<Object> a = new ArrayList<>();

    Node current = this.head;
    while (current != null) {
      a.add(current.value);
      current = current.next;
    }

    return a.toArray();
  }

  @Override
  public <T> T[] toArray(T[] a) {
    throw new UnsupportedOperationException("Метод toArray не переопределен для списка");
  }

  @Override
  public boolean remove(Object o) {
    throw new UnsupportedOperationException("Метод remove не переопределен для списка");
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    throw new UnsupportedOperationException("Метод containsAll не переопределен для списка");
  }

  @Override
  public boolean addAll(Collection<?> c) {
    throw new UnsupportedOperationException("Метод addAll не переопределен для списка");
  }

  @Override
  public boolean addAll(int index, Collection<?> c) {
    throw new UnsupportedOperationException("Метод addAll не переопределен для списка");
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    throw new UnsupportedOperationException("Метод removeAll не переопределен для списка");
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    throw new UnsupportedOperationException("Метод retainAll не переопределен для списка");
  }

  @Override
  public void clear() {
    throw new UnsupportedOperationException("Метод clear не переопределен для списка");

  }

  @Override
  public Object set(int index, Object element) {
    throw new UnsupportedOperationException("Метод set не переопределен для списка");
  }

  @Override
  public int indexOf(Object o) {
    throw new UnsupportedOperationException("Метод indexOf не переопределен для списка");
  }

  @Override
  public int lastIndexOf(Object o) {
    throw new UnsupportedOperationException("Метод lastIndexOf не переопределен для списка");
  }

  @Override
  public ListIterator<Object> listIterator() {
    throw new UnsupportedOperationException("Метод listIterator не переопределен для списка");
  }

  @Override
  public ListIterator<Object> listIterator(int index) {
    throw new UnsupportedOperationException("Метод listIterator не переопределен для списка");
  }

  @Override
  public List<Object> subList(int fromIndex, int toIndex) {
    throw new UnsupportedOperationException("Метод subList не переопределен для списка");
  }
}
