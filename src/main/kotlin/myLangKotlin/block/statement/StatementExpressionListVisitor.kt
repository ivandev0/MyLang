package myLangKotlin.block.statement

import myLangKotlin.response.EmptyResponse
import myLangKotlin.response.Response
import myLangParser.MyLangBaseVisitor
import myLangParser.MyLangParser

class StatementExpressionListVisitor : MyLangBaseVisitor<Response<*>>(){
    override fun visitStatementExpressionList(ctx: MyLangParser.StatementExpressionListContext): Response<*> {
        ctx.assignment()
                .stream()
                .forEach { AssignmentVisitor().visitAssignment(it) }
        return EmptyResponse();
    }
}