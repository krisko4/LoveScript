package operations;

import containers.Function;
import main.LLVMGenerator;

public class EnterElseOperation extends Operation {

    public EnterElseOperation(Function currentFunction) {
        this.currentFunction = currentFunction;
    }

    public void operate(boolean insideFunction){
        if(insideFunction){
            currentFunction.operations.add(this);
            return;
        }
    //    LLVMGenerator.enterElse(currentFunction);
    }

}
