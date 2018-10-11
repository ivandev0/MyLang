package myLangKotlin.response

/**
 * Реализация [AbstractResponse] для возвращения булевского значения.
 */
class BooleanResponse(response: Boolean) : AbstractResponse<Boolean>(response) {

    override val response: Boolean
        get() = super.response
}
