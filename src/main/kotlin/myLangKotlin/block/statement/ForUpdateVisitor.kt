package myLangKotlin.block.statement

import myLangKotlin.response.EmptyResponse
import myLangKotlin.response.MyLangException
import myLangKotlin.response.Response
import myLangParser.MyLangBaseVisitor
import myLangParser.MyLangParser

class ForUpdateVisitor : MyLangBaseVisitor<Response<*>>() {

    @Throws(MyLangException::class)
    override fun visitForUpdate(ctx: MyLangParser.ForUpdateContext): Response<*> {
        return StatementExpressionListVisitor().visitStatementExpressionList(ctx.statementExpressionList())
    }
}