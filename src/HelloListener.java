// Generated from C:/Users/optiplex/IdeaProjects/MyLanguage1/src\Hello.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HelloParser}.
 */
public interface HelloListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link HelloParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(HelloParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(HelloParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(HelloParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(HelloParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#print_stmt}.
	 * @param ctx the parse tree
	 */
	void enterPrint_stmt(HelloParser.Print_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#print_stmt}.
	 * @param ctx the parse tree
	 */
	void exitPrint_stmt(HelloParser.Print_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#read_stmt}.
	 * @param ctx the parse tree
	 */
	void enterRead_stmt(HelloParser.Read_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#read_stmt}.
	 * @param ctx the parse tree
	 */
	void exitRead_stmt(HelloParser.Read_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#assign_stmt}.
	 * @param ctx the parse tree
	 */
	void enterAssign_stmt(HelloParser.Assign_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#assign_stmt}.
	 * @param ctx the parse tree
	 */
	void exitAssign_stmt(HelloParser.Assign_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#function_stmt}.
	 * @param ctx the parse tree
	 */
	void enterFunction_stmt(HelloParser.Function_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#function_stmt}.
	 * @param ctx the parse tree
	 */
	void exitFunction_stmt(HelloParser.Function_stmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code add}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAdd(HelloParser.AddContext ctx);
	/**
	 * Exit a parse tree produced by the {@code add}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAdd(HelloParser.AddContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sub}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSub(HelloParser.SubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sub}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSub(HelloParser.SubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mul}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMul(HelloParser.MulContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mul}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMul(HelloParser.MulContext ctx);
	/**
	 * Enter a parse tree produced by the {@code divide}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterDivide(HelloParser.DivideContext ctx);
	/**
	 * Exit a parse tree produced by the {@code divide}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitDivide(HelloParser.DivideContext ctx);
	/**
	 * Enter a parse tree produced by the {@code value}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterValue(HelloParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code value}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitValue(HelloParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code id}
	 * labeled alternative in {@link HelloParser#val}.
	 * @param ctx the parse tree
	 */
	void enterId(HelloParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code id}
	 * labeled alternative in {@link HelloParser#val}.
	 * @param ctx the parse tree
	 */
	void exitId(HelloParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code int}
	 * labeled alternative in {@link HelloParser#val}.
	 * @param ctx the parse tree
	 */
	void enterInt(HelloParser.IntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code int}
	 * labeled alternative in {@link HelloParser#val}.
	 * @param ctx the parse tree
	 */
	void exitInt(HelloParser.IntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code real}
	 * labeled alternative in {@link HelloParser#val}.
	 * @param ctx the parse tree
	 */
	void enterReal(HelloParser.RealContext ctx);
	/**
	 * Exit a parse tree produced by the {@code real}
	 * labeled alternative in {@link HelloParser#val}.
	 * @param ctx the parse tree
	 */
	void exitReal(HelloParser.RealContext ctx);
	/**
	 * Enter a parse tree produced by the {@code array}
	 * labeled alternative in {@link HelloParser#val}.
	 * @param ctx the parse tree
	 */
	void enterArray(HelloParser.ArrayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code array}
	 * labeled alternative in {@link HelloParser#val}.
	 * @param ctx the parse tree
	 */
	void exitArray(HelloParser.ArrayContext ctx);
	/**
	 * Enter a parse tree produced by the {@code string}
	 * labeled alternative in {@link HelloParser#val}.
	 * @param ctx the parse tree
	 */
	void enterString(HelloParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code string}
	 * labeled alternative in {@link HelloParser#val}.
	 * @param ctx the parse tree
	 */
	void exitString(HelloParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code toint}
	 * labeled alternative in {@link HelloParser#val}.
	 * @param ctx the parse tree
	 */
	void enterToint(HelloParser.TointContext ctx);
	/**
	 * Exit a parse tree produced by the {@code toint}
	 * labeled alternative in {@link HelloParser#val}.
	 * @param ctx the parse tree
	 */
	void exitToint(HelloParser.TointContext ctx);
	/**
	 * Enter a parse tree produced by the {@code toreal}
	 * labeled alternative in {@link HelloParser#val}.
	 * @param ctx the parse tree
	 */
	void enterToreal(HelloParser.TorealContext ctx);
	/**
	 * Exit a parse tree produced by the {@code toreal}
	 * labeled alternative in {@link HelloParser#val}.
	 * @param ctx the parse tree
	 */
	void exitToreal(HelloParser.TorealContext ctx);
	/**
	 * Enter a parse tree produced by the {@code priorityExpression}
	 * labeled alternative in {@link HelloParser#val}.
	 * @param ctx the parse tree
	 */
	void enterPriorityExpression(HelloParser.PriorityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code priorityExpression}
	 * labeled alternative in {@link HelloParser#val}.
	 * @param ctx the parse tree
	 */
	void exitPriorityExpression(HelloParser.PriorityExpressionContext ctx);
}