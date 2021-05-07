import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LLVMGenerator {

    static String header_text = "";
    static String main_text = "";
    static int reg = 1;


    static String generate() {
        String text = "";
        text += "declare i32 @printf(i8*, ...)\n";
        text += "declare i32 @scanf(i8*, ...)\n";
        text += "@strpi = constant [4 x i8] c\"%d\\0A\\00\"\n";
        text += "@strpd = constant [4 x i8] c\"%f\\0A\\00\"\n";
        text += "@strs = constant [3 x i8] c\"%d\\00\"\n";
        text += "@strdd = constant [4 x i8] c\"%lf\\00\"\n";
        text += header_text;
        text += "define i32 @main() nounwind{\n";
        text += main_text;
        text += "ret i32 0 }\n";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("test.ll"));
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

    public static void printf1(Value value) {
        int length = value.name.length() + 2;
        if (value.type == VarType.INT) {
            main_text += "%" + reg + " = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @strpi, i32 0, i32 0), i32 " + (value.name) + ")\n";

        } else if (value.type == VarType.REAL) {
            main_text += "%" + reg + " = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @strpd, i32 0, i32 0), double " + (value.name) + ")\n";

        } else if(value.variable != null) {
            main_text += "%" + reg + " = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([" + length + " x i8], [" + length + " x i8]* " + value.variable + ", i32 0, i32 0))\n";

        }
        else{
            header_text += "@str" + reg + " = private constant [" + length + " x i8] c\"" + value.name + "\\0A\\00\"\n";
            main_text += "%" + reg + " = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([" + length + " x i8], [" + length + " x i8]* @str" + reg + ", i32 0, i32 0))\n";
        }
        reg++;

    }

    static void declare(String id, VarType varType) {
        if (varType == VarType.INT) {
            main_text += "%" + id + " = alloca i32\n";
        } else if(varType == VarType.REAL) {
            main_text += "%" + id + " = alloca double\n";
        }

    }



    public static void assign(String id, String name, VarType varType) {
        if (varType == VarType.INT) {
            main_text += "store i32 " + name + ", i32* %" + id + "\n";
            return;
        }
        if (varType == VarType.REAL) {
            main_text += "store double " + name + ", double* %" + id + "\n";
        }
        if(varType == VarType.STRING){
            int length = name.length() + 2;
            header_text += "@" + id + " = private constant [" + length + " x i8] c\"" + name + "\\0A\\00\"\n";
        }
    }

    public static String load(String id, VarType varType) {
        if (varType == VarType.INT) {
            main_text += "%" + reg + " = load i32, i32* %" + id + "\n";
        } else if (varType == VarType.REAL) {
            main_text += "%" + reg + " = load double, double* %" + id + "\n";
        }
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    static String multiplyTwoIntegers(String val1, String val2) {
        main_text += "%" + reg + " = mul i32 " + val1 + "," + val2 + "\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    static String multiplyTwoDoubles(String val1, String val2) {
        main_text += "%" + reg + " = fmul double " + val1 + "," + val2 + "\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    public static String addIntAndReal(String val) {
        main_text += "%" + reg + " = fadd double " + val + ",%" + (reg - 1) + "\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    public static String addTwoIntegers(String val1, String val2) {
        main_text += "%" + reg + " = add i32 " + val1 + "," + val2 + "\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    public static String addTwoDoubles(String val1, String val2) {
        main_text += "%" + reg + " = fadd double " + val1 + "," + val2 + "\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    static String sitofp(String value) {
        main_text += "%" + reg + " = sitofp i32 " + value + " to double\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    static String fptosi(String value){
        main_text += "%"+reg+" = fptosi double "+value+" to i32\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    public static String subIntFromReal(String val) {
        main_text += "%" + reg + " = fsub double " + val + ",%" + (reg - 1) + "\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    public static String subIntFromReal1(String val) {
        main_text += "%" + reg + " = fsub double %" + (reg - 1) + "," + val + "\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    public static String subIntFromInt(String val1, String val2) {
        main_text += "%" + reg + " = sub i32 " + val1 + "," + val2 + "\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    public static String subDoubleFromDouble(String val1, String val2) {
        main_text += "%" + reg + " = fsub double " + val1 + "," + val2 + "\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    public static String multiplyIntAndReal(String val) {
        main_text += "%" + reg + " = fmul double " + val + ",%" + (reg - 1) + "\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    public static String divideIntAndReal(String val) {
        main_text += "%" + reg + " = fdiv double " + val + "," + "%" + (reg - 1) + "\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    public static String divideTwoDoubles(String val2, String val1) {
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
}