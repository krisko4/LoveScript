// Generated from C:/Users/optiplex/IdeaProjects/MyLanguage1/src\Hello.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link HelloParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface HelloVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link HelloParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(HelloParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(HelloParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#print_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint_stmt(HelloParser.Print_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#read_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRead_stmt(HelloParser.Read_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#assign_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign_stmt(HelloParser.Assign_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(HelloParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#add_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdd_expression(HelloParser.Add_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#mult_divide_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMult_divide_expression(HelloParser.Mult_divide_expressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code divide}
	 * labeled alternative in {@link HelloParser#divide_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivide(HelloParser.DivideContext ctx);
	/**
	 * Visit a parse tree produced by the {@code singleDivide}
	 * labeled alternative in {@link HelloParser#divide_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleDivide(HelloParser.SingleDivideContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mult}
	 * labeled alternative in {@link HelloParser#mult_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMult(HelloParser.MultContext ctx);
	/**
	 * Visit a parse tree produced by the {@code singleMult}
	 * labeled alternative in {@link HelloParser#mult_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleMult(HelloParser.SingleMultContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#subtract_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubtract_expression(HelloParser.Subtract_expressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code id}
	 * labeled alternative in {@link HelloParser#val}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(HelloParser.IdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code int}
	 * labeled alternative in {@link HelloParser#val}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt(HelloParser.IntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code real}
	 * labeled alternative in {@link HelloParser#val}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReal(HelloParser.RealContext ctx);
	/**
	 * Visit a parse tree produced by the {@code array}
	 * labeled alternative in {@link HelloParser#val}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray(HelloParser.ArrayContext ctx);
	/**
	 * Visit a parse tree produced by the {@code string}
	 * labeled alternative in {@link HelloParser#val}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(HelloParser.StringContext ctx);
}