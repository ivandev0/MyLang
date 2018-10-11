package myLangKotlin.block.statement

import myLangKotlin.ContextHandler
import myLangKotlin.expression.ExpressionContext
import myLangKotlin.response.BooleanResponse
import myLangKotlin.response.MyLangException
import myLangKotlin.response.Response
import myLangParser.MyLangParser

class IfThenElseStatementContext : ContextHandler<MyLangParser.IfThenElseStatementContext> {
    @Throws(MyLangException::class)
    override fun handler(ctx: MyLangParser.IfThenElseStatementContext): Response<*> {
        val response = ExpressionContext().handler(ctx.expression()) as? BooleanResponse
                ?: throw MyLangException("Оператор if требует тип boolean")
        return if (response.response as Boolean) {
            StatementContext().handler(ctx.statement(0))
        } else {
            StatementContext().handler(ctx.statement(1))
        }
    }
}
