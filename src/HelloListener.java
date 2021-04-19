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
	 * Enter a parse tree produced by {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(HelloParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(HelloParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#add_expression}.
	 * @param ctx the parse tree
	 */
	void enterAdd_expression(HelloParser.Add_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#add_expression}.
	 * @param ctx the parse tree
	 */
	void exitAdd_expression(HelloParser.Add_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#mult_divide_expression}.
	 * @param ctx the parse tree
	 */
	void enterMult_divide_expression(HelloParser.Mult_divide_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#mult_divide_expression}.
	 * @param ctx the parse tree
	 */
	void exitMult_divide_expression(HelloParser.Mult_divide_expressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code divide}
	 * labeled alternative in {@link HelloParser#divide_expression}.
	 * @param ctx the parse tree
	 */
	void enterDivide(HelloParser.DivideContext ctx);
	/**
	 * Exit a parse tree produced by the {@code divide}
	 * labeled alternative in {@link HelloParser#divide_expression}.
	 * @param ctx the parse tree
	 */
	void exitDivide(HelloParser.DivideContext ctx);
	/**
	 * Enter a parse tree produced by the {@code singleDivide}
	 * labeled alternative in {@link HelloParser#divide_expression}.
	 * @param ctx the parse tree
	 */
	void enterSingleDivide(HelloParser.SingleDivideContext ctx);
	/**
	 * Exit a parse tree produced by the {@code singleDivide}
	 * labeled alternative in {@link HelloParser#divide_expression}.
	 * @param ctx the parse tree
	 */
	void exitSingleDivide(HelloParser.SingleDivideContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mult}
	 * labeled alternative in {@link HelloParser#mult_expression}.
	 * @param ctx the parse tree
	 */
	void enterMult(HelloParser.MultContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mult}
	 * labeled alternative in {@link HelloParser#mult_expression}.
	 * @param ctx the parse tree
	 */
	void exitMult(HelloParser.MultContext ctx);
	/**
	 * Enter a parse tree produced by the {@code singleMult}
	 * labeled alternative in {@link HelloParser#mult_expression}.
	 * @param ctx the parse tree
	 */
	void enterSingleMult(HelloParser.SingleMultContext ctx);
	/**
	 * Exit a parse tree produced by the {@code singleMult}
	 * labeled alternative in {@link HelloParser#mult_expression}.
	 * @param ctx the parse tree
	 */
	void exitSingleMult(HelloParser.SingleMultContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#subtract_expression}.
	 * @param ctx the parse tree
	 */
	void enterSubtract_expression(HelloParser.Subtract_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#subtract_expression}.
	 * @param ctx the parse tree
	 */
	void exitSubtract_expression(HelloParser.Subtract_expressionContext ctx);
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
}