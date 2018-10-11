package myLangKotlin.interpreter

import myLangParser.MyLangParser

/**
 * Класс обертка, хранит информацию о функции:
 *
 *  1. имя `name`
 *  2. возвращаемое значение `resultType`
 *  3. список аргументов `args`
 *  4. ссылка на блок метода `ctx`
 *
 */
class Function internal constructor(val name: String, val resultType: String, val args: List<FunArgs>, val ctx: MyLangParser.FunDeclarationContext)
