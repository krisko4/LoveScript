package operations;

import containers.Container;
import containers.Function;
import containers.Value;
import main.LLVMGenerator;
import types.OperationType;

import java.util.Stack;

public class ReturnOperation extends Operation {


    private Stack<Container> stack;
    public Value value;



    public ReturnOperation(Stack<Container> stack, Function currentFunction){
        this.stack = stack;
        this.currentFunction = currentFunction;
        this.operationType = OperationType.RETURN;
    }

    public void operate(boolean insideFunction){
        if(insideFunction){
            currentFunction.operations.add(this);
            return;
        }
        value = (Value)stack.pop();
        LLVMGenerator.returnValue(value);
    }

}
