

import java.util.*;

enum VarType {INT, REAL, UNKNOWN}

class Value {
    public String name;
    public VarType type;
    public String variable;

    public Value(String name, VarType type, String variable) {
        this.name = name;
        this.type = type;
        this.variable = variable;
    }

    public Value(String name, VarType type) {
        this.name = name;
        this.type = type;
    }

}

public class LLVMActions extends HelloBaseListener {

    HashMap<String, Value> memory = new HashMap<>();
    Stack<Value> stack = new Stack<>();

    @Override
    public void exitPrint_stmt(HelloParser.Print_stmtContext ctx) {
        Value value = stack.pop();
        LLVMGenerator.printf1(value.name, value.type);
    }

    @Override
    public void exitId(HelloParser.IdContext ctx) {
        if (!memory.containsKey(ctx.ID().getText())) {
            throw new RuntimeException(ctx.ID().getText() + " is undefined.");
        }
        Value value = memory.get(ctx.ID().getText());
        String lineNo = LLVMGenerator.load(ctx.ID().getText(), value.type);
        stack.push(new Value(lineNo, value.type, ctx.ID().getText()));
    }

    @Override
    public void exitInt(HelloParser.IntContext ctx) {
        stack.push(new Value(ctx.INT().getText(), VarType.INT));
    }

    @Override
    public void exitReal(HelloParser.RealContext ctx) {
        stack.push(new Value(ctx.REAL().getText(), VarType.REAL));
    }


    @Override
    public void exitRead_stmt(HelloParser.Read_stmtContext ctx) {

        String ID = ctx.ID().getText();
        String type = ctx.TYPE().getText();
        if (!memory.containsKey(ID)) {
            Value value;
            if (type.equals("INT")) {
                value = new Value(ID, VarType.INT);
            } else {
                value = new Value(ID, VarType.REAL);
            }
            LLVMGenerator.declare(value.name, value.type);
            String lineNo = LLVMGenerator.scanf(ID, value.type);
            Value val = new Value(lineNo, value.type);
            memory.put(ID, val);
        }

    }

    @Override
    public void exitAssign_stmt(HelloParser.Assign_stmtContext ctx) {
        String ID = ctx.ID().getText();
        Value value = stack.pop();
        if (!memory.containsKey(ID)) {
            LLVMGenerator.declare(ID, value.type);
            memory.put(ID, value);
        } else {
            Value previousVal = memory.get(ID);
            if (previousVal.type != value.type) {
                String lineNo;
                if (value.type == VarType.REAL) {
                    lineNo = LLVMGenerator.sitofp(previousVal.name);
                } else {
                    lineNo = LLVMGenerator.sitofp(value.name);
                }
                Value newVal = new Value(lineNo, value.type);
                memory.replace(ID, memory.get(ID), newVal);
            } else {
                memory.replace(ID, memory.get(ID), value);
            }
        }
        LLVMGenerator.assign(ID, value.name, value.type);

    }


    @Override
    public void exitMul(HelloParser.MulContext ctx) {
        Value value1 = stack.pop();
        Value value2 = stack.pop();
        VarType varType;
        String lineNo;
        if (value1.type == VarType.REAL || value2.type == VarType.REAL) {
            varType = VarType.REAL;
            if (value1.type == VarType.INT) {
                LLVMGenerator.sitofp(value1.name);
                lineNo = LLVMGenerator.multiplyIntAndReal(value2.name);
            } else if (value2.type == VarType.INT) {
                LLVMGenerator.sitofp(value2.name);
                lineNo = LLVMGenerator.multiplyIntAndReal(value1.name);
            } else {
                lineNo = LLVMGenerator.multiplyTwoDoubles(value1.name, value2.name);
            }
        } else {
            varType = VarType.INT;
            lineNo = LLVMGenerator.multiplyTwoIntegers(value1.name, value2.name);
        }
        Value value = new Value(lineNo, varType);
        stack.push(value);
    }


    @Override
    public void exitAdd(HelloParser.AddContext ctx) {
        VarType varType;
        String lineNo;
        Value value1 = stack.pop();
        Value value2 = stack.pop();
        if (value1.type == VarType.REAL || value2.type == VarType.REAL) {
            varType = VarType.REAL;
            if (value1.type == VarType.INT) {
                LLVMGenerator.sitofp(value1.name);
                lineNo = LLVMGenerator.addIntAndReal(value2.name);
            } else if (value2.type == VarType.INT) {
                LLVMGenerator.sitofp(value2.name);
                lineNo = LLVMGenerator.addIntAndReal(value1.name);
            } else {
                lineNo = LLVMGenerator.addTwoDoubles(value1.name, value2.name);
            }
        } else {
            varType = VarType.INT;
            lineNo = LLVMGenerator.addTwoIntegers(value1.name, value2.name);
        }
        Value value = new Value(lineNo, varType);
        stack.push(value);
    }


    @Override
    public void exitDivide(HelloParser.DivideContext ctx) {
        Value value2 = stack.pop();
        Value value1 = stack.pop();
        VarType varType = VarType.REAL;
        String lineNo;
        if (value1.type == VarType.REAL || value2.type == VarType.REAL) {
            if (value1.type == VarType.INT) {
                LLVMGenerator.sitofp(value1.name);
                lineNo = LLVMGenerator.divideIntAndReal(value2.name);
            } else if (value2.type == VarType.INT) {
                LLVMGenerator.sitofp(value2.name);
                lineNo = LLVMGenerator.divideIntAndReal(value1.name);
            } else {
                lineNo = LLVMGenerator.divideTwoDoubles(value1.name, value2.name);
            }
        } else {
            LLVMGenerator.sitofp(value1.name);
            LLVMGenerator.sitofp(value2.name);
            lineNo = LLVMGenerator.divideTwoIntegers();
        }
        Value value = new Value(lineNo, varType);
        stack.push(value);
    }




    @Override
    public void exitSub(HelloParser.SubContext ctx) {
        Value value1 = stack.pop();
        Value value2 = stack.pop();
        VarType varType;
        String lineNo;
        if (value1.type == VarType.REAL || value2.type == VarType.REAL) {
            varType=VarType.REAL;
            if (value1.type == VarType.INT) {
                LLVMGenerator.sitofp(value1.name);
                lineNo = LLVMGenerator.subIntFromReal(value2.name);
            } else if (value2.type == VarType.INT) {
                LLVMGenerator.sitofp(value2.name);
                lineNo = LLVMGenerator.subIntFromReal1(value1.name);
            } else {
                lineNo = LLVMGenerator.subDoubleFromDouble(value2.name, value1.name);
            }
        } else {
            varType=VarType.INT;
            lineNo = LLVMGenerator.subIntFromInt(value2.name, value1.name);
        }
        Value value = new Value(lineNo, varType);
        stack.push(value);


    }


    @Override
    public void exitStart(HelloParser.StartContext ctx) {
        System.out.println(LLVMGenerator.generate());
    }


}
