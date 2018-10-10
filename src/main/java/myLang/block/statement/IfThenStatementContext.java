package myLang.block.statement;

import myLang.ContextHandler;
import myLang.expression.ExpressionContext;
import myLang.response.BooleanResponse;
import myLang.response.EmptyResponse;
import myLang.response.MyLangException;
import myLang.response.Response;
import myLangParser.MyLangParser;

public class IfThenStatementContext implements ContextHandler<MyLangParser.IfThenStatementContext> {
    @Override
    public Response handler(MyLangParser.IfThenStatementContext ctx) throws MyLangException {
        Response response = new ExpressionContext().handler(ctx.expression());
        if(!(response instanceof BooleanResponse)){
            throw new MyLangException("Оператор if требует тип boolean");
        }
        if((boolean)response.getResponse()){
            return new StatementContext().handler(ctx.statement());
        }
        return new EmptyResponse();
    }
}
