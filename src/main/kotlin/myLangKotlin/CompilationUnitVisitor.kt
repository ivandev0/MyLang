package myLangKotlin

import myLangKotlin.block.BlockVisitor
import myLangKotlin.function.FunDeclarationVisitor
import myLangKotlin.function.Function
import myLangKotlin.response.EmptyResponse
import myLangKotlin.response.MyLangException
import myLangKotlin.response.Response
import myLangKotlin.response.ReturnResponse
import myLangParser.MyLangBaseVisitor
import myLangParser.MyLangParser
import java.util.stream.Collectors

/**
 * Точка входа.
 *
 * @author Ivan Kylchik
 */
class CompilationUnitVisitor : MyLangBaseVisitor<Response<*>>(){

    companion object {
        var functions: List<Function> = emptyList()
    }

    /**
     * Находит все методы объявленные в коде, испоьзуя класс [FunDeclarationVisitor],
     * и добавляет их в статический список `functions`.
     *
     * Вызывает обработчик блока функции описанный в классе [BlockVisitor].
     *
     * @param ctx корень синтаксического дерева
     * @throws MyLangException
     *  1. Перавя функция в файле не main
     *  2. Функция main принимает аргументы
     *  3. Функция main возвращает не int
     *  4. Все принятые исключения из вызванных классов
     */
    @Throws(MyLangException::class)
    override fun visitCompilationUnit(ctx: MyLangParser.CompilationUnitContext): Response<*> {

        functions = ctx.funDeclaration()
                .stream()
                .map { p -> FunDeclarationVisitor().visitFunDeclaration(p) }
                .collect(Collectors.toList())

        if (functions.isEmpty()) {
            System.exit(0)
        }

        if (functions.first().name != "main" || functions.first().args.isNotEmpty() || functions.first().resultType != "int") {
            throw MyLangException("Первая функция должна быть main без аргументов, возвращающая int")
        }

        val response : ReturnResponse = BlockVisitor().visitBlock(functions.first().ctx.block()) as ReturnResponse
        if (response.response == null || response.response == "") {
            throw MyLangException("Функция main должна возвращать тип int")
        }
        //functions = emptyList()
        return EmptyResponse()
    }
}