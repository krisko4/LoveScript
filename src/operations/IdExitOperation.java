package operations;

import antlr.HelloParser;
import blocks.Block;
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
    private Map<String, Container> globalMemory;
    private HelloParser.IdContext ctx;
    private Block block;

    public IdExitOperation(HashMap<String, Container> currentMemory,
                           HashMap<String, Container> globalMemory,
                           Function currentFunction,
                           Stack<Container> stack,
                           HelloParser.IdContext ctx,
                           Block block) {
        this.currentFunction = currentFunction;
        this.stack = stack;
        this.ctx = ctx;
        this.operationType = OperationType.EXIT_ID;
        this.currentMemory = currentMemory;
        this.globalMemory = globalMemory;
        this.block = block;
    }


    public void operate(boolean insideFunction) {
        // undefined
        if (!currentMemory.containsKey(ctx.ID().getText()) && !globalMemory.containsKey(ctx.ID().getText())) {
            throw new RuntimeException(ctx.ID().getText() + " is undefined. Line: " + ctx.getStart().getLine());
        }
        // global variable
        if (!currentMemory.containsKey(ctx.ID().getText())) {
            currentMemory.put(ctx.ID().getText(), globalMemory.get(ctx.ID().getText()));
        }
        Container container;
        container = currentMemory.get(ctx.ID().getText());
        if (container.getClass() == Array.class) {
            stack.push(container);
            return;
        }
        Value value = (Value) container;
        String lineNo = LLVMGenerator.load(ctx.ID().getText(), value, currentFunction, insideFunction, block);
        stack.push(new Value(lineNo, value.type, value.isGlobal, value.isParam, ctx.ID().getText()));

        if (insideFunction) {
            currentFunction.operations.add(this);
        }

    }
}


