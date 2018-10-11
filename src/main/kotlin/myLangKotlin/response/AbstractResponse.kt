package myLangKotlin.response

/**
 * Дополняет интерфейс [Response] реализуя хранение ответа и удобный конструктор.
 *
 * @param <T>
 */
abstract class AbstractResponse<T>
/**
 * Конструктор ответа методов в интерфейсе [Response].
 *
 * @param response тело ответа
 */
(override val response: T) : Response<T>
