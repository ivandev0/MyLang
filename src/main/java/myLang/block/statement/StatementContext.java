package myLang.block.statement;

import myLang.ContextHandler;
import myLang.response.*;
import myLangParser.MyLangParser;

public class StatementContext implements ContextHandler<MyLangParser.StatementContext> {

    @Override
    public Response handler(MyLangParser.StatementContext ctx) throws MyLangException {
        /*if(ctx.block() != null){
            return new myLang.block.BlockContext().handler(ctx.block());
        }*/
        return defaultHandler(ctx);
    }
}

