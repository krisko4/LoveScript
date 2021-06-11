package operations;

import antlr.HelloParser;
import containers.Container;
import containers.Function;
import containers.Value;
import containers.Variable;
import types.VarType;

import java.util.Stack;

public class ExitIntOperation extends Operation {

    private HelloParser.IntContext ctx;
    private Stack<Container> stack;
    private int argNo;
    private boolean insideFunctionCall;

    public ExitIntOperation(Function currentFunction,
                            Stack<Container> stack,
                            HelloParser.IntContext ctx,
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
           param.getValue().setType(VarType.INT);
           param.setParam(true);
           param.setName(ctx.INT().getText());
           currentFunction.getStack().push(param.getValue());
           currentFunction.getMemory().replace(param.getName(), param);
           argNo++;
           return;
       }
        stack.push(new Value(ctx.INT().getText(), VarType.INT));

    }

}
