/*------------------------------------------------
File Name: FileReverse.java
Desc: Reverses lines of text in a file and outputs to a file
Instructions: Run using java FileReverse <input> <output>

Name:   Joseph Csoti
CruzID: 1617438
Class:  CMPS 12M
Date:   01/22/18
------------------------------------------------*/

import java.io.*;
import java.util.Scanner;

class FileReverse{

  public static void main(String[] args) throws IOException{

    //check has 2 arguments
    if(args.length < 2){
      System.out.println("Usage: FileReverse <input> <output>");
      System.exit(1);
    }

    //Init scanner + reader
    Scanner scan = new Scanner(new File(args[0]));
    PrintWriter print = new PrintWriter(new FileWriter(args[1]));

    //Read through each line
    while(scan.hasNextLine()){

      String line = scan.nextLine().trim() + " "; //Get line, remove spacing, add trailing to work on "blank" lines
      String[] tokens = line.split("\\s+"); //Split lines into tokens into array

      //Loop each token
      for(String str: tokens){
        String res = stringReverse(str, str.length()); //reverse string
        print.println(res); //write to file
      }
    }

    // close files
    scan.close();
    print.close();
  }

  // s:string > string to reverse
  // n:int > length to reverse
  public static String stringReverse(String s, int n){

    //String is one letter
    if(n < 1)
      return s; //return letter
    
    //return last letter
    return s.charAt(n-1) + stringReverse(s.substring(0,n-1), n-1);
  }
}