package myLangKotlin.interpreter

import myLangKotlin.response.MyLangException

/**
 * Класс реализует простую абстракию для реализации переменных.
 * Содержит имя `name` и тип `T`.
 *
 * @param <T> тип переменной
 */
class Variable<T> @Throws(MyLangException::class)
constructor(val name: String, value: T) {
    private val MAX_SYMBOL_COUNT = 40
    var value: T? = null
        private set

    init {
        if (name.length > MAX_SYMBOL_COUNT) {
            throw MyLangException("Имя переменной $name содержит больше $MAX_SYMBOL_COUNT символов")
        }
        this.value = value
    }

    fun updateValue(newValue: T) {
        value = newValue
    }

    override fun equals(other: Any?): Boolean {
        if (other is Variable<*>) {
            val variable = other as Variable<*>?
            return name == variable!!.name
        }
        return false
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

}
