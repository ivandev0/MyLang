package myLangKotlin.block.statement

import myLangKotlin.ContextHandler
import myLangKotlin.response.MyLangException
import myLangKotlin.response.Response
import myLangParser.MyLangParser

class StatementContext : ContextHandler<MyLangParser.StatementContext> {

    @Throws(MyLangException::class)
    override fun handler(ctx: MyLangParser.StatementContext): Response<*> {
        return defaultHandler(ctx)
    }
}

