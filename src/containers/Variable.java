package containers;

public class Variable extends Container {

    public boolean isParam() {
        return isParam;
    }

    public Integer getParamIndex() {
        return paramIndex;
    }

    private boolean isGlobal;
    private boolean isParam;

    public void setParamIndex(Integer paramIndex) {
        this.paramIndex = paramIndex;
    }

    public Integer paramIndex;

    public void setParam(boolean param) {
        isParam = param;
    }

    private String name;
    private Value value;


    public Variable(Value value){
        this.value = value;
    }

    public boolean isGlobal() {
        return isGlobal;
    }

    public void setGlobal(boolean global) {
        isGlobal = global;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }
}
