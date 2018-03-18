/*------------------------------------------------
File Name: Simulation.java
Desc:  desc
Instructions:  Run using Simulation <filename>

Name:   Joseph Csoti
CruzID: 1617438 // jcsoti
Class:  CMPS 12B
Date:   03/01/18
------------------------------------------------*/

import java.io.*;
import java.util.Scanner;

public class Simulation {

  public static Job getJob(Scanner in ) {
    String[] s = in .nextLine().split(" ");
    int a = Integer.parseInt(s[0]);
    int d = Integer.parseInt(s[1]);
    return new Job(a, d);
  }

  public static void main(String[] args) throws IOException {

    String line;

    Queue waitingQueue = new Queue();
    Queue queueTwo = new Queue();
    Queue queueThree = new Queue();
    Queue[] processorQueues = null;

    int numJobs;
    int time = 0;

    String filename;

    if (args.length < 1) {
      System.out.println("Usage: Simulation input_file");
      System.exit(1);
    }

    filename = args[0];

    // open input file
    Scanner in = new Scanner(new File(filename));

    // write files
    PrintWriter report = new PrintWriter(new FileWriter(filename + ".rpt"));
    PrintWriter trace = new PrintWriter(new FileWriter(filename + ".trc"));

    // numJobs is the first integer of an input_file
    numJobs = Integer.parseInt(in.nextLine());

    while (in.hasNext()) {
      Job j = getJob(in);
      waitingQueue.enqueue(j);
    }

    // prints header of report files
    report.println("Report file: " + (filename + ".rpt"));
    report.println(numJobs + " Jobs:");
    report.println(waitingQueue.toString());
    report.println();
    report.println("*****************************************************************************");

    // prints header of trace files
    trace.println("Trace file: " + (filename + ".trc"));
    trace.println(numJobs + " Jobs:");
    trace.println(waitingQueue.toString());
    trace.println();

    // n = numProcessors
    // loop until m(number of jobs)-1
    for (int n = 1; n <= numJobs - 1; n++) {
      int totalWait = 0;
      int maxWait = 0;
      double averageWait = 0.00;
      for (int i = 1; i < waitingQueue.length() + 1; i++) {
        Job j = (Job) waitingQueue.dequeue();
        j.resetFinishTime();
        queueTwo.enqueue(j);
        waitingQueue.enqueue(j);
      }

      int processors = n;
      processorQueues = new Queue[n + 2];
      processorQueues[0] = queueTwo;
      processorQueues[n + 1] = queueThree;
      for (int i = 0; i <= n; i++) {
        processorQueues[i] = new Queue();
      }

      // prints the heading of each amount of processors in trace
      trace.println("*****************************");
      trace.println(n + " processor" + (n==1 ? "" : "s") + ":");
      trace.println("*****************************");
      trace.println("time=" + time);
      trace.println("0: " + waitingQueue.toString());
      for (int i = 1; i < processors + 1; i++) {
        trace.println(i + ": " + processorQueues[i]);
      }

      // while loops traces all queue processor
      // 0: should print out the in-queue and finished elements
      while (queueThree.length() != numJobs) { // while unprocessed jobs remain
        int compFinal = Integer.MAX_VALUE;
        int finalIndex = 1;
        int comp = -1;
        int length = -1;
        int finalLength = -1;
        Job compare = null;

        if (!queueTwo.isEmpty()) {
          compare = (Job) queueTwo.peek();
          compFinal = compare.getArrival();
          finalIndex = 0;
        }

        for (int i = 1; i < processors + 1; i++) {
          if (processorQueues[i].length() != 0) {
            compare = (Job) processorQueues[i].peek();
            comp = compare.getFinish();
          }
          if (comp == -1) {} else if (comp < compFinal) {
            compFinal = comp;
            finalIndex = i;
          }
          time = compFinal;
        }

        if (finalIndex == 0) {
          int tempIndex = 1;
          finalLength = processorQueues[tempIndex].length();
          for (int i = 1; i < processors + 1; i++) {
            length = processorQueues[i].length();
            if (length < finalLength) {
              finalLength = length;
              tempIndex = i;
            }
          }

          compare = (Job) queueTwo.dequeue();
          processorQueues[tempIndex].enqueue(compare);
          if (processorQueues[tempIndex].length() == 1) {
            compare = (Job) processorQueues[tempIndex].peek();
            compare.computeFinishTime(time);
          }
        } else {
          compare = (Job) processorQueues[finalIndex].dequeue();
          queueThree.enqueue(compare);
          int tempWait = compare.getWaitTime();
          if (tempWait > maxWait) {
            maxWait = tempWait;
          }
          totalWait += tempWait;

          if (processorQueues[finalIndex].length() >= 1) {
            compare = (Job) processorQueues[finalIndex].peek();
            compare.computeFinishTime(time);
          }
        }

        trace.println();
        trace.println("time=" + time);
        trace.println("0: " + queueThree.toString() + queueTwo.toString());
        for (int i = 1; i < processors + 1; i++) {
          trace.println(i + ": " + processorQueues[i]);
        }
      }
      averageWait = ((double) totalWait / numJobs);
      averageWait = (double) Math.round(averageWait * 100) / 100;
      trace.println();

      // prints stats
      report.println(processors + " processor" + (n==1 ? "" : "s") + ": totalWait=" + totalWait +
          ", maxWait=" + maxWait + ", averageWait=" + String.format("%.2f", averageWait));
      time = 0;
      queueThree.dequeueAll();

    }

    // close files
    in .close();
    report.close();
    trace.close();
  }
}