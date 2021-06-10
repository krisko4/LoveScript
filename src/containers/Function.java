package containers;

import main.LLVMGenerator;
import operations.Operation;
import types.VarType;

import java.util.ArrayList;
import java.util.List;

public class Function extends Container {

    public List<Value> params;
    public boolean isDeclared;
    public String startLine;
    public List<Operation> operations;
    public boolean isConstructed = false;
    public int operationCounter = 0;

    public Function(String name, VarType type) {
        this.params = new ArrayList<>();
        this.name = name;
        this.type = type;
        this.isDeclared = false;
        this.operations = new ArrayList<>();
    }

    public void generateLLVM(){

        LLVMGenerator.function_reg = LLVMGenerator.function_reg - operationCounter;
        operations.forEach(operation -> {
            operation.operate(false);
        });
        LLVMGenerator.generateFunction(this);
        LLVMGenerator.callFunction(this);
        LLVMGenerator.closeFunction();
    }
}
