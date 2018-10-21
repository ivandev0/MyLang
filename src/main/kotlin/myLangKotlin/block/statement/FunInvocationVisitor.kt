package myLangKotlin.block.statement

import myLangKotlin.CompilationUnitVisitor
import myLangKotlin.block.BlockVisitor
import myLangKotlin.block.Storage
import myLangKotlin.response.EmptyResponse
import myLangKotlin.response.MyLangException
import myLangKotlin.response.Response
import myLangParser.MyLangBaseVisitor
import myLangParser.MyLangParser

import java.util.LinkedList

class FunInvocationVisitor : MyLangBaseVisitor<Response<*>>() {
    @Throws(MyLangException::class)
    override fun visitFunInvocation(ctx: MyLangParser.FunInvocationContext): Response<*> {
        val name = ctx.ID().text
        val function = CompilationUnitVisitor.functions
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

        Storage.pushStack(function.args, funArgs)
        val fdc = function.ctx
        val response = BlockVisitor().visitBlock(fdc.block())
        if (response.response == null && function.resultType != "void") {
            throw MyLangException("Функция " + name + " должна возвращать значение типа " + function.resultType)
        }
        if (response.response != null && (response.response == "" && function.resultType != "void" || response.response != "" && function.resultType == "void")) {
            throw MyLangException("Функция " + name + " должна возвращать значение типа " + function.resultType)
        }
        Storage.popStack()

        return if (response !is EmptyResponse && response.response == "") {
            EmptyResponse()
        } else response
    }
}
