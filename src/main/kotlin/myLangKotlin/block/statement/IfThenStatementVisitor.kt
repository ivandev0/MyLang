package myLangKotlin.block.statement

import myLangKotlin.expression.ExpressionVisitor
import myLangKotlin.response.BooleanResponse
import myLangKotlin.response.EmptyResponse
import myLangKotlin.response.MyLangException
import myLangKotlin.response.Response
import myLangParser.MyLangBaseVisitor
import myLangParser.MyLangParser

class IfThenStatementVisitor : MyLangBaseVisitor<Response<*>>() {

    @Throws(MyLangException::class)
    override fun visitIfThenStatement(ctx: MyLangParser.IfThenStatementContext): Response<*> {
        val response = ExpressionVisitor().visitExpression(ctx.expression()) as? BooleanResponse
                ?: throw MyLangException("Оператор if требует тип boolean")
        return if (response.response) {
            StatementVisitor().visitStatement(ctx.statement())
        } else EmptyResponse()
    }
}
