package myLangKotlin.response

/**
 * Реализация [Response] для возвращения целочисленного значения.
 */
class IntegerResponse(override var response: Int) : Response<Int>
