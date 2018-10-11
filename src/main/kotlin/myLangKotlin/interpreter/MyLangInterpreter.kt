package myLangKotlin.interpreter

import myLangKotlin.block.BlockContext
import myLangKotlin.response.MyLangException
import myLangParser.MyLangBaseVisitor
import myLangParser.MyLangParser

import java.util.stream.Collectors

/**
 * Основной класс интерпретатор - точка входа.
 *
 * @author Ivan Kylchik
 */
class MyLangInterpreter {

    companion object {
        var functions: List<Function> = emptyList()
    }

    /**
     * Единственнй метод класса [MyLangInterpreter].
     *
     * Находит все методы объявленные в коде, испоьзуя класс [FunctionVisitor],
     * и добавляет их в статический список `functions`.
     *
     * Вызывает обработчик блока функции описанный в классе [FunBodyVisitor].
     *
     * @param ctx корень синтаксического дерева
     * @throws MyLangException
     *  1. Перавя функция в файле не main
     *  2. Функция main принимает аргументы
     *  3. Функция main возвращает не int
     *  4. Все принятые исключения из вызванных классов
     */
    @Throws(MyLangException::class)
    fun visitCompilationUnit(ctx: MyLangParser.CompilationUnitContext) {
        val functionVisitor = FunctionVisitor()
        functions = ctx.funDeclaration()
                .stream()
                .map { function -> function.accept(functionVisitor) }
                .collect(Collectors.toList())

        if (functions.isEmpty()) {
            System.exit(0)
        }

        if (functions.first().name != "main" || functions.first().args.isNotEmpty() || functions.first().resultType != "int") {
            throw MyLangException("Первая функция должна быть main без аргументов, возвращающая int")
        }

        FunBodyVisitor().visitBlock(functions.first().ctx.block())
        functions = emptyList()
    }

    /**
     * Класс содержит единственный метод `visitFunDeclaration` наследованный от [MyLangBaseVisitor]
     */
    internal inner class FunctionVisitor : MyLangBaseVisitor<Function>() {
        /**
         * Пробегает по всех блокам и парсит аргументы, блок  и возвращаемый тип.
         * @param ctx лист дерева с объявлением метода.
         * @return объяект класса [Function] содержащий всю необходимую информацию о методе.
         */
        override fun visitFunDeclaration(ctx: MyLangParser.FunDeclarationContext): Function {
            val resultType = ctx.getChild(0).text
            val name = ctx.getChild(1).text

            val funArgsVisitor = FunArgsVisitor()
            var funArgs : List<FunArgs> = emptyList()
            if (ctx.funCallArgs() != null) {
                funArgs = ctx.funCallArgs().funArgs()
                        .stream()
                        .map { args -> args.accept(funArgsVisitor) }
                        .collect(Collectors.toList())
            }

            return Function(name, resultType, funArgs, ctx)
        }
    }

    /**
     * Класс содержит единственный метод `visitFunArgs` наследованный от [MyLangBaseVisitor]
     */
    private inner class FunArgsVisitor : MyLangBaseVisitor<FunArgs>() {
        /**
         * Парсит аргумент метода.
         *
         * @param ctx лист с объявлением аргументов
         * @return объект класса [FunArgs] содержащий всю информацию об аргументе.
         */
        override fun visitFunArgs(ctx: MyLangParser.FunArgsContext): FunArgs {
            return FunArgs(ctx.types().text, ctx.ID().text)
        }


    }

    /**
     * Класс содержит единственный метод `visitBlock` наследованный от [MyLangBaseVisitor]
     */
    private inner class FunBodyVisitor {

        /**
         * Парсит блок метода.
         *
         * @param ctx лист содержащий блок метода
         * @throws MyLangException
         *  1. Функция main не содержит оператора return, возвращающего int
         *  2. Все принятые исключения из вызванных классов
         */
        @Throws(MyLangException::class)
        internal fun visitBlock(ctx: MyLangParser.BlockContext) {
            val response = BlockContext().handler(ctx)
            if (response.response == null || response.response == "") {
                throw MyLangException("Функция main должна возвращать тип int")
            }
        }

    }
}

