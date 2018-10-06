package response;

abstract class AbstractResponse<T> implements Response<T> {
    final T response;

    AbstractResponse(T response) {
        this.response = response;
    }
}
