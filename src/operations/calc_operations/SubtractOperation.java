package operations.calc_operations;

import containers.Container;
import containers.Function;
import containers.Value;
import main.LLVMGenerator;
import operations.Operation;
import types.OperationType;
import types.VarType;

import java.util.Stack;

public class SubtractOperation extends Operation {

    private Value value1;
    private Value value2;
    private Stack<Container> stack;


    public SubtractOperation(Value value1, Value value2, Stack<Container> stack, Function currentFunction) {
        this.stack = stack;
        this.currentFunction = currentFunction;
        this.operationType = OperationType.ADD;
        this.value1 = value1;
        this.value2 = value2;

    }

    public void operate(boolean insideFunction) {
        VarType varType;
        String lineNo;
        if (value1.type == VarType.REAL || value2.type == VarType.REAL) {
            varType = VarType.REAL;
            if (value1.type == VarType.INT) {
                LLVMGenerator.sitofp(value1.name, currentFunction, insideFunction);
                lineNo = LLVMGenerator.subIntFromReal(value2.name, currentFunction, insideFunction);
            } else if (value2.type == VarType.INT) {
                LLVMGenerator.sitofp(value2.name, currentFunction, insideFunction);
                lineNo = LLVMGenerator.subIntFromReal1(value1.name, currentFunction, insideFunction);
            } else {
                lineNo = LLVMGenerator.subDoubleFromDouble(value2.name, value1.name, currentFunction, insideFunction);
            }
        } else {
            varType = VarType.INT;
            lineNo = LLVMGenerator.subIntFromInt(value2.name, value1.name, currentFunction, insideFunction);
        }
        Value value = new Value(lineNo, varType);
        stack.push(value);
        if (insideFunction) {
            currentFunction.operations.add(this);
        }
    }

}
