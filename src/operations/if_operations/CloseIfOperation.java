package operations.if_operations;

import containers.Function;
import main.LLVMGenerator;
import operations.Operation;

public class CloseIfOperation extends Operation {

    public CloseIfOperation(Function currentFunction){
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
