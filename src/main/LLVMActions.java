package main;

import antlr.HelloBaseListener;
import antlr.HelloParser;
import blocks.Block;
import blocks.IfBlock;
import blocks.WhileBlock;
import containers.Array;
import containers.Container;
import containers.Function;
import containers.Value;
import operations.*;
import operations.calc_operations.AddOperation;
import operations.calc_operations.DivideOperation;
import operations.calc_operations.MultiplyOperation;
import operations.calc_operations.SubtractOperation;
import operations.if_operations.CloseIfOperation;
import operations.if_operations.EnterIfOperation;
import operations.if_operations.ExitIfOperation;
import operations.while_operations.EnterWhileOperation;
import operations.while_operations.ExitWhileOperation;
import operations.while_operations.WhileStartOperation;
import types.VarType;
import java.util.*;
import java.util.stream.Collectors;


public class LLVMActions extends HelloBaseListener {

    private final HashMap<String, Container> globalMemory = new HashMap<>();
    private final HashMap<String, Container> temporaryMemory = new HashMap<>();
    private HashMap<String, Container> currentMemory = new HashMap<>();

    private Stack<Container> stack = new Stack<>();
    private Function currentFunction = null;
    private boolean insideFunction = false;
    private boolean insideFunctionCall = false;
    private int functionIndex = 0;
    private int argNo = 0;
    private Block block;
    private boolean insideBlock = false;
    private String blockName = null;
    private int ifBlockNumber = 0;
    private int whileBlockNumber = 0;
    private final List<Function> functions = new ArrayList<>();

    @Override
    public void exitPrint_stmt(HelloParser.Print_stmtContext ctx) {
        new PrintOperation(currentFunction, stack, block, ctx).operate(insideFunction);
    }


    @Override
    public void exitId(HelloParser.IdContext ctx) {

        new IdExitOperation(currentMemory, globalMemory, currentFunction, stack, ctx, block).operate(insideFunction);
    }

    @Override
    public void exitArray_element(HelloParser.Array_elementContext ctx) {
        Array array = (Array) currentMemory.get(ctx.ID().getText());
        Value value = array.values.get(ctx.INT().getText());
        value.isGlobal = false;
        try {
            String lineNo = LLVMGenerator.load(value.name, value, currentFunction, insideFunction, block);
            stack.push(new Value(lineNo, value.type, value.isGlobal, value.name));
        } catch (NullPointerException nullPointerException) {
            throw new RuntimeException("The value " + ctx.ID().getText() + "[" + ctx.INT().getText() + "] has not been defined. Line: " + ctx.getStart().getLine());
        }


    }

    @Override
    public void enterFunction_start(HelloParser.Function_startContext ctx) {
        if(!currentMemory.isEmpty()){
            LLVMGenerator.function_reg = currentFunction.params.get(currentFunction.params.size() - 1).paramIndex + 1;
        }
    }

    @Override
    public void exitCompare(HelloParser.CompareContext ctx) {
        //Value value1 = (Value)stack.pop();
        Value value1 = (Value) stack.pop();
//        String operator = ctx.COMPARE().getText();
//        LLVMGenerator.compare(value1, value2, operator);
        new CompareOperation(value1, currentFunction, stack, ctx).operate(insideFunction);

    }

    @Override
    public void enterWhile_stmt(HelloParser.While_stmtContext ctx) {
        new EnterWhileOperation(currentFunction).operate(insideFunction);
    }

    @Override
    public void enterWhile_start(HelloParser.While_startContext ctx) {
       // insideBlock = true;
        whileBlockNumber++;
        block = new WhileBlock(whileBlockNumber);
        currentMemory = new HashMap<>();
        if(currentFunction != null){
            if(!currentFunction.params.isEmpty()){
                currentFunction.params.forEach(param -> {
                    currentMemory.put(param.variable, param);
                });
            }
        }
        new WhileStartOperation(currentFunction).operate(insideFunction);
    }

    @Override
    public void enterIf_start(HelloParser.If_startContext ctx) {
        ifBlockNumber++;
        block = new IfBlock(ifBlockNumber);
        currentMemory = new HashMap<>();
        if(currentFunction != null){
            if(!currentFunction.params.isEmpty()){
                currentFunction.params.forEach(param -> {
                    currentMemory.put(param.variable, param);
                });
            }
        }

        new EnterIfOperation(currentFunction, currentMemory).operate(insideFunction);
    }

    @Override
    public void enterIf_end(HelloParser.If_endContext ctx) {
        new ExitIfOperation(currentFunction).operate(insideFunction);
    }

    @Override
    public void exitWhile_stmt(HelloParser.While_stmtContext ctx) {
        currentMemory = globalMemory;
        block = null;
        new ExitWhileOperation(currentFunction).operate(insideFunction);
    }

