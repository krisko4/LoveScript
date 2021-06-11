package operations.if_operations;

import blocks.IfBlock;
import containers.Function;
import main.LLVMGenerator;
import operations.Operation;

public class CloseIfOperation extends Operation {

    private IfBlock block;

    public CloseIfOperation(Function currentFunction, IfBlock block){
        this.block = block;
        this.currentFunction = currentFunction;
    }

    public void operate(boolean insideFunction){
        if(insideFunction){
            currentFunction.operations.add(this);
            return;
        }
        LLVMGenerator.closeIf(currentFunction);
    }
}
