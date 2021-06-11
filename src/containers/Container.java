package containers;

import types.VarType;

public abstract class Container {

    protected String name;
    protected VarType type;
    protected boolean isAssigned;

    public void setName(String name) {
        this.name = name;
    }

    public void setType(VarType type) {
        this.type = type;
    }

    public void setAssigned(boolean assigned) {
        isAssigned = assigned;
    }

    public String getName() {
        return name;
    }

    public VarType getType() {
        return type;
    }

    public boolean isAssigned() {
        return isAssigned;
    }
}
