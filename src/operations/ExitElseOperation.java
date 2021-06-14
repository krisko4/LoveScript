package operations;

import containers.Function;
import main.LLVMGenerator;

public class ExitElseOperation extends Operation {


    public ExitElseOperation(Function currentFunction) {
        this.currentFunction = currentFunction;
    }

    public void operate(boolean insideFunction){
        if(insideFunction){
            currentFunction.operations.add(this);
            return;
        }
     //   LLVMGenerator.jumpToEndif(currentFunction);
    }
}