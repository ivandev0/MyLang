package myLangKotlin.block.localVariable

import myLangKotlin.function.TypesVisitor
import myLangKotlin.response.EmptyResponse
import myLangKotlin.response.MyLangException
import myLangKotlin.response.Response
import myLangParser.MyLangBaseVisitor
import myLangParser.MyLangParser

class LocalVariableDeclarationStatementVisitor : MyLangBaseVisitor<Response<*>>(){
    @Throws(MyLangException::class)
    override fun visitLocalVariableDeclarationStatement(ctx: MyLangParser.LocalVariableDeclarationStatementContext): Response<*> {
        val type = TypesVisitor().visitTypes(ctx.types()).response as String
        if (type != "int") {
            throw MyLangException("Нереализованный тип данных $type")
        }

        VariableDeclaratorListVisitor().visitVariableDeclaratorList(ctx.variableDeclaratorList())

        return EmptyResponse()
    }
}