    @Override
    public void exitIf_stmt(HelloParser.If_stmtContext ctx) {
        currentMemory = globalMemory;
        block = null;
        new CloseIfOperation(currentFunction).operate(insideFunction);
    }

//    @Override
//    public void enterElse_stmt(HelloParser.Else_stmtContext ctx) {
//        new EnterElseOperation(currentFunction).operate(insideFunction);
//    }

//    @Override
//    public void exitElse_stmt(HelloParser.Else_stmtContext ctx) {
//        new ExitElseOperation(currentFunction).operate(insideFunction);
//    }

    // Insert function parameters into memory with correct LLVM indices
    @Override
    public void exitFunction_param(HelloParser.Function_paramContext ctx) {
        Value value = new Value(null, VarType.UNDEFINED,false, true, ctx.ID().getText());
        value.isParam = true;
        currentFunction.params.add(value);
        currentMemory.put(ctx.ID().getText(), value);
        int i = 1;
        for (String paramName : currentMemory.keySet()) {
            Value val = (Value) currentMemory.get(paramName);
            val.paramIndex = currentMemory.size() + i;
            i++;
        }
    }


    @Override
    public void enterFunction_stmt(HelloParser.Function_stmtContext ctx) {
        currentMemory = new HashMap<>();
        insideFunction = true;
        currentFunction = new Function(ctx.ID().getText(), VarType.UNDEFINED, stack, functionIndex);
        functionIndex++;
    }

    @Override
    public void exitFunction_stmt(HelloParser.Function_stmtContext ctx) {
        functions.add(currentFunction);
        stack = new Stack<>();
        currentFunction = null;
        insideFunction = false;
        currentMemory = globalMemory;
    }


    @Override
    public void exitInt(HelloParser.IntContext ctx) {
        if (insideFunctionCall) {
            Value param = currentFunction.params.get(argNo);
            param.type = VarType.INT;
            param.name = ctx.INT().getText();
            stack.push(param);
            argNo++;
            return;
        }
        if (insideFunction) {
            stack.push(new Value(ctx.INT().getText(), VarType.INT, false, false));
            return;
        }
        stack.push(new Value(ctx.INT().getText(), VarType.INT, true, false));
    }

    @Override
    public void exitReal(HelloParser.RealContext ctx) {
        if (insideFunctionCall) {
            Value param = currentFunction.params.get(argNo);
            param.type = VarType.REAL;
            param.name = ctx.REAL().getText();
            stack.push(param);
            argNo++;
            return;
        }
        if (insideFunction) {
            stack.push(new Value(ctx.REAL().getText(), VarType.REAL, false, false));
            return;
        }
        stack.push(new Value(ctx.REAL().getText(), VarType.REAL, true, false));
    }

    @Override
    public void exitString(HelloParser.StringContext ctx) {
        String text = ctx.STRING().getText().replace("\"", "");
        stack.push(new Value(text, VarType.STRING, true, null));
    }


    @Override
    public void exitRead_stmt(HelloParser.Read_stmtContext ctx) {
        try {
            String ID = ctx.ID().getText();
            String type = ctx.TYPE().getText();
            if (!currentMemory.containsKey(ID)) {
                Value value;
                if (type.equals("INT")) {
                    value = new Value(ID, VarType.INT);
                } else {
                    value = new Value(ID, VarType.REAL);
                }
                LLVMGenerator.declare(value.name, value, currentFunction, block);
                String lineNo = LLVMGenerator.scanf(ID, value.type);
                Value val = new Value(lineNo, value.type);
                currentMemory.put(ID, val);
            }

        } catch (NullPointerException nullPointerException) {
            throw new RuntimeException("Invalid read construction - ID not found. Valid example: odczytaj INT do x. Line: " + ctx.getStart().getLine());
        }
    }

    @Override
    public void exitReturn_stmt(HelloParser.Return_stmtContext ctx) {
        new ReturnOperation(stack, currentFunction).operate(insideFunction);

    }




    @Override
    public void exitFunction_call(HelloParser.Function_callContext ctx) {
        if (!currentFunction.isConstructed) {
            currentFunction.generateLLVM();
            currentFunction.isConstructed = true;
        } else {
            LLVMGenerator.callFunction(currentFunction);
        }
        currentFunction = null;
        insideFunctionCall = false;
        argNo = 0;
    }

