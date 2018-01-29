/*------------------------------------------------
File Name: Search.java
Desc: Implements recursion (mergesort & binary search) to find specified targets in given file
Instructions: Search <file> <search_term_1> <search_term_2> ...

Name:   Joseph Csoti
CruzID: 1617438
Class:  CMPS 12B
Date:   01/24/18
------------------------------------------------*/

import java.io.*;
import java.util.Scanner;

public class Search {

  public static void main(String[] args) throws IOException {

    if(args.length < 2){
      System.err.println("Usage: Search file target1 [target2 ..]");
      System.exit(1);
    }

    //Get file
    String file_in = args[0];

    //Init scanner
    Scanner scan = new Scanner(new File(file_in));

    //Get file count
    int lines = 0;
    while(scan.hasNextLine()) {
      scan.nextLine();
      lines++;
    }

    //Create arr
    String[] words = new String[lines];
    
    //Read through each line
    scan = new Scanner(new File(file_in));

    int iterator = 0;
    while(scan.hasNextLine()) {
      words[iterator] = scan.nextLine().trim() + ""; //Get line, remove spacing;
      iterator++;
    }

    int[] lineNumbers = new int[lines];
    //create line numbers
    for(int i=0; i<lineNumbers.length; i++){
      lineNumbers[i] = i;
    }

    //sort
    mergeSort(words, lineNumbers, 0, words.length-1);

    //print

    //search for pos of each word
    for(int i=0; i<words.length; i++){
      int index = binarySearch(words, 0, words.length-1, args[i+1]);
      String res = index >= 0 ? " found on line " + lineNumbers[i] : " not found";
      System.out.println(args[i+1] + res);
    }

    scan.close();
    
 }

 //returns index if TARGET is found in list A
  static int binarySearch(String[] A, int p, int r, String target){
    int q;
    if(p > r) {
      return -1;
    }else{
      q = (p+r)/2;
      if(target.compareTo(A[q]) == 0){
          return q;
      }else if(target.compareTo(A[q]) < 0){
        return binarySearch(A, p, q-1, target);
      }else{ // target > A[q]
        return binarySearch(A, q+1, r, target);
      }
    }
  }

  // mergeSort()
  // sort subarray A[p...r]
  public static void mergeSort(String[] A, int[] lineNumber, int p, int r){
    int q;
    if(p < r) {
       q = (p+r)/2;
       // System.out.println(p+" "+q+" "+r);
       mergeSort(A, lineNumber, p, q);
       mergeSort(A, lineNumber, q+1, r);
       merge(A, lineNumber, p, q, r);
    }
 }

 // merge()
 // merges sorted subarrays A[p..q] and A[q+1..r]
 public static void merge(String[] A, int[] lineNumber, int p, int q, int r){
    int n1 = q-p+1;
    int n2 = r-q;
    String[] L = new String[n1];
    String[] R = new String[n2];
    int[] L2 = new int[n1];
    int[] R2 = new int[n2];
    int i, j, k;

    for(i=0; i<n1; i++){
       L[i] = A[p+i];
       L2[i] = lineNumber[p+i];
    }
    for(j=0; j<n2; j++){ 
       R[j] = A[q+j+1];
       R2[j] = lineNumber[q+j+1];
    }

    i = 0; j = 0;
    for(k=p; k<=r; k++){
       if( i<n1 && j<n2 ){
          if( L[i].compareTo(R[j]) < 0 ){
             A[k] = L[i];
             lineNumber[k] = L2[i];
             i++;
          }else{
             A[k] = R[j];
             lineNumber[k] = R2[j];
             j++;
          }
       }else if( i<n1 ){
          A[k] = L[i];
          lineNumber[k] = L2[i];
          i++;
       }else{ // j<n2
          A[k] = R[j];
          lineNumber[k] = R2[j];
          j++;
       }
    }
 }

}
