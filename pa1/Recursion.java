/*------------------------------------------------
File Name: Recursion.java
Desc: Implements recursion to reverse arrays in multiple methods (L->R, R->L, OUT-> MIDDLE)
Instructions: Run using makefile (perferably)

Name:   Joseph Csoti
CruzID: 1617438
Class:  CMPS 12B
Date:   01/18/18
------------------------------------------------*/

class Recursion {
   
  // reverseArray1()
  // Places the leftmost n elements of X[] into the rightmost n positions in
  // Y[] in reverse order
  static void reverseArray1(int[] X, int n, int[] Y){

    if(n < 1)
      return;
    
      Y[X.length-n] = X[n-1]; // print nth element from left
      reverseArray1(X, n-1, Y);   // print leftmost n-1 elements, reversed
    
  }

  // reverseArray2()
  // Places the rightmost n elements of X[] into the leftmost n positions in
  // Y[] in reverse order.
  static void reverseArray2(int[] X, int n, int[] Y){
    
    if(n < 1)
      return;
    
    reverseArray2(X, n-1, Y);  // print the rightmost n-1 elements, reversed
    Y[n-1] = X[Y.length - n];  // print the nth element from right
    
  }
  
  // reverseArray3()
  // Reverses the subarray X[i...j].
  static void reverseArray3(int[] X, int i, int j){

    if(i==j || j < i)
      return;
    
    int copy = X[j];
    X[j] = X[i];
    X[i] = copy;
    reverseArray3(X, i+1, j-1);
    
  }
  
  // maxArrayIndex()
  // returns the index of the largest value in int array X
  static int maxArrayIndex(int[] X, int p, int r){
    // like merge sort?????

    if(p<=r) {
      int split = (r+p)/2;

      int left = maxArrayIndex(X, p+1, split);
      int right = maxArrayIndex(X, split+1, r-1);

      return X[left] > X[right] ? left : right;
    } else {
      return X[p] > X[r] ? p : r;
    }
  }
  
  // minArrayIndex()
  // returns the index of the smallest value in int array X
  static int minArrayIndex(int[] X, int p, int r){

    if(p<=r) {
      int split = (r+p)/2;

      int left = minArrayIndex(X, p+1, split);
      int right = minArrayIndex(X, split+1, r-1);

      return X[left] < X[right] ? left : right;
    } else {
      return X[p] < X[r] ? p : r;
    }
  }
  
  // main()
  public static void main(String[] args){
     
     int[] A = {-1, 2, 6, 12, 9, 2, -5, -2, 8, 5, 7};
     int[] B = new int[A.length];
     int[] C = new int[A.length];
     int minIndex = minArrayIndex(A, 0, A.length-1);
     int maxIndex = maxArrayIndex(A, 0, A.length-1);
     
     for(int x: A) System.out.print(x+" ");
     System.out.println(); 
     
     System.out.println( "minIndex = " + minIndex );  
     System.out.println( "maxIndex = " + maxIndex );  

     reverseArray1(A, A.length, B);
     for(int x: B) System.out.print(x+" ");
     System.out.println();
     
     reverseArray2(A, A.length, C);
     for(int x: C) System.out.print(x+" ");
     System.out.println();
     
     reverseArray3(A, 0, A.length-1);
     for(int x: A) System.out.print(x+" ");
     System.out.println();  
     
  }
  
}
/* Output:
-1 2 6 12 9 2 -5 -2 8 5 7
minIndex = 6
maxIndex = 3
7 5 8 -2 -5 2 9 12 6 2 -1
7 5 8 -2 -5 2 9 12 6 2 -1
7 5 8 -2 -5 2 9 12 6 2 -1
*/