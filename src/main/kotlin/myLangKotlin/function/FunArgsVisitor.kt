package myLangKotlin.function

import myLangParser.MyLangBaseVisitor
import myLangParser.MyLangParser

/**
 * Класс содержит единственный метод `visitFunArgs` наследованный от [MyLangBaseVisitor]
 */
class FunArgsVisitor : MyLangBaseVisitor<FunArgs>(){
    /**
     * Парсит аргумент метода.
     *
     * @param ctx лист с объявлением аргументов
     * @return объект класса [FunArgs] содержащий всю информацию об аргументе.
     */
    override fun visitFunArgs(ctx: MyLangParser.FunArgsContext): FunArgs {
        val type = TypesVisitor().visitTypes(ctx.types()).response as String
        return FunArgs(type, ctx.ID().text)
    }
}