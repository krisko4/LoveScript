package types;

public class CompareMapper {

    public static String mapToLLVM(String operator, VarType varType) {
        switch(varType){
            case REAL:
                switch (operator) {
                    case ":>":
                        return "ogt";
                    case ":<":
                        return "olt";

                    case "<3<3":
                        return "oeq";

                    case ":<3":
                        return "ole";

                    case ":><3":
                        return "oge";

                    default:
                        throw new RuntimeException("Invalid compare operator");
                }
            case INT:
                switch (operator) {
                    case ":>":
                        return "sgt";
                    case ":<":
                        return "slt";

                    case "<3<3":
                        return "eq";

                    case ":<3":
                        return "sle";

                    case ":><3":
                        return "sge";

                    default:
                        throw new RuntimeException("Invalid compare operator");
                }
            default:
                throw new RuntimeException("Invalid compare operator");
        }
    }
}
