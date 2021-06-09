package operations;

import antlr.HelloParser;
import containers.Array;
import containers.Container;
import containers.Function;
import containers.Value;
import main.LLVMGenerator;
import types.OperationType;
import types.VarType;

import java.util.Map;
import java.util.Stack;

public class IdExitOperation extends Operation {


    private Container container;
    private Stack<Container> stack;
    private Map<String, Container> currentMemory;
    private HelloParser.IdContext ctx;

    public IdExitOperation(Container container,
                           Function currentFunction,
                           Stack<Container> stack,
                           HelloParser.IdContext ctx) {
        this.container = container;
        this.currentFunction = currentFunction;
        this.stack = stack;
        this.ctx = ctx;
        this.operationType = OperationType.EXIT_ID;
    }


    public void operate(boolean insideFunction) {
        if (insideFunction) {
            currentFunction.operations.add(this);
            return;
        }
        if (container.getClass() == Array.class) {
            stack.push(container);
            return;
        }
        Value value = (Value) container;
        if (value.type != VarType.STRING) {
            String lineNo = LLVMGenerator.load(ctx.ID().getText(), value, currentFunction);
            stack.push(new Value(lineNo, value.type, ctx.ID().getText()));
        }

    }
}


