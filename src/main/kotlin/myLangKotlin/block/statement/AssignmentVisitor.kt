package myLangKotlin.block.statement

import myLangKotlin.response.IntegerResponse
import myLangKotlin.response.MyLangException
import myLangKotlin.response.Response
import myLangKotlin.block.Storage
import myLangKotlin.expression.AdditiveExpressionVisitor
import myLangParser.MyLangBaseVisitor
import myLangParser.MyLangLexer
import myLangParser.MyLangParser

class AssignmentVisitor : MyLangBaseVisitor<Response<*>>() {

    @Throws(MyLangException::class)
    override fun visitAssignment(ctx: MyLangParser.AssignmentContext): Response<*> {
        val name = ctx.ID().text
        val num = Storage[name].value as Int
        val response : IntegerResponse = when {
            ctx.additiveExpression() != null -> AdditiveExpressionVisitor().visitAdditiveExpression(ctx.additiveExpression())
            ctx.assignment() != null -> visitAssignment(ctx.assignment()) as IntegerResponse
            else -> throw MyLangException("Нереализованная операция в Assignment")
        }
        return when (ctx.assignmentOperator().start.type) {
            MyLangLexer.ASSIGN -> IntegerResponse(Storage.update(name, response.response))
            MyLangLexer.ADD_ASSIGN -> IntegerResponse(Storage.update(name, num + response.response))
            MyLangLexer.SUB_ASSIGN -> IntegerResponse(Storage.update(name, num - response.response))
            MyLangLexer.MUL_ASSIGN -> IntegerResponse(Storage.update(name, num * response.response))
            MyLangLexer.DIV_ASSIGN -> {
                if (response.response == 0) {
                    throw MyLangException("Деление на 0")
                }
                IntegerResponse(Storage.update(name, num / response.response))
            }
            else -> throw MyLangException("Операция " + ctx.assignmentOperator().start.text + " не поддерживается")
        }
    }
}
