package myLangKotlin.expression

import myLangKotlin.response.BooleanResponse
import myLangKotlin.response.MyLangException
import myLangKotlin.response.Response
import myLangParser.MyLangBaseVisitor
import myLangParser.MyLangLexer
import myLangParser.MyLangParser
import org.antlr.v4.runtime.tree.TerminalNodeImpl

class EqualityExpressionVisitor : MyLangBaseVisitor<Response<*>>() {

    @Throws(MyLangException::class)
    override fun visitEqualityExpression(ctx: MyLangParser.EqualityExpressionContext): Response<*> {
        if (ctx.childCount == 1) {
            return RelationalExpressionVisitor().visitRelationalExpression(ctx.relationalExpression())
        }
        val firstResponse = visitEqualityExpression(ctx.equalityExpression())
        val secondResponse = RelationalExpressionVisitor().visitRelationalExpression(ctx.relationalExpression())
        when ((ctx.getChild(1) as TerminalNodeImpl).getSymbol().type) {
            MyLangLexer.EQUAL -> return BooleanResponse(firstResponse.response == secondResponse.response)
            MyLangLexer.NOTEQUAL -> return BooleanResponse(firstResponse.response != secondResponse.response)
        }

        throw MyLangException("Неподдерживаемая операция сравнения")
    }
}
