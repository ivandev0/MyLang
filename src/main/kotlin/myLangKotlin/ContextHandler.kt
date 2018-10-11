package myLangKotlin

import myLangKotlin.response.MyLangException
import myLangKotlin.response.Response
import org.antlr.v4.runtime.ParserRuleContext

import java.io.IOException
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

/**
 * Интерфейс помогает в реализации классов обработчиков для листьев синтаксического дерева.
 *
 *
 * Для реализации класса обраточика не обязательно реализовывать данный интерфейс,
 * достаточно чтобы класс осдержал в себе метод handler.
 * Но тогда в классе обработчике не будет доступа к стандартному методу `defaultHandler`.
 *
 *
 * **Обязательное условие** - класс обработчик должен называться также как класс описывающий лист в дереве.
 *
 * @param <T> указывает на обрабатываемый класс. Обязательно один из листов дерева. Обязательно имя класса совпадает с параметром `T`.
 * @author Ivan Kylchik
</T> */
interface ContextHandler<T : ParserRuleContext> {

    /**
     * Стандартный метод обработчик. Необходимо реализовать основные операции над листом,
     * либо передать управление дальше по дереву вызвав обработчик по умолчанию `defaultHandler`
     *
     * @param ctx обрабатываемый лист.
     * @return ответ реадизуемый интерфейсом [Response].
     * @throws MyLangException пробрасывается всякий раз, когда возникает критическая ошибка.
     */
    @Throws(MyLangException::class)
    fun handler(ctx: T): Response<*>

    /**
     * Обработчик по умолчанию реализует поиск класса, совпадающего по названию с параметром `T`,
     * и вызывает метод `handler`.
     *
     * @param ctx обрабатываемый лист.
     * @param child номер обрабатываемого ребенка листа.
     * @return ответ реадизуемый интерфейсом [Response].
     * @throws MyLangException пробрасывается всякий раз, когда возникает критическая ошибка.
     */
    @Throws(MyLangException::class)
    //@JvmOverloads
    fun defaultHandler(ctx: T, child: Int = 0): Response<*> {
        val className = ctx.getChild(child).javaClass.simpleName
        try {
            val clazz = DynamicClassLoader().loadClass(className)

            val method = clazz.getDeclaredMethod("handler", ctx.getChild(child).javaClass)
            return method.invoke(clazz.newInstance(), ctx.getChild(child)) as Response<*>
        } catch (e: ClassNotFoundException) {
            throw MyLangException("Класс обработчик $className не найден")
        } catch (e: NoSuchMethodException) {
            throw MyLangException("Класс обработчик $className должен реализовывать метод handle")
        } catch (e: IllegalAccessException) {
            throw MyLangException(e.message.toString())
        } catch (e: InstantiationException) {
            throw MyLangException(e.message.toString())
        } catch (e: ClassCastException) {
            throw MyLangException(e.message.toString())
        } catch (e: IOException) {
            throw MyLangException(e.message.toString())
        } catch (e: InvocationTargetException) {
            throw MyLangException(e.targetException.message.toString())
        }

    }
}