# ------------------------------------------------
# File Name: Makefile
# Desc: Runs, compiles, and submits C/C++ files
# Instructions: Run using command "make <task>"

# Name:   Joseph Csoti
# CruzID: 1617438
# Class:  CMPS 12M
# Date:   02/06/18
# ------------------------------------------------

FLAGS = -std=c99 -Wall
CSOURCE = charType.c
SOURCES = $(CSOURCE) README Makefile 
OBJECTS = charType.o
EXEBIN = charType
SUBMIT = submit cmps012b-pt.w18 lab4

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
	valgrind --leak-check=full charType charType.c out

grade:
	ls /afs/cats.ucsc.edu/class/cmps012b-pt.w18/lab4/jcsoti/