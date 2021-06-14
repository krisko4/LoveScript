package operations;

import antlr.HelloParser;
import blocks.Block;
import containers.*;
import main.LLVMGenerator;
import types.OperationType;
import types.VarType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class IdExitOperation extends Operation {


    private Container container;
    private Stack<Container> stack;
    private HashMap<String, Container> currentMemory;
    private HelloParser.IdContext ctx;
    private Block block;

    public IdExitOperation(HashMap<String, Container> currentMemory,
                           Function currentFunction,
                           Stack<Container> stack,
                           HelloParser.IdContext ctx,
                           Block block) {
        this.currentFunction = currentFunction;
        this.stack = stack;
        this.ctx = ctx;
        this.operationType = OperationType.EXIT_ID;
        this.currentMemory = currentMemory;
        this.block = block;
    }

    public IdExitOperation(Function currentFunction, HelloParser.IdContext ctx, Block block) {
        this.currentFunction = currentFunction;
        this.ctx = ctx;
        this.block = block;
    }

//
//    public void operate1(boolean insideFunction) {
//        // undefined
//        if (!currentMemory.containsKey(ctx.ID().getText()) && !globalMemory.containsKey(ctx.ID().getText())) {
//            throw new RuntimeException(ctx.ID().getText() + " is undefined. Line: " + ctx.getStart().getLine());
//        }
//        // global variable
//        if (!currentMemory.containsKey(ctx.ID().getText())) {
//            currentMemory.put(ctx.ID().getText(), globalMemory.get(ctx.ID().getText()));
//        }
//        Container container;
//        container = currentMemory.get(ctx.ID().getText());
//        if (container.getClass() == Array.class) {
//            stack.push(container);
//            return;
//        }
//        Value value = (Value) container;
//        String lineNo = LLVMGenerator.load(ctx.ID().getText(), value, currentFunction, insideFunction, block);
//        stack.push(new Value(lineNo, value.type, value.isGlobal, value.isParam, ctx.ID().getText()));
//        if (insideFunction) {
//            currentFunction.operations.add(this);
//        }
//
//    }


    public void operate(boolean insideFunction) {
        if (!currentMemory.containsKey(ctx.ID().getText())) {
            throw new RuntimeException(ctx.ID().getText() + " is undefined. Line: " + ctx.getStart().getLine());
        }
        if (insideFunction) {
            Variable variable = (Variable) currentMemory.get(ctx.ID().getText());
            stack.push(variable.getValue());
            currentFunction.operations.add(this);
            return;
        }
        Variable variable = (Variable) currentMemory.get(ctx.ID().getText());
        variable.getValue().setName(LLVMGenerator.load(ctx.ID().getText(), variable, currentFunction, insideFunction, block));
        stack.push(variable.getValue());

    }

}


