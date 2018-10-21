package myLangKotlin.block.print

import myLangKotlin.response.MyLangException
import myLangKotlin.response.Response
import myLangParser.MyLangBaseVisitor
import myLangParser.MyLangParser

class PrintVisitor : MyLangBaseVisitor<Response<*>>() {

    @Throws(MyLangException::class)
    override fun visitPrint(ctx: MyLangParser.PrintContext): Response<*> {
        return PrintVariationsVisitor().visitPrintVariations(ctx.printVariations())
    }
}
