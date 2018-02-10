# ------------------------------------------------
# File Name: Makefile
# Desc: Runs, compiles, and submits java files
# Instructions: Run using command "make <task>"

# Name:   Joseph Csoti
# CruzID: 1617438
# Class:  CMPS 12B
# Date:   02/02/18
# ------------------------------------------------

JAVAC      = javac 
MAINCLASS  = DictionaryClient
JAVASRC    = $(wildcard *.java)
SOURCES    = $(JAVASRC) Makefile README
CLASSES    = $(patsubst %.java, %.class, $(JAVASRC))
JARCLASSES = $(patsubst %.class, %*.class, $(CLASSES))
JARFILE    = $(MAINCLASS)

SUBMIT = submit cmps012b-pt.w18 pa3
CHECK = ls /afs/cats.ucsc.edu/class/cmps012b-pt.w18/pa3/jcsoti/


all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(JARCLASSES)
	chmod +x $(JARFILE)
	rm Manifest

%.class: %.java
	$(JAVAC) $<

clean:
	rm -f *.class $(JARFILE)

submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)

check:
	$(CHECK)

test:
	javac DictionaryTest.java
	java DictionaryTest

