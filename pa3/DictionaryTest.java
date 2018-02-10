/*------------------------------------------------
File Name: DictionaryTest.java
Desc:  Tests that "Dictionary" works properly
Instructions: None

Name:   Joseph Csoti
CruzID: 1617438
Class:  CMPS 12B
Date:   02/02/18
------------------------------------------------*/
public class DictionaryTest {

  public static void main(String[] args) {

    System.out.println("=== TEST IS STARTING ===");
    
    Dictionary dict = new Dictionary();


    System.out.println("--- Empty Dict ---");
    System.out.println("isEmpty(): " + dict.isEmpty());
    System.out.println("size(): " + dict.size());
    System.out.println("lookup(key): " + dict.lookup("key"));

    System.out.println("\n--- Filling up Dict ---");
    System.out.println("Trying: insert(A, 1)");
    dict.insert("A", "1");
    System.out.println("Trying: insert(B, 2)");
    dict.insert("B", "2");
    System.out.println("Trying: insert(C, 3)");
    dict.insert("C", "3");
    System.out.println("Trying: insert(D, 4)");
    dict.insert("D", "4");
    System.out.println("Trying: insert(E, 5)");
    dict.insert("E", "5");
    System.out.println("Trying: insert(F, 6)");
    dict.insert("F", "6");
    System.out.println("Trying: insert(G, 7)");
    dict.insert("G", "7");
    System.out.println("Trying: insert(H, 8)");
    dict.insert("H", "8");
    System.out.println("Trying: insert(I, 9)");
    dict.insert("I", "9");

    System.out.println("Trying: insert(A, 99)");
    try {dict.insert("A", "-1");} 
    catch (DuplicateKeyException e){System.out.println(e);}


    System.out.println("\n--- What is Dict ---");
    System.out.println("isEmpty(): " + dict.isEmpty());
    System.out.println("size(): " + dict.size());
    System.out.println("lookup(key): " + dict.lookup("key"));
    System.out.println(dict.toString());

    System.out.println("\n--- Deleting Dict ---");

    System.out.println("Body Delete >>");
    
    System.out.println("  Trying: delete(B)");
    dict.delete("B");
    System.out.println("  Trying: delete(C)");
    dict.delete("C");
    System.out.println("  Trying: delete(D)");
    dict.delete("D");
    System.out.println("  Trying: delete(E)");
    dict.delete("E");

    System.out.println("Head Delete >>");
    System.out.println("  Trying: delete(A)");
    dict.delete("A");

    System.out.println("Tail Delete >>");
    System.out.println("  Trying: delete(I)");
    dict.delete("I");

    System.out.println("Null Delete >>");
    System.out.println("  Trying: delete(Z)");
    try{dict.delete("Z");}
    catch(KeyNotFoundException e){System.out.println(e);}
    
    System.out.println("\n--- What is Dict ---");
    System.out.println("isEmpty(): " + dict.isEmpty());
    System.out.println("size(): " + dict.size());
    System.out.println("lookup(key): " + dict.lookup("key"));
    System.out.println(dict.toString());

    System.out.println("\n--- Emptying Dict ---");
    System.out.println("Trying: makeEmpty()");
    dict.makeEmpty();

    System.out.println("\n--- What is Dict ---");
    System.out.println("isEmpty(): " + dict.isEmpty());
    System.out.println("size(): " + dict.size());
    System.out.println("lookup(key): " + dict.lookup("key"));
    System.out.println(dict.toString());

    System.out.println("=== TEST IS COMPLETE ===");
    
  }
  
}