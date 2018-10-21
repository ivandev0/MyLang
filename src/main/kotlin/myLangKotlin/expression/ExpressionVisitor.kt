package myLangKotlin.expression

import myLangKotlin.block.statement.AssignmentVisitor
import myLangKotlin.response.MyLangException
import myLangKotlin.response.Response
import myLangParser.MyLangBaseVisitor
import myLangParser.MyLangParser

class ExpressionVisitor : MyLangBaseVisitor<Response<*>>() {
    @Throws(MyLangException::class)
    override fun visitExpression(ctx: MyLangParser.ExpressionContext): Response<*> {
        if (ctx.conditionalOrExpression() != null){
            return ConditionalOrExpressionVisitor().visitConditionalOrExpression(ctx.conditionalOrExpression())
        } else if (ctx.assignment() != null){
            return AssignmentVisitor().visitAssignment(ctx.assignment())
        }
        throw MyLangException("Нереализованная операция в Expression")
    }
}

