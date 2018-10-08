package myLang.expression;

import myLang.ContextHandler;
import myLang.response.BooleanResponse;
import myLang.response.IntegerResponse;
import myLang.response.MyLangException;
import myLang.response.Response;
import myLangParser.MyLangParser;

public class ConditionalOrExpressionContext implements ContextHandler<MyLangParser.ConditionalOrExpressionContext> {

    @Override
    public Response handler(MyLangParser.ConditionalOrExpressionContext ctx) throws MyLangException {
        if(ctx.getChildCount() == 1){
            return defaultHandler(ctx);
        }
        Response firstResponse = defaultHandler(ctx, 0);
        Response secondResponse = defaultHandler(ctx, 2);
        if(firstResponse instanceof IntegerResponse || secondResponse instanceof IntegerResponse){
            throw new MyLangException("Оператор || не применим к типу int");
        }
        return new BooleanResponse((boolean)firstResponse.getResponse() || (boolean)secondResponse.getResponse());
    }
}
