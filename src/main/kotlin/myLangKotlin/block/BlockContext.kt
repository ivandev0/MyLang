package myLangKotlin.block

import myLangKotlin.ContextHandler
import myLangKotlin.expression.ExpressionContext
import myLangKotlin.interpreter.FunArgs
import myLangKotlin.interpreter.Variable
import myLangKotlin.response.EmptyResponse
import myLangKotlin.response.MyLangException
import myLangKotlin.response.Response
import myLangKotlin.response.ReturnResponse
import myLangParser.MyLangParser

import java.util.*

/**
 * Данный класс расширяет интерфейс [ContextHandler]
 * и реализует основной обработчик всех блоков, заключенных в фигурные скобки { }.
 *
 * В данном класе хранятся следующие статичные поля:
 *
 *  1.  `LinkedList<Set<Variable>> localStorage`
 * Содержит список Set из Variable. Каждый Set представляет собой вложенный блок.
 *  2. `Deque<LinkedList<Set<Variable>>> stack`
 * Содержит списки блоков и необходим для переключения и восстановления контекста.
 *
 */
class BlockContext : ContextHandler<MyLangParser.BlockContext> {

    /**
     * Основной блок обработчик блока метода. В цикле шаг за шагом вызывается каждая строка и обрабатывается.
     * При встрече с оператором `return` цикл завершает работу и возврашает результат оператора return.
     *
     * @param ctx обрабатываемый лист.
     * @return результат оператора return, если он был.
     * @throws MyLangException пробрасывается из внутреннего обработчика `handler`
     */
    @Throws(MyLangException::class)
    override fun handler(ctx: MyLangParser.BlockContext): Response<*> {
        var response: Response<*> = EmptyResponse()
        if (ctx.blockStatements() != null) {
            createNestedStorage()
            for (blockStatement in ctx.blockStatements()) {
                response = BlockStatementsContext().handler(blockStatement)
                if (response is ReturnResponse) {
                    break
                }
            }
            deleteNestedStorage()
        }

        return response
    }

    companion object {

        var localStorage : MutableList<MutableSet<Variable<Any?>>> = mutableListOf()
        val stack : MutableList<MutableList<MutableSet<Variable<Any?>>>> = mutableListOf()
        private var stackCount = 0
        private val STACK_OVERFLOW = 100

        /**
         * Возвращает значение переменной по имени, если она была объявлена.
         *
         * @param name имя переменной
         * @return значение переменной
         * @throws MyLangException если переменной не существует
         */
        @Throws(MyLangException::class)
        operator fun get(name: String): Variable<*> {
            return localStorage
                    .stream()
                    .flatMap { it.stream() }
                    .filter { obj -> obj.name == name }
                    .findFirst()
                    .orElse(null) ?: throw MyLangException("Переменная $name не объявлена")
        }

        /**
         * Добавляет новую переменную в список.
         *
         * @param name имя переменной
         * @param value значение переменной
         * @param <T> тип переменной
         * @throws MyLangException если переменная была объявлена ранее
        </T> */
        @Throws(MyLangException::class)
        fun <T> add(name: String, value: T) {
            if (!localStorage.last().add(Variable(name, value))) {
                throw MyLangException("Переменная $name уже существует")
            }
        }

        /**
         * Обновляет значение переменной на новое.
         *
         * @param name имя переменной
         * @param newValue новое значение переменной
         * @param <T> тип переменной
         * @return возвращает `newValue` для конвееризации
         * @throws MyLangException если переменной не существует.
        </T> */
        @Throws(MyLangException::class)
        fun <T> update(name: String, newValue: T): T {
            val variable = localStorage
                    .stream()
                    .flatMap { it.stream() }
                    .filter { obj -> obj.name == name }
                    .findFirst()
                    .orElse(null) ?: throw MyLangException("Переменная $name не объявлена")

            variable.updateValue(newValue)
            return newValue
        }

        /**
         * Создает новый Set в `localStorage` при инициализации нового блока.
         */
        fun createNestedStorage() {
            localStorage.add(mutableSetOf()) //добавляет в конец
        }

        /**
         * Удаляет последний созданный Set в `localStorage` при выходе из блока.
         */
        fun deleteNestedStorage() {
            localStorage.removeAt(localStorage.lastIndex)
        }

        /**
         * Добавляет контекст текущей программы в стэк. Вызывается при вызове метода.
         * Здесь же реализуется сопоставление передаваемых и ожидаемых аргументов.
         *
         * @param names список ожидаемых аргументов
         * @param values список передаваемых аргументов
         * @throws MyLangException пробрасывается из внутреннего обработчика `handler`
         */
        @Throws(MyLangException::class)
        fun pushStack(names: List<FunArgs>, values: List<MyLangParser.ExpressionContext>) {
            if (++stackCount >= STACK_OVERFLOW) {
                throw MyLangException("Произошло переполнение стека")
            }
            createNestedStorage()
            for (i in values.indices) {
                add(names[i].name, ExpressionContext().handler(values[i]).response)
            }
            val set = localStorage.last()
            localStorage.removeAt(localStorage.lastIndex)

            stack.add(0, localStorage.toMutableList())
            localStorage.clear()

            localStorage.add(set)
        }

        /**
         * Восстанавливает контекст прерванного метода.
         */
        fun popStack() {
            deleteNestedStorage()
            localStorage = stack.first()
            stack.removeAt(0)
        }

        /**
         * Данный метод используется при работе с тестами. Он очищает `localStorage` и `stack`.
         */
        fun clear() {
            localStorage.clear()
            stack.clear()
            stackCount = 0
        }
    }
}

internal class BlockStatementsContext : ContextHandler<MyLangParser.BlockStatementsContext> {

    @Throws(MyLangException::class)
    override fun handler(ctx: MyLangParser.BlockStatementsContext): Response<*> {
        return defaultHandler(ctx)
    }

}

