package operations;

import containers.Function;
import main.LLVMGenerator;

public class ExitIfOperation extends Operation {

    public ExitIfOperation(Function currentFunction){
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
