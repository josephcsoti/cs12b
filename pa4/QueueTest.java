/*------------------------------------------------
File Name: QueueTest.java
Desc: A testfile for Queue.java
Instructions: Compile, then run using QueueTest

Name:   Joseph Csoti
CruzID: 1617438 // jcsoti
Class:  CMPS 12B
Date:   03/01/18
------------------------------------------------*/

public class QueueTest {

  public static void main(String[] args) {

    System.out.println("=== TEST IS STARTING ===");
    
    Queue queue = new Queue();

    System.out.println("--- Empty Queue ---");
    System.out.println("isEmpty(): " + queue.isEmpty());
    System.out.println("length(): " + queue.length());
    System.out.print("peek(): ");
    try {System.out.println(queue.peek());} 
    catch (QueueEmptyException e){System.out.println(e);}
    System.out.print("dequeue(): ");
    try {queue.dequeue();} 
    catch (QueueEmptyException e){System.out.println(e);}
    System.out.print("dequeueAll(): ");
    try {queue.dequeueAll();} 
    catch (QueueEmptyException e){System.out.println(e);}


    System.out.println("--- Filling Queue ---");
    for(int i=1; i<11; i++) {
      System.out.println("Adding Job("+i+", "+(i+5)+")");
      queue.enqueue(new Job(i, i + 5));
    }

    System.out.println("--- Queue ---");
    System.out.println(queue.toString());

    System.out.println("--- Dequeue Queue ---");
    for(int i=0; i<5; i++) {
      System.out.println("Dequeue Job("+i+", "+(i+5)+")");
      queue.dequeue();
    }

    System.out.println("--- Queue ---");
    System.out.println(queue.toString());

    System.out.println("--- Peek Queue ---");
    System.out.println("Peek(): " + queue.peek());

    System.out.println("=== TEST IS COMPLETE ===");
    
  }
  
}