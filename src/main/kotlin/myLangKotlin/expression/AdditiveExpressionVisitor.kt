package myLangKotlin.expression

import myLangKotlin.CompilationUnitVisitor
import myLangKotlin.block.Storage
import myLangKotlin.block.statement.FunInvocationVisitor
import myLangKotlin.response.IntegerResponse
import myLangKotlin.response.MyLangException
import myLangKotlin.response.Response
import myLangParser.MyLangBaseVisitor
import myLangParser.MyLangLexer
import myLangParser.MyLangParser
import org.antlr.v4.runtime.tree.TerminalNodeImpl

class AdditiveExpressionVisitor : MyLangBaseVisitor<Response<*>>() {

    @Throws(MyLangException::class)
    override fun visitAdditiveExpression(ctx: MyLangParser.AdditiveExpressionContext): IntegerResponse {

        if (ctx.NUMBER() != null) {
            return IntegerResponse(Integer.valueOf(ctx.NUMBER().text))
        }

        if (ctx.ID() != null) {
            val result = Storage[ctx.ID().text].value as Int
            return IntegerResponse(result)
        }

        if (ctx.funInvocation() != null) {
            val name = ctx.funInvocation().ID().text
            val function = CompilationUnitVisitor.functions
                    .stream()
                    .filter { `fun` -> `fun`.name == name }
                    .findFirst()
                    .orElse(null) ?: throw MyLangException("Функция $name не объявлена")
            if (function.resultType == "void") {
                throw MyLangException("Функция $name не возвращает результата")
            }
            return IntegerResponse(Integer.valueOf(FunInvocationVisitor().visitFunInvocation(ctx.funInvocation()).response as String))
        }

        if (ctx.LPAREN() != null && ctx.RPAREN() != null) {
            return visitAdditiveExpression(ctx.additiveExpression(0))
        } else {
            val firstResponse = visitAdditiveExpression(ctx.additiveExpression(0))
            val secondResponse = visitAdditiveExpression(ctx.additiveExpression(1))
            when ((ctx.getChild(1) as TerminalNodeImpl).getSymbol().type) {
                MyLangLexer.ADD -> return IntegerResponse(firstResponse.response + secondResponse.response)
                MyLangLexer.SUB -> return IntegerResponse(firstResponse.response - secondResponse.response)
                MyLangLexer.MUL -> return IntegerResponse(firstResponse.response * secondResponse.response)
                MyLangLexer.DIV -> {
                    if (secondResponse.response == 0) {
                        throw MyLangException("Деление на 0")
                    }
                    return IntegerResponse(firstResponse.response / secondResponse.response)
                }
            }
        }

        throw MyLangException("Неподдерживаемая арифметическая операция")
    }
}
