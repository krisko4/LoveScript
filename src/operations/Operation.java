package operations;

import containers.Function;
import types.OperationType;

public abstract class Operation {

    public Function currentFunction;
    public OperationType operationType;
    public void operate(boolean insideFunction){}


}
