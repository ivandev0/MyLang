// Generated from E:\!PROJECTS\IntelliJ IDEA\MyLang/src/main/antlr/MyLang.g4 by ANTLR 4.7.1
package generated;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MyLangParser}.
 */
public interface MyLangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MyLangParser#types}.
	 * @param ctx the parse tree
	 */
	void enterTypes(MyLangParser.TypesContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#types}.
	 * @param ctx the parse tree
	 */
	void exitTypes(MyLangParser.TypesContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void enterCompilationUnit(MyLangParser.CompilationUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void exitCompilationUnit(MyLangParser.CompilationUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#funDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFunDeclaration(MyLangParser.FunDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#funDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFunDeclaration(MyLangParser.FunDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#result}.
	 * @param ctx the parse tree
	 */
	void enterResult(MyLangParser.ResultContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#result}.
	 * @param ctx the parse tree
	 */
	void exitResult(MyLangParser.ResultContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#funCallArgs}.
	 * @param ctx the parse tree
	 */
	void enterFunCallArgs(MyLangParser.FunCallArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#funCallArgs}.
	 * @param ctx the parse tree
	 */
	void exitFunCallArgs(MyLangParser.FunCallArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#funArgs}.
	 * @param ctx the parse tree
	 */
	void enterFunArgs(MyLangParser.FunArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#funArgs}.
	 * @param ctx the parse tree
	 */
	void exitFunArgs(MyLangParser.FunArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(MyLangParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(MyLangParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#blockStatements}.
	 * @param ctx the parse tree
	 */
	void enterBlockStatements(MyLangParser.BlockStatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#blockStatements}.
	 * @param ctx the parse tree
	 */
	void exitBlockStatements(MyLangParser.BlockStatementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#localVariableDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void enterLocalVariableDeclarationStatement(MyLangParser.LocalVariableDeclarationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#localVariableDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void exitLocalVariableDeclarationStatement(MyLangParser.LocalVariableDeclarationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#variableDeclaratorList}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaratorList(MyLangParser.VariableDeclaratorListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#variableDeclaratorList}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaratorList(MyLangParser.VariableDeclaratorListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#variableDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclarator(MyLangParser.VariableDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#variableDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclarator(MyLangParser.VariableDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(MyLangParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(MyLangParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#ifThenStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfThenStatement(MyLangParser.IfThenStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#ifThenStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfThenStatement(MyLangParser.IfThenStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#ifThenElseStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfThenElseStatement(MyLangParser.IfThenElseStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#ifThenElseStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfThenElseStatement(MyLangParser.IfThenElseStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void enterForStatement(MyLangParser.ForStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void exitForStatement(MyLangParser.ForStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#forInit}.
	 * @param ctx the parse tree
	 */
	void enterForInit(MyLangParser.ForInitContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#forInit}.
	 * @param ctx the parse tree
	 */
	void exitForInit(MyLangParser.ForInitContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#forUpdate}.
	 * @param ctx the parse tree
	 */
	void enterForUpdate(MyLangParser.ForUpdateContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#forUpdate}.
	 * @param ctx the parse tree
	 */
	void exitForUpdate(MyLangParser.ForUpdateContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#statementExpressionList}.
	 * @param ctx the parse tree
	 */
	void enterStatementExpressionList(MyLangParser.StatementExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#statementExpressionList}.
	 * @param ctx the parse tree
	 */
	void exitStatementExpressionList(MyLangParser.StatementExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#funInvocation}.
	 * @param ctx the parse tree
	 */
	void enterFunInvocation(MyLangParser.FunInvocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#funInvocation}.
	 * @param ctx the parse tree
	 */
	void exitFunInvocation(MyLangParser.FunInvocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#argumentList}.
	 * @param ctx the parse tree
	 */
	void enterArgumentList(MyLangParser.ArgumentListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#argumentList}.
	 * @param ctx the parse tree
	 */
	void exitArgumentList(MyLangParser.ArgumentListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#print}.
	 * @param ctx the parse tree
	 */
	void enterPrint(MyLangParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#print}.
	 * @param ctx the parse tree
	 */
	void exitPrint(MyLangParser.PrintContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#printVariations}.
	 * @param ctx the parse tree
	 */
	void enterPrintVariations(MyLangParser.PrintVariationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#printVariations}.
	 * @param ctx the parse tree
	 */
	void exitPrintVariations(MyLangParser.PrintVariationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(MyLangParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(MyLangParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(MyLangParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(MyLangParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#assignmentOperator}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentOperator(MyLangParser.AssignmentOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#assignmentOperator}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentOperator(MyLangParser.AssignmentOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#conditionalOrExpression}.
	 * @param ctx the parse tree
	 */
	void enterConditionalOrExpression(MyLangParser.ConditionalOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#conditionalOrExpression}.
	 * @param ctx the parse tree
	 */
	void exitConditionalOrExpression(MyLangParser.ConditionalOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#conditionalAndExpression}.
	 * @param ctx the parse tree
	 */
	void enterConditionalAndExpression(MyLangParser.ConditionalAndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#conditionalAndExpression}.
	 * @param ctx the parse tree
	 */
	void exitConditionalAndExpression(MyLangParser.ConditionalAndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpression(MyLangParser.EqualityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpression(MyLangParser.EqualityExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#relationalExpression}.
	 * @param ctx the parse tree
	 */
	void enterRelationalExpression(MyLangParser.RelationalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#relationalExpression}.
	 * @param ctx the parse tree
	 */
	void exitRelationalExpression(MyLangParser.RelationalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyLangParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpression(MyLangParser.AdditiveExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyLangParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpression(MyLangParser.AdditiveExpressionContext ctx);
}