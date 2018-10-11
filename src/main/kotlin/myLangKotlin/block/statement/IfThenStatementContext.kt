package myLangKotlin.block.statement

import myLangKotlin.ContextHandler
import myLangKotlin.expression.ExpressionContext
import myLangKotlin.response.BooleanResponse
import myLangKotlin.response.EmptyResponse
import myLangKotlin.response.MyLangException
import myLangKotlin.response.Response
import myLangParser.MyLangParser

class IfThenStatementContext : ContextHandler<MyLangParser.IfThenStatementContext> {
    @Throws(MyLangException::class)
    override fun handler(ctx: MyLangParser.IfThenStatementContext): Response<*> {
        val response = ExpressionContext().handler(ctx.expression()) as? BooleanResponse
                ?: throw MyLangException("Оператор if требует тип boolean")
        return if (response.response as Boolean) {
            StatementContext().handler(ctx.statement())
        } else EmptyResponse()
    }
}
