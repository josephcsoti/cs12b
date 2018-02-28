/*------------------------------------------------
File Name: ListTest.java
Desc: Tests the List.java class
Instructions:  Run and compile using makefile

Name:   Joseph Csoti
CruzID: 1617438
Class:  CMPS 12M
Date:   02/27/18
------------------------------------------------*/

public class ListTest {

  public static void main(String[] args) {

    List<String> A = new List<String>();
    List<String> B = new List<String>();

    List<Integer> C = new List<Integer>();
    List<Integer> D = new List<Integer>();

    final int LIST_SIZE = 5;

    for(int i=1; i<=LIST_SIZE; i++) {
      A.add(i, i + "_ABC");
      B.add(i, i + "_ABC");
      C.add(i, i);
      D.add(i, i);
     }

    System.out.println(">>> STARTING TEST <<<");
    
    System.out.println("A: "+A);
    System.out.println("B: "+B);
    System.out.println("C: "+C);
    System.out.println("D: "+D);

    System.out.println("A == B ? "+A.equals(B));
    System.out.println("C == D ? "+C.equals(D));

    System.out.println("A.size() is "+A.size());
    System.out.println("B.size() is "+B.size());
    System.out.println("C.size() is "+C.size());
    System.out.println("D.size() is "+D.size());

    System.out.println("-- REMOVING ELELMENTS ---");
    A.remove(2);
    C.remove(2);

    System.out.println("A == B ? "+A.equals(B));
    System.out.println("C == D ? "+C.equals(D));

    System.out.println("A.size() is "+A.size());
    System.out.println("B.size() is "+B.size());
    System.out.println("C.size() is "+C.size());
    System.out.println("D.size() is "+D.size());

    System.out.println("A.get(2): " + A.get(2));
    System.out.println("B.get(2): " + B.get(2));
    System.out.println("C.get(2): " + C.get(2));
    System.out.println("D.get(2): " + D.get(2));

    System.out.println("Trying to remove invalid index...");
    try {A.get(999);}
    catch(ListIndexOutOfBoundsException e) {
      System.out.println("Caught Exception: " + e);
      System.out.println("Continuing without interuption");
    }

    System.out.println("--- Making empty ---");
    A.removeAll();
    B.removeAll();
    C.removeAll();
    D.removeAll();

    System.out.println("A.size() is "+A.size());
    System.out.println("B.size() is "+B.size());
    System.out.println("C.size() is "+C.size());
    System.out.println("D.size() is "+D.size());
    
    System.out.println(">>> TEST FINISHED <<<");

  }
}