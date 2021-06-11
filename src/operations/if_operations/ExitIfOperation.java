package operations.if_operations;

import blocks.IfBlock;
import containers.Container;
import containers.Function;
import main.LLVMGenerator;
import operations.Operation;

import java.util.HashMap;

public class ExitIfOperation extends Operation {

    private IfBlock block;

    public ExitIfOperation(Function currentFunction, IfBlock block) {
        this.currentFunction = currentFunction;
        this.block = block;
    }

    public void operate(boolean insideFunction) {
        if (insideFunction) {
            currentFunction.operations.add(this);
            return;
        }
        LLVMGenerator.exitIf(currentFunction);
    }

}