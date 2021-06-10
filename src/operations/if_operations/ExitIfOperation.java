package operations.if_operations;

import containers.Container;
import containers.Function;
import main.LLVMGenerator;
import operations.Operation;

import java.util.HashMap;

public class ExitIfOperation extends Operation {



    public ExitIfOperation(Function currentFunction) {
        this.currentFunction = currentFunction;
    }

    public void operate(boolean insideFunction) {
        if (insideFunction) {
            currentFunction.operations.add(this);
            return;
        }
        LLVMGenerator.exitIf(currentFunction);
    }

}