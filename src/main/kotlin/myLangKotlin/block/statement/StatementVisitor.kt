package myLangKotlin.block.statement

import myLangKotlin.block.BlockVisitor
import myLangKotlin.block.localVariable.LocalVariableDeclarationStatementVisitor
import myLangKotlin.block.print.PrintVisitor
import myLangKotlin.response.MyLangException
import myLangKotlin.response.Response
import myLangParser.MyLangBaseVisitor
import myLangParser.MyLangParser

class StatementVisitor : MyLangBaseVisitor<Response<*>>() {

    @Throws(MyLangException::class)
    override fun visitStatement(ctx: MyLangParser.StatementContext): Response<*> {
        return when {
            ctx.block() != null -> BlockVisitor().visitBlock(ctx.block())
            ctx.assignment() != null -> AssignmentVisitor().visitAssignment(ctx.assignment())
            ctx.ifThenStatement() != null -> IfThenStatementVisitor().visitIfThenStatement(ctx.ifThenStatement())
            ctx.ifThenElseStatement() != null -> IfThenElseStatementVisitor().visitIfThenElseStatement(ctx.ifThenElseStatement())
            ctx.forStatement() != null -> ForStatementVisitor().visitForStatement(ctx.forStatement())
            ctx.funInvocation() != null -> FunInvocationVisitor().visitFunInvocation(ctx.funInvocation())
            ctx.returnStatement() != null -> ReturnStatementVisitor().visitReturnStatement(ctx.returnStatement())
            ctx.localVariableDeclarationStatement() != null -> LocalVariableDeclarationStatementVisitor()
                    .visitLocalVariableDeclarationStatement(ctx.localVariableDeclarationStatement())
            ctx.print() != null -> PrintVisitor().visitPrint(ctx.print())
            else -> throw MyLangException("Нереализованная операция в Statement")
        }
    }
}

