package containers;

import types.VarType;

public class Value extends Container {

    public String variable;
    public boolean isGlobal;
    public boolean isParam;
    public Integer paramIndex;

    public Value(String name, VarType type, String variable) {
        this.name = name;
        this.type = type;
        this.variable = variable;
    }

    public Value(String name, VarType type) {
        this.name = name;
        this.type = type;
    }

    public Value(String name, VarType type, boolean isGlobal, boolean isParam) {
        this.name = name;
        this.type = type;
        this.isGlobal = isGlobal;
        this.isParam = isParam;
    }

}
