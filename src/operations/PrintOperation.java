package operations;

import antlr.HelloParser;
import blocks.Block;
import containers.Array;
import containers.Container;
import containers.Function;
import containers.Value;
import main.LLVMGenerator;
import types.OperationType;

import java.util.Stack;

public class PrintOperation extends Operation {


    private Container container;
    private Stack<Container> stack;
    private Block block;
    private HelloParser.Print_stmtContext ctx;

    public PrintOperation(Function currentFunction, Stack<Container> stack, Block block, HelloParser.Print_stmtContext ctx){
        this.currentFunction = currentFunction;
        this.stack = stack;
        this.operationType = OperationType.PRINT;
        this.block = block;
        this.ctx = ctx;

    }


    public void operate(boolean insideFunction){
        if(insideFunction){
            currentFunction.operations.add(this);
            return;
        }
        Container container = stack.pop();
        if(container.getClass() == Array.class){
            Array array = (Array)container;
            if(array.values.isEmpty()){
                throw new RuntimeException("You are trying to print an empty array. Array name: " + array.name  + ". Line: " + ctx.getStart().getLine());
            }
            array.values.forEach((key, value) -> {
                String val = value.name;
                value.isGlobal = false;
                value.name = LLVMGenerator.load(value.name, value, currentFunction, insideFunction, block);
                LLVMGenerator.printf1(value, currentFunction);
                value.name = val;
            });
            return;
        }
        Value value =(Value)container;
        LLVMGenerator.printf1(value, currentFunction);
    }


}
