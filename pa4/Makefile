# ------------------------------------------------
# File Name: Makefile
# Desc: Runs, compiles, and submits java files
# Instructions: Run using command "make <task>"

# Name:   Joseph Csoti
# CruzID: 1617438 // jcsoti
# Class:  CMPS 12B
# Date:   03/01/18
# ------------------------------------------------

JAVAC      = javac 
MAINCLASS  = Simulation
JAVASRC    = $(wildcard *.java)
SOURCES    = $(JAVASRC) Makefile README
CLASSES    = $(patsubst %.java, %.class, $(JAVASRC))
JARCLASSES = $(patsubst %.class, %*.class, $(CLASSES))
JARFILE    = $(MAINCLASS)

SUBMIT = submit cmps012b-pt.w18 pa4
CHECK = ls /afs/cats.ucsc.edu/class/cmps012b-pt.w18/pa4/jcsoti/


all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(JARCLASSES)
	chmod +x $(JARFILE)
	rm Manifest

%.class: %.java
	$(JAVAC) $<

submit: $(SOURCES)
	$(SUBMIT) $(SOURCES) QueueEmptyException.java  QueueInterface.java  Queue.java  QueueTest.java

check:
	$(CHECK)

clean:
	rm *.class $(JARFILE)