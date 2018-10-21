package myLangKotlin.block.localVariable

import myLangKotlin.response.EmptyResponse
import myLangKotlin.response.Response
import myLangParser.MyLangBaseVisitor
import myLangParser.MyLangParser

class VariableDeclaratorListVisitor : MyLangBaseVisitor<Response<*>>(){
    override fun visitVariableDeclaratorList(ctx: MyLangParser.VariableDeclaratorListContext): Response<*> {
        ctx.variableDeclarator().forEach {
            VariableDeclaratorVisitor().visitVariableDeclarator(it)
        }
        return EmptyResponse()
    }
}