package myLang.expression;

import myLang.ContextHandler;
import myLang.response.BooleanResponse;
import myLang.response.MyLangException;
import myLang.response.Response;
import myLangParser.MyLangLexer;
import myLangParser.MyLangParser;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

public class EqualityExpressionContext implements ContextHandler<MyLangParser.EqualityExpressionContext> {

    @Override
    public Response handler(MyLangParser.EqualityExpressionContext ctx) throws MyLangException {
        if(ctx.getChildCount() == 1){
            return defaultHandler(ctx);
        }
        Response firstResponse = defaultHandler(ctx, 0);
        Response secondResponse = defaultHandler(ctx, 2);
        switch (((TerminalNodeImpl) ctx.getChild(1)).getSymbol().getType()) {
            case MyLangLexer.EQUAL:
                return new BooleanResponse(firstResponse.getResponse().equals(secondResponse.getResponse()));
            case MyLangLexer.NOTEQUAL:
                return new BooleanResponse(!firstResponse.getResponse().equals(secondResponse.getResponse()));
        }

        throw new MyLangException("Неподдерживаемая операция сравнения");
    }
}
