package response;

public class IntegerResponse extends AbstractResponse<Integer>{
    public IntegerResponse(Integer response) {
        super(response);
    }

    public Integer getResponse() {
        return super.response;
    }
}
