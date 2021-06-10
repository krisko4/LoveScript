package operations;

import containers.Function;
import main.LLVMGenerator;

public class EnterWhileOperation extends Operation {

    public EnterWhileOperation(Function currentFunction){
        this.currentFunction = currentFunction;
    }

    public void operate(boolean insideFunction){
        if(insideFunction){
            currentFunction.operations.add(this);
            return;
        }
        LLVMGenerator.generateWhile(currentFunction);
    }

}
