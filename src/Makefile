

ANTLR=../antlr-4.9.2-complete.jar

all: generate compile test

generate:
	java -jar $(ANTLR) -o output Hello.g4

compile:
	javac -cp $(ANTLR):output:. Hello.java

test:
	java -cp $(ANTLR):output:. Hello test.mojFajnyJezyk > test.ll
	lli test.ll

clean:
	rm -f test.ll
	rm -f *.class
	rm -rf output

