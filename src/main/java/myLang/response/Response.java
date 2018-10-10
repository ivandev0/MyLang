package myLang.response;

/**
 * Интерфейс реализует ответ всех обработчиков интерфейса {@link myLang.ContextHandler}.
 * Реализован для унификации всех ответов.
 *
 * @param <T> тип возвращаемого в ответе значения
 * @author Ivan Kylchik
 */
public interface Response<T> {
    T getResponse();
}

