package myLangKotlin.response

/**
 * Реализация [Response] для возврата строк.
 */
class StringResponse(override var response: String) : Response<String>