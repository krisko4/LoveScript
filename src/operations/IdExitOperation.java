package operations;

import antlr.HelloParser;
import containers.Array;
import containers.Container;
import containers.Function;
import containers.Value;
import main.LLVMGenerator;
import types.OperationType;
import types.VarType;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class IdExitOperation extends Operation {


    private Container container;
    private Stack<Container> stack;
    private Map<String, Container> currentMemory;
    private HelloParser.IdContext ctx;

    public IdExitOperation(HashMap<String, Container> currentMemory,
                           Function currentFunction,
                           Stack<Container> stack,
                           HelloParser.IdContext ctx) {
        this.currentFunction = currentFunction;
        this.stack = stack;
        this.ctx = ctx;
        this.operationType = OperationType.EXIT_ID;
        this.currentMemory = currentMemory;
    }


    public void operate(boolean insideFunction) {
        if (insideFunction) {
            currentFunction.operations.add(this);
            return;
        }
        if (!currentMemory.containsKey(ctx.ID().getText())) {
            throw new RuntimeException(ctx.ID().getText() + " is undefined. Line: " + ctx.getStart().getLine());
        }
        Container container = currentMemory.get(ctx.ID().getText());
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


