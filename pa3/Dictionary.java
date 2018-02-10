/*------------------------------------------------
File Name: Dictionary.java
Desc:  Uses doubly-linked list w/ head/tail tracking to store key-value pairs
Instructions: Run by creating an instance of the class and use its methods

Name:   Joseph Csoti
CruzID: 1617438
Class:  CMPS 12B
Date:   02/02/18
------------------------------------------------*/

public class Dictionary implements DictionaryInterface {

  Node head;

  public class Node {

    String a;
    String b;
    Node next;
    Node prev;

    Node(){};

    Node(String a, String b){
      this.a = a;
      this.b = b;
      next = null;
      prev = null;
    }

    public String toString(){
      return "a:"+a+" b:"+b;
    }
  }

  Dictionary(){}

  //helper method to quickly get tail
  private Node getTail(){
    Node cursor = head;
    while(cursor.next != null)
      cursor = cursor.next;
    return cursor;
  }

  //returns matching node
  private Node findKey(String key) {
    Node cursor = head;
    while(cursor != null){
      if(key.equals(cursor.a))
        return cursor;
      cursor = cursor.next;
    }
    return null;
  }

  // isEmpty()
  // pre: none
  // returns true if this Dictionary is empty, false otherwise
  public boolean isEmpty(){
    return head == null;
  }

  // size()
  // pre: none
  // returns the number of entries in this Dictionary
  public int size(){
    if(head == null)
      return 0;

    int size = 0;
    Node cursor = head;
    while(cursor != null){
      cursor = cursor.next;
      size++;
    }
    return size;
  }

  // lookup()
  // pre: none
  // returns value associated key, or null reference if no such key exists
  public String lookup(String key) {
    return findKey(key) == null ? null : findKey(key).b;
  }

  // insert()
  // inserts new (key,value) pair into this Dictionary
  // pre: lookup(key)==null
  public void insert(String key, String value) throws DuplicateKeyException {

    if(lookup(key) != null){
      throw new DuplicateKeyException("cannot insert duplicate keys");
    }

    // new data
    Node data = new Node(key, value);
    data.next = null;

    //New dict
    if(head == null){
      data.prev = null;
      head = data;
      return;
    }
    
    //append data to tail
    Node tail = getTail();
    tail.next = data;
    data.prev = tail;

  }

  // delete()
  // deletes pair with the given key
  // pre: lookup(key)!=null
  public void delete(String key) throws KeyNotFoundException {

    if(lookup(key) == null) {
      throw new KeyNotFoundException("cannot delete non-existent key");
    }

    //Empty list
    if(head == null)
      return;

    // get the next and previous node
    Node cursor = findKey(key);
    Node prev = cursor.prev;
    Node next = cursor.next;

    // Delete HEAD
    if (prev == null) {
        head = next;
        return;
    }

    // delete TAIL
    if (next == null) {
        prev.next = null;
        return;
    }

    // "cut" node out
    prev.next = next;
    next.prev = prev;

  }

  // makeEmpty()
  // pre: none
  public void makeEmpty(){
    head = null;
  }

  // toString()
  // returns a String representation of this Dictionary
  // overrides Object's toString() method
  // pre: none
  public String toString(){
    String res = "";
    Node cursor = head;
    while(cursor != null){
      res += cursor.a+" "+cursor.b + "\n";
      cursor = cursor.next;
    }
    return res;
  }
}