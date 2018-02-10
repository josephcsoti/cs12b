/*------------------------------------------------
File Name: charType.c
Desc: Takes in line from file and prints # of types of chars it has
Instructions: Compile using gcc & Run using charType <in> <out>

Name:   Joseph Csoti
CruzID: 1617438
Class:  CMPS 12M
Date:   02/06/18
------------------------------------------------*/

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <assert.h>

#define MAX_STRING_LENGTH 100

// function prototype 
void extract_chars(char* s, char* a, char* d, char* p, char* w);

// function main which takes command line arguments 
int main(int argc, char* argv[]) {

  //IN & OUT
  FILE* in;                 
  FILE* out;

  char* l;  //line              
  char* a; //alphanum
  char* d; //digits
  char* p; //punc
  char* w; //whitespace

  int lineNumber;

  // check command line for correct number of arguments 
  if( argc != 3 ){
    printf("Usage: %s input-file output-file\n", argv[0]);
    exit(EXIT_FAILURE);
  }

  // open input file for reading 
  if( (in=fopen(argv[1], "r")) == NULL ) {
    printf("Unable to read from file %s\n", argv[1]);
    exit(EXIT_FAILURE);
  }

  // open output file for writing 
  if( (out=fopen(argv[2], "w")) == NULL ) {
    printf("Unable to write to file %s\n", argv[2]);
    exit(EXIT_FAILURE);
  }

  // allocate strings l, a, d, p, w
  l = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
  a = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
  d = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
  p = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
  w = calloc(MAX_STRING_LENGTH+1, sizeof(char) );

  lineNumber = 1;

  assert(l!=NULL && a!=NULL && d!=NULL && p!=NULL && w!=NULL);

  // read each line in input file, extract alpha-numeric characters 
  while( fgets(l, MAX_STRING_LENGTH, in) != NULL ) {

    extract_chars(l, a, d, p, w);

    //print lines
    fprintf(out, "line %i contains:\n", lineNumber);
    fprintf(out, "%i alphabetic character%s: %s\n", strlen(a), strlen(a) == 1 ? "":"s",  a);
    fprintf(out, "%i numeric character%s: %s\n", strlen(d), strlen(d) == 1 ? "":"s", d);
    fprintf(out, "%i punctuation character%s: %s\n", strlen(p), strlen(p) == 1 ? "":"s", p);
    fprintf(out, "%i whitespace character%s: %s\n", strlen(w), strlen(w) == 1 ? "":"s", w);

    lineNumber++;

   }

  // free heap memory 
  free(l);
  free(a);
  free(d);
  free(p);
  free(w);

  // close input and output files 
  fclose(in);
  fclose(out);

  return EXIT_SUCCESS;
}

//get chars from string and put in correct "arrays"
void extract_chars(char* s, char* a, char* d, char* p, char* w) {

   int i = 0;
   int a_cur = 0;
   int d_cur = 0;
   int p_cur = 0;
   int w_cur = 0;

   while(s[i]!='\0' && i<MAX_STRING_LENGTH) {

    //What is it
    if( isdigit((int) s[i]) )      d[d_cur++] = s[i];
    else if( isalnum((int) s[i]) ) a[a_cur++] = s[i];
    else if( ispunct((int) s[i]) ) p[p_cur++] = s[i];
    else if( isspace((int) s[i]) ) w[w_cur++] = s[i];

    i++;

   }

   //End array so it is a string
   a[a_cur] = '\0';
   d[d_cur] = '\0';
   p[p_cur] = '\0';
   w[w_cur] = '\0';
}