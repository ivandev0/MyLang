package myLangKotlin.block.print

import myLangKotlin.expression.ExpressionVisitor
import myLangKotlin.response.EmptyResponse
import myLangKotlin.response.MyLangException
import myLangKotlin.response.Response
import myLangParser.MyLangBaseVisitor
import myLangParser.MyLangLexer
import myLangParser.MyLangParser

class PrintVariationsVisitor : MyLangBaseVisitor<Response<*>>() {
    @Throws(MyLangException::class)
    override fun visitPrintVariations(ctx: MyLangParser.PrintVariationsContext): Response<*> {

        if (ctx.childCount >= 3 && ctx.start.type == MyLangLexer.STRING && ctx.COLON() != null) {
            val response = ExpressionVisitor().visitExpression(ctx.expression())
            print(ctx.start.text.replace("\"", "") + " " + response.response)
        } else if (ctx.start.type == MyLangLexer.STRING) {
            print(ctx.start.text.replace("\"", ""))
        } else if (ctx.expression() != null) {
            val response = ExpressionVisitor().visitExpression(ctx.expression())
            print(response.response)
        } else {
            throw MyLangException(ctx.text + "\nНереализованная форма оператора print")
        }

        if (ctx.printVariations().size != 0) {
            print(" ")
        } else {
            println("")
        }

        for (context in ctx.printVariations()) {
            visitPrintVariations(context)
        }

        return EmptyResponse()
    }
}
