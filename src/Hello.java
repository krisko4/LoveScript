


import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Hello {

    public static void main(String[] args) throws IOException {


        Path path = Paths.get("test.mojFajnyJezyk");
        CharStream charStream = CharStreams.fromPath(path);
        HelloLexer helloLexer = new HelloLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(helloLexer);
        HelloParser helloParser = new HelloParser(tokens);
        ParseTree tree = helloParser.start();
        System.out.println(tree.toStringTree(helloParser));
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new LLVMActions(), tree);

    }

}
