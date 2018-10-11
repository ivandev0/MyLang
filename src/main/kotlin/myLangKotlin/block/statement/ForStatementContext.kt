package myLangKotlin.block.statement

import myLangKotlin.ContextHandler
import myLangKotlin.expression.ExpressionContext
import myLangKotlin.response.BooleanResponse
import myLangKotlin.response.EmptyResponse
import myLangKotlin.response.MyLangException
import myLangKotlin.response.Response
import myLangKotlin.block.BlockContext
import myLangKotlin.block.localVariable.LocalVariableDeclarationStatementContext
import myLangParser.MyLangParser

class ForStatementContext : ContextHandler<MyLangParser.ForStatementContext> {
    @Throws(MyLangException::class)
    override fun handler(ctx: MyLangParser.ForStatementContext): Response<*> {
        BlockContext.createNestedStorage()
        LocalVariableDeclarationStatementContext().handler(ctx.localVariableDeclarationStatement())
        var response: Response<*> = ExpressionContext().handler(ctx.expression()) as? BooleanResponse
                ?: throw MyLangException("Условие в цикле for должно быть типа boolean")

        while (response.response as Boolean) {
            StatementContext().handler(ctx.statement())
            ForUpdateContext().handler(ctx.forUpdate())
            response = ExpressionContext().handler(ctx.expression())
        }

        BlockContext.deleteNestedStorage()
        return EmptyResponse()
    }
}

internal class ForUpdateContext : ContextHandler<MyLangParser.ForUpdateContext> {

    @Throws(MyLangException::class)
    override fun handler(ctx: MyLangParser.ForUpdateContext): Response<*> {
        for (ac in ctx.statementExpressionList().assignment()) {
            AssignmentContext().handler(ac)
        }
        return EmptyResponse()
    }
}
