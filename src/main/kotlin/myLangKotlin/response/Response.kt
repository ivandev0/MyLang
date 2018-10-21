package myLangKotlin.response

/**
 * Интерфейс реализует ответ всех методов visitor.
 * Реализован для унификации всех ответов.
 *
 * @param <T> тип возвращаемого в ответе значения
 * @author Ivan Kylchik
 */
interface Response<T> {
    var response: T
}

