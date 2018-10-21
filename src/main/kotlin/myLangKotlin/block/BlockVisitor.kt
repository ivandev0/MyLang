package myLangKotlin.block

import myLangKotlin.response.EmptyResponse
import myLangKotlin.response.MyLangException
import myLangKotlin.response.Response
import myLangKotlin.response.ReturnResponse
import myLangParser.MyLangBaseVisitor
import myLangParser.MyLangParser

/**
 * Данный класс реализует основной обработчик всех блоков, заключенных в фигурные скобки { }.
 */
class BlockVisitor : MyLangBaseVisitor<Response<*>>(){

    /**
     * Основной блок обработчик блока метода. В цикле шаг за шагом вызывается каждая строка и обрабатывается.
     * При встрече с оператором `return` цикл завершает работу и возврашает результат оператора return.
     *
     * @param ctx обрабатываемый лист.
     * @return результат оператора return, если он был.
     * @throws MyLangException пробрасывается из внутреннего обработчика `handler`
     */
    @Throws(MyLangException::class)
    override fun visitBlock(ctx: MyLangParser.BlockContext): Response<*> {
        var response: Response<*> = ReturnResponse("")

        if (ctx.blockStatements() != null) {
            Storage.createNestedStorage()
            for (blockStatement in ctx.blockStatements()) {
                response = BlockStatementsVisitor().visitBlockStatements(blockStatement)
                if (response is ReturnResponse) {
                    break
                }
            }
            Storage.deleteNestedStorage()
        }

        return response
    }
}