package ru.spbu.apcyb.svp.tasks.task2;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class LinkedQueue implements Queue<Object> {

  private final DoublyLinkedLinearList queue;

  public LinkedQueue() {
    this.queue = new DoublyLinkedLinearList();
  }

  @Override
  public int size() {
    return this.queue.size();
  }

  @Override
  public boolean isEmpty() {
    return this.queue.isEmpty();
  }

  @Override
  public boolean contains(Object o) {
    throw new UnsupportedOperationException("Метод contains не переопределен для очереди");
  }

  @Override
  public Iterator<Object> iterator() {
    throw new UnsupportedOperationException("Метод iterator не переопределен для очереди");
  }

  @Override
  public Object[] toArray() {
    return this.queue.toArray();
  }

  @Override
  public <T> T[] toArray(T[] a) {
    throw new UnsupportedOperationException("Метод toArray не переопределен для очереди");
  }

  @Override
  public boolean add(Object o) {
    return this.queue.add(o);
  }

  @Override
  public boolean remove(Object o) {
    throw new UnsupportedOperationException("Метод remove не переопределен для очереди");
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    throw new UnsupportedOperationException("Метод containsAll не переопределен для очереди");
  }

  @Override
  public boolean addAll(Collection<?> c) {
    throw new UnsupportedOperationException("Метод addAll не переопределен для очереди");
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    throw new UnsupportedOperationException("Метод removeAll не переопределен для очереди");
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    throw new UnsupportedOperationException("Метод retainAll не переопределен для очереди");
  }

  @Override
  public void clear() {
    throw new UnsupportedOperationException("Метод clear не переопределен для очереди");
  }

  @Override
  public boolean offer(Object o) {
    throw new UnsupportedOperationException("Метод offer не переопределен для очереди");
  }

  @Override
  public Object remove() {
    throw new UnsupportedOperationException("Метод remove не переопределен для очереди");
  }

  @Override
  public Object poll() {
    throw new UnsupportedOperationException("Метод poll не переопределен для очереди");
  }

  @Override
  public Object element() {
    throw new UnsupportedOperationException("Метод element не переопределен для очереди");
  }

  @Override
  public Object peek() {
    Object result = null;
    if (!this.isEmpty()) {
      result = this.queue.get(0);
    }
    return result;
  }
}