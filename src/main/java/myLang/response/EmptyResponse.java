package myLang.response;

/**
 * Реализация {@link AbstractResponse} для возвращения пустого {@code null} значения.
 * Релизован для избежания ошибок {@link NullPointerException}.
 */
public class EmptyResponse extends AbstractResponse<Void> {
    public EmptyResponse() {
        super(null);
    }

    @Override
    public Void getResponse() {
        return null;
    }
}
