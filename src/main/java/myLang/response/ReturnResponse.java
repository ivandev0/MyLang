package myLang.response;

public class ReturnResponse extends AbstractResponse<String> {
    public ReturnResponse(String response) {
        super(response);
    }

    @Override
    public String getResponse() {
        return response;
    }
}
