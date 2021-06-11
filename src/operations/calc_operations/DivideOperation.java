package operations.calc_operations;

import containers.Container;
import containers.Function;
import containers.Value;
import main.LLVMGenerator;
import operations.Operation;
import types.OperationType;
import types.VarType;

import java.util.Stack;

public class DivideOperation extends Operation {

    private Value value1;
    private Value value2;
    private Stack<Container> stack;


    public DivideOperation(Value value1, Value value2, Stack<Container> stack, Function currentFunction) {
        this.stack = stack;
        this.currentFunction = currentFunction;
        this.operationType = OperationType.ADD;
        this.value1 = value1;
        this.value2 = value2;

    }

    public void operate(boolean insideFunction) {
        VarType varType = VarType.REAL;
        String lineNo;
        if (value1.getType() == VarType.REAL || value2.getType() == VarType.REAL) {
            if (value1.getType() == VarType.INT) {
                LLVMGenerator.sitofp(value1.getName(), currentFunction, insideFunction);
                lineNo = LLVMGenerator.divideIntAndReal(value2.getName(), currentFunction, insideFunction);
            } else if (value2.getType() == VarType.INT) {
                LLVMGenerator.sitofp(value2.getName(), currentFunction, insideFunction);
                lineNo = LLVMGenerator.divideIntAndReal(value1.getName(), currentFunction, insideFunction);
            } else {
                lineNo = LLVMGenerator.divideTwoDoubles(value1.getName(), value2.getName(), currentFunction, insideFunction);
            }
        } else {
            LLVMGenerator.sitofp(value1.getName(), currentFunction, insideFunction);
            LLVMGenerator.sitofp(value2.getName(), currentFunction, insideFunction);
            lineNo = LLVMGenerator.divideTwoIntegers(currentFunction, insideFunction);
        }
        Value value = new Value(lineNo, varType);
        stack.push(value);
        if (insideFunction) {
            currentFunction.operations.add(this);
        }
    }
}
