package myLang.response;

/**
 * Реализация {@link AbstractResponse} для определения оператора {@code return} в коде.
 */
public class ReturnResponse extends AbstractResponse<String> {
    public ReturnResponse(String response) {
        super(response);
    }

    @Override
    public String getResponse() {
        return response;
    }
}
