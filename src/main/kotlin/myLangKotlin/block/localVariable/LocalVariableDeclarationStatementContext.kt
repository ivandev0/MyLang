package myLangKotlin.block.localVariable

import myLangKotlin.ContextHandler
import myLangKotlin.expression.AdditiveExpressionContext
import myLangKotlin.response.EmptyResponse
import myLangKotlin.response.MyLangException
import myLangKotlin.response.Response
import myLangKotlin.block.BlockContext
import myLangParser.MyLangParser
import org.antlr.v4.runtime.tree.TerminalNodeImpl

class LocalVariableDeclarationStatementContext : ContextHandler<MyLangParser.LocalVariableDeclarationStatementContext> {

    @Throws(MyLangException::class)
    override fun handler(ctx: MyLangParser.LocalVariableDeclarationStatementContext): Response<*> {
        val type = ctx.types().text
        if (type != "int") {
            throw MyLangException("Нереализованный тип данных $type")
        }
        for (i in 0 until ctx.variableDeclaratorList().childCount) {
            if (ctx.variableDeclaratorList().getChild(i) is TerminalNodeImpl) {
                continue
            }

            val vdCtx = ctx.variableDeclaratorList().getChild(i) as MyLangParser.VariableDeclaratorContext
            if (vdCtx.childCount == 1) {
                BlockContext.add(vdCtx.text, 0)
            } else {
                //count additive myLang.expression
                val response = AdditiveExpressionContext().handler(vdCtx.additiveExpression())
                BlockContext.add(vdCtx.getChild(0).text, response.response)
            }
        }

        return EmptyResponse()
    }
}
