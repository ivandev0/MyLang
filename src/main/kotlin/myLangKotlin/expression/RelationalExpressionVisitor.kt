package myLangKotlin.expression

import myLangKotlin.response.BooleanResponse
import myLangKotlin.response.IntegerResponse
import myLangKotlin.response.MyLangException
import myLangKotlin.response.Response
import myLangParser.MyLangBaseVisitor
import myLangParser.MyLangLexer
import myLangParser.MyLangParser
import org.antlr.v4.runtime.tree.TerminalNodeImpl

class RelationalExpressionVisitor : MyLangBaseVisitor<Response<*>>() {

    @Throws(MyLangException::class)
    override fun visitRelationalExpression(ctx: MyLangParser.RelationalExpressionContext): Response<*> {
        if (ctx.childCount == 1) {
            return AdditiveExpressionVisitor().visitAdditiveExpression(ctx.additiveExpression())
        }
        val firstResponse = visitRelationalExpression(ctx.relationalExpression()) as IntegerResponse
        val secondResponse = AdditiveExpressionVisitor().visitAdditiveExpression(ctx.additiveExpression())
        when ((ctx.getChild(1) as TerminalNodeImpl).getSymbol().type) {
            MyLangLexer.LT -> return BooleanResponse(firstResponse.response < secondResponse.response)
            MyLangLexer.GT -> return BooleanResponse(firstResponse.response > secondResponse.response)
            MyLangLexer.LE -> return BooleanResponse(firstResponse.response <= secondResponse.response)
            MyLangLexer.GE -> return BooleanResponse(firstResponse.response >= secondResponse.response)
        }

        throw MyLangException("Неподдерживаемая операция сравнения")
    }
}
