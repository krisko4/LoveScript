package main;

import antlr.HelloParser;
import containers.Function;
import containers.Value;
import operations.ReturnOperation;
import types.OperationType;
import types.TypeMapper;
import types.VarType;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class LLVMGenerator {

    static String header_text = "";
    static String main_text = "";
    static int reg = 1;
    static int function_reg = 1;
    static String function_header = "";
    static String function_text = "";


    static String generate() {
        String text = "";
        text += "declare i32 @printf(i8*, ...)\n";
        text += "declare i32 @scanf(i8*, ...)\n";
        text += "@strpi = constant [4 x i8] c\"%d\\0A\\00\"\n";
        text += "@strpd = constant [4 x i8] c\"%f\\0A\\00\"\n";
        text += "@strs = constant [3 x i8] c\"%d\\00\"\n";
        text += "@strdd = constant [4 x i8] c\"%lf\\00\"\n";
        text += header_text;
        text += function_header;
        text += function_text;
        text += "define i32 @main() nounwind{\n";
        text += main_text;
        text += "ret i32 0 }\n";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("./tests/test.ll"));
            writer.write(text);
            writer.close();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        return text;

    }

    static String scanf(String id, VarType varType) {
        if (varType == VarType.INT) {
            main_text += "%" + reg + " = call i32 (i8*, ...) @scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @strs, i32 0, i32 0), i32* %" + (id) + ")\n";
        } else {
            main_text += "%" + reg + " = call i32 (i8*, ...) @scanf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @strdd, i32 0, i32 0), double* %" + (id) + ")\n";
        }
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    public static void printf1(Value value, Function currentFunction) {
        int length = value.name.length() + 2;
        if (currentFunction != null) {
            String valueType = TypeMapper.mapToLLVM(value.type);
            String valueName = value.name;
            if (value.type == VarType.INT) {
                function_text += "%" + function_reg + " = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @strpi, i32 0, i32 0), " +valueType  + " " + valueName + ")\n";
            }
            else if (value.type == VarType.REAL) {
                function_text += "%" + function_reg + " = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @strpd, i32 0, i32 0), " +valueType+ " " + valueName + ")\n";
            }
            function_reg++;
            return;
        }
        if (value.type == VarType.INT) {
            main_text += "%" + reg + " = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @strpi, i32 0, i32 0), i32 " + (value.name) + ")\n";

        } else if (value.type == VarType.REAL) {
            main_text += "%" + reg + " = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @strpd, i32 0, i32 0), double " + (value.name) + ")\n";

        } else if (value.variable != null) {
            main_text += "%" + reg + " = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([" + length + " x i8], [" + length + " x i8]* " + value.variable + ", i32 0, i32 0))\n";

        } else {
            header_text += "@str" + reg + " = private constant [" + length + " x i8] c\"" + value.name + "\\0A\\00\"\n";
            main_text += "%" + reg + " = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([" + length + " x i8], [" + length + " x i8]* @str" + reg + ", i32 0, i32 0))\n";
        }
        reg++;

    }

    public static void declare(String id, Value value, Function function) {
        String type = TypeMapper.mapToLLVM(value.type);

        if (value.isGlobal) {
            if(type.equals("i32")){
                header_text += "@" + id + " = global i32 0\n";
                return;
            }
            header_text += "@" + id + " = global double 0.0\n";
            return;
        }
        if (function != null) {
            if (value.isParam) {
                function_header += "%" + function_reg + " = alloca " + type + "\n";
                function_reg++;
                return;
            }
            function_text += "%" + function.name + "." + id + " = alloca " + type + "\n";
            return;
        }
        main_text += "%" + id + " = alloca " + type + "\n";

    }


    public static void assign(String id,
                              Value value,
                              Function function,
                              HelloParser.Assign_stmtContext ctx
                              ) {
        String pointer;
        if (value.isGlobal) {
            pointer = "@";
        } else {
            pointer = "%";
        }
        if (function != null) {
            if (value.type == VarType.INT) {
                if (value.isParam) {
                    function_text += "store i32 " + value.name + ", i32* " + " %" + value.paramIndex + "\n";
                    return;
                }
                function_text += "store i32 " + value.name + ", i32* " + pointer + function.name + "." + id + "\n";
                return;
            }
            if (value.isParam) {
                function_text += "store double " + value.name + ", double* " + " %" + value.paramIndex + "\n";
                return;
            }
            function_text += "store double " + value.name + ", double* " + pointer + function.name + "." + id + "\n";
            return;
        }
        if(ctx.function_call() != null){
            if (value.type == VarType.INT) {
                main_text += "store i32 %" + (reg - 1) + ", i32* " + pointer + id + "\n";
            } else if (value.type == VarType.REAL) {
                main_text += "store double %" + (reg - 1) + ", double* " + pointer + id + "\n";
            }
            return;
        }
        if (value.type == VarType.INT) {
            main_text += "store i32 " + value.name + ", i32* " + pointer + id + "\n";
        } else if (value.type == VarType.REAL) {
            main_text += "store double " + value.name + ", double* " + pointer + id + "\n";
        } else {
            int length = value.name.length() + 2;
            header_text += "@" + id + " = private constant [" + length + " x i8] c\"" + value.name + "\\0A\\00\"\n";
        }
    }

    private static String checkIfGlobal(boolean isGlobal) {
        if (isGlobal) {
            return "@";
        }
        return "%";
    }



    public static String load(String id, Value value, Function currentFunction) {
        String pointer = checkIfGlobal(value.isGlobal);
        if (id.startsWith("%")) {
            id = id.substring(1);
        }
        if (currentFunction != null) {
            if(value.isParam){
                if(value.type == VarType.INT){
                    function_text += "%" + function_reg + " = load i32, i32* " + pointer + value.paramIndex + "\n";
                }
                else {
                    function_text += "%" + function_reg + " = load double, double* " + pointer + value.paramIndex + "\n";
                }
            }

            else {
                if (value.type == VarType.INT) {
                    function_text += "%" + function_reg + " = load i32, i32* " + pointer + currentFunction.name + "." + id + "\n";
                } else {
                    function_text += "%" + function_reg + " = load double, double* " + pointer + currentFunction.name + "." + id + "\n";
                }
            }
            String lineNo = "%" + function_reg;
            function_reg++;
            return lineNo;
        }

        if (value.type == VarType.INT) {
            main_text += "%" + reg + " = load i32, i32* " + pointer + id + "\n";
        }
        if (value.type == VarType.REAL) {
            main_text += "%" + reg + " = load double, double* " + pointer + id + "\n";
        }
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    static String multiplyTwoIntegers(String val1, String val2, Function currentFunction) {
        if (currentFunction != null) {
            function_text += "%" + function_reg + " = mul i32 " + val1 + "," + val2 + "\n";
            String lineNo = "%" + function_reg;
            function_reg++;
            return lineNo;
        }
        main_text += "%" + reg + " = mul i32 " + val1 + "," + val2 + "\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    static String multiplyTwoDoubles(String val1, String val2, Function currentFunction) {
        if (currentFunction != null) {
            function_text += "%" + function_reg + " = fmul double " + val1 + "," + val2 + "\n";
            String lineNo = "%" + function_reg;
            function_reg++;
            return lineNo;
        }
        main_text += "%" + reg + " = fmul double " + val1 + "," + val2 + "\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    public static String addIntAndReal(String val, Function currentFunction) {
        if (currentFunction != null) {
            function_text += "%" + function_reg + " = fadd double " + val + ",%" + (function_reg - 1) + "\n";
            String lineNo = "%" + function_reg;
            function_reg++;
            return lineNo;
        }
        main_text += "%" + reg + " = fadd double " + val + ",%" + (reg - 1) + "\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    public static String addTwoIntegers(String val1, String val2, Function currentFunction) {
        if (currentFunction != null) {
            function_text += "%" + function_reg + " = add i32 " + val1 + "," + val2 + "\n";
            String lineNo = "%" + function_reg;
            function_reg++;
            return lineNo;
        }
        main_text += "%" + reg + " = add i32 " + val1 + "," + val2 + "\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    public static String addTwoDoubles(String val1, String val2, Function currentFunction) {
        if (currentFunction != null) {
            function_text += "%" + function_reg + " = fadd double " + val1 + "," + val2 + "\n";
            String lineNo = "%" + function_reg;
            function_reg++;
            return lineNo;
        }
        main_text += "%" + reg + " = fadd double " + val1 + "," + val2 + "\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    public static String sitofp(String value, Function currentFunction) {
        if (currentFunction != null) {
            function_text += "%" + function_reg + " = sitofp i32 " + value + " to double\n";
            String lineNo = "%" + function_reg;
            function_reg++;
            return lineNo;
        }
        main_text += "%" + reg + " = sitofp i32 " + value + " to double\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    public static String fptosi(String value, Function currentFunction) {
        if (currentFunction != null) {
            function_text += "%" + function_reg + " = fptosi double " + value + " to i32\n";
            String lineNo = "%" + function_reg;
            function_reg++;
            return lineNo;
        }
        main_text += "%" + reg + " = fptosi double " + value + " to i32\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    public static String subIntFromReal(String val, Function currentFunction) {
        if (currentFunction != null) {
            function_text += "%" + function_reg + " = fsub double " + val + ",%" + (function_reg - 1) + "\n";
            String lineNo = "%" + function_reg;
            function_reg++;
            return lineNo;
        }
        main_text += "%" + reg + " = fsub double " + val + ",%" + (reg - 1) + "\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    public static String subIntFromReal1(String val, Function currentFunction) {
        if (currentFunction != null) {
            function_text += "%" + function_reg + " = fsub double %" + (function_reg - 1) + "," + val + "\n";
            String lineNo = "%" + function_reg;
            function_reg++;
            return lineNo;
        }
        main_text += "%" + reg + " = fsub double %" + (reg - 1) + "," + val + "\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    public static String subIntFromInt(String val1, String val2, Function currentFunction) {
        if (currentFunction != null) {
            function_text += "%" + function_reg + " = sub i32 " + val1 + "," + val2 + "\n";
            String lineNo = "%" + function_reg;
            function_reg++;
            return lineNo;
        }
        main_text += "%" + reg + " = sub i32 " + val1 + "," + val2 + "\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    public static String subDoubleFromDouble(String val1, String val2, Function currentFunction) {
        if (currentFunction != null) {
            function_text += "%" + function_reg + " = fsub double " + val1 + "," + val2 + "\n";
            String lineNo = "%" + function_reg;
            function_reg++;
            return lineNo;
        }
        main_text += "%" + reg + " = fsub double " + val1 + "," + val2 + "\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    public static String multiplyIntAndReal(String val, Function currentFunction) {
        if (currentFunction != null) {
            function_text += "%" + function_reg + " = fmul double " + val + ",%" + (function_reg - 1) + "\n";
            String lineNo = "%" + function_reg;
            function_reg++;
            return lineNo;
        }
        main_text += "%" + reg + " = fmul double " + val + ",%" + (reg - 1) + "\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    public static String divideIntAndReal(String val, Function currentFunction) {
        if (currentFunction != null) {
            function_text += "%" + function_reg + " = fdiv double " + val + "," + "%" + (function_reg - 1) + "\n";
            String lineNo = "%" + function_reg;
            function_reg++;
            return lineNo;
        }
        main_text += "%" + reg + " = fdiv double " + val + "," + "%" + (reg - 1) + "\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    public static String divideTwoDoubles(String val2, String val1, Function currentFunction) {
        if (currentFunction != null) {
            function_text += "%" + function_reg + " = fdiv double " + val2 + "," + val1 + "\n";
            String lineNo = "%" + function_reg;
            function_reg++;
            return lineNo;
        }
        main_text += "%" + reg + " = fdiv double " + val2 + "," + val1 + "\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    public static String divideTwoIntegers() {
        main_text += "%" + reg + " = fdiv double %" + (reg - 2) + ",%" + (reg - 1) + "\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    public static void declareArray(String ID, String size, VarType type, boolean isGlobal) {
        String varType;
        if (type == VarType.REAL) {
            varType = "double";
        } else {
            varType = "i32";
        }
        if (isGlobal) {
            header_text += "@" + ID + " = common dso_local global [" + size + " x " + varType + "] zeroinitializer, align 16\n";
            return;
        }
        main_text += "%" + ID + " = alloca [" + size + " x " + varType + "], align 16\n";
    }

    public static String assignArray(String size, String ID, String value, VarType type, String index) {
        String varType;
        if (type == VarType.REAL) {
            varType = "double";
        } else {
            varType = "i32";
        }
        main_text += "%" + reg + " = getelementptr inbounds [" + size + " x " + varType + "],[" + size + " x " + varType + "]* @" + ID + ", i64 0, i64 " + index + "\n";
        String lineNo = "%" + reg;
        reg++;
        main_text += "store " + varType + " " + value + ", " + varType + "* %" + (reg - 1) + "\n";
        return lineNo;
    }


    public static void exitFunction() {
        function_text += "ret i32 0\n}\n";
    }

    public static void returnValue(Value value) {
        String type = TypeMapper.mapToLLVM(value.type);
        function_text += "ret " + type + " " + value.name + "\n";
    }

    public static String callFunction(Function function) {
        String type = TypeMapper.mapToLLVM(function.type);
        main_text += "%" + reg + " = call " + type + " @" + function.name + "(";
        for (int i = 0; i < function.params.size(); i++) {
            String paramType = TypeMapper.mapToLLVM(function.params.get(i).type);
            if (function.params.size() == 1 || i == function.params.size() - 1) {
                main_text += paramType + " " + function.params.get(i).name;
            } else {
                main_text += paramType + " " + function.params.get(i).name + ", ";
            }
        }
        main_text += ")\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    public static void generateFunction(Function currentFunction) {
        AtomicBoolean isReturnPresent = new AtomicBoolean(true);
        currentFunction.operations.stream()
                .filter(operation -> operation.operationType == OperationType.RETURN)
                .findFirst()
                .ifPresentOrElse((operation) -> {
                    ReturnOperation returnOperation = (ReturnOperation)operation;
                    currentFunction.type = returnOperation.value.type;
                }, () ->{
                            currentFunction.type = VarType.INT;
                            isReturnPresent.set(false);
                        });
      //  if(returnOperation.isPresent()){
       //     ReturnOperation operation = (ReturnOperation) returnOperation.get();
       //     currentFunction.type = operation.value.type;
      //  }
        String functionType = TypeMapper.mapToLLVM(currentFunction.type);
        function_header += "define dso_local " + functionType + " @" + currentFunction.name + "(";
        for (int i = 0; i < currentFunction.params.size(); i++) {
            String paramType = TypeMapper.mapToLLVM(currentFunction.params.get(i).type);
            if (currentFunction.params.size() == 1 || i == currentFunction.params.size() - 1) {
                function_header += paramType;
            } else {
                function_header += paramType + ", ";
            }
        }
        function_header += ") nounwind {\n";
        for (int i = 0; i < currentFunction.params.size(); i++) {
            int index = currentFunction.params.size() + i + 1;
            String paramType = TypeMapper.mapToLLVM(currentFunction.params.get(i).type);
            function_header += "%" + index + " = alloca " + paramType + "\n";
            function_header += "store " + paramType + " %" + i + ", " + paramType + "* " + "%" + index + "\n";
          //  function_reg = index;
          //  function_reg++;
        }
        if(!isReturnPresent.get()){
            function_text += "ret i32 0\n";
        }

    }

    public static void closeFunction() {
        function_text += "}\n";
    }
}