package myLangKotlin.block.print

import myLangKotlin.ContextHandler
import myLangKotlin.response.MyLangException
import myLangKotlin.response.Response
import myLangParser.MyLangParser

class PrintContext : ContextHandler<MyLangParser.PrintContext> {

    @Throws(MyLangException::class)
    override fun handler(ctx: MyLangParser.PrintContext): Response<*> {
        return defaultHandler(ctx, 1)
    }
}
