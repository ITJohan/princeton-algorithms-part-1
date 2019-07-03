import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
  private Node<Item> head, tail;
  private int size;

  public Deque() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  /**
   * Private inner class representing a node.
   * 
   * @param <Item> Type of item contained in the node.
   */
  private class Node<Item> {
    public Item item;
    public Node<Item> next, prev;

    private Node(Item item, Node<Item> next, Node<Item> prev) {
      this.item = item;
      this.next = next;
      this.prev = prev;
    }
  }

  /**
   * Checks if the deque is empty.
   * @return True if empty, false if not.
   */
  public boolean isEmpty() {
    return this.head == null;
  }

  /**
   * @return The number of nodes in the deque.
   */
  public int size() {
    return this.size;
  }

  /**
   * Adds item to the front of the deque
   */
  public void addFirst(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("item can't be null");
    }

    if (this.head == null) {
      this.head = new Node<Item>(item, null, null);
      this.tail = this.head;
    } else {
      Node<Item> tmp = new Node<Item>(item, this.head, null);
      this.head.prev = tmp;
      this.head = tmp;
    }

    this.size++;
  }

  /**
   * Adds item to the back of the deque
   */
  public void addLast(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("item can't be null");
    }

    if (this.tail == null) {
      this.tail = new Node<Item>(item, null, null);
      this.head = tail;
    } else {
      Node<Item> tmp = new Node<Item>(item, null, this.tail);
      this.tail.next = tmp;
      this.tail = tmp;
    }

    this.size++;
  }

  /**
   * Removes the first element from the deque and returns it.
   * @return The first item in the deque.
   */
  public Item removeFirst() {
    if (this.head == null) {
      throw new NoSuchElementException("deque is empty");
    }

    Item tmp = this.head.item;
    this.head = this.head.next;
    if (this.size == 1) {
      this.head = null;
      this.tail = null;
    } else {
      this.head.prev = null;
    }
    this.size--;
    return tmp;
  }

  /**
   * Removes the last elements from the deque and returns it.
   */
  public Item removeLast() {
    if (this.tail == null) {
      throw new NoSuchElementException("deque is empty");
    }

    Item tmp = this.tail.item;
    this.tail = tail.prev;
    if (this.tail != null)
      this.tail.next = null;
    else
      this.head = null;
    this.size--;
    return tmp;
  }

  /**
   * Returns an iterator for the deque.
   */
  @Override
  public Iterator<Item> iterator() {
    return new ListIterator(this.head);
  }

  /**
   * Iterator implementation of the deque.
   */
  private class ListIterator implements Iterator<Item> {
    private Node<Item> current;

    public ListIterator(Node<Item> current) {
      this.current = current;
    }

    public boolean hasNext() {
      return this.current != null;
    }

    public void remove() {
      throw new UnsupportedOperationException("remove is not supported");
    }

    public Item next() {
      if (!hasNext()) {
        throw new NoSuchElementException("next is null");
      }

      Item item = this.current.item;
      this.current = this.current.next;
      return item;
    }
  }

  public static void main(String[] args) {
    Deque<String> deque = new Deque<String>();
    System.out.println(deque.isEmpty());
    System.out.println(deque.size());
    deque.addFirst("1");
    deque.printList(deque);
    deque.addFirst("2");
    deque.printList(deque);
    deque.addLast("3");
    deque.printList(deque);
    deque.addFirst("4");
    deque.printList(deque);
    deque.addFirst("5");
    deque.printList(deque);
    deque.addLast("6");
    deque.printList(deque);
    deque.removeLast();
    deque.printList(deque);
    deque.removeFirst();
    deque.printList(deque);
    System.out.println(deque.isEmpty());
    System.out.println(deque.size());
  }

  private void printList(Deque<Item> deque) {
    for (Item node : deque) {
      System.out.print(node + " => ");
    }
    System.out.println("");
  }
}