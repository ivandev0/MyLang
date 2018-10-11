package myLangKotlin.expression

import myLangKotlin.ContextHandler
import myLangKotlin.response.BooleanResponse
import myLangKotlin.response.IntegerResponse
import myLangKotlin.response.MyLangException
import myLangKotlin.response.Response
import myLangParser.MyLangParser

class ConditionalAndExpressionContext : ContextHandler<MyLangParser.ConditionalAndExpressionContext> {

    @Throws(MyLangException::class)
    override fun handler(ctx: MyLangParser.ConditionalAndExpressionContext): Response<*> {
        if (ctx.childCount == 1) {
            return defaultHandler(ctx)
        }
        val firstResponse = defaultHandler(ctx, 0)
        val secondResponse = defaultHandler(ctx, 2)
        if (firstResponse is IntegerResponse || secondResponse is IntegerResponse) {
            throw MyLangException("Оператор && не применим к типу int")
        }

        return BooleanResponse(firstResponse.response as Boolean && secondResponse.response as Boolean)
    }
}
