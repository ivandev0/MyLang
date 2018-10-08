package myLang.block.statement;

import myLang.block.BlockContext;
import myLang.ContextHandler;
import myLang.response.*;
import myLang.expression.AdditiveExpressionContext;
import myLangParser.MyLangLexer;
import myLangParser.MyLangParser;

public class AssignmentContext implements ContextHandler<MyLangParser.AssignmentContext> {
    @Override
    public Response handler(MyLangParser.AssignmentContext ctx) throws MyLangException {
        String name = ctx.ID().getText();
        Integer num = (Integer) BlockContext.get(name).getValue();
        IntegerResponse response = new AdditiveExpressionContext().handler(ctx.additiveExpression());
        switch (ctx.assignmentOperator().start.getType()) {
            case MyLangLexer.ASSIGN:
                BlockContext.update(name, response.getResponse());
                break;
            case MyLangLexer.ADD_ASSIGN:
                BlockContext.update(name, response.getResponse() + num);
                break;
            case MyLangLexer.SUB_ASSIGN:
                BlockContext.update(name,response.getResponse() - num);
                break;
            case MyLangLexer.MUL_ASSIGN:
                BlockContext.update(name,response.getResponse() * num);
                break;
            case MyLangLexer.DIV_ASSIGN:
                if(num == 0){
                    throw new MyLangException("Деление на 0");
                }
                BlockContext.update(name,response.getResponse() / num);
                break;
            default:
                throw new MyLangException("Операция " + ctx.assignmentOperator().start.getText() + " не поддерживается");
        }
        return new EmptyResponse();
    }
}
