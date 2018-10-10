package myLang.block.statement;

import myLang.ContextHandler;
import myLang.expression.ExpressionContext;
import myLang.response.BooleanResponse;
import myLang.response.MyLangException;
import myLang.response.Response;
import myLangParser.MyLangParser;

public class IfThenElseStatementContext implements ContextHandler<MyLangParser.IfThenElseStatementContext> {
    @Override
    public Response handler(MyLangParser.IfThenElseStatementContext ctx) throws MyLangException {
        Response response = new ExpressionContext().handler(ctx.expression());
        if(!(response instanceof BooleanResponse)){
            throw new MyLangException("Оператор if требует тип boolean");
        }
        if((boolean)response.getResponse()){
            return new StatementContext().handler(ctx.statement(0));
        } else {
            return new StatementContext().handler(ctx.statement(1));
        }
    }
}
