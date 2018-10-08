package myLang.block.statement;

import myLang.ContextHandler;
import myLang.expression.ExpressionContext;
import myLang.response.MyLangException;
import myLang.response.Response;
import myLang.response.ReturnResponse;
import myLangParser.MyLangParser;

public class ReturnStatementContext implements ContextHandler<MyLangParser.ReturnStatementContext> {
    @Override
    public Response handler(MyLangParser.ReturnStatementContext ctx) throws MyLangException {
        if(ctx.expression() != null){
            Response response = new ExpressionContext().handler(ctx.expression());
            return new ReturnResponse(String.valueOf(response.getResponse()));
        }
        return new ReturnResponse("");
    }
}
