package operations;

import antlr.HelloParser;
import blocks.Block;
import containers.*;
import main.LLVMGenerator;
import types.OperationType;
import types.VarType;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class AssignOperation extends Operation {


    private Value value;
    private HashMap<String, Container> currentMemory;
    private Stack<Container> stack;
    private HelloParser.Assign_stmtContext ctx;
    private List<Function> functions;
    private HashMap<String, Container> globalMemory;
    private Block block;


    public AssignOperation(
            Function currentFunction,
            HelloParser.Assign_stmtContext ctx,
            Block block,
            HashMap<String, Container> currentMemory,
            HashMap<String, Container> globalMemory,
            Stack<Container> stack
    ) {
        this.currentFunction = currentFunction;
        this.ctx = ctx;
        this.operationType = OperationType.ASSIGN;
        this.block = block;
        this.currentMemory = currentMemory;
        this.stack = stack;
        this.globalMemory = globalMemory;
    }


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
        this.value = value;
        this.currentFunction = currentFunction;
        this.currentMemory = currentMemory;
        this.stack = stack;
        this.ctx = ctx;
        this.functions = functions;
        this.operationType = OperationType.ASSIGN;
        this.block = block;
    }

//    public void operate1(boolean insideFunction) {
//        if (insideFunction) {
//            currentFunction.operations.add(this);
//            return;
//        }
//        if (!currentMemory.containsKey(value.variable) && !globalMemory.containsKey(value.variable)) {
//            if (block != null) {
//                value.isGlobal = false;
//            }
//            if (value.type == VarType.ARRAY) {
//                Array array = new Array(value.variable, value.name);
//                currentMemory.put(value.variable, array);
//                return;
//            }
//            LLVMGenerator.declare(value.variable, value, currentFunction, block);
//            LLVMGenerator.assign(value.variable, value, currentFunction, ctx, block);
//            currentMemory.put(value.variable, value);
//
//            return;
//
//        }
//        if (!currentMemory.containsKey(value.variable)) {
//            currentMemory.put(value.variable, globalMemory.get(value.variable));
//        }
//
//        Container previousVal = currentMemory.get(value.variable);
//        if (previousVal.getClass() == Array.class) {
//            Array array = (Array) previousVal;
//            String index = ctx.INT().getText();
//            String size = array.size;
//            if (Integer.parseInt(index) > Integer.parseInt(size)) {
//                throw new RuntimeException("Index " + index + " exceeds array " + previousVal.name + " size, which is " + size + ". Line: " + ctx.getStart().getLine());
//            }
//            if (!array.isDeclared) {
//                LLVMGenerator.declareArray(value.variable, array.size, value.type, value.isGlobal);
//                array.isDeclared = true;
//                array.valuesType = value.type;
//            }
//            if (value.type != array.valuesType) {
//                throw new RuntimeException("Type mismatch error: array " + value.variable + " type is " + array.valuesType + ". containers.Value " + value.name + " type is " + value.type + ". Line: " + ctx.getStart().getLine());
//            }
//            value.name = LLVMGenerator.assignArray(((Array) previousVal).size, value.variable, value.name, value.type, index);
//            array.values.put(index, value);
//            return;
//        }
//        if (((Value) previousVal).isParam) {
//            value.isParam = true;
//            value.isGlobal = false;
//            value.paramIndex = ((Value) previousVal).paramIndex;
//        }
//        if (previousVal.type != value.type) {
//
//            throw new RuntimeException("Mismatched types. Cannot convert " + previousVal.type + " to " + value.type + ". Line: " + ctx.getStart().getLine());
//        }
//        LLVMGenerator.assign(value.variable, value, currentFunction, ctx, block);
//        currentMemory.replace(value.variable, value);
//    }

    public void operate(boolean insideFunction) {
        boolean isFunctionCalled = false;
        if(ctx.function_call() != null){
            isFunctionCalled = true;
        }
        value = (Value)stack.pop();
        Variable variable = new Variable(value);
        variable.setType(value.getType());
        variable.setName(ctx.ID().getText());
        if(currentFunction == null || globalMemory.containsKey(variable.getName())){
            variable.setGlobal(true);
        }
        if(insideFunction){
            if(!currentMemory.containsKey(variable.getName())){
                currentMemory.put(variable.getName(), variable);
            }
            if(variable.isParam()){
                variable.setValue(value);
            }
            currentFunction.operations.add(this);
            return;
        }
        if(!currentMemory.containsKey(variable.getName()) ||
                (currentMemory.containsKey(variable.getName()) && !currentMemory.get(variable.getName()).isAssigned()) ){
            if(block != null){
                variable.setGlobal(false);
            }
            currentMemory.put(variable.getName(), variable);
            LLVMGenerator.declare(variable.getName(), variable, currentFunction, block);
            LLVMGenerator.assign(variable.getName(), variable, currentFunction, isFunctionCalled, block);
            variable.setAssigned(true);
            return;
        }
        Container previousVal = currentMemory.get(variable.getName());
        if (((Variable) previousVal).isParam()) {
            variable.setParam(true);
            variable.setGlobal(false);
            variable.setParamIndex(((Variable) previousVal).paramIndex);
        }
        if (((Variable) previousVal).getValue().getType() != value.getType()) {

            throw new RuntimeException("Mismatched types. Cannot convert " + ((Variable) previousVal).getValue().getType() + " to " + value.getType() + ". Line: " + ctx.getStart().getLine());
        }
        LLVMGenerator.assign(variable.getName(), variable, currentFunction, isFunctionCalled, block);
        variable.setAssigned(true);
        currentMemory.replace(variable.getName(), variable);



    }
}
