package operations;

import antlr.HelloParser;
import containers.Container;
import containers.Function;
import containers.Value;
import main.LLVMGenerator;
import types.OperationType;

import java.util.Stack;

public class ReturnOperation extends Operation {


    private Stack<Container> stack;
    public Value value;
    private HelloParser.Return_stmtContext ctx;



    public ReturnOperation(Stack<Container> stack, Function currentFunction, HelloParser.Return_stmtContext ctx){
        this.stack = stack;
        this.currentFunction = currentFunction;
        this.operationType = OperationType.RETURN;
        this.ctx = ctx;
    }

    public void operate(boolean insideFunction){
        if(insideFunction){
            currentFunction.operations.add(this);
            currentFunction = null;
            return;
        }
        if(currentFunction == null){
            throw new RuntimeException("Return statement can be used only inside function. Line: " + ctx.getStart().getLine());
        }
        value = (Value)stack.pop();
        LLVMGenerator.returnValue(value);
    }

}
