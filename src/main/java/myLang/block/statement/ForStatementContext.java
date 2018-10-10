package myLang.block.statement;

import myLang.ContextHandler;
import myLang.block.BlockContext;
import myLang.block.localVariable.LocalVariableDeclarationStatementContext;
import myLang.expression.ExpressionContext;
import myLang.response.BooleanResponse;
import myLang.response.EmptyResponse;
import myLang.response.MyLangException;
import myLang.response.Response;
import myLangParser.MyLangParser;

public class ForStatementContext implements ContextHandler<MyLangParser.ForStatementContext> {
    @Override
    public Response handler(MyLangParser.ForStatementContext ctx) throws MyLangException {
        BlockContext.createNestedStorage();
        new LocalVariableDeclarationStatementContext().handler(ctx.localVariableDeclarationStatement());
        Response response = new ExpressionContext().handler(ctx.expression());
        if(!(response instanceof BooleanResponse)){
            throw new MyLangException("Условие в цикле for должно быть типа boolean");
        }

        while((boolean)response.getResponse()){
            new StatementContext().handler(ctx.statement());
            new ForUpdateContext().handler(ctx.forUpdate());
            response = new ExpressionContext().handler(ctx.expression());
        }

        BlockContext.deleteNestedStorage();
        return new EmptyResponse();
    }
}

class ForUpdateContext implements ContextHandler<MyLangParser.ForUpdateContext>{

    @Override
    public Response handler(MyLangParser.ForUpdateContext ctx) throws MyLangException {
        for(MyLangParser.AssignmentContext ac : ctx.statementExpressionList().assignment()){
            new AssignmentContext().handler(ac);
        }
        return new EmptyResponse();
    }
}
