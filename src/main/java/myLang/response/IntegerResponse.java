package myLang.response;

/**
 * Реализация {@link AbstractResponse} для возвращения целочисленного значения.
 */
public class IntegerResponse extends AbstractResponse<Integer>{
    public IntegerResponse(Integer response) {
        super(response);
    }

    public Integer getResponse() {
        return super.response;
    }
}
