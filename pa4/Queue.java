/*------------------------------------------------
File Name: Queue.java
Desc:  Queue built using Linked list ADT
Instructions: None

Name:   Joseph Csoti
CruzID: 1617438 // jcsoti
Class:  CMPS 12B
Date:   03/01/18
------------------------------------------------*/

class Queue implements QueueInterface {

  private int length;
  private Node head;
  private Node tail;

  private class Node {

    Object item;
    Node next;

    Node(Object item){
      this.item = item;
    }
  }

  Queue(){
    length = 0;
    head = null;
    tail = null;
  }

  // isEmpty()
  // pre: none
  // post: returns true if this Queue is empty, false otherwise
  public boolean isEmpty(){
    return head == null;
  }

  // length()
  // pre: none
  // post: returns the length of this Queue.
  public int length() {
    return length;
  }

  // enqueue()
  // adds newItem to back of this Queue
  // pre: none
  // post: !isEmpty()
  public void enqueue(Object newItem){

    if(isEmpty()){
      head = new Node(newItem);
      head.next = null;
      tail = head;
      length++;
      return;
    }

    Node newNode = new Node(newItem);
    newNode.next = null;

    tail.next = newNode;
    tail = newNode;
    length++;
  }

  // dequeue()
  // deletes and returns item from front of this Queue
  // pre: !isEmpty()
  // post: this Queue will have one fewer element
  public Object dequeue() throws QueueEmptyException {

    if(isEmpty())
      throw new QueueEmptyException("queue is empty");

    Node newHead = head.next;
    Node oldHead = head;
    head = newHead;
    length--;

    return oldHead.item;
  }

  // peek()
  // pre: !isEmpty()
  // post: returns item at front of Queue
  public Object peek() throws QueueEmptyException {

    if(isEmpty())
      throw new QueueEmptyException("queue is empty");

    return head.item;
  }

  // dequeueAll()
  // sets this Queue to the empty state
  // pre: !isEmpty()
  // post: isEmpty()
  public void dequeueAll() throws QueueEmptyException {

    if(isEmpty())
      throw new QueueEmptyException("queue is empty");

    head = null;
  }

  // toString()
  // overrides Object's toString() method
  public String toString() {
    String res = "";

    Node cursor = head;
    while(cursor!=null){
      res += cursor.item.toString() + " ";
      cursor = cursor.next;
    }

    return res;
  }
}