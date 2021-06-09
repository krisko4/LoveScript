package containers;

import types.VarType;

import java.util.HashMap;

public class Array extends Container {

    public String size;
    public HashMap<String, Value> values;
    public boolean isDeclared;
    public VarType valuesType;

    public Array(String name, String size) {
        this.name = name;
        this.values = new HashMap<>();
        this.size = size;
    }
}
