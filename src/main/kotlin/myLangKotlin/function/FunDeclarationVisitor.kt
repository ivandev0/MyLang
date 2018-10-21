package myLangKotlin.function

import myLangParser.MyLangBaseVisitor
import myLangParser.MyLangParser

/**
 * Класс содержит единственный метод `visitFunDeclaration` наследованный от [MyLangBaseVisitor]
 */
class FunDeclarationVisitor : MyLangBaseVisitor<Function>() {
    /**
     * Пробегает по всех блокам и парсит аргументы, блок  и возвращаемый тип.
     * @param ctx лист дерева с объявлением метода.
     * @return объяект класса [Function] содержащий всю необходимую информацию о методе.
     */
    override fun visitFunDeclaration(ctx: MyLangParser.FunDeclarationContext): Function {
        val resultType = ResultVisitor().visitResult(ctx.result()).response as String
        val name = ctx.ID().text
        val funArgs : List<FunArgs> = FunCallArgsVisitor().visitFunCallArgs(ctx.funCallArgs())

        return Function(name, resultType, funArgs, ctx)
    }
}