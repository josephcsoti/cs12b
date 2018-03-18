# ------------------------------------------------
# File Name: Makefile
# Desc: Runs, compiles, and submits c files
# Instructions: Run using command "make <task>"

# Name:   Joseph Csoti
# CruzID: 1617438 // jcsoti
# Class:  CMPS 12B
# Date:   03/16/18
# ------------------------------------------------

ADT     = Dictionary
EXEBIN  = $(ADT)Client
SOURCES = $(ADT).c $(EXEBIN).c
OBJECTS = $(ADT).o $(EXEBIN).o
HEADERS = $(ADT).h
SUBMIT  = submit cmps012b-pt.w18 pa5

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS) $(HEADERS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES) $(HEADERS)
	gcc -c $(FLAGS) $(SOURCES)

clean :
	rm -f $(EXEBIN) $(OBJECTS)

check:
	valgrind --leak-check=full $(EXEBIN)

grade:
	ls /afs/cats.ucsc.edu/class/cmps012b-pt.w18/pa5/jcsoti/

submit:
	$(SUBMIT) $(SOURCES) $(HEADERS) DictionaryTest.c README Makefile