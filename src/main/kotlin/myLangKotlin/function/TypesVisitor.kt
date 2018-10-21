package myLangKotlin.function

import myLangKotlin.response.Response
import myLangKotlin.response.StringResponse
import myLangParser.MyLangBaseVisitor
import myLangParser.MyLangParser

class TypesVisitor : MyLangBaseVisitor<Response<*>>(){
    override fun visitTypes(ctx: MyLangParser.TypesContext): Response<*> {
        return StringResponse(ctx.text)
    }
}