# ------------------------------------------------
# File Name: Makefile
# Desc: Runs, compiles, and submits C/C++ files
# Instructions: Run using command "make <task>"

# Name:   Joseph Csoti
# CruzID: 1617438
# Class:  CMPS 12M
# Date:   01/31/18
# ------------------------------------------------

FLAGS = -std=c99 -Wall
CSOURCE = FileReverse.c
SOURCES = $(CSOURCE) README Makefile 
OBJECTS = FileReverse.o
EXEBIN = FileReverse
SUBMIT = submit cmps012b-pt.w18 lab3

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(CSOURCE)
	gcc -c $(FLAGS) $(CSOURCE)

submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)

clean:
	rm $(EXEBIN) $(OBJECTS)

check:
	ls /afs/cats.ucsc.edu/class/cmps012b-pt.w18/lab3/jcsoti/
