#------------------------------------------------------------------------------
#   Makefile for List ADT
#------------------------------------------------------------------------------

# Variables
JARFILE    = DictionaryClient
MAINCLASS  = DictionaryClient
SOURCES    = Dictionary.java DictionaryClient.java DictionaryInterface.java\
							DuplicateKeyException.java KeyNotFoundException.java
CLASSES    = Dictionary.class DictionaryClient.class DictionaryInterface.class\
            	DuplicateKeyException.class KeyNotFoundException.class Dictionary\$$Node.class
SUBMIT = submit cmps012b-pt.w18 lab7

# Build Targets
all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	chmod +x $(JARFILE)

$(CLASSES): $(SOURCES)
	javac -Xlint $(SOURCES)  

clean:
	rm -f $(CLASSES) $(JARFILE)

submit: $(SOURCES)
	$(SUBMIT) $(SOURCES) README Makefile

check:
	ls /afs/cats.ucsc.edu/class/cmps012b-pt.w18/lab7/jcsoti/