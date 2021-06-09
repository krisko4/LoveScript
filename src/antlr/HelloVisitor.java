// Generated from C:/Users/optiplex/IdeaProjects/MyLanguage1/src/antlr\Hello.g4 by ANTLR 4.9.1
package antlr;
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
	 * Visit a parse tree produced by {@link HelloParser#function_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_stmt(HelloParser.Function_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#function_param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_param(HelloParser.Function_paramContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#function_start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_start(HelloParser.Function_startContext ctx);
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
	 * Visit a parse tree produced by {@link HelloParser#function_call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_call(HelloParser.Function_callContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#return_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn_stmt(HelloParser.Return_stmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code add}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdd(HelloParser.AddContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sub}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSub(HelloParser.SubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mul}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMul(HelloParser.MulContext ctx);
	/**
	 * Visit a parse tree produced by the {@code divide}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivide(HelloParser.DivideContext ctx);
	/**
	 * Visit a parse tree produced by the {@code value}
	 * labeled alternative in {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(HelloParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#array_element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray_element(HelloParser.Array_elementContext ctx);
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
	/**
	 * Visit a parse tree produced by the {@code toint}
	 * labeled alternative in {@link HelloParser#val}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToint(HelloParser.TointContext ctx);
	/**
	 * Visit a parse tree produced by the {@code toreal}
	 * labeled alternative in {@link HelloParser#val}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToreal(HelloParser.TorealContext ctx);
	/**
	 * Visit a parse tree produced by the {@code priorityExpression}
	 * labeled alternative in {@link HelloParser#val}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPriorityExpression(HelloParser.PriorityExpressionContext ctx);
}