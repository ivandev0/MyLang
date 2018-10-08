package myLang.response;

public class EmptyResponse extends AbstractResponse<Void> {
    public EmptyResponse() {
        super(null);
    }

    @Override
    public Void getResponse() {
        return null;
    }
}
