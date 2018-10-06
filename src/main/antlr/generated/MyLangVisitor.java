// Generated from E:\!PROJECTS\IntelliJ IDEA\MyLang/src/main/antlr/MyLang.g4 by ANTLR 4.7.1
package generated;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MyLangParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MyLangVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MyLangParser#types}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypes(MyLangParser.TypesContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser#compilationUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompilationUnit(MyLangParser.CompilationUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser#funDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunDeclaration(MyLangParser.FunDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser#result}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitResult(MyLangParser.ResultContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser#funCallArgs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunCallArgs(MyLangParser.FunCallArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser#funArgs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunArgs(MyLangParser.FunArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(MyLangParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser#blockStatements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStatements(MyLangParser.BlockStatementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser#localVariableDeclarationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocalVariableDeclarationStatement(MyLangParser.LocalVariableDeclarationStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser#variableDeclaratorList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclaratorList(MyLangParser.VariableDeclaratorListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser#variableDeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclarator(MyLangParser.VariableDeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(MyLangParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser#ifThenStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfThenStatement(MyLangParser.IfThenStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser#ifThenElseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfThenElseStatement(MyLangParser.IfThenElseStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser#forStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStatement(MyLangParser.ForStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser#forInit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForInit(MyLangParser.ForInitContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser#forUpdate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForUpdate(MyLangParser.ForUpdateContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser#statementExpressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementExpressionList(MyLangParser.StatementExpressionListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser#funInvocation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunInvocation(MyLangParser.FunInvocationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser#argumentList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgumentList(MyLangParser.ArgumentListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser#print}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(MyLangParser.PrintContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser#printVariations}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintVariations(MyLangParser.PrintVariationsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(MyLangParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(MyLangParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser#assignmentOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentOperator(MyLangParser.AssignmentOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser#conditionalOrExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionalOrExpression(MyLangParser.ConditionalOrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser#conditionalAndExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionalAndExpression(MyLangParser.ConditionalAndExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser#equalityExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityExpression(MyLangParser.EqualityExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser#relationalExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalExpression(MyLangParser.RelationalExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyLangParser#additiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExpression(MyLangParser.AdditiveExpressionContext ctx);
}