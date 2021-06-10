package main;

import antlr.HelloBaseListener;
import antlr.HelloParser;
import containers.Array;
import containers.Container;
import containers.Function;
import containers.Value;
import operations.*;
import types.TypeMapper;
import types.VarType;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class LLVMActions extends HelloBaseListener {

    private final HashMap<String, Container> memory = new HashMap<>();
    private final HashMap<String, Container> temporaryMemory = new HashMap<>();
    private HashMap<String, Container> currentMemory = new HashMap<>();
    private final Stack<Container> stack = new Stack<>();
    private Function currentFunction = null;
    private boolean insideFunction = false;
    private boolean insideFunctionCall = false;
    private int argNo = 0;
    private final List<Function> functions = new ArrayList<>();

    @Override
    public void exitPrint_stmt(HelloParser.Print_stmtContext ctx) {
        new PrintOperation(currentFunction, stack).operate(insideFunction);
    }


    @Override
    public void exitId(HelloParser.IdContext ctx) {

        new IdExitOperation(currentMemory, currentFunction, stack, ctx).operate(insideFunction);
    }

    @Override
    public void exitArray_element(HelloParser.Array_elementContext ctx) {
        Array array = (Array) currentMemory.get(ctx.ID().getText());
        Value value = array.values.get(ctx.INT().getText());
        try {
            String lineNo = LLVMGenerator.load(value.name, value, currentFunction, insideFunction);
            stack.push(new Value(lineNo, value.type, value.name));
        } catch (NullPointerException nullPointerException) {
            throw new RuntimeException("The value " + ctx.ID().getText() + "[" + ctx.INT().getText() + "] has not been defined. Line: " + ctx.getStart().getLine());
        }


    }

    @Override
    public void enterFunction_start(HelloParser.Function_startContext ctx) {
        LLVMGenerator.function_reg = currentFunction.params.size() + 2;
    }

    @Override
    public void exitCompare(HelloParser.CompareContext ctx) {
        //Value value1 = (Value)stack.pop();
        Value value1 = (Value)stack.pop();
//        String operator = ctx.COMPARE().getText();
//        LLVMGenerator.compare(value1, value2, operator);
        new CompareOperation(value1, currentFunction,stack,ctx).operate(insideFunction);

    }

    @Override
    public void enterWhile_stmt(HelloParser.While_stmtContext ctx) {
        new EnterWhileOperation(currentFunction).operate(insideFunction);
    }

    @Override
    public void enterWhile_start(HelloParser.While_startContext ctx) {
        new EnterWhileOperation(currentFunction).operate(insideFunction);
    }

    @Override
    public void enterIf_start(HelloParser.If_startContext ctx) {
        new EnterIfOperation(currentFunction).operate(insideFunction);
    }

    @Override
    public void exitWhile_stmt(HelloParser.While_stmtContext ctx) {
        new ExitWhileOperation(currentFunction).operate(insideFunction);
    }

    @Override
    public void exitIf_stmt(HelloParser.If_stmtContext ctx) {
        new ExitIfOperation(currentFunction).operate(insideFunction);
    }

    // Insert function parameters into memory with correct LLVM indices
    @Override
    public void exitFunction_param(HelloParser.Function_paramContext ctx) {
        Value value = new Value(null, VarType.UNDEFINED, ctx.ID().getText());
        value.isGlobal = false;
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
        //   main.LLVMGenerator.enterFunction(ctx.ID().getText(), ctx.TYPE().getText());
        // VarType type = TypeMapper.mapType(ctx.TYPE().getText());
        insideFunction = true;
        currentFunction = new Function(ctx.ID().getText(), VarType.UNDEFINED);
    }

    @Override
    public void exitFunction_stmt(HelloParser.Function_stmtContext ctx) {
        functions.add(currentFunction);
        temporaryMemory.clear();
        currentFunction = null;
        insideFunction = false;
        currentMemory = memory;
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
            currentFunction.params.get(argNo).name = ctx.REAL().getText();
            currentFunction.params.get(argNo).type = VarType.REAL;
            argNo++;
        }
        stack.push(new Value(ctx.REAL().getText(), VarType.REAL));
    }

    @Override
    public void exitString(HelloParser.StringContext ctx) {
        String text = ctx.STRING().getText().replace("\"", "");
        stack.push(new Value(text, VarType.STRING));
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
                LLVMGenerator.declare(value.name, value, currentFunction);
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
        currentFunction = functions.stream()
                .filter(function -> function.name.equals(ctx.ID().getText()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Function " + ctx.ID().getText() + " is undefined. Line: " + ctx.getStart().getLine()));
        if (ctx.expression().size() != currentFunction.params.size()) {
            throw new RuntimeException("Arguments quantity mismatch. Function " + ctx.ID().getText()
                    + " has " + currentFunction.params.size() + " argument/s." +
                    " You are calling " + ctx.expression().size() + " argument/s. Line: " + ctx.getStart().getLine());
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
        if (currentMemory.containsKey(ID) && currentFunction != null) {
            value.isParam = true;
            value.isGlobal = false;
        }
        // if we're not in function, get normal memory reference
        else if(currentFunction == null) {
            currentMemory = memory;
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
        new AssignOperation(value, currentFunction, currentMemory, memory, stack, ctx, functions).operate(insideFunction);
    }


    @Override
    public void exitMul(HelloParser.MulContext ctx) {
        Value value1 = (Value) stack.pop();
        Value value2 = (Value) stack.pop();
        VarType varType;
        String lineNo;
        if (value1.type == VarType.REAL || value2.type == VarType.REAL) {
            varType = VarType.REAL;
            if (value1.type == VarType.INT) {
                LLVMGenerator.sitofp(value1.name, currentFunction);
                lineNo = LLVMGenerator.multiplyIntAndReal(value2.name, currentFunction);
            } else if (value2.type == VarType.INT) {
                LLVMGenerator.sitofp(value2.name, currentFunction);
                lineNo = LLVMGenerator.multiplyIntAndReal(value1.name, currentFunction);
            } else {
                lineNo = LLVMGenerator.multiplyTwoDoubles(value1.name, value2.name, currentFunction);
            }
        } else {
            varType = VarType.INT;
            lineNo = LLVMGenerator.multiplyTwoIntegers(value1.name, value2.name, currentFunction);
        }
        Value value = new Value(lineNo, varType);
        stack.push(value);
    }


    @Override
    public void exitAdd(HelloParser.AddContext ctx) {
        Value value1 = (Value) stack.pop();
        Value value2 = (Value) stack.pop();
        new AddOperation(value1, value2, stack, currentFunction).operate(insideFunction);

//        VarType varType;
//        String lineNo;
//        if (value1.type == VarType.REAL || value2.type == VarType.REAL) {
//            varType = VarType.REAL;
//            if (value1.type == VarType.INT) {
//                LLVMGenerator.sitofp(value1.name, currentFunction);
//                lineNo = LLVMGenerator.addIntAndReal(value2.name, currentFunction);
//            } else if (value2.type == VarType.INT) {
//                LLVMGenerator.sitofp(value2.name, currentFunction);
//                lineNo = LLVMGenerator.addIntAndReal(value1.name, currentFunction);
//            } else {
//                lineNo = LLVMGenerator.addTwoDoubles(value1.name, value2.name, currentFunction);
//            }
//
//        } else {
//            varType = VarType.INT;
//            lineNo = LLVMGenerator.addTwoIntegers(value1.name, value2.name, currentFunction, insideFunction);
//        }
//        Value value = new Value(lineNo, varType);
//        stack.push(value);
    }


    @Override
    public void exitDivide(HelloParser.DivideContext ctx) {
        Value value2 = (Value) stack.pop();
        Value value1 = (Value) stack.pop();
        VarType varType = VarType.REAL;
        String lineNo;
        if (value1.type == VarType.REAL || value2.type == VarType.REAL) {
            if (value1.type == VarType.INT) {
                LLVMGenerator.sitofp(value1.name, currentFunction);
                lineNo = LLVMGenerator.divideIntAndReal(value2.name, currentFunction);
            } else if (value2.type == VarType.INT) {
                LLVMGenerator.sitofp(value2.name, currentFunction);
                lineNo = LLVMGenerator.divideIntAndReal(value1.name, currentFunction);
            } else {
                lineNo = LLVMGenerator.divideTwoDoubles(value1.name, value2.name, currentFunction);
            }
        } else {
            LLVMGenerator.sitofp(value1.name, currentFunction);
            LLVMGenerator.sitofp(value2.name, currentFunction);
            lineNo = LLVMGenerator.divideTwoIntegers();
        }
        Value value = new Value(lineNo, varType);
        stack.push(value);
    }


    @Override
    public void exitSub(HelloParser.SubContext ctx) {
        Value value1 = (Value) stack.pop();
        Value value2 = (Value) stack.pop();
        VarType varType;
        String lineNo;
        if (value1.type == VarType.REAL || value2.type == VarType.REAL) {
            varType = VarType.REAL;
            if (value1.type == VarType.INT) {
                LLVMGenerator.sitofp(value1.name, currentFunction);
                lineNo = LLVMGenerator.subIntFromReal(value2.name, currentFunction);
            } else if (value2.type == VarType.INT) {
                LLVMGenerator.sitofp(value2.name, currentFunction);
                lineNo = LLVMGenerator.subIntFromReal1(value1.name, currentFunction);
            } else {
                lineNo = LLVMGenerator.subDoubleFromDouble(value2.name, value1.name, currentFunction);
            }
        } else {
            varType = VarType.INT;
            lineNo = LLVMGenerator.subIntFromInt(value2.name, value1.name, currentFunction);
        }
        Value value = new Value(lineNo, varType);
        stack.push(value);


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
            value.name = LLVMGenerator.fptosi(value.name, currentFunction);
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
        value.name = LLVMGenerator.sitofp(value.name, currentFunction);
        value.type = VarType.REAL;
        stack.push(value);
    }


}
