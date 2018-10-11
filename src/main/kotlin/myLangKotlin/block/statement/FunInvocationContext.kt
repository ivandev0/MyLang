package myLangKotlin.block.statement

import myLangKotlin.ContextHandler
import myLangKotlin.interpreter.MyLangInterpreter
import myLangKotlin.response.EmptyResponse
import myLangKotlin.response.MyLangException
import myLangKotlin.response.Response
import myLangKotlin.block.BlockContext
import myLangParser.MyLangParser

import java.util.LinkedList

class FunInvocationContext : ContextHandler<MyLangParser.FunInvocationContext> {
    @Throws(MyLangException::class)
    override fun handler(ctx: MyLangParser.FunInvocationContext): Response<*> {
        val name = ctx.ID().text
        val function = MyLangInterpreter.functions
                .stream()
                .filter { func -> func.name == name }
                .findFirst()
                .orElse(null) ?: throw MyLangException("Функция $name не объявлена")

        if (function.name == "main") {
            throw MyLangException("Нельзя вызывать рекурсивно функцию main")
        }

        val funArgs = if (ctx.argumentList() == null) LinkedList() else ctx.argumentList().expression()
        if (function.args.size != funArgs.size) {
            throw MyLangException("Неверное количество параметров для функции " + name
                    + "\nОжидалось " + function.args.size + " аргументов")
        }

        BlockContext.pushStack(function.args, funArgs)
        val fdc = function.ctx
        val response = BlockContext().handler(fdc.block())
        if (response.response == null && function.resultType != "void") {
            throw MyLangException("Функция " + name + " должна возвращать значение типа " + function.resultType)
        }
        if (response.response != null && (response.response == "" && function.resultType != "void" || response.response != "" && function.resultType == "void")) {
            throw MyLangException("Функция " + name + " должна возвращать значение типа " + function.resultType)
        }
        BlockContext.popStack()

        return if (response !is EmptyResponse && response.response == "") {
            EmptyResponse()
        } else response
    }
}
