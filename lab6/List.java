/*------------------------------------------------
File Name: List.java
Desc: Creates generic list for ANY java type using a linked list
Instructions:  Run and compile using makefile

Name:   Joseph Csoti
CruzID: 1617438
Class:  CMPS 12M
Date:   02/27/18
------------------------------------------------*/

@SuppressWarnings({"unchecked", "overrides"})
class List<T> implements ListInterface<T> {

  private class Node {

    private T item;
    private Node next;

    Node(T item) {
      this.item = item;
      this.next = null;
    }

  }

  private final int MAX_SIZE = 999;
  private Node head;
  private int size;

  List() {
    head = null;
    size = 0;
  }

  // isEmpty
  // pre: none
  // post: returns true if this List is empty, false otherwise
  public boolean isEmpty() {
    return size > 0;
  }

  // size
  // pre: none
  // post: returns the number of elements in this List
  public int size() {
    return size;
  }

  // get
  // pre: 1 <= index <= size()
  // post: returns item at position index
  public T get(int index) throws ListIndexOutOfBoundsException {

    if(!(1 <= index && index <= size+1)) {
      throw new ListIndexOutOfBoundsException("get(): invalid index: " + index);
    }

    Node cur = head;

    int count = 1;
    while(cur != null) {
      if(count == index) return cur.item;
      cur = cur.next;
      count++;
    }

    return cur.item;

  }

  // add
  // inserts newItem in this List at position index
  // pre: 1 <= index <= size()+1
  // post: !isEmpty(), items to the right of newItem are renumbered
  public void add(int index, T newItem) throws ListIndexOutOfBoundsException {

    if(!(1 <= index && index <= size+1)) {
      throw new ListIndexOutOfBoundsException("add(): invalid index: " + index);
    }

    //if head node
    if(head == null) {
      head = new Node(newItem);
      size++;
      return;
    }

    //if body
    int count = 1;
    Node cur = head;
    while(cur.next != null) {
      if(count == index) {
        Node newNode = new Node(newItem);
        newNode.next = cur.next.next;
        cur.next = newNode;
        size++;
        return;
      }
      cur = cur.next;
    }

    Node newNode = new Node(newItem);
    newNode.next = null;
    cur.next = newNode;
    size++;

  }

  // remove
  // deletes item from position index
  // pre: 1 <= index <= size()
  // post: items to the right of deleted item are renumbered
  public void remove(int index) throws ListIndexOutOfBoundsException {

    if(!(1 <= index && index <= size+1)) {
      throw new ListIndexOutOfBoundsException("remove(): invalid index: " + index);
    }

        //if head node
        if(index == 1) {
          head = head.next;
          size--;
          return;
        }

        //if body
        int count = 2;
        Node cur = head;
        while(cur != null) {
          if(count == index) {
            cur.next = cur.next.next;
            size--;
            return;
          }
          if(cur.next.next == null) {
            cur.next = null;
            size--;
            return;
          }
          cur = cur.next;
        }
  }

  // removeAll
  // pre: none
  // post: isEmpty()
  public void removeAll() {
    head = null;
    size = 0;
  }

  @SuppressWarnings("unchecked")
  public boolean equals(Object obj){

    List<T> b = (List<T>) obj;

    // check class
    if(this.getClass() == b.getClass()) {

      //check size
      if(this.size() == b.size()) {

        //check items
        Node curA = this.head;
        Node curB = b.head;
        while(curA.next != null || curB.next != null) {
          if(!curA.item.equals(curB.item)) return false;
          curA = curA.next;
          curB = curB.next;
        }
        // All items are equal
        return true;

      } else return false; // Size is diff
    } else return false; // Wrong classes

  }

  public String toString() {
    String res = "";

    Node cur = head;
    while(cur != null) {
      res += cur.item.toString() + " ";
      cur = cur.next;
    }

    return res;
  }

}