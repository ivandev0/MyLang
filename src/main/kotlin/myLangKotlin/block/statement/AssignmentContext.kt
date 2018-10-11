package myLangKotlin.block.statement

import myLangKotlin.ContextHandler
import myLangKotlin.response.IntegerResponse
import myLangKotlin.response.MyLangException
import myLangKotlin.response.Response
import myLangKotlin.block.BlockContext
import myLangParser.MyLangLexer
import myLangParser.MyLangParser

class AssignmentContext : ContextHandler<MyLangParser.AssignmentContext> {
    @Throws(MyLangException::class)
    override fun handler(ctx: MyLangParser.AssignmentContext): Response<*> {
        val name = ctx.ID().text
        val num = BlockContext[name].value as Int
        val response = defaultHandler(ctx, 2) as IntegerResponse
        when (ctx.assignmentOperator().start.type) {
            MyLangLexer.ASSIGN -> return IntegerResponse(BlockContext.update(name, response.response))
            MyLangLexer.ADD_ASSIGN -> return IntegerResponse(BlockContext.update(name, num + response.response))
            MyLangLexer.SUB_ASSIGN -> return IntegerResponse(BlockContext.update(name, num - response.response))
            MyLangLexer.MUL_ASSIGN -> return IntegerResponse(BlockContext.update(name, num * response.response))
            MyLangLexer.DIV_ASSIGN -> {
                if (response.response == 0) {
                    throw MyLangException("Деление на 0")
                }
                return IntegerResponse(BlockContext.update(name, num / response.response))
            }
            else -> throw MyLangException("Операция " + ctx.assignmentOperator().start.text + " не поддерживается")
        }
    }
}
