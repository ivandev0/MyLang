package myLang.expression;

import myLang.ContextHandler;
import myLang.response.*;
import myLangParser.MyLangParser;

public class ExpressionContext implements ContextHandler<MyLangParser.ExpressionContext> {
    @Override
    public Response handler(MyLangParser.ExpressionContext ctx) throws MyLangException {
        return defaultHandler(ctx);
    }
}

