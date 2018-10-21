package myLangKotlin.block.statement

import myLangKotlin.expression.ExpressionVisitor
import myLangKotlin.response.BooleanResponse
import myLangKotlin.response.EmptyResponse
import myLangKotlin.response.MyLangException
import myLangKotlin.response.Response
import myLangKotlin.block.Storage
import myLangKotlin.block.localVariable.LocalVariableDeclarationStatementVisitor
import myLangParser.MyLangBaseVisitor
import myLangParser.MyLangParser

class ForStatementVisitor : MyLangBaseVisitor<Response<*>>() {
    @Throws(MyLangException::class)
    override fun visitForStatement(ctx: MyLangParser.ForStatementContext): Response<*> {
        Storage.createNestedStorage()

        LocalVariableDeclarationStatementVisitor().visitLocalVariableDeclarationStatement(ctx.localVariableDeclarationStatement())
        var response: Response<*> = ExpressionVisitor().visitExpression(ctx.expression()) as? BooleanResponse
                ?: throw MyLangException("Условие в цикле for должно быть типа boolean")

        while (response.response as Boolean) {
            StatementVisitor().visitStatement(ctx.statement())
            ForUpdateVisitor().visitForUpdate(ctx.forUpdate())
            response = ExpressionVisitor().visitExpression(ctx.expression())
        }

        Storage.deleteNestedStorage()
        return EmptyResponse()
    }
}
