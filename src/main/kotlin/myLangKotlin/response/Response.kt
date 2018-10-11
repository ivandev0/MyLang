package myLangKotlin.response

/**
 * Интерфейс реализует ответ всех обработчиков интерфейса [myLang.ContextHandler].
 * Реализован для унификации всех ответов.
 *
 * @param <T> тип возвращаемого в ответе значения
 * @author Ivan Kylchik
</T> */
interface Response<out T> {
    val response: T
}

