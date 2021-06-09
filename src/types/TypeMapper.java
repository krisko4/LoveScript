package types;

public class TypeMapper {
    public static VarType mapType(String type) {
        switch (type) {
            case "INT":
                return VarType.INT;
            case "REAL":
                return VarType.REAL;
            case "STRING":
                return VarType.STRING;
            default:
                throw new IllegalArgumentException(type);
        }

    }

    public static String mapToLLVM(VarType varType) {
        switch (varType) {
            case INT:
                return "i32";
            case REAL:
                return "double";
            case STRING:
                return "string";
            default:
                throw new IllegalArgumentException(String.valueOf(varType));
        }
    }
}
