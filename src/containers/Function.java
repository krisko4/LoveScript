package containers;

import main.LLVMGenerator;
import operations.Operation;
import types.VarType;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Function extends Container {

    public List<Value> params;
    public boolean isDeclared;
    public String startLine;
    public List<Operation> operations;
    public boolean isConstructed = false;
    public int operationCounter = 0;
    private Stack stack;
    private int index;
    public Function(String name, VarType type, Stack stack, int index) {
        this.params = new ArrayList<>();
        this.name = name;
        this.type = type;
        this.isDeclared = false;
        this.operations = new ArrayList<>();
        this.stack = stack;
        this.index = index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void generateLLVM(){

      //  LLVMGenerator.function_reg = LLVMGenerator.function_reg - operationCounter;
        operations.forEach(operation -> {
            operation.operate(false);
        });
        LLVMGenerator.generateFunction(this);
        LLVMGenerator.callFunction(this);
        LLVMGenerator.closeFunction();
    }

    public Stack<Container> getStack() {
        return this.stack;
    }
}
