package myLangKotlin.block.print

import myLangKotlin.ContextHandler
import myLangKotlin.expression.ExpressionContext
import myLangKotlin.response.EmptyResponse
import myLangKotlin.response.MyLangException
import myLangKotlin.response.Response
import myLangParser.MyLangLexer
import myLangParser.MyLangParser

class PrintVariationsContext : ContextHandler<MyLangParser.PrintVariationsContext> {
    @Throws(MyLangException::class)
    override fun handler(ctx: MyLangParser.PrintVariationsContext): Response<*> {
        if (ctx.childCount >= 3 && ctx.start.type == MyLangLexer.STRING && ctx.COLON() != null) {
            val response = ExpressionContext().handler(ctx.expression())
            print(ctx.start.text.replace("\"", "") + " " + response.response)
        } else if (ctx.start.type == MyLangLexer.STRING) {
            print(ctx.start.text.replace("\"", ""))
        } else if (ctx.expression() != null) {
            val response = ExpressionContext().handler(ctx.expression())
            print(response.response)
        } else {
            throw MyLangException(ctx.text + "\nНереализованная форма оператора print")
        }

        if (ctx.printVariations().size != 0) {
            print(" ")
        } else {
            println("")
        }

        for (context in ctx.printVariations()) {
            handler(context)
        }

        return EmptyResponse()
    }
}
