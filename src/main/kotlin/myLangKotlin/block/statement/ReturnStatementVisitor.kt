package myLangKotlin.block.statement

import myLangKotlin.expression.ExpressionVisitor
import myLangKotlin.response.MyLangException
import myLangKotlin.response.Response
import myLangKotlin.response.ReturnResponse
import myLangParser.MyLangBaseVisitor
import myLangParser.MyLangParser

class ReturnStatementVisitor : MyLangBaseVisitor<Response<*>>() {
    @Throws(MyLangException::class)
    override fun visitReturnStatement(ctx: MyLangParser.ReturnStatementContext): Response<*> {
        if (ctx.expression() != null) {
            val response = ExpressionVisitor().visitExpression(ctx.expression())
            return ReturnResponse(response.response.toString())
        }
        return ReturnResponse("")
    }
}
