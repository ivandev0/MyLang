package myLang.expression;

import myLang.ContextHandler;
import myLang.response.BooleanResponse;
import myLang.response.IntegerResponse;
import myLang.response.MyLangException;
import myLang.response.Response;
import myLangParser.MyLangLexer;
import myLangParser.MyLangParser;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

public class RelationalExpressionContext implements ContextHandler<MyLangParser.RelationalExpressionContext> {

    @Override
    public Response handler(MyLangParser.RelationalExpressionContext ctx) throws MyLangException {
        if(ctx.getChildCount() == 1){
            return defaultHandler(ctx);
        }
        IntegerResponse firstResponse = (IntegerResponse) defaultHandler(ctx, 0);
        IntegerResponse secondResponse = (IntegerResponse) defaultHandler(ctx, 2);
        switch (((TerminalNodeImpl) ctx.getChild(1)).getSymbol().getType()) {
            case MyLangLexer.LT:
                return new BooleanResponse(firstResponse.getResponse() < secondResponse.getResponse());
            case MyLangLexer.GT:
                return new BooleanResponse(firstResponse.getResponse() > secondResponse.getResponse());
            case MyLangLexer.LE:
                return new BooleanResponse(firstResponse.getResponse() <= secondResponse.getResponse());
            case MyLangLexer.GE:
                return new BooleanResponse(firstResponse.getResponse() >= secondResponse.getResponse());
        }

        throw new MyLangException("Неподдерживаемая операция сравнения");
    }
}
