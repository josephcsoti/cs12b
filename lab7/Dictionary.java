/*------------------------------------------------
File Name: Dictionary.java
Desc: Dictionary ADT using a BST structure
Instructions:  Create an instance using Dictionary() in another file

Name:   Joseph Csoti
CruzID: 1617438 // jcsoti
Class:  CMPS 12M
Date:   03/08/18
------------------------------------------------*/

class Dictionary implements DictionaryInterface {

  private int size;
  private Node root;

  // Node class
  private class Node {
    String key, value;
    Node left, right;
    Node(String key, String value) {
      this.key = key;
      this.value = value;
    }
  }

  //constructor
  Dictionary() {
    root = null;
    size = 0;
  }

  // isEmpty()
  // pre: none
  // returns true if this Dictionary is empty, false otherwise
  public boolean isEmpty() {
    return size == 0;
  }

  // size()
  // pre: none
  // returns the number of entries in this Dictionary
  public int size() {
    return size;
  }

  // lookup()
  // pre: none
  // returns value associated key, or null reference if no such key exists
  public String lookup(String key) {
    return lookup(root, key);
  }

  private String lookup(Node parent, String key) {

    if(parent == null)
      return null;

    if(key.equals(parent.key))
      return parent.value;

    if(key.compareTo(parent.key) < 0) 
      return lookup(parent.left, key);
    else
      return lookup(parent.right, key);
  }

  private Node findNode(String key) {
    return findNode(root, key);
  }

  private Node findNode(Node parent, String key) {

    if(parent == null)
      return null;

    if(key.equals(parent.key))
      return parent;

    if(key.compareTo(parent.key) < 0) 
      return findNode(parent.left, key);
    else
      return findNode(parent.right, key);
  }

  private Node findParent(Node root, Node node) {
    Node parent = null;
    if(!root.equals(node)) {
      parent = root;
      while(!node.equals(parent.left) && !node.equals(parent.right)) {
        if(node.value.compareTo(parent.value) < 0)
          parent = parent.left;
        else
          parent = parent.right;
       }
    }
    return parent;
  }

  // findMin()
  // find minimum item in subtree rooted at R
  Node findMin(Node root){
    Node n = root;
    if(n != null){
      for(; n.left != null; n=n.left);
    }
    return n;
  }

  // insert()
  // inserts new (key,value) pair into this Dictionary
  // pre: lookup(key)==null
  public void insert(String key, String value) throws DuplicateKeyException {
    if(lookup(key) != null)
      throw new DuplicateKeyException("insert(): Duplicate key");

    // Create proper nodes
    Node n = new Node(key, value); 
    Node parent = null;
    Node child = root;

    while(child != null) {
      //Walk the tree together...
      parent = child;
      
      // ...until 'C' takes the spot where the new node 'n' would be
      if(key.compareTo(child.key) < 0)
        child = child.left;
      else
        child = child.right;
    }

    // Empty dict, node becomes root
    if(parent == null)
      root = n;
    else if(key.compareTo(parent.key) < 0)
      parent.left = n; //Left child
    else
      parent.right = n; //Right child

    size++;
  }

  // delete()
  // deletes pair with the given key
  // pre: lookup(key)!=null
  public void delete(String key) throws KeyNotFoundException {
    if(lookup(key) == null)
      throw new KeyNotFoundException("delete(): Key not found");
    
    // Init nodes
    Node parent, s, n;

    // Get node OBJ assosiated w/ key
    n = findNode(key);

    // CASE 1: No children
    if(n.left == null && n.right == null) {
      if(n.equals(root)) {
        root = null;
      }
      else {
        parent = findParent(root, n);
        
        if(n.equals(parent.right))
          parent.right = null;
        else
          parent.left = null;
      }
    }
    // CASE 2-A: Has left child
    else if(n.right == null) {
      if(n.equals(root)) {
        root = n.left;
      }
      else {
        parent = findParent(root, n);

        if(n.equals(parent.right))
          parent.right = n.left;
        else
          parent.left = n.left;
      }

      System.out.println("case 2 - no right");
      
    }
    // CASE 2-B: Has right child
    else if(n.left == null) {
      if(n.equals(root)) {
        root = n.right;
      }
      else {
        parent = findParent(root, n);

        if(n.equals(parent.right))
          parent.right = n.right;
        else
          parent.left = n.right;
      }  
    }
    // CASE 3: Has BOTH right and left channel
    else {
      s = findMin(n.right); //get min node from right side of tree
      n.value = s.value;
      parent = findParent(n, s); // get its parent

      if(s.equals(parent.right))
        parent.right = s.right;
      else
        parent.left = s.right; 
    }

    size--;
  }

  // makeEmpty()
  // pre: none
  public void makeEmpty() {
    root = null;
    size = 0;
  }

  // toString()
  // returns a String representation of this Dictionary
  // overrides Object's toString() method
  // pre: none
  public String toString() {
    return print(root);
  }

  //Recursive print function
  private String print(Node parent){
    if(parent == null)
      return "";

    String str, a, b;

    a = print(parent.left); //get left side of tree
    str = parent.key + " " + parent.value + "\n"; // print "head" node
    b = print(parent.right); //get right side of tree

    return a + str + b; //combine
 }
}