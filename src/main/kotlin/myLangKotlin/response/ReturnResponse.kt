package myLangKotlin.response

/**
 * Реализация [AbstractResponse] для определения оператора `return` в коде.
 */
class ReturnResponse(response: String) : AbstractResponse<String>(response) {

    override val response: String
        get() = super.response
}
