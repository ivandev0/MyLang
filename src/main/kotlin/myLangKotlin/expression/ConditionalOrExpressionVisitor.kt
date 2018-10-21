package myLangKotlin.expression

import myLangKotlin.response.BooleanResponse
import myLangKotlin.response.IntegerResponse
import myLangKotlin.response.MyLangException
import myLangKotlin.response.Response
import myLangParser.MyLangBaseVisitor
import myLangParser.MyLangParser

class ConditionalOrExpressionVisitor : MyLangBaseVisitor<Response<*>>() {
    @Throws(MyLangException::class)
    override fun visitConditionalOrExpression(ctx: MyLangParser.ConditionalOrExpressionContext): Response<*> {
        if (ctx.childCount == 1) {
            return ConditionalAndExpressionVisitor().visitConditionalAndExpression(ctx.conditionalAndExpression())
        }
        val firstResponse = visitConditionalOrExpression(ctx.conditionalOrExpression())
        val secondResponse = ConditionalAndExpressionVisitor().visitConditionalAndExpression(ctx.conditionalAndExpression())
        if (firstResponse is IntegerResponse || secondResponse is IntegerResponse) {
            throw MyLangException("Оператор || не применим к типу int")
        }
        return BooleanResponse(firstResponse.response as Boolean || secondResponse.response as Boolean)
    }
}