    @Override
    public void enterFunction_call(HelloParser.Function_callContext ctx) {

        List<Function> foundFunctions = functions.stream().filter(function ->
                function.name.equals(ctx.ID().getText())
        ).collect(Collectors.toList());
        if(foundFunctions.isEmpty()){
            throw new RuntimeException("Function " + ctx.ID().getText() + " is undefined. Line: " + ctx.getStart().getLine());
        }
        int size = foundFunctions.get(0).params.size();
        currentFunction = foundFunctions.stream()
                .filter(function -> function.params.size() == ctx.expression().size())
                .findFirst()
                .orElseThrow(()->new RuntimeException("Arguments quantity mismatch. Function " + ctx.ID().getText()
                + " has " + size + " argument/s." +
                " You are calling " + ctx.expression().size() + " argument/s. Line: " + ctx.getStart().getLine()));
        stack = currentFunction.getStack();
        if(currentFunction.params.size() > 0){
            LLVMGenerator.function_reg = currentFunction.params.get(currentFunction.params.size() - 1).paramIndex + 1;
        }
        else{
            LLVMGenerator.function_reg = 1;
        }

        insideFunctionCall = true;


    }

    @Override
    public void exitAssign_stmt(HelloParser.Assign_stmtContext ctx) {
        // take value off the stack
        Value value = (Value) stack.pop();
        // get variable name
        String ID = ctx.ID().getText();
        // assign variable to value
        value.variable = ID;
        // if we're in function and variable is a function parameter
        if(currentFunction != null){
          //  if(currentMemory.containsKey(ID)){
          //      value.isParam = true;
          //      value.isGlobal = false;
          //      currentMemory.get(ID).name = value.name;
          //  }
             if(globalMemory.containsKey(ID)){
                value.isGlobal = true;
            }
        }

        // if we're not in function, get normal memory reference
        else {
                if(block == null){
                    currentMemory = globalMemory;
                    // check if function call is being assigned. If so, our value will be a function
                    if (ctx.function_call() != null) {
                        Function function = functions.stream()
                                .filter(function1 -> function1.name.equals(ctx.function_call().ID().getText()))
                                .findFirst()
                                .orElseThrow(() -> new RuntimeException("Invalid function call. Line: " + ctx.getStart().getLine()));
                        value.type = function.type;
                        value.name = function.name;
                        value.isGlobal = true;
                        value.isParam = false;

                    }


            }
        }
        new AssignOperation(value, currentFunction, currentMemory, globalMemory, stack, ctx, functions, block).operate(insideFunction);

    }


    @Override
    public void exitMul(HelloParser.MulContext ctx) {
        Value value1 = (Value) stack.pop();
        Value value2 = (Value) stack.pop();
        new MultiplyOperation(value1, value2, stack, currentFunction).operate(insideFunction);

    }


    @Override
    public void exitAdd(HelloParser.AddContext ctx) {
        Value value1 = (Value) stack.pop();
        Value value2 = (Value) stack.pop();
        new AddOperation(value1, value2, stack, currentFunction).operate(insideFunction);

    }


    @Override
    public void exitDivide(HelloParser.DivideContext ctx) {
        Value value2 = (Value) stack.pop();
        Value value1 = (Value) stack.pop();
        new DivideOperation(value1, value2, stack, currentFunction).operate(insideFunction);

    }


    @Override
    public void exitSub(HelloParser.SubContext ctx) {
        Value value1 = (Value) stack.pop();
        Value value2 = (Value) stack.pop();
        new SubtractOperation(value1, value2, stack, currentFunction).operate(insideFunction);

    }

    @Override
    public void exitArray(HelloParser.ArrayContext ctx) {
        stack.push(new Value(ctx.INT().getText(), VarType.ARRAY));
    }


    @Override
    public void exitStart(HelloParser.StartContext ctx) {
        System.out.println(LLVMGenerator.generate());
    }

    @Override
    public void exitToint(HelloParser.TointContext ctx) {
        try {
            String id = ctx.ID().getText();
            Value value = (Value) currentMemory.get(id);
            if (value.type == VarType.INT) {
                return;
            }
            if (value.type == VarType.STRING) {
                throw new RuntimeException("Cannot convert type STRING to INT. Line: " + ctx.getStart().getLine());
            }
            value.name = LLVMGenerator.fptosi(value.name, currentFunction, insideFunction);
            value.type = VarType.INT;
            stack.push(value);
        } catch (NullPointerException nullPointerException) {
            throw new RuntimeException("Invalid construction - converting value must be an ID. Valid example: (int)x. In future versions number convertions will be available. Line: " + ctx.getStart().getLine());
        }


    }

    @Override
    public void exitToreal(HelloParser.TorealContext ctx) {
        String id = ctx.ID().getText();
        Value value = (Value) currentMemory.get(id);
        if (value.type == VarType.REAL) {
            return;
        }
        if (value.type == VarType.STRING) {
            throw new RuntimeException("Cannot convert type STRING to INT. Line: " + ctx.getStart().getLine());
        }
        value.name = LLVMGenerator.sitofp(value.name, currentFunction, insideFunction);
        value.type = VarType.REAL;
        stack.push(value);
    }


}
