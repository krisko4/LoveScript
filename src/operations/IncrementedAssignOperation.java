package operations;

import antlr.HelloParser;
import blocks.Block;
import containers.Container;
import containers.Function;
import containers.Value;
import containers.Variable;
import main.LLVMGenerator;
import operations.calc_operations.AddOperation;

import java.util.Stack;

public class IncrementedAssignOperation extends Operation {

    private Variable variable;
    private Stack<Container> stack;
    private Block block;
    private HelloParser.Increment_stmtContext ctx;

    public IncrementedAssignOperation(Variable variable,
                                      Stack<Container> stack,
                                      Function currentFunction,
                                      Block block,
                                      HelloParser.Increment_stmtContext ctx){
        this.variable = variable;
        this.stack = stack;
        this.currentFunction = currentFunction;
        this.block = block;
        this.ctx = ctx;
    }

    public void operate(boolean insideFunction){
        if(insideFunction){
            return;
        }
        variable.getValue().setName(LLVMGenerator.load(ctx.ID().getText(), variable, currentFunction, false, block));
        stack.push(variable.getValue());
        new AddOperation(stack, currentFunction).operate(false);

        Value value = (Value) stack.pop();
        variable.setValue(value);
       LLVMGenerator.assign(variable.getName(), variable, currentFunction, false, block);
    }


}
