

import java.util.*;

enum VarType {INT, REAL, STRING, ARRAY}

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
        LLVMGenerator.printf1(value);
    }

    @Override
    public void exitId(HelloParser.IdContext ctx) {
        if (!memory.containsKey(ctx.ID().getText())) {
            throw new RuntimeException(ctx.ID().getText() + " is undefined. Line: " + ctx.getStart().getLine());
        }
        Value value = memory.get(ctx.ID().getText());
        if (value.type != VarType.STRING) {
            String lineNo = LLVMGenerator.load(ctx.ID().getText(), value.type);
            stack.push(new Value(lineNo, value.type, ctx.ID().getText()));
        }
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
    public void exitString(HelloParser.StringContext ctx) {
        String text = ctx.STRING().getText().replace("\"", "");
        stack.push(new Value(text, VarType.STRING));
    }


    @Override
    public void exitRead_stmt(HelloParser.Read_stmtContext ctx) {
        try {
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

        } catch (NullPointerException nullPointerException) {
            throw new RuntimeException("Invalid read construction - ID not found. Valid example: odczytaj INT do x. Line: " + ctx.getStart().getLine());
        }
    }


    @Override
    public void exitAssign_stmt(HelloParser.Assign_stmtContext ctx) {
        String ID = ctx.ID().getText();
        try {
            Value value = stack.pop();
            if (!memory.containsKey(ID)) {
                switch (value.type) {
                    case ARRAY:
                        LLVMGenerator.declareArray(ID, value.name, true);
                        return;
                    case STRING:
                        value.variable = "@" + ID;
                        stack.push(value);
                        return;
                    default:
                        LLVMGenerator.declare(ID, value.type);
                        LLVMGenerator.assign(ID, value.name, value.type);
                        memory.put(ID, value);
                        return;
                }
            }
//                if(value.type == VarType.ARRAY){
//                    LLVMGenerator.declareArray()
//                }
//                if (value.type != VarType.STRING) {
//                    LLVMGenerator.declare(ID, value.type);
//                } else {
//                    value.variable = "@" + ID;
//                    stack.push(value);
//                }
//                LLVMGenerator.assign(ID, value.name, value.type);
//                memory.put(ID, value);
//                return;
//            }
            Value previousVal = memory.get(ID);
            if (previousVal.type != value.type) {
                throw new RuntimeException("Mismatched types. Cannot convert " + previousVal.type + " to " + value.type + ". Line: " + ctx.getStart().getLine());
            }
            LLVMGenerator.assign(ID, value.name, value.type);
            memory.replace(ID, value);
        } catch (EmptyStackException e) {
            throw new RuntimeException("Invalid assing attempt. Valid example: x=5. Line: " + ctx.getStart().getLine());
        }

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
            varType = VarType.REAL;
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
            varType = VarType.INT;
            lineNo = LLVMGenerator.subIntFromInt(value2.name, value1.name);
        }
        Value value = new Value(lineNo, varType);
        stack.push(value);


    }

    @Override public void exitArray(HelloParser.ArrayContext ctx) {
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
            Value value = memory.get(id);
            if (value.type == VarType.INT) {
                return;
            }
            if (value.type == VarType.STRING) {
                throw new RuntimeException("Cannot convert type STRING to INT. Line: " + ctx.getStart().getLine());
            }
            value.name = LLVMGenerator.fptosi(value.name);
            value.type = VarType.INT;
            stack.push(value);
        } catch (NullPointerException nullPointerException) {
            throw new RuntimeException("Invalid construction - converting value must be an ID. Valid example: (int)x. In future versions number convertions will be available. Line: " + ctx.getStart().getLine());
        }


    }

    @Override
    public void exitToreal(HelloParser.TorealContext ctx) {
        String id = ctx.ID().getText();
        Value value = memory.get(id);
        if (value.type == VarType.REAL) {
            return;
        }
        if (value.type == VarType.STRING) {
            throw new RuntimeException("Cannot convert type STRING to INT. Line: " + ctx.getStart().getLine());
        }
        value.name = LLVMGenerator.sitofp(value.name);
        value.type = VarType.REAL;
        stack.push(value);
    }


}
