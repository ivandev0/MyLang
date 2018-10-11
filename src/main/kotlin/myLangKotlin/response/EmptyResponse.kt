package myLangKotlin.response

/**
 * Реализация [AbstractResponse] для возвращения пустого `null` значения.
 * Релизован для избежания ошибок [NullPointerException].
 */
class EmptyResponse : AbstractResponse<Void?>(null) {

    override val response: Void?
        get() = null
}
