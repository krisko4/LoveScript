package operations;

import antlr.HelloParser;
import containers.Array;
import containers.Container;
import containers.Function;
import containers.Value;
import main.LLVMGenerator;
import types.OperationType;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class AssignOperation extends Operation {


    private Value value;
    private HashMap<String, Container> currentMemory;
    private HashMap<String, Container> memory;
    private Stack<Container> stack;
    private HelloParser.Assign_stmtContext ctx;
    private List<Function> functions;


    public AssignOperation(Value value) {

    }

    public AssignOperation(
            Value value,
            Function currentFunction,
            HashMap<String, Container> currentMemory,
            HashMap<String, Container> memory,
            Stack<Container> stack,
            HelloParser.Assign_stmtContext ctx,
            List<Function> functions) {
        this.memory = memory;
        this.value = value;
        this.currentFunction = currentFunction;
        this.currentMemory = currentMemory;
        this.stack = stack;
        this.ctx = ctx;
        this.functions = functions;
        this.operationType = OperationType.ASSIGN;
    }

    public void operate(boolean insideFunction) {
        if (insideFunction) {
            currentFunction.operations.add(this);
            return;
        }
        if (!currentMemory.containsKey(value.variable)) {
            switch (value.type) {
                case ARRAY:
                    Array array = new Array(value.variable, value.name);
                    currentMemory.put(value.variable, array);
                    return;
                case STRING:
                    value.variable = "@" + value.variable;
                    stack.push(value);
                    return;
                default:
                    LLVMGenerator.declare(value.variable, value, currentFunction);
                    LLVMGenerator.assign(value.variable, value, currentFunction, ctx);
                    currentMemory.put(value.variable, value);
                    return;
            }
        }
        Container previousVal = currentMemory.get(value.variable);
        if (previousVal.getClass() == Array.class) {
            Array array = (Array) previousVal;
            String index = ctx.INT().getText();
            String size = array.size;
            if (Integer.parseInt(index) > Integer.parseInt(size)) {
                throw new RuntimeException("Index " + index + " exceeds array " + previousVal.name + " size, which is " + size + ". Line: " + ctx.getStart().getLine());
            }
            if (!array.isDeclared) {
                LLVMGenerator.declareArray(value.variable, array.size, value.type, value.isGlobal);
                array.isDeclared = true;
                array.valuesType = value.type;
            }
            if (value.type != array.valuesType) {
                throw new RuntimeException("Type mismatch error: array " + value.variable + " type is " + array.valuesType + ". containers.Value " + value.name + " type is " + value.type + ". Line: " + ctx.getStart().getLine());
            }
            value.name = LLVMGenerator.assignArray(((Array) previousVal).size, value.variable, value.name, value.type, index);
            array.values.put(index, value);
            return;
        }
        if (((Value) previousVal).isParam) {
            value.isParam = true;
            value.isGlobal = false;
            value.paramIndex = ((Value) previousVal).paramIndex;
        }
        if (previousVal.type != value.type) {
            throw new RuntimeException("Mismatched types. Cannot convert " + previousVal.type + " to " + value.type + ". Line: " + ctx.getStart().getLine());
        }
        LLVMGenerator.assign(value.variable, value, currentFunction, ctx);
        currentMemory.replace(value.variable, value);
    }
}
