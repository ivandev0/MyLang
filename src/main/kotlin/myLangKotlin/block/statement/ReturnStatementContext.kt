package myLangKotlin.block.statement

import myLangKotlin.ContextHandler
import myLangKotlin.expression.ExpressionContext
import myLangKotlin.response.MyLangException
import myLangKotlin.response.Response
import myLangKotlin.response.ReturnResponse
import myLangParser.MyLangParser

class ReturnStatementContext : ContextHandler<MyLangParser.ReturnStatementContext> {
    @Throws(MyLangException::class)
    override fun handler(ctx: MyLangParser.ReturnStatementContext): Response<*> {
        if (ctx.expression() != null) {
            val response = ExpressionContext().handler(ctx.expression())
            return ReturnResponse(response.response.toString())
        }
        return ReturnResponse("")
    }
}
