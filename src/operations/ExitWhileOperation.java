package operations;

import containers.Function;
import main.LLVMGenerator;

public class ExitWhileOperation extends Operation {

    public ExitWhileOperation(Function currentFunction){
        this.currentFunction = currentFunction;
    }

    public void operate(boolean insideFunction){
        if(insideFunction){
            currentFunction.operations.add(this);
            return;
        }
        LLVMGenerator.closeWhile(currentFunction);
    }
}
