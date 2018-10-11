package myLangKotlin.response

/**
 * Реализация [AbstractResponse] для возвращения целочисленного значения.
 */
class IntegerResponse(response: Int) : AbstractResponse<Int>(response) {

    override val response: Int
        get() = super.response
}
