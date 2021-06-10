package operations;

import antlr.HelloParser;
import blocks.Block;
import containers.Array;
import containers.Container;
import containers.Function;
import containers.Value;
import main.LLVMGenerator;
import types.OperationType;
import types.VarType;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class AssignOperation extends Operation {


    private Value value;
    private HashMap<String, Container> currentMemory;
    private HashMap<String, Container> globalMemory;
    private Stack<Container> stack;
    private HelloParser.Assign_stmtContext ctx;
    private List<Function> functions;
    private Block block;


    public AssignOperation(
            Value value,
            Function currentFunction,
            HashMap<String, Container> currentMemory,
            HashMap<String, Container> globalMemory,
            Stack<Container> stack,
            HelloParser.Assign_stmtContext ctx,
            List<Function> functions,
            Block block
    ) {
        this.globalMemory = globalMemory;
        this.value = value;
        this.currentFunction = currentFunction;
        this.currentMemory = currentMemory;
        this.stack = stack;
        this.ctx = ctx;
        this.functions = functions;
        this.operationType = OperationType.ASSIGN;
        this.block = block;
    }

    public void operate(boolean insideFunction) {
        if (insideFunction) {
            currentFunction.operations.add(this);
            return;
        }
        if (!currentMemory.containsKey(value.variable) && !globalMemory.containsKey(value.variable)) {
            if (block != null) {
                value.isGlobal = false;
            }
            if (value.type == VarType.ARRAY) {
                Array array = new Array(value.variable, value.name);
                currentMemory.put(value.variable, array);
                return;
            }
            LLVMGenerator.declare(value.variable, value, currentFunction, block);
            LLVMGenerator.assign(value.variable, value, currentFunction, ctx, block);
            currentMemory.put(value.variable, value);

            return;

        }
        if (!currentMemory.containsKey(value.variable)) {
            currentMemory.put(value.variable, globalMemory.get(value.variable));
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
        LLVMGenerator.assign(value.variable, value, currentFunction, ctx, block);
        currentMemory.replace(value.variable, value);
    }
}
