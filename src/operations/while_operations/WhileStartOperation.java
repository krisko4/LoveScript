package operations.while_operations;

import containers.Function;
import main.LLVMGenerator;
import operations.Operation;

public class WhileStartOperation extends Operation {

    public WhileStartOperation(Function currentFunction){
        this.currentFunction = currentFunction;
    }

    public void operate(boolean insideFunction){
        if(insideFunction){
            currentFunction.operations.add(this);
            return;
        }
        LLVMGenerator.startWhile(currentFunction);
    }
}
