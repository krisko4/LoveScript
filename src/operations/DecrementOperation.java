package operations;

import antlr.HelloParser;
import blocks.Block;
import containers.Container;
import containers.Function;
import containers.Value;
import containers.Variable;
import types.VarType;

import java.util.HashMap;
import java.util.Stack;

public class DecrementOperation extends Operation{

    private Stack<Container> stack;
    private HelloParser.Decrement_stmtContext ctx;
    private HashMap<String, Container> currentMemory;
    private Block block;

    public DecrementOperation(HashMap<String, Container> currentMemory,
                              Stack<Container> stack,
                              Function currentFunction,
                              HelloParser.Decrement_stmtContext ctx,
                              Block block) {
        this.stack = stack;
        this.currentFunction = currentFunction;
        this.ctx = ctx;
        this.currentMemory = currentMemory;
        this.block = block;
    }

    public void operate(boolean insideFunction){
        if (!currentMemory.containsKey(ctx.ID().getText())) {
            throw new RuntimeException(ctx.ID().getText() + " is undefined. Line: " + ctx.getStart().getLine());
        }
        Variable variable = (Variable) currentMemory.get(ctx.ID().getText());
        stack.push(new Value("1", VarType.INT));
        if(insideFunction){
            currentFunction.operations.add(this);
        }
        new DecrementedAssignOperation(variable, stack, currentFunction, block, ctx).operate(insideFunction);

    }
}
