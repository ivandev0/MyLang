package myLangKotlin.expression

import myLangKotlin.ContextHandler
import myLangKotlin.response.BooleanResponse
import myLangKotlin.response.MyLangException
import myLangKotlin.response.Response
import myLangParser.MyLangLexer
import myLangParser.MyLangParser
import org.antlr.v4.runtime.tree.TerminalNodeImpl

class EqualityExpressionContext : ContextHandler<MyLangParser.EqualityExpressionContext> {

    @Throws(MyLangException::class)
    override fun handler(ctx: MyLangParser.EqualityExpressionContext): Response<*> {
        if (ctx.childCount == 1) {
            return defaultHandler(ctx)
        }
        val firstResponse = defaultHandler(ctx, 0)
        val secondResponse = defaultHandler(ctx, 2)
        when ((ctx.getChild(1) as TerminalNodeImpl).getSymbol().type) {
            MyLangLexer.EQUAL -> return BooleanResponse(firstResponse.response == secondResponse.response)
            MyLangLexer.NOTEQUAL -> return BooleanResponse(firstResponse.response != secondResponse.response)
        }

        throw MyLangException("Неподдерживаемая операция сравнения")
    }
}
