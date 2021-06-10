package operations;

import containers.Function;
import main.LLVMGenerator;

public class EnterIfOperation extends Operation {

    public EnterIfOperation(Function currentFunction){
        this.currentFunction = currentFunction;
    }

    public void operate(boolean insideFunction){
        if(insideFunction){
            currentFunction.operations.add(this);
            return;
        }
        LLVMGenerator.generateIf(currentFunction);
    }

}
