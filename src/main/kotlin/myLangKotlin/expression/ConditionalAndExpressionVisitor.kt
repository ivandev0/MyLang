package myLangKotlin.expression

import myLangKotlin.response.BooleanResponse
import myLangKotlin.response.IntegerResponse
import myLangKotlin.response.MyLangException
import myLangKotlin.response.Response
import myLangParser.MyLangBaseVisitor
import myLangParser.MyLangParser

class ConditionalAndExpressionVisitor : MyLangBaseVisitor<Response<*>>() {

    @Throws(MyLangException::class)
    override fun visitConditionalAndExpression(ctx: MyLangParser.ConditionalAndExpressionContext): Response<*> {
        if (ctx.childCount == 1) {
            return EqualityExpressionVisitor().visitEqualityExpression(ctx.equalityExpression())
        }
        val firstResponse = visitConditionalAndExpression(ctx.conditionalAndExpression())
        val secondResponse = EqualityExpressionVisitor().visitEqualityExpression(ctx.equalityExpression())
        if (firstResponse is IntegerResponse || secondResponse is IntegerResponse) {
            throw MyLangException("Оператор && не применим к типу int")
        }

        return BooleanResponse(firstResponse.response as Boolean && secondResponse.response as Boolean)
    }
}
