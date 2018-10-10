package myLang.response;

/**
 * Реализация {@link AbstractResponse} для возвращения булевского значения.
 */
public class BooleanResponse extends AbstractResponse<Boolean>{
    public BooleanResponse(Boolean response) {
        super(response);
    }

    public Boolean getResponse() {
        return super.response;
    }
}
