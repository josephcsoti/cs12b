# ------------------------------------------------
# File Name: Makefile
# Desc: Runs, compiles, and submits C/C++ files
# Instructions: Run using command "make <task>"

# Name:   Joseph Csoti
# CruzID: 1617438
# Class:  CMPS 12M
# Date:   02/20/18
# ------------------------------------------------

ADT_NAME  = Dictionary
SOURCES   = $(ADT_NAME).c $(ADT_NAME)Client.c
OBJECTS   = $(ADT_NAME).o $(ADT_NAME)Client.o
TESTS     = $(ADT_NAME)Test.c
HEADERS   = $(ADT_NAME).h
EXEBIN    = $(ADT_NAME)Client
FLAGS     = -std=c99 -Wall
SUBMIT    = submit cmps012b-pt.w18 lab5

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS) $(HEADERS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES) $(HEADERS)
	gcc -c $(FLAGS) $(SOURCES)

submit:
	$(SUBMIT) $(SOURCES) $(HEADERS) $(TESTS) README Makefile

clean:
	rm -f $(EXEBIN) $(OBJECTS)

check:
	valgrind --leak-check=full $(EXEBIN)

grade:
	ls /afs/cats.ucsc.edu/class/cmps012b-pt.w18/lab5/jcsoti/