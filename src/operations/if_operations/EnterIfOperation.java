package operations.if_operations;

import containers.Container;
import containers.Function;
import main.LLVMGenerator;
import operations.Operation;

import java.util.HashMap;

public class EnterIfOperation extends Operation {

    private HashMap<String, Container> currentMemory;

    public EnterIfOperation(Function currentFunction, HashMap<String, Container> currentMemory){
        this.currentFunction = currentFunction;
        this.currentMemory = currentMemory;
    }

    public void operate(boolean insideFunction){
        if(insideFunction){
            currentFunction.operations.add(this);
            return;
        }
        LLVMGenerator.enterIf(currentFunction);
    }

}
