# ------------------------------------------------
# File Name: Makefile
# Desc: Runs, compiles, submits java files and more
# Instructions: Run using command "make <task>"

# Name:   Joseph Csoti
# CruzID: 1617438
# Class:  CMPS 12M
# Date:   01/18/18
# ------------------------------------------------

JAVASRC = Recursion.java
SOURCES = README Makefile $(JAVASRC)
MAINCLASS = Recursion
CLASSES = Recursion.class
JARFILE = Recursion
SUBMIT = submit cmps012b-pt.w18 pa1

all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	chmod +x $(JARFILE)

$(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)

clean:
	rm $(CLASSES) $(JARFILE)

submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)

grade:
	./pa1.sh

grade_clean:
	rm -f *.txt *.sh
	rm -r backup

check:
	ls /afs/cats.ucsc.edu/class/cmps012b-pt.w18/pa1/jcsoti/
