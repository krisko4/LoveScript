package operations;

import antlr.HelloParser;
import containers.Container;
import containers.Function;
import containers.Value;
import main.LLVMGenerator;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.Stack;

public class CompareOperation extends Operation {


    private Value value1;
    private Value value2;
    private HelloParser.CompareContext ctx;
    private Stack<Container> stack;


    public CompareOperation(Value value1, Function currentFunction, Stack<Container> stack, HelloParser.CompareContext ctx) {
        this.currentFunction = currentFunction;
        this.ctx = ctx;
        this.stack = stack;
        this.value1 = value1;
    }

    public void operate(boolean insideFunction) {
        if (insideFunction) {
            currentFunction.operations.add(this);
            return;
        }
        value2 = (Value) stack.pop();
       // value2 = (Value) stack.pop();
        String operator = ctx.COMPARE().getText();
        LLVMGenerator.compare(value1, value2, operator, currentFunction);

    }

}
