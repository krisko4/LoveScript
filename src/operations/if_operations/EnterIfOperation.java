package operations.if_operations;

import blocks.IfBlock;
import containers.Container;
import containers.Function;
import main.LLVMGenerator;
import operations.Operation;

import java.util.HashMap;

public class EnterIfOperation extends Operation {

    private HashMap<String, Container> currentMemory;
    private IfBlock block;

    public EnterIfOperation(Function currentFunction, HashMap<String, Container> currentMemory, IfBlock block){
        this.currentFunction = currentFunction;
        this.currentMemory = currentMemory;
        this.block = block;
    }

    public void operate(boolean insideFunction){
        if(insideFunction){
            currentFunction.operations.add(this);
            return;
        }
        LLVMGenerator.enterIf(currentFunction);
    }

}
