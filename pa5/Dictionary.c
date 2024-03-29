/*------------------------------------------------
File Name: Dictionary.c
Desc:  A key/value dictionary using a hashtable
Instructions:  Complie using make command

Name:   Joseph Csoti
CruzID: 1617438 // jcsoti
Class:  CMPS 12B
Date:   03/01/18
------------------------------------------------*/

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"

const int tableSize = 101; // must be prime

// private types --------

// NodeObj
typedef struct NodeObj{
   char* key;
   char* value;
   struct NodeObj* next;
} NodeObj;
typedef NodeObj* Node;

// newNode()
// constructor for private Node type
Node newNode(char* k, char* v) {
  Node N = malloc(sizeof(NodeObj));
  assert(N!=NULL);
  N->key = k;
  N->value = v;
  N->next = NULL;
  return(N);
}

// freeNode()
// destructor for private Node type
void freeNode(Node* pN) {
  if(pN!=NULL && *pN!=NULL){
    free(*pN);
    *pN = NULL;
  }
}

// DictionaryObj
typedef struct DictionaryObj {
  Node* table;
  int numItems;
} DictionaryObj;

// findKey()
// returns the Node containing key k in R, or returns
// NULL if no such Node exists
Node findKey(Node R, char* k) {
  while(R!=NULL) {
    if((strcmp(R->key,k)==0)==1) {
      return R;
    }
    R=R->next;
  }
  return NULL;
}

// public functions -----------------------------------------------------------

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void){
  Dictionary D = malloc(sizeof(DictionaryObj));
  assert(D!=NULL);
  D->table = calloc(tableSize, sizeof(NodeObj));
  D->numItems = 0;
  return D;
}

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD) {
  if(pD!=NULL && *pD!=NULL) {
    if(!isEmpty(*pD))
      makeEmpty(*pD);

    free(*pD);
    *pD = NULL;
  }
}

// isEmpty()
// returns 1 (true) if D is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D) {
  if( D==NULL ){
    fprintf(stderr, "Error: calling isEmpty() on NULL Dictionary\n");
    return;
  }
  return D->numItems == 0;
}

// size()
// returns the number of (key, value) pairs in D
// pre: none
int size(Dictionary D) {
  if(D == NULL){
    fprintf(stderr, "Dictionary Error: calling size() on NULL Dictionary reference\n");
    return;
  }
  return(D->numItems);
}

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no 
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* k) {
  if(D == NULL) {
    fprintf(stderr, "Error: calling lookup() on NULL Dictionary\n");
    return;
  }
  int index = hash(k);
  Node N = findKey(D->table[index], k);
  if(N == NULL)
    return NULL;
   else
    return N->value;
}

// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v) {
  if(D == NULL) {
    fprintf(stderr, "Error: calling insert() on NULL Dictionary\n");
    return;
  }
  int index = hash(k);
  if(findKey(D->table[index], k) != NULL) {
    fprintf(stderr, "Error: cannot insert() duplicate key: \"%s\"\n", k);
    return;
  }
  Node N=newNode(k,v);
  N->next=D->table[index];
  D->table[index]=N;
  N = NULL;
  D->numItems++;
}

// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char* k){
  if(D == NULL) {
    fprintf(stderr, "Error: calling delete() on NULL Dictionary reference\n");
    return;
   }
  
  int index = hash(k);
  Node N = findKey(D->table[index], k);
  Node P;
  if(N == NULL) {
    fprintf(stderr, "Error: cannot delete() non-existent key: \"%s\"\n", k);
    return;
  }
  else{
      if(N==D->table[index]){
         P=D->table[index];
         D->table[index]=D->table[index]->next;
         P->next=NULL;
      }
      else{
         Node before=D->table[index];
         Node temp=D->table[index]->next;
         while(temp!=N){
            temp=temp->next;
            before=before->next;
         }
         before->next=N->next;
         N->next=NULL;
      }
   }
   D->numItems--;
   freeNode(&N);
}

// makeEmpty()
// re-sets D to the empty state.
// pre: none
void makeEmpty(Dictionary D) {
  if(D == NULL) {
    fprintf(stderr, "Error: calling makeEmpty() on NULL Dictionary reference\n");
    return;
  }
   if(D->numItems == 0) {
    fprintf(stderr, "Error: calling makeEmpty() on an empty Dictionary\n");
    return;
  }
   Node N;
   for(int i=0; i<tableSize; i++){
      while(D->table[i] != NULL){
         N=D->table[i];
         D->table[i] = D->table[i]->next;
         freeNode(&N);
      }
   }
  D->numItems = 0;
  free(D->table);
}

// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D){
  if(D == NULL) {
    fprintf(stderr, "Error: calling printDictionary() on NULL Dictionary\n");
    return;
  }
  Node N;
  for(int i=0; i<tableSize; i++){
    N=D->table[i];
    while(N!=NULL){
      fprintf(out, "%s %s\n", N->key, N->value);
      N=N->next;
    }
  }
}

// --- HASH FUNCTIONS ---

// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift) {
  int sizeInBits = 8*sizeof(unsigned int);
  shift = shift & (sizeInBits - 1);
  if ( shift == 0 )
    return value;
  return (value << shift) | (value >> (sizeInBits - shift));
}
// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char* input) {
  unsigned int result = 0xBAE86554;
  while (*input) {
    result ^= *input++;
    result = rotate_left(result, 5);
 }
 return result;
}
// hash()
// turns a string into an int in the range 0 to tableSize-1
int hash(char* key){
  return pre_hash(key)%tableSize;
}

// --- END HASH FUNCTIONS ---