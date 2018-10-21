package myLangKotlin.function

import myLangKotlin.response.EmptyResponse
import myLangKotlin.response.Response
import myLangKotlin.response.ReturnResponse
import myLangParser.MyLangBaseVisitor
import myLangParser.MyLangParser

class ResultVisitor : MyLangBaseVisitor<Response<*>>(){
    override fun visitResult(ctx: MyLangParser.ResultContext): Response<*> {
        return ReturnResponse(ctx.text)
    }
}