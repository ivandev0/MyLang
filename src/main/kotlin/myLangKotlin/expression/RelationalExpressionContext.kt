package myLangKotlin.expression

import myLangKotlin.ContextHandler
import myLangKotlin.response.BooleanResponse
import myLangKotlin.response.IntegerResponse
import myLangKotlin.response.MyLangException
import myLangKotlin.response.Response
import myLangParser.MyLangLexer
import myLangParser.MyLangParser
import org.antlr.v4.runtime.tree.TerminalNodeImpl

class RelationalExpressionContext : ContextHandler<MyLangParser.RelationalExpressionContext> {

    @Throws(MyLangException::class)
    override fun handler(ctx: MyLangParser.RelationalExpressionContext): Response<*> {
        if (ctx.childCount == 1) {
            return defaultHandler(ctx)
        }
        val firstResponse = defaultHandler(ctx, 0) as IntegerResponse
        val secondResponse = defaultHandler(ctx, 2) as IntegerResponse
        when ((ctx.getChild(1) as TerminalNodeImpl).getSymbol().type) {
            MyLangLexer.LT -> return BooleanResponse(firstResponse.response < secondResponse.response)
            MyLangLexer.GT -> return BooleanResponse(firstResponse.response > secondResponse.response)
            MyLangLexer.LE -> return BooleanResponse(firstResponse.response <= secondResponse.response)
            MyLangLexer.GE -> return BooleanResponse(firstResponse.response >= secondResponse.response)
        }

        throw MyLangException("Неподдерживаемая операция сравнения")
    }
}
