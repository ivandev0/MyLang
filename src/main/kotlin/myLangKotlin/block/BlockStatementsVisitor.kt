package myLangKotlin.block

import myLangKotlin.block.localVariable.LocalVariableDeclarationStatementVisitor
import myLangKotlin.block.print.PrintVisitor
import myLangKotlin.block.statement.StatementVisitor
import myLangKotlin.response.MyLangException
import myLangKotlin.response.Response
import myLangParser.MyLangBaseVisitor
import myLangParser.MyLangParser

class BlockStatementsVisitor : MyLangBaseVisitor<Response<*>>(){
    override fun visitBlockStatements(ctx: MyLangParser.BlockStatementsContext): Response<*> {
        return when {
            ctx.localVariableDeclarationStatement() != null -> LocalVariableDeclarationStatementVisitor()
                    .visitLocalVariableDeclarationStatement(ctx.localVariableDeclarationStatement())
            ctx.statement() != null -> StatementVisitor().visitStatement(ctx.statement())
            ctx.print() != null -> PrintVisitor().visitPrint(ctx.print())
            else -> throw MyLangException("Нереализованная операция в BlockStatement")
        }
    }
}