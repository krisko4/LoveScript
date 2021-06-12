package operations;

import antlr.HelloParser;
import containers.Container;
import containers.Function;
import containers.Value;
import main.LLVMGenerator;
import types.VarType;

import java.util.Stack;

public class CompareOperation extends Operation {


    private Value value1;
    private Value value2;
    private HelloParser.CompareContext ctx;
    private Stack<Container> stack;

    public CompareOperation(
                            Function currentFunction,
                            Stack<Container> stack,
                            HelloParser.CompareContext ctx
                            ) {
        this.currentFunction = currentFunction;
        this.ctx = ctx;
        this.stack = stack;
       // this.value1 = value1;
    }

    public void operate(boolean insideFunction) {
        if (insideFunction) {
            currentFunction.operations.add(this);
            return;
        }
        value1 = (Value) stack.pop();
        value2 = (Value) stack.pop();
        String operator = ctx.COMPARE().getText();
        String lineNo;
        if (value1.getType() == VarType.REAL || value2.getType() == VarType.REAL) {
            if (value1.getType() == VarType.INT) {
                lineNo = LLVMGenerator.sitofp(value1.getName(), currentFunction, insideFunction);
                LLVMGenerator.compareIntAndDouble(lineNo, value2.getName(), operator, currentFunction);
            } else if (value2.getType() == VarType.INT) {
                lineNo =  LLVMGenerator.sitofp(value2.getName(), currentFunction, insideFunction);
                LLVMGenerator.compareIntAndDouble(value1.getName(),lineNo, operator, currentFunction);
            } else {
                LLVMGenerator.compareTwoDoubles(value1, value2, operator, currentFunction);
            }
        } else {
            LLVMGenerator.compareTwoIntegers(value1, value2, operator, currentFunction);
        }

       // LLVMGenerator.compare(value1, value2, operator, currentFunction);

    }

}
