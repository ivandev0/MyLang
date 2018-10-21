package myLangKotlin.block.localVariable

import myLangKotlin.block.Storage
import myLangKotlin.expression.AdditiveExpressionVisitor
import myLangKotlin.response.EmptyResponse
import myLangKotlin.response.Response
import myLangParser.MyLangBaseVisitor
import myLangParser.MyLangParser

class VariableDeclaratorVisitor : MyLangBaseVisitor<Response<*>>(){
    override fun visitVariableDeclarator(ctx: MyLangParser.VariableDeclaratorContext): Response<*> {
        val name = ctx.ID().text

        if(ctx.additiveExpression() != null) {
            val value = AdditiveExpressionVisitor().visitAdditiveExpression(ctx.additiveExpression())
            Storage.add(ctx.ID().text, value.response)
        } else {
            Storage.add(name, 0)
        }

        return EmptyResponse()
    }
}