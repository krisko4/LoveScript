package operations;

import antlr.HelloParser;
import containers.Container;
import containers.Function;
import containers.Value;
import containers.Variable;
import types.VarType;

import java.util.Stack;

public class ExitRealOperation extends Operation {

    private HelloParser.RealContext ctx;
    private Stack<Container> stack;
    private int argNo;
    private boolean insideFunctionCall;

    public ExitRealOperation(Function currentFunction,
                            Stack<Container> stack,
                            HelloParser.RealContext ctx,
                            int argNo,
                            boolean insideFunctionCall
    ){
        this.currentFunction = currentFunction;
        this.ctx = ctx;
        this.stack = stack;
        this.argNo = argNo;
        this.insideFunctionCall = insideFunctionCall;
    }

    public void operate(boolean insideFunction){
        if(insideFunction){
            currentFunction.operations.add(this);
        }

        if (insideFunctionCall) {
            Variable param = currentFunction.params.get(argNo);
            param.getValue().setType(VarType.REAL);
            param.setParam(true);
            param.setName(ctx.REAL().getText());
            currentFunction.getStack().push(param.getValue());
            currentFunction.getMemory().replace(param.getName(), param);
            argNo++;
            return;
        }
        stack.push(new Value(ctx.REAL().getText(), VarType.REAL));

    }

}
