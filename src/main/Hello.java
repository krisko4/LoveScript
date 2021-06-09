package main;


import antlr.HelloLexer;
import antlr.HelloParser;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

public class Hello {

    public static void main(String[] args) throws IOException {

       // Path path = Paths.get("./tests/test.mojFajnyJezyk");
        //CharStream charStream = CharStreams.fromPath(path);
        ANTLRFileStream input = new ANTLRFileStream("./tests/test.mojFajnyJezyk");
        HelloLexer helloLexer = new HelloLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(helloLexer);
        HelloParser helloParser = new HelloParser(tokens);
        ParseTree tree = helloParser.start();
        System.out.println(tree.toStringTree(helloParser));
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new LLVMActions(), tree);

    }

}
