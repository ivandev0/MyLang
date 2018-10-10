package myLang.response;

/**
 * Дополняет интерфейс {@link Response} реализуя хранение ответа и удобный конструктор.
 *
 * @param <T>
 */
abstract class AbstractResponse<T> implements Response<T> {
    final T response;

    /**
     * Конструктор ответа методов в интерфейсе {@link Response}.
     *
     * @param response тело ответа
     */
    AbstractResponse(T response) {
        this.response = response;
    }
}
