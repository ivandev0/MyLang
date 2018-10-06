package response;

public class BooleanResponse extends AbstractResponse<Boolean>{
    public BooleanResponse(Boolean response) {
        super(response);
    }

    public Boolean getResponse() {
        return super.response;
    }
}
