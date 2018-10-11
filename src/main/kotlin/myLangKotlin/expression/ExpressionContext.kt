package myLangKotlin.expression

import myLangKotlin.ContextHandler
import myLangKotlin.response.MyLangException
import myLangKotlin.response.Response
import myLangParser.MyLangParser

class ExpressionContext : ContextHandler<MyLangParser.ExpressionContext> {
    @Throws(MyLangException::class)
    override fun handler(ctx: MyLangParser.ExpressionContext): Response<*> {
        return defaultHandler(ctx)
    }
}

