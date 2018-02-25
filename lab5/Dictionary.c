/*------------------------------------------------
File Name: Dictionary.c
Desc: A dictionary in C using linked lists
Instructions: Run using DictionaryClient harType <in> <out>

Name:   Joseph Csoti
CruzID: 1617438
Class:  CMPS 12M
Date:   02/20/18
------------------------------------------------*/

#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

#include "Dictionary.h"

// private types --------------------------------

// NodeObj
typedef struct NodeObj{
  char* key;
  char* value;
  struct NodeObj* next;
} NodeObj;
typedef NodeObj* Node;

// newNode()
// constructor of the Node type
Node newNode(char* k, char* v) {
  Node N = malloc(sizeof(NodeObj));
  assert(N!=NULL);
  N->key = k;
  N->value = v;
  N->next = NULL;
  return(N);
}

// freeNode()
// destructor for the Node type
void freeNode(Node pN){
    free(pN);
    pN = NULL;
}
// ------------------------------------

//DictionaryObj
typedef struct DictionaryObj {
  struct NodeObj* head;
} DictionaryObj;

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void) {
  Dictionary D = malloc(sizeof(DictionaryObj));
  assert(D!=NULL);
  D->head = NULL;
  return D;
}

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD) {
  if( pD!=NULL && *pD!=NULL ){
    free(*pD);
    *pD = NULL;
 }
}

// isEmpty()
// returns 1 (true) if S is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D) {
  return D->head == NULL;
}

// size()
// returns the number of (key, value) pairs in D
// pre: none
int size(Dictionary D) {
  int size = 0;
  Node cur = D->head;

  while(cur != NULL) {
    size++;
    cur = cur->next;
  }

  return size;
}

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no 
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* k) {

  //Get head
  Node cur = D->head;

  while(cur != NULL) {
    if(strcmp(cur->key, k) == 0)
       return cur->value;
    
    cur = cur->next;
  }

  return NULL;
}

// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v) {

  //Make sure key does not exist
  if(lookup(D, k) != NULL) {
    fprintf(stderr, "Error: key collision\n");
    return;
  }

  //if head node
  if(D->head == NULL) {
    D->head = newNode(k, v);
    return;
  }

  //If in body
  Node cur = D->head;
  while(cur->next != NULL) {
    cur = cur->next;
  }

  cur->next = newNode(k, v);
}

// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char* k) {

  Node cur = D->head;

  //if head
  if((strcmp(cur->key, k) == 0)) {
    Node copy = D->head;
    D->head = cur->next;
    freeNode(copy);
    return;
  }

  //Find node
  while(cur->next != NULL) {
     if(strcmp(cur->next->key, k) == 0){
       Node copy = cur->next;
       cur->next = cur->next->next;
       freeNode(copy);
       return;
     }
    
    cur = cur->next;
  }
}

// makeEmpty()
// re-sets D to the empty state.
// pre: none
void makeEmpty(Dictionary D) {

  Node head = D->head;
  Node temp;

  //Free each node
  while (head != NULL) {
    temp = head;
    head = head->next;
    freeNode(temp);
  }

  freeDictionary(D);
}

// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D) {

  Node cur = D->head;

  while(cur != NULL) {
    fprintf(out, "%s %s\n", cur->key, cur->value);
    cur = cur->next;
  }
}