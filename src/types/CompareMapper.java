package types;

public class CompareMapper {

    public static String mapToLLVM(String operator, VarType varType) {
        switch(varType){
            case REAL:
                switch (operator) {
                    case ">":
                        return "ogt";
                    case "<":
                        return "olt";

                    case "==":
                        return "oeq";

                    case "<=":
                        return "ole";

                    case ">=":
                        return "oge";

                    default:
                        throw new RuntimeException("Invalid compare operator");
                }
            case INT:
                switch (operator) {
                    case ">":
                        return "sgt";
                    case "<":
                        return "slt";

                    case "==":
                        return "eq";

                    case "<=":
                        return "sle";

                    case ">=":
                        return "sge";

                    default:
                        throw new RuntimeException("Invalid compare operator");
                }
            default:
                throw new RuntimeException("Invalid compare operator");
        }
    }
}
