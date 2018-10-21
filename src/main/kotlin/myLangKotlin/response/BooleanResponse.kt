package myLangKotlin.response

/**
 * Реализация [Response] для возвращения булевского значения.
 */
class BooleanResponse(override var response: Boolean) : Response<Boolean>
