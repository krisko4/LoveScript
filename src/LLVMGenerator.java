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

    public static void printf1(String value, VarType varType) {
        if (varType == VarType.INT) {
            main_text += "%" + reg + " = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @strpi, i32 0, i32 0), i32 " + (value) + ")\n";
            reg++;
        } else if (varType == VarType.REAL) {
            main_text += "%" + reg + " = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @strpd, i32 0, i32 0), double " + (value) + ")\n";
            reg++;
        } else {
            String text = value.replace("\"", "");
            header_text += "@.str" + reg + " = private constant [" + text.length() + " x i8] c\"" + text + "\"\n";
            main_text += "%sup" + reg + " = getelementptr inbounds [" + text.length() + " x i8], [" + text.length() + " x i8]* @.str" + reg + ", i32 0, i32 0\n";
            main_text += "%" + reg + " = call i32 (i8*, ...) @printf(i8* %sup" + reg + ")\n";
            reg++;
        }

    }

    static void declare(String id, VarType varType) {
        if (varType == VarType.INT) {
            main_text += "%" + id + " = alloca i32\n";
        } else {
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
    }

    public static String load(String id, VarType varType) {
        if (varType == VarType.INT) {
            main_text += "%" + reg + " = load i32, i32* %" + id + "\n";
        } else {
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
     /*   static void load(String id){
        main_text += "%"+reg+" = load i32, i32* %"+id+"\n";
        reg++;
    }*/

    static void printf(VarType varType) {
        if (varType == VarType.INT) {
            main_text += "%" + reg + " = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @strpi, i32 0, i32 0), i32 %" + (reg - 1) + ")\n";
        } else {
            main_text += "%" + reg + " = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @strpd, i32 0, i32 0), double %" + (reg - 1) + ")\n";
        }
        reg++;
    }

    public static String addMultiple(String val, VarType varType) {
        if (varType == VarType.INT) {
            main_text += "%" + reg + " = fadd double %" + (reg - 2) + ",%" + (reg - 1) + "\n";
        } else {
            main_text += "%" + reg + " = fadd double %" + (reg - 1) + "," + val + "\n";
        }
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    public static void sub(String val1, String val2) {
        main_text += "%" + reg + " = sub i32 " + val1 + "," + val2 + "\n";
        reg++;
        // main_text += "%"+reg+" = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @strp, i32 0, i32 0), i32 %"+(reg - 1)+")\n";
        //  reg++;
    }

    public static String subDoublesFromDouble() {
        main_text += "%" + reg + " = fsub double %" + (reg - 1) + ",%" + (reg - 2) + "\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    public static String subDoublesFromDouble(String val) {
        main_text += "%" + reg + " = fsub double " + val + ",%" + (reg - 1) + "\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }


    public static void add1(VarType varType) {
        if (varType == VarType.INT) {
            main_text += "%" + reg + " = add i32 %" + (reg - 2) + ",%" + (reg - 1) + "\n";
        } else {
            main_text += "%" + reg + " = fadd double %" + (reg - 2) + ",%" + (reg - 1) + "\n";
        }

        reg++;
    }

    public static void add2(String value1, VarType varType) {
        if (varType == VarType.INT) {
            main_text += "%" + reg + " = add i32 %" + (reg - 1) + "," + value1 + "\n";
        } else {
            main_text += "%" + reg + " = fadd double %" + (reg - 1) + "," + value1 + "\n";
        }

        reg++;
    }

    static void fptosi(String value) {
        main_text += "%" + reg + " = fptosi double " + value + " to i32\n";
        reg++;
    }

    public static String addMultiple1(String val) {
        main_text += "%" + reg + " = add i32 " + val + ",%" + (reg - 1) + "\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;

    }

    public static void subMultiple1() {
        main_text += "%" + reg + " = sub i32 %" + (reg - 1) + ",%" + (reg - 2) + "\n";
        reg++;
    }

    public static void sub1() {
        main_text += "%" + reg + " = sub i32 %" + (reg - 1) + ",%" + (reg - 2) + "\n";
        reg++;
    }

    public static void sub2(String value) {
        main_text += "%" + reg + " = sub i32 " + value + ",%" + (reg - 1) + "\n";
        reg++;
    }

    public static void sub3(String value) {
        main_text += "%" + reg + " = sub i32 %" + (reg - 1) + "," + value + "\n";
        reg++;
    }

    public static String subIntsFromInt(String val) {
        main_text += "%" + reg + " = sub i32 " + val + ",%" + (reg - 1) + "\n";
        String lineNo = "%" + reg;
        reg++;
        return lineNo;
    }

    public static void subIntsFromDouble() {
        main_text += "%" + reg + " = fsub double %" + (reg - 1) + ",%" + (reg - 2) + "\n";
        reg++;
    }

    public static void assignInt(String id, String name) {
        main_text += "store i32 " + name + ", i32* %" + id + "\n";
    }

    public static void assignReal(String id, String name) {
        main_text += "store double " + name + ", double* %" + id + "\n";
    }

    public static void loadInteger(String id) {
        main_text += "%" + reg + " = load i32, i32* %" + id + "\n";
        reg++;
    }

    public static void loadReal(String id) {
        main_text += "%" + reg + " = load double, double* %" + id + "\n";
        reg++;
    }

    static void assign(String id, String value) {
        main_text += "store i32 " + value + ", i32* %" + id + "\n";
    }

    static void assign1(String id, VarType varType) {
        if (varType == VarType.INT) {
            main_text += "store i32 %" + (reg - 1) + ", i32* %" + id + "\n";
        } else {
            main_text += "store double %" + (reg - 1) + ", double* %" + id + "\n";
        }
    }
}