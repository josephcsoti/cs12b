/*------------------------------------------------
File Name: DictionaryTest.c
Desc: a Test(er) for Dictionary.c
Instructions: Compile and run

Name:   Joseph Csoti
CruzID: 1617438
Class:  CMPS 12B
Date:   03/16/18
------------------------------------------------*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include"Dictionary.h"

int main(int argc, char* argv[]) {

  Dictionary dict = newDictionary();

  char* keys[] = {"a","b","c","d","e","f","g","h"};
  char* values[] = {"apple","banana","corn","dimsum","eBay","fork","game", "hot"};

  printDictionary(stdout, dict);

  for(int i=0; i<8; i++)
    insert(dict, keys[i], values[i]);

   printDictionary(stdout, dict);

  for(int i=0; i<8; i++)
    printf("(%s, %s)\n", keys[i], lookup(dict, keys[i]));

  // insert(dict, "a", "some new text"); // error: key collision

  delete(dict, "a");
  delete(dict, "d");
  delete(dict, "g");

  printDictionary(stdout, dict);

  for(int i=0; i<8; i++)
    printf("(%s, %s)\n", keys[i], lookup(dict, keys[i])); 

  // delete(dict, "notakey");  // error: key not found

  printf("isEmpty(): %s\n", (isEmpty(dict)?"true":"false"));
  printf("size(): %d\n", size(dict));

  makeEmpty(dict);

  printf("isEmpty(): %s\n", (isEmpty(dict)?"true":"false"));
  printf("size(): %d\n", size(dict));

  freeDictionary(&dict);

  return(EXIT_SUCCESS);
}