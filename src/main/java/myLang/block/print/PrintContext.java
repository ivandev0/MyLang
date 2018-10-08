package myLang.block.print;

import myLang.ContextHandler;
import myLang.response.MyLangException;
import myLang.response.Response;
import myLangParser.MyLangParser;

public class PrintContext implements ContextHandler<MyLangParser.PrintContext> {

    @Override
    public Response handler(MyLangParser.PrintContext ctx) throws MyLangException {
        return defaultHandler(ctx, 1);
    }
}
