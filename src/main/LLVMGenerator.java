package main;

import antlr.HelloParser;
import blocks.Block;
import containers.Function;
import containers.Value;
import containers.Variable;
import operations.ReturnOperation;
import types.CompareMapper;
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
    public static int function_reg = 1;
    static String function_header = "";
    static String function_text = "";
    static int temp_reg = 1;
    private static int ifCounter = 0;
    private static int whileCounter = 0;
    private static String functionZone = "";


    static String generate() {


        String text = "";
        text += "declare i32 @printf(i8*, ...)\n";
        text += "declare i32 @scanf(i8*, ...)\n";
        text += "@strpi = constant [4 x i8] c\"%d\\0A\\00\"\n";
        text += "@strpd = constant [4 x i8] c\"%f\\0A\\00\"\n";
        text += "@strs = constant [3 x i8] c\"%d\\00\"\n";
        text += "@strps = constant [4 x i8] c\"%s\\0A\\00\"";
        text += "@strdd = constant [4 x i8] c\"%lf\\00\"\n";
        text += functionZone;
    //    text += function_header;
     //   text += function_text;
        text += header_text;
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
        int length = value.getName().length() + 2;
        if (currentFunction != null) {
            String valueType = TypeMapper.mapToLLVM(value.getType());
            String valueName = value.getName();
            if (value.getType() == VarType.INT) {
                function_text += "%" + function_reg + " = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @strpi, i32 0, i32 0), " + valueType + " " + valueName + ")\n";
            } else if (value.getType() == VarType.REAL) {
                function_text += "%" + function_reg + " = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @strpd, i32 0, i32 0), " + valueType + " " + valueName + ")\n";
            }
            function_reg++;
            return;
        }
        if (value.getType() == VarType.INT) {
            main_text += "%" + reg + " = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @strpi, i32 0, i32 0), i32 " + value.getName() + ")\n";

        } else if (value.getType() == VarType.REAL) {
            main_text += "%" + reg + " = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @strpd, i32 0, i32 0), double " + value.getName() + ")\n";

        } else if (value.getName() != null) {
            main_text += "%" + reg + " = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @strps, i32 0, i32 0), i8* " + value.getName() + ")\n";

        } else {
            header_text += "@str" + reg + " = private constant [" + length + " x i8] c\"" + value.getName() + "\\0A\\00\"\n";
            main_text += "%" + reg + " = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([" + length + " x i8], [" + length + " x i8]* @str" + reg + ", i32 0, i32 0))\n";
        }
        reg++;

    }

    public static void declare(String id, Variable variable, Function function, Block block) {
        String type = TypeMapper.mapToLLVM(variable.getValue().getType());
        if(block != null && !variable.isGlobal()){
            id = block.getName() + "." + id;
        }
        if (variable.isGlobal()) {
            if (type.equals("i32")) {
                header_text += "@" + id + " = global i32 0\n";
                return;
            }
            else if(type.equals("double")){
                header_text += "@" + id + " = global double 0.0\n";
                return;
            }
            int length =  variable.getValue().getName().length() + 1;
            header_text += "@" + id + " = private constant [" + length + " x i8] c\"" + variable.getValue().getName() + "\\00\"\n";
            return;
        }
        if (function != null) {
            if (variable.isParam()) {
                function_header += "%" + function_reg + " = alloca " + type + "\n";
                function_reg++;
                return;
            }
            function_text += "%" + function.getName() + function.getIndex() + "." + id + " = alloca " + type + "\n";
            return;
        }
        main_text += "%" + id + " = alloca " + type + "\n";

    }


    public static void increment(String id, Variable variable, Function currentFunction, boolean insideFunction, Block block){
        load(id, variable, currentFunction, insideFunction, block);

    }


    public static void assign(String id,
                              Variable variable,
                              Function function,
                              boolean isFunctionCalled,
                              Block block
    ) {
        if(block != null && !variable.isGlobal()){
            id = block.getName() + "." + id;
        }
        String pointer;
        if (variable.isGlobal()) {
            pointer = "@";
        } else {
            pointer = "%";
        }
        if (function != null) {
            if (variable.getValue().getType() == VarType.INT) {
                if (variable.isParam()) {
                    function_text += "store i32 " + variable.getValue().getName() + ", i32* " + " %" + variable.paramIndex + "\n";
                    return;
                }
                if(variable.isGlobal()){
                    function_text += "store i32 " + variable.getValue().getName() + ", i32* " + pointer + id + "\n";
                    return;
                }
                function_text += "store i32 " + variable.getValue().getName() + ", i32* " + pointer + function.getName() + function.getIndex() + "." + id + "\n";
                return;
            }
            if(variable.isGlobal()){
                function_text += "store double " + variable.getValue().getName() + ", double* " + pointer + id + "\n";
                return;
            }
            if (variable.isParam()) {
                function_text += "store double " + variable.getValue().getName() + ", double* " + " %" + variable.paramIndex + "\n";
                return;
            }
            function_text += "store double " + variable.getValue().getName() + ", double* " + pointer + function.getName() + function.getIndex() + "." + id + "\n";
            return;
        }
        if (isFunctionCalled) {
            if (variable.getValue().getType() == VarType.INT) {
                main_text += "store i32 %" + (reg - 1) + ", i32* " + pointer + id + "\n";
            } else if (variable.getType() == VarType.REAL) {
                main_text += "store double %" + (reg - 1) + ", double* " + pointer + id + "\n";
            }
            return;
        }

        if (variable.getValue().getType() == VarType.INT) {
            main_text += "store i32 " + variable.getValue().getName() + ", i32* " + pointer + id + "\n";
        } else if (variable.getValue().getType() == VarType.REAL) {
            main_text += "store double " + variable.getValue().getName()  + ", double* " + pointer + id + "\n";
        }
    }

    private static String checkIfGlobal(boolean isGlobal) {
        if (isGlobal) {
            return "@";
        }
        return "%";
    }


    public static String load(String id, Variable variable, Function currentFunction, boolean insideFunction, Block block) {
        if(block != null && !variable.isGlobal()){
            id = block.getName() + "." + id;
        }
        String pointer = checkIfGlobal(variable.isGlobal());
        if (id.startsWith("%")) {
            id = id.substring(1);
        }
        if (currentFunction != null) {
            if(insideFunction){
                currentFunction.operationCounter++;
            }
            else {
                if (variable.isParam()) {
                    if (variable.getValue().getType() == VarType.INT) {
                        function_text += "%" + function_reg + " = load i32, i32* " + pointer + variable.paramIndex + "\n";
                    } else {
                        function_text += "%" + function_reg + " = load double, double* " + pointer + variable.paramIndex + "\n";
                    }
                } else {
                    if (variable.getValue().getType() == VarType.INT) {
                        if(variable.isGlobal()){
                            function_text += "%" + function_reg + " = load i32, i32* " + pointer + id + "\n";
                        }
                        else{
                            function_text += "%" + function_reg + " = load i32, i32* " + pointer + currentFunction.getName() + currentFunction.getIndex() + "." + id + "\n";
                        }
                    } else {
                        if(variable.isGlobal()){
                            function_text += "%" + function_reg + " = load double, double* " + pointer + id + "\n";
                        }

                        else{
                            function_text += "%" + function_reg + " = load double, double* " + pointer + currentFunction.getName() + currentFunction.getIndex() + "." + id + "\n";
                        }

                    }
                }
            }
            String lineNo = "%" + function_reg;
            function_reg++;
            return lineNo;
        }

        if (variable.getValue().getType() == VarType.INT) {
            main_text += "%" + reg + " = load i32, i32* " + pointer + id + "\n";
        }
        if (variable.getValue().getType() == VarType.REAL) {
            main_text += "%" + reg + " = load double, double* " + pointer + id + "\n";
        }
        if(variable.getValue().getType() == VarType.STRING) {
            int length =  variable.getName().length() + 1;
            main_text += "%" + reg+ " = getelementptr inbounds [" + length + " x i8], [" + length + " x i8]* " + pointer + id + ", i32 0, i32 0\n";
        }
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

   public static String multiplyTwoIntegers(String val1, String val2, Function currentFunction, boolean insideFunction) {
        if (currentFunction != null) {
            if(insideFunction){
                currentFunction.operationCounter++;
            }
            else {
                function_text += "%" + function_reg + " = mul i32 " + val1 + "," + val2 + "\n";
            }
            String lineNo = "%" + function_reg;
            function_reg++;
            return lineNo;
        }
        main_text += "%" + reg + " = mul i32 " + val1 + "," + val2 + "\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }


   public  static String multiplyTwoDoubles(String val1, String val2, Function currentFunction, boolean insideFunction) {
        if (currentFunction != null) {
            if(insideFunction){
                currentFunction.operationCounter++;
            }
            else {
                function_text += "%" + function_reg + " = fmul double " + val1 + "," + val2 + "\n";
            }
            String lineNo = "%" + function_reg;
            function_reg++;
            return lineNo;
        }
        main_text += "%" + reg + " = fmul double " + val1 + "," + val2 + "\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    public static String addIntAndReal(String val, Function currentFunction, boolean insideFunction) {
        String lineNo;
        if (currentFunction != null) {
            if(insideFunction){
                currentFunction.operationCounter++;
            }
            else {
                function_text += "%" + function_reg + " = fadd double " + val + ",%" + (function_reg - 1) + "\n";
            }
            lineNo = "%" + function_reg;
            function_reg++;
            return lineNo;
        }
        main_text += "%" + reg + " = fadd double " + val + ",%" + (reg - 1) + "\n";
        lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    public static String addTwoIntegers(String val1, String val2, Function currentFunction, boolean insideFunction) {
        String lineNo;
        if (currentFunction != null) {
            if(insideFunction){
                currentFunction.operationCounter++;
            }
            else{
                function_text += "%" + function_reg + " = add i32 " + val1 + "," + val2 + "\n";
            }
            lineNo = "%" + function_reg;
            function_reg++;
            return lineNo;
        }
        main_text += "%" + reg + " = add i32 " + val1 + "," + val2 + "\n";
        lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    public static String addTwoDoubles(String val1, String val2, Function currentFunction, boolean insideFunction) {
        if (currentFunction != null) {
            if(insideFunction){
                currentFunction.operationCounter++;
            }
            else{
                function_text += "%" + function_reg + " = fadd double " + val1 + "," + val2 + "\n";
            }
            String lineNo = "%" + function_reg;
            function_reg++;
            return lineNo;
        }
        main_text += "%" + reg + " = fadd double " + val1 + "," + val2 + "\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    public static String sitofp(String value, Function currentFunction, boolean insideFunction) {
        if (currentFunction != null) {
            if(insideFunction){
                currentFunction.operationCounter++;
            }
            else {
                function_text += "%" + function_reg + " = sitofp i32 " + value + " to double\n";
            }
            String lineNo = "%" + function_reg;
            function_reg++;
            return lineNo;
        }
        main_text += "%" + reg + " = sitofp i32 " + value + " to double\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    public static String fptosi(String value, Function currentFunction, boolean insideFunction) {
        if (currentFunction != null) {
            if(insideFunction){
                currentFunction.operationCounter++;
            }
            else {
                function_text += "%" + function_reg + " = fptosi double " + value + " to i32\n";
            }
            String lineNo = "%" + function_reg;
            function_reg++;
            return lineNo;
        }
        main_text += "%" + reg + " = fptosi double " + value + " to i32\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    public static String subIntFromReal(String val, Function currentFunction, boolean insideFunction) {
        if (currentFunction != null) {
            if(insideFunction) {
                currentFunction.operationCounter++;
            }
            else {
                function_text += "%" + function_reg + " = fsub double " + val + ",%" + (function_reg - 1) + "\n";
            }
            String lineNo = "%" + function_reg;
            function_reg++;
            return lineNo;
        }
        main_text += "%" + reg + " = fsub double " + val + ",%" + (reg - 1) + "\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    public static String subIntFromReal1(String val, Function currentFunction,  boolean insideFunction) {
        if (currentFunction != null) {
            if(insideFunction){
                currentFunction.operationCounter++;
            }
            else {
                function_text += "%" + function_reg + " = fsub double %" + (function_reg - 1) + "," + val + "\n";
            }
            String lineNo = "%" + function_reg;
            function_reg++;
            return lineNo;
        }
        main_text += "%" + reg + " = fsub double %" + (reg - 1) + "," + val + "\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    public static String subIntFromInt(String val1, String val2, Function currentFunction, boolean insideFunction) {
        if (currentFunction != null) {
            if(insideFunction){
                currentFunction.operationCounter++;
            }
            else {
                function_text += "%" + function_reg + " = sub i32 " + val1 + "," + val2 + "\n";
            }
            String lineNo = "%" + function_reg;
            function_reg++;
            return lineNo;
        }
        main_text += "%" + reg + " = sub i32 " + val1 + "," + val2 + "\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    public static String subDoubleFromDouble(String val1, String val2, Function currentFunction, boolean insideFunction) {
        if (currentFunction != null) {
            if(insideFunction){
                currentFunction.operationCounter++;
            }
            else {
                function_text += "%" + function_reg + " = fsub double " + val1 + "," + val2 + "\n";
            }
            String lineNo = "%" + function_reg;
            function_reg++;
            return lineNo;
        }
        main_text += "%" + reg + " = fsub double " + val1 + "," + val2 + "\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    public static String multiplyIntAndReal(String val, Function currentFunction, boolean insideFunction) {
        if (currentFunction != null) {
            if(insideFunction){
                currentFunction.operationCounter++;
            }
            else {
                function_text += "%" + function_reg + " = fmul double " + val + ",%" + (function_reg - 1) + "\n";
            }
            String lineNo = "%" + function_reg;
            function_reg++;
            return lineNo;
        }
        main_text += "%" + reg + " = fmul double " + val + ",%" + (reg - 1) + "\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    public static String divideIntAndReal(String val, Function currentFunction, boolean insideFunction) {
        if (currentFunction != null) {
            if(insideFunction){
                currentFunction.operationCounter++;
            }
            else {
                function_text += "%" + function_reg + " = fdiv double " + val + "," + "%" + (function_reg - 1) + "\n";
            }
            String lineNo = "%" + function_reg;
            function_reg++;
            return lineNo;
        }
        main_text += "%" + reg + " = fdiv double " + val + "," + "%" + (reg - 1) + "\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    public static String divideTwoDoubles(String val2, String val1, Function currentFunction, boolean insideFunction) {
        if (currentFunction != null) {
            if(insideFunction){
                currentFunction.operationCounter++;
            }
            else {
                function_text += "%" + function_reg + " = fdiv double " + val2 + "," + val1 + "\n";
            }
            String lineNo = "%" + function_reg;
            function_reg++;
            return lineNo;
        }
        main_text += "%" + reg + " = fdiv double " + val2 + "," + val1 + "\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    public static String divideTwoIntegers(Function currentFunction, boolean insideFunction) {
        if (currentFunction != null) {
            if (insideFunction) {
                currentFunction.operationCounter++;
            } else {
                function_text += "%" + reg + " = fdiv double %" + (reg - 2) + ",%" + (reg - 1) + "\n";
            }
            String lineNo = "%" + function_reg;
            function_reg++;
            return lineNo;
        }
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
        String type = TypeMapper.mapToLLVM(value.getType());
        function_text += "ret " + type + " " + value.getName() + "\n";
    }

    public static String callFunction(Function function) {
        String type = TypeMapper.mapToLLVM(function.getType());
        main_text += "%" + reg + " = call " + type + " @" + function.getName() + function.getIndex() + "(";
        for (int i = 0; i < function.params.size(); i++) {
            String paramType = TypeMapper.mapToLLVM(function.params.get(i).getValue().getType());
            if (function.params.size() == 1 || i == function.params.size() - 1) {
                main_text += paramType + " " + function.params.get(i).getName();
            } else {
                main_text += paramType + " " + function.params.get(i).getName() + ", ";
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
                    ReturnOperation returnOperation = (ReturnOperation) operation;
                    currentFunction.setType(returnOperation.value.getType());
                }, () -> {
                    currentFunction.setType(VarType.INT);
                    isReturnPresent.set(false);
                });
        String functionType = TypeMapper.mapToLLVM(currentFunction.getType());
        function_header += "define dso_local " + functionType + " @" + currentFunction.getName() + currentFunction.getIndex() + "(";
        for (int i = 0; i < currentFunction.params.size(); i++) {
            String paramType = TypeMapper.mapToLLVM(currentFunction.params.get(i).getValue().getType());
            if (currentFunction.params.size() == 1 || i == currentFunction.params.size() - 1) {
                function_header += paramType;
            } else {
                function_header += paramType + ", ";
            }
        }
        function_header += ") nounwind {\n";
        for (int i = 0; i < currentFunction.params.size(); i++) {
            int index = currentFunction.params.size() + i + 1;
            String paramType = TypeMapper.mapToLLVM(currentFunction.params.get(i).getValue().getType());
            function_header += "%" + index + " = alloca " + paramType + "\n";
            function_header += "store " + paramType + " %" + i + ", " + paramType + "* " + "%" + index + "\n";

        }
        if (!isReturnPresent.get()) {
            function_text += "ret i32 0\n";
        }

    }

    public static void closeFunction() {
        function_text += "}\n";
        functionZone += function_header;
        functionZone += function_text;
        function_text = "";
        function_header = "";
    }






    public static void enterIf(Function currentFunction) {
        ifCounter ++;
        if(currentFunction != null){
            function_text += "br i1 %" + (function_reg - 1) + ", label %true" + ifCounter + ", label %false" + ifCounter + "\ntrue" + ifCounter + ":\n";
            return;
        }
        main_text += "br i1 %" + (reg - 1) + ", label %true" + ifCounter + ", label %false" + ifCounter + "\ntrue" + ifCounter + ":\n";
    }


    public static void closeIf(Function currentFunction) {
        if(currentFunction != null){
            function_text += "br label %endif" + ifCounter + "\n";
            function_text += "endif" + ifCounter + ":\n";

            return;
        }
        main_text += "br label %endif" + ifCounter + "\n";
        main_text += "endif" + ifCounter + ":\n";
        ifCounter--;
    }

    public static void generateWhile(Function currentFunction) {
        if(currentFunction != null){
            function_text += "br label %whilestart" + whileCounter + "\nwhilestart" + whileCounter + ":\n";
            return;
        }
        main_text += "br label %whilestart" + whileCounter + "\nwhilestart" + whileCounter + ":\n";
    }

    public static void closeWhile(Function currentFunction) {

        if(currentFunction != null){
            function_text += "br label %whilestart" + whileCounter + "\n";
            function_text += "br label %whilefalse" + whileCounter + "\n";
            function_text += "whilefalse" + whileCounter + ":\n";
            function_reg++;
            whileCounter++;
            return;
        }
        main_text += "br label %whilestart" + whileCounter + "\n";
        main_text += "br label %whilefalse" + whileCounter + "\n";
        main_text += "whilefalse" + whileCounter + ":\n";
        reg++;
        whileCounter++;
    }

    public static void startWhile(Function currentFunction) {
        if(currentFunction != null){
            function_text += "br i1 %" + (function_reg - 1) + ", label %whiletrue" + whileCounter + ", label %whilefalse" + whileCounter + "\n";
            function_text += "whiletrue" + whileCounter + ":\n";
            return;
        }
        main_text += "br i1 %" + (reg - 1) + ", label %whiletrue" + whileCounter + ", label %whilefalse" + whileCounter + "\n";
        main_text += "whiletrue" + whileCounter + ":\n";
    }



    public static void compareIntAndDouble(String value1, String value2, String operator, Function currentFunction) {
        String llvmOperator = CompareMapper.mapToLLVM(operator, VarType.REAL);
        if(currentFunction != null){
            function_text += "%" + function_reg + " = fcmp " + llvmOperator + " double " +   value2 + ", "  + value1 +  "\n";
            function_reg++;
            return;
        }
        main_text += "%" + reg + " = fcmp " + llvmOperator + " double "   + value2 + ", " + value1 + "\n";
        reg++;
    }

    public static void compareTwoDoubles(Value value1, Value value2, String operator, Function currentFunction) {
        String llvmOperator = CompareMapper.mapToLLVM(operator, VarType.REAL);
        if(currentFunction != null){
            function_text += "%" + function_reg + " = fcmp " + llvmOperator + " double " +   value2.getName() + ", "  + value1.getName() +  "\n";
            function_reg++;
            return;
        }
        main_text += "%" + reg + " = fcmp " + llvmOperator + " double "   + value2.getName() + ", " + value1.getName() + "\n";
        reg++;
    }

    public static void compareTwoIntegers(Value value1, Value value2, String operator, Function currentFunction) {
        String llvmOperator = CompareMapper.mapToLLVM(operator, VarType.INT);
        if(currentFunction != null){
            function_text += "%" + function_reg + " = icmp " + llvmOperator + " i32 " +   value2.getName() + ", "  + value1.getName() +  "\n";
            function_reg++;
            return;
        }
        main_text += "%" + reg + " = icmp " + llvmOperator + " i32 "   + value2.getName() + ", " + value1.getName() + "\n";
        reg++;
    }

//    public static void enterElse(Function currentFunction) {
//        if(currentFunction != null){
//            function_text += "false" + ifCounter + ":\n";
//            return;
//        }
//        main_text += "false" + ifCounter + ":\n";
//    }

    public static void jumpToEndif(Function currentFunction) {
        if(currentFunction != null){
            function_text += "br label %endif" + ifCounter + "\n";
            return;
        }
        main_text += "br label %endif" + ifCounter + "\n";
    }

    public static void exitIf(Function currentFunction){
        if(currentFunction != null){
            function_text += "br label %endif" + ifCounter + "\n";
            function_text += "false" + ifCounter + ":\n";
            return;
        }
        main_text += "br label %endif" + ifCounter + "\n";
        main_text += "false" + ifCounter + ":\n";
    }

}