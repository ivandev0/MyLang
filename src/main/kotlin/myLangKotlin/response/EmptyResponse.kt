package myLangKotlin.response

/**
 * Реализация [Response] для возвращения пустого `null` значения.
 * Релизован для избежания ошибок [NullPointerException].
 */
class EmptyResponse(override var response: Void? = null) : Response<Void?>
