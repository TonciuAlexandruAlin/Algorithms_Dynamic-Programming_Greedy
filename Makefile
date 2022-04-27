JFLAGS = -g
JC = javac

build:
	$(JC) *.java

run-p1:
	java Walsh

run-p2:
	java Statistics

run-p3:
	java Prinel

run-p4:
	java Crypto

run-p5:
	java Regele

clean:
	$(RM) *.class