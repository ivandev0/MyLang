package myLang;

import org.antlr.v4.runtime.ParserRuleContext;
import myLang.response.EmptyResponse;
import myLang.response.MyLangException;
import myLang.response.Response;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Интерфейс помогает в реализации классов обработчиков для листьев синтаксического дерева.
 * <p>
 * Для реализации класса обраточика не обязательно реализовывать данный интерфейс,
 * достаточно чтобы класс осдержал в себе метод handler.
 * Но тогда в классе обработчике не будет доступа к стандартному методу {@code defaultHandler}.
 * <p>
 * <b>Обязательное условие</b> - класс обработчик должен называться также как класс описывающий лист в дереве.
 *
 * @param <T> указывает на обрабатываемый класс. Обязательно один из листов дерева. Обязательно имя класса совпадает с параметром {@code T}.
 * @author Ivan Kylchik
 */
public interface ContextHandler<T extends ParserRuleContext>{

    /**
     * Стандартный метод обработчик. Необходимо реализовать основные операции над листом,
     * либо передать управление дальше по дереву вызвав обработчик по умолчанию {@code defaultHandler}
     *
     * @param ctx обрабатываемый лист.
     * @return ответ реадизуемый интерфейсом {@link Response}.
     * @throws MyLangException пробрасывается всякий раз, когда возникает критическая ошибка.
     */
    Response handler(T ctx) throws MyLangException;

    /**
     * Обработчик совершает те же действия, что и {@code defaultHandler(T ctx, int child)},
     * но в качестве {@code child} указан 0.
     *
     * @param ctx обрабатываемый лист.
     * @return ответ реадизуемый интерфейсом {@link Response}.
     * @throws MyLangException пробрасывается всякий раз, когда возникает критическая ошибка.
     */
    default Response defaultHandler(T ctx)  throws MyLangException{
        return defaultHandler(ctx, 0);
    }

    /**
     * Обработчик по умолчанию реализует поиск класса, совпадающего по названию с параметром {@code T},
     * и вызывает метод {@code handler}.
     *
     * @param ctx обрабатываемый лист.
     * @param child номер обрабатываемого ребенка листа.
     * @return ответ реадизуемый интерфейсом {@link Response}.
     * @throws MyLangException пробрасывается всякий раз, когда возникает критическая ошибка.
     */
    default Response defaultHandler(T ctx, int child) throws MyLangException {
        String className = ctx.getChild(child).getClass().getSimpleName();
        try {
            Class<?> clazz = new DynamicClassLoader().loadClass(className);

            Method method = clazz.getDeclaredMethod("handler", ctx.getChild(child).getClass());
            return (Response) method.invoke(clazz.newInstance(), ctx.getChild(child));
        } catch (ClassNotFoundException e) {
            throw new MyLangException("Класс обработчик " + className + " не найден");
        } catch (NoSuchMethodException e) {
            throw new MyLangException("Класс обработчик " + className + " должен реализовывать метод handle");
        } catch (IllegalAccessException | InstantiationException | ClassCastException | IOException e) {
            throw new MyLangException(e.getMessage());
        } catch (InvocationTargetException e) {
            throw new MyLangException(e.getTargetException().getMessage());
        }
    }
}