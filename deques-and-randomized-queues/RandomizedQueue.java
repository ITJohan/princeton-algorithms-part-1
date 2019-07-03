import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
  private int head, tail, size, capacity;
  private Item[] array;

  public RandomizedQueue() {
    this.head = 0;
    this.tail = 0;
    this.size = 0;
    this.capacity = 1;
    this.array = (Item[]) new Object[this.capacity];
  }

  public boolean isEmpty() {
    return this.size == 0;
  }

  public int size() {
    return this.size;
  }

  public void enqueue(Item item) {
    if (item == null)
      throw new IllegalArgumentException("item can't be null");

    this.array[this.tail] = item;
    this.tail++;
    this.size++;

    if (this.tail == this.capacity) {
      if (this.size == this.capacity)
        resize(this.capacity * 2);
      else
        resize(this.capacity);
    }
  }

  public Item dequeue() {
    if (this.size == 0)
      throw new NoSuchElementException("randomized queue is empty");

    int index = StdRandom.uniform(this.head, this.tail);
    Item item = this.array[index];
    this.array[index] = this.array[this.head];
    this.array[this.head] = null;
    this.head++;
    this.size--;

    if (this.size <= this.capacity / 4)
      resize(this.capacity / 2);

    return item;
  }

  public Item sample() {
    if (this.size == 0)
      throw new NoSuchElementException("randomized queue is empty");

    int index = StdRandom.uniform(this.head, this.tail);
    return this.array[index];
  }

  private void resize(int cap) {
    Item[] newArray = (Item[]) new Object[cap];
    int j = 0;
    for (int i = 0; i < this.capacity; i++) {
      if (this.array[i] != null) {
        newArray[j] = this.array[i];
        j++;
      }
    }

    this.capacity = cap;
    this.head = 0;
    this.tail = this.size;
    this.array = newArray;
  }
  
  @Override
  public Iterator<Item> iterator() {
    return new ArrayIterator(this.array, this.head);
  }

  private class ArrayIterator implements Iterator<Item> {
    private final Item[] array;
    private int cursor;

    public ArrayIterator(Item[] array, int head) {
      this.array = array.clone();
      this.cursor = head;
    }

    @Override
    public boolean hasNext() {
      return this.array[this.cursor] != null;
    }

    @Override
    public Item next() {
      if (!hasNext())
        throw new NoSuchElementException("no more items to return");

      return this.array[cursor++];
    }
    
    @Override
    public void remove() {
      throw new UnsupportedOperationException("remove is not supported");
    }
  }
  
  public static void main(String[] args) {
    RandomizedQueue<String> rq = new RandomizedQueue<>();
    rq.printArray(rq);
    rq.enqueue("a");
    rq.printArray(rq);
    rq.enqueue("b");
    rq.printArray(rq);
    rq.enqueue("c");
    rq.printArray(rq);
    rq.enqueue("d");
    rq.printArray(rq);
    rq.enqueue("e");
    rq.printArray(rq);
    rq.dequeue();
    rq.printArray(rq);
    rq.dequeue();
    rq.printArray(rq);
    rq.dequeue();
    rq.printArray(rq);
    rq.dequeue();
    rq.printArray(rq);
    rq.enqueue("f");
    rq.printArray(rq);
    rq.enqueue("g");
    rq.printArray(rq);
    rq.enqueue("h");
    rq.printArray(rq);
    rq.enqueue("i");
    rq.printArray(rq);
    rq.enqueue("j");
    rq.printArray(rq);
    rq.dequeue();
    rq.printArray(rq);
    rq.dequeue();
    rq.printArray(rq);
    rq.dequeue();
    rq.printArray(rq);
    rq.dequeue();
    rq.printArray(rq);
    System.out.println("sample: " + rq.sample());
    System.out.println("isEmpty: " + rq.isEmpty());
    System.out.println("size: " + rq.size());
  }
  
  private void printArray(RandomizedQueue<Item> rq) {
    for (Item item : rq) {
      if (item == null)
        System.out.print("- ");
      else
        System.out.print(item + " ");
    }
    System.out.println("");
  }
